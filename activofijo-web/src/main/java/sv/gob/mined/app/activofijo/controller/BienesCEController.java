/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.app.activofijo.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import sv.gob.mined.activofijo.ejb.BienesEJB;
import sv.gob.mined.activofijo.ejb.CatalogosEJB;
import sv.gob.mined.activofijo.model.AfBienesDepreciables;
import sv.gob.mined.activofijo.model.AfCalidadBien;
import sv.gob.mined.activofijo.model.AfCategoriasBien;
import sv.gob.mined.activofijo.model.AfEjercicioFiscal;
import sv.gob.mined.activofijo.model.AfEstatusBien;
import sv.gob.mined.activofijo.model.AfFormaAdquisicion;
import sv.gob.mined.activofijo.model.AfFuenteFinanciamiento;
import sv.gob.mined.activofijo.model.AfProyectos;
import sv.gob.mined.activofijo.model.AfSeccionesUnidad;
import sv.gob.mined.activofijo.model.AfTipoBienes;
import sv.gob.mined.activofijo.model.AfUnidadesActivoFijo;
import sv.gob.mined.activofijo.model.AfUnidadesAdministrativas;
import sv.gob.mined.activofijo.model.VwBienes;
import sv.gob.mined.activofijo.model.VwCorrelativos;
import sv.gob.mined.app.activofijo.util.JsfUtil;
import sv.gob.mined.app.activofijo.util.UtilReport;
import sv.gob.mined.seguridad.model.Usuario;
import sv.gob.mined.seguridad.web.controller.LoginController;

/**
 *
 * @author JISTorres
 */
@ManagedBean
@ViewScoped

public class BienesCEController implements Serializable {

    @EJB
    public CatalogosEJB cejb;
    @EJB
    public BienesEJB bejb;
    private AfBienesDepreciables bd = new AfBienesDepreciables();
    private AfTipoBienes tb = new AfTipoBienes();
    private String unidadAF;
    private String unidadAdm;
    private String tipoUnidad = "CE";
    private String desBien;
    private String lote = "N";
    private Integer numLote = 0;
    private Long idBien;
    private Long cat;
    private Long tipo = 0l;
    private Long tipoBien;
    private Long section;
    private Long calidad = Long.valueOf(1);
    private String AsignadoA;
    private Long estatus = Long.valueOf(1);
    private String marcaM;
    private String marcaV;
    private Long fuente = Long.valueOf(0);
    private Long formaAdq;
    private Long proyecto;
    private String modelo;
    private String serie;
    private String actFijo;
    private String responsable;
    private Date fecAdq1;
    private Date fecLimit = new Date();
    private Date fecAdq2;
    private Date fecCrea1;
    private Date fecCrea2;
    private Date fecRep;
    private String numSerie;
    private String anio;
    private String mes;
    private String asignadoA;
    private String correlativo;
    private String codigoInventario;
    private String descripcion;
    private String observacion;
    private String valor;
    private String estatuSae;
    private String periodo;
    private boolean mostrarmsg = false;
    private boolean pnlVh = false;
    private boolean pnlMb = true;
    private boolean pnlLote = true;
    private boolean buscar = true;
    private boolean modifica = false;
    private boolean mostrarProy = true;
    private Usuario usuDao = new Usuario();
    private List<AfBienesDepreciables> lstBienesDepreciable = new ArrayList<AfBienesDepreciables>();
    private List<AfUnidadesAdministrativas> lstUnidadAdm = new ArrayList<AfUnidadesAdministrativas>();
    private List<AfTipoBienes> lstTipoBienes = new ArrayList<AfTipoBienes>();
    private List<AfCategoriasBien> lstCatBien = new ArrayList<AfCategoriasBien>();
    private List<AfFormaAdquisicion> lstFormaAdq = new ArrayList<AfFormaAdquisicion>();
    private List<AfEjercicioFiscal> lstAnios = new ArrayList<AfEjercicioFiscal>();
    private List<AfProyectos> lstProyectos = new ArrayList<AfProyectos>();
    private List<VwBienes> lstBienes = new ArrayList<VwBienes>();
    private List<VwCorrelativos> lstCorrelativos = new ArrayList<VwCorrelativos>();

    /**
     * Creates a new instance of BienesController
     */
    public BienesCEController() {

    }

    public AfTipoBienes getTb() {
        return tb;
    }

    public void setTb(AfTipoBienes tb) {
        this.tb = tb;
    }

    @PostConstruct
    public void cargarUnidad() {

        usuDao = ((LoginController) FacesContext.getCurrentInstance().getApplication().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "loginController")).getUsuario();
        if (usuDao.getCodigoEntidad() == null) {
            try {
                System.out.println("Usuario sin Entidad asignada " + usuDao.getLogin());
                ((LoginController) FacesContext.getCurrentInstance().getApplication().getELResolver().
                        getValue(FacesContext.getCurrentInstance().getELContext(), null, "loginController")).logout();
            } catch (IOException ex) {
                Logger.getLogger(BienesCEController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            unidadAdm = usuDao.getCodigoEntidad();
            unidadAF = cejb.getUnidadAf(usuDao.getCodigoEntidad(), tipoUnidad);
            lstUnidadAdm = cejb.getUnidadAdm(unidadAF, tipoUnidad);

            if (JsfUtil.getRequestParameter("idBien") != null) {
                bd = bejb.bienId(new Long(JsfUtil.getRequestParameter("idBien"))).get(0);
                tipo = bd.getIdTipoBien();// cejb.tipoBien(bd.getIdCatBien().getIdCatBien(), bd.getCodigoTipoBien());
                calidad = bd.getCodigoCalidadBien().getCodigoCalidadBien();
                cat = bd.getIdCatBien().getIdCatBien();
                desBien = bd.getDescripcionBien();
                correlativo = bd.getCorrelativo();
                codigoInventario = bd.getCodigoInventario();
                if (bd.getIdFormaAdquisicion() != null) {
                    formaAdq = bd.getIdFormaAdquisicion().getIdFormaAdquisicion();
                }
                lstTipoBienes = cejb.getTipoBien(cat);
                modifica = true;
                pnlLote = false;
                if (cat == 5) {
                    marcaV = bd.getMarca();
                    pnlVh = true;
                    pnlMb = false;
                } else {
                    marcaM = bd.getMarca();
                    pnlVh = false;
                    pnlMb = true;
                }
            } else {
                //   cat = getLstCatBien().get(0).getIdCatBien();
                lstTipoBienes = cejb.getTipoBien();
                pnlLote = true;
            }
        }

    }

    public List<VwCorrelativos> getLstCorrelativos() {
        return lstCorrelativos;
    }

    public void setLstCorrelativos(List<VwCorrelativos> lstCorrelativos) {
        this.lstCorrelativos = lstCorrelativos;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getDesBien() {
        return desBien;
    }

    public void setDesBien(String desBien) {
        this.desBien = desBien;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Long getIdBien() {
        return idBien;
    }

    public void setIdBien(Long idBien) {
        this.idBien = idBien;
    }

    public String getUnidadAF() {
        return unidadAF;
    }

    public boolean isPnlLote() {
        return pnlLote;
    }

    public void setPnlLote(boolean pnlLote) {
        this.pnlLote = pnlLote;
    }

    public List<AfProyectos> getLstProyectos() {
        return lstProyectos;
    }

    public void setLstProyectos(List<AfProyectos> lstProyectos) {
        this.lstProyectos = lstProyectos;
    }

    public Date getFecLimit() {
        return fecLimit;
    }

    public void setFecLimit(Date fecLimit) {
        this.fecLimit = fecLimit;
    }

    public boolean isPnlVh() {
        return pnlVh;
    }

    public void setPnlVh(boolean pnlVh) {
        this.pnlVh = pnlVh;
    }

    public boolean isMostrarmsg() {
        return mostrarmsg;
    }

    public boolean isPnlMb() {
        return pnlMb;
    }

    public void setPnlMb(boolean pnlMb) {
        this.pnlMb = pnlMb;
    }

    public boolean isModifica() {
        return modifica;
    }

    public void setModifica(boolean modifica) {
        this.modifica = modifica;
    }

    public void setUnidadAF(String unidadAF) {
        this.unidadAF = unidadAF;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getAsignadoA() {
        return asignadoA;
    }

    public void setAsignadoA(String asignadoA) {
        this.asignadoA = asignadoA;
    }

    public AfBienesDepreciables getBd() {
        return bd;
    }

    public void setBd(AfBienesDepreciables bd) {
        this.bd = bd;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getNumLote() {
        return numLote;
    }

    public void setNumLote(Integer numLote) {
        this.numLote = numLote;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public Date getFecRep() {
        return fecRep;
    }

    public void setFecRep(Date fecRep) {
        this.fecRep = fecRep;
    }

    public Long getFormaAdq() {
        return formaAdq;
    }

    public void setFormaAdq(Long formaAdq) {
        this.formaAdq = formaAdq;
    }

    public Long getTipoBien() {
        return tipoBien;
    }

    public void setTipoBien(Long tipoBien) {
        this.tipoBien = tipoBien;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public String getEstatuSae() {
        return estatuSae;
    }

    public void setEstatuSae(String estatuSae) {
        this.estatuSae = estatuSae;
    }

    public String getUnidadAdm() {
        return unidadAdm;
    }

    public void setUnidadAdm(String unidadAdm) {
        this.unidadAdm = unidadAdm;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getActFijo() {
        return actFijo;
    }

    public void setActFijo(String actFijo) {
        this.actFijo = actFijo;
    }

    public Date getFecAdq1() {
        return fecAdq1;
    }

    public void setFecAdq1(Date fecAdq1) {
        this.fecAdq1 = fecAdq1;
    }

    public Date getFecAdq2() {
        return fecAdq2;
    }

    public void setFecAdq2(Date fecAdq2) {
        this.fecAdq2 = fecAdq2;
    }

    public Date getFecCrea1() {
        return fecCrea1;
    }

    public void setFecCrea1(Date fecCrea1) {
        this.fecCrea1 = fecCrea1;
    }

    public Date getFecCrea2() {
        return fecCrea2;
    }

    public void setFecCrea2(Date fecCrea2) {
        this.fecCrea2 = fecCrea2;
    }

    public String getCodigoInventario() {
        return codigoInventario;
    }

    public void setCodigoInventario(String codigoInventario) {
        this.codigoInventario = codigoInventario;
    }

    public List<AfBienesDepreciables> getLstBienesDepreciable() {
        return lstBienesDepreciable;
    }

    public void setLstBienesDepreciable(List<AfBienesDepreciables> lstBienesDepreciable) {
        this.lstBienesDepreciable = lstBienesDepreciable;
    }

    public Long getCat() {
        return cat;
    }

    public void setCat(Long cat) {
        this.cat = cat;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public Long getCalidad() {
        return calidad;
    }

    public void setCalidad(Long calidad) {
        this.calidad = calidad;
    }

    public Long getEstatus() {
        return estatus;
    }

    public void setEstatus(Long estatus) {
        this.estatus = estatus;
    }

    public String getMarcaM() {
        return marcaM;
    }

    public void setMarcaM(String marcaM) {
        this.marcaM = marcaM;
    }

    public String getMarcaV() {
        return marcaV;
    }

    public void setMarcaV(String marcaV) {
        this.marcaV = marcaV;
    }

    public Long getSection() {
        return section;
    }

    public void setSection(Long section) {
        this.section = section;
    }

    public Long getFuente() {
        return fuente;
    }

    public void setFuente(Long fuente) {
        this.fuente = fuente;
    }

    public Long getProyecto() {
        return proyecto;
    }

    public void setProyecto(Long proyecto) {
        this.proyecto = proyecto;
    }

    public List<VwBienes> getLstBienes() {
        return lstBienes;
    }

    public void setLstBienes(List<VwBienes> lstBienes) {
        this.lstBienes = lstBienes;
    }

    public List<AfUnidadesActivoFijo> getLstUnidadAF() {
        return cejb.getUnidadAf();
    }

    public List<AfUnidadesAdministrativas> getLstUnidadAdm() {

        return lstUnidadAdm;
    }

    public List<AfFormaAdquisicion> getLstFormaAdq() {
        return cejb.getFormaAdquisicion();
    }

    public List<AfCategoriasBien> getLstCatBien() {
        return cejb.getCategoria();
    }

    public List<AfTipoBienes> getLstTipoBienes() {
        return lstTipoBienes;
    }

    public List<AfSeccionesUnidad> getLstSeccion() {
        return cejb.getSeccion();
    }

    public List<AfCalidadBien> getCalidadBien() {
        return cejb.getCalidadBien();
    }

    public List<AfEstatusBien> getEstatuBien() {
        return cejb.getEstatusBien();
    }

    public List<AfFuenteFinanciamiento> getLstFuente() {
        return cejb.getFuenteCE();
    }

    public List<AfProyectos> getLstProyecto() {
        return cejb.getProyectosCE();
    }

    public void filtrarUnidadesAdm() {
        lstUnidadAdm = cejb.getUnidadAdm(unidadAF, tipoUnidad);
    }

    public void filtrarProyectos() {
        if (fuente == 3) {
            mostrarProy = false;
        } else {
            mostrarProy = true;
        }
        lstProyectos = cejb.getProyectosCE();

    }

    public boolean isMostrarProy() {
        return mostrarProy;
    }

    public void setMostrarProy(boolean mostrarProy) {
        this.mostrarProy = mostrarProy;
    }

    public void imprimirCorrelativos() throws IOException, JRException {
        HashMap param = new HashMap();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de ' MMMM 'de' yyyy", new Locale("es"));
        Calendar cal = Calendar.getInstance();
        //String nomReporte="rep_solvencia_CE.jasper";

        //    String  year= String.valueOf(cal.get(Calendar.YEAR)); 
        String fecRep = formateador.format(cal.getTime());

        param.put("p_unidadAF", unidadAF);
        param.put("p_unidadAdm", unidadAdm);
        //    param.put("p_fecRep",fecRep);

        UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(), param, "rep_control_correl.jasper", cejb.getEm());

    }

    public void imprimirBienes() throws IOException, JRException {
        HashMap param = new HashMap();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
        if (fecRep == null) {
            fecRep = new Date();
        }

        UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(), bejb.getLst(unidadAF, unidadAdm, formateador.format(fecRep), lstBienes, usuDao.getLogin()), param, "rep_mobiliario.jasper");

        //  UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(),param, "rep_mobiliario.jasper", cejb.getEm());
    }

    public void limpiarFiltro() {
        fecAdq1 = null;
        fecAdq2 = null;
        formaAdq = 0l;
        actFijo = null;
        tipo = 0l;
        estatus = 0l;
        codigoInventario = null;
        lstBienes = null;

    }

    public void onValidarFecha(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (bd.getFechaAdquisicion().after(fecLimit)) {
            facesContext.addMessage(null, new FacesMessage("Fecha debe ser menor a la actual"));
        }
    }

    /* public boolean validarFecha(){
        
           mostrarmsg = true;
           JsfUtil.mensajeAlerta("Fecha debe ser menor a la actual");
         } 
         return mostrarmsg;
    }*/
    public boolean validarMonto() {
        if (bd.getValorAdquisicion().intValue() >= 2000) {
            mostrarmsg = true;
            JsfUtil.mensajeAlerta("Valor es mayor de 2000");
        }
        return mostrarmsg;
    }

    public void existeCorre(AjaxBehaviorEvent event) {
        String tipoBien = cejb.getTBien(tipo).getCodigoTipoBien();
        if (cejb.getBuscarCorrelativo(unidadAF, unidadAdm, tipoBien, correlativo) != 0) {
            JsfUtil.mensajeCorrelativo();
        } else {
            codigoInventario = unidadAdm + "-" + tipoBien + "-" + correlativo;
        }

    }

    public void obtenerCorre() {
        String tipoBien = cejb.getTBien(tipo).getCodigoTipoBien();
        correlativo = String.format("%04d", cejb.getCorrelativo(unidadAF, unidadAdm, tipo.toString()));
        codigoInventario = unidadAdm + "-" + tipoBien + "-" + correlativo;
    }

    public void guardarBien() {
        BigDecimal pct = new BigDecimal(10);
        int i = 0;
        String co;
        if (tipo!=null){
        String tipoBien = cejb.getTBien(tipo).getCodigoTipoBien();

        bd.setUnidadActivoFijo(unidadAF);
        bd.setCodigoUnidad(unidadAdm);
        bd.setIdCatBien(cejb.getCatBien(cejb.getCategoria(tipo)));

        bd.setIdTipoBien(tipo); //.getCodigoTipoBien());
        bd.setCodigoCalidadBien(cejb.getCalidadBien(calidad));
        bd.setIdEstatusBien(cejb.getEstatusBien(estatus));
        bd.setDescripcionBien(desBien);
        if (fuente == null) {
            bd.setIdFuente(0l);
        } else {
            bd.setIdFuente(fuente);
        }
        bd.setEsValorEstimado(bd.getEsValorEstimado());
        if (marcaM != null) {
            bd.setMarca(marcaM);
            bd.setVidaUtil(BigInteger.valueOf(5));
        } else {
            bd.setMarca(marcaV);
            bd.setVidaUtil(BigInteger.valueOf(10));
        }
        bd.setIdFormaAdquisicion(cejb.getFormaAdquisicion(formaAdq));
        if (proyecto == null) {
            bd.setIdProyecto(Long.valueOf(cejb.getProyecto(Long.toString(20))));
        } else {
            bd.setIdProyecto(proyecto);
        }
        if (bd.getValorAdquisicion().intValue() >= 600) {
            bd.setActivoFijo('1');
        } else {
            bd.setActivoFijo('0');
        }
        bd.setValorResidual(bd.getValorAdquisicion().multiply(new BigDecimal(0.1)));
        if (bd.getIdBien() == null) {
            if (lote.equals("S")) {
                for (i = 1; i <= numLote; i++) {
                    bd.setIdBien(null);
                    if (i == 1) {
                        co = correlativo;
                    } else {
                        int sum = Integer.parseInt(correlativo) + 1;
                        co = String.format("%04d", sum);
                    }

                    if (cejb.getBuscarCorrelativo(unidadAF, unidadAdm, tipo.toString(), co) == 0) {
                        bd.setCorrelativo(co);
                        codigoInventario = unidadAdm + "-" + tipoBien + "-" + co;
                    } else {
                        obtenerCorre();
                        co = correlativo;
                        bd.setCorrelativo(co);
                    }
                    bd.setCodigoInventario(codigoInventario);
                    bejb.guardarBien(bd, usuDao.getLogin());
                }
                JsfUtil.mensajeInsertLote(i - 1);
            } else {

                // obtenerCorre();
                if (cejb.getBuscarCorrelativo(unidadAF, unidadAdm, tipo.toString(), correlativo) == 0) {
                    bd.setCorrelativo(correlativo);
                    codigoInventario = unidadAdm + "-" + tipoBien + "-" + correlativo;
                } else {
                    obtenerCorre();
                }
                bd.setCodigoInventario(codigoInventario);
                bejb.guardarBien(bd, usuDao.getLogin());
                JsfUtil.mensajeInsert();
            }
        } else {
            bd.setCorrelativo(correlativo);
            codigoInventario = unidadAdm + "-" + tipoBien + "-" + correlativo;
            bd.setCodigoInventario(codigoInventario);
            bejb.guardarBien(bd, usuDao.getLogin());
            JsfUtil.mensajeInsert();
        }
        //  JsfUtil.mensajeAlerta("Bien Guardado satisfactoriamente");
         JsfUtil.redireccionar("buscarbienesCE.mined?faces-redirect=true");
        }else{
            JsfUtil.mensajeInformacion("Debe Seleccionar el tipo de Bien");
        }
        
    }

    public void buscarCorrelativo() {
        String condicion = "WHERE A.UNIDAD_ACTIVO_FIJO='" + unidadAF + "' AND A.CODIGO_UNIDAD= '" + unidadAdm + "' ";
        lstCorrelativos = bejb.buscarCorrelativos(condicion);
    }

    public void buscarBien() {
        String condicion;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        condicion = " A.UNIDAD_ACTIVO_FIJO='" + unidadAF + "' AND A.CODIGO_UNIDAD= '" + unidadAdm + "' AND";
        if (estatus != 0) {
            condicion = condicion + " A.ID_ESTATUS_BIEN =" + estatus + " and ";
        }

        if (tipo != 0) {
            condicion = condicion + " A.id_TIPO_BIEN = " + tipo + " AND";
        }
        if (actFijo != null && !actFijo.isEmpty()) {

            if (actFijo.equals("S")) {
                condicion = condicion + " A.VALOR_ADQUISICION >=600 AND";
            } else {
                condicion = condicion + " A.VALOR_ADQUISICION <600 AND";
            }
        }
        if (codigoInventario != null && !codigoInventario.isEmpty()) {
            condicion = condicion + " A.CODIGO_INVENTARIO like '%" + codigoInventario + "%' and ";
        }
        if (fecAdq1 != null) {
            condicion = condicion + " A.FECHA_ADQUISICION >= TO_DATE('" + sdf.format(fecAdq1) + "','DD/MM/YYYY') AND";
        }
        if (fecAdq2 != null) {
            condicion = condicion + " A.FECHA_ADQUISICION <= TO_DATE('" + sdf.format(fecAdq2) + "','DD/MM/YYYY') AND";
        }
        if (formaAdq != 0) {
            condicion = condicion + " A.ID_FORMA_ADQUISICION =" + formaAdq + " AND";
        }
        condicion = condicion.substring(0, condicion.length() - 4);

        lstBienes = bejb.buscarBien(condicion);
    }

    public void ingresaBienes() {

        JsfUtil.redireccionar("bienesDepreciablesCE.mined?faces-redirect=true&idBien=" + idBien);
    }

    public void nuevoBienes() {

        JsfUtil.redireccionar("bienesDepreciablesCE.mined?faces-redirect=true");
    }

    public void buscarTipoBien() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("buscarTipoBienCE", options, null);
    }

    public void onTipoSelect2(SelectEvent event) {
        if (event.getObject() instanceof Long) {
            tipo = (Long) event.getObject();
            // desBien = cejb.getDesBien(tipo);
        }

    }

    public void onTipoSelect(SelectEvent event) {
        if (event.getObject() instanceof Long) {
            tipo = (Long) event.getObject();
            desBien = cejb.getDesBien(tipo);
            if (cejb.getCategoria(tipo) == 5) {
                pnlVh = true;
                pnlMb = false;
            } else {
                pnlVh = false;
                pnlMb = true;
            }
            obtenerCorre();
        }

    }

    public void bienesId(SelectEvent event) {
        lstBienesDepreciable = bejb.bienId(bd.getIdBien());
    }

    public void onRowSelect(SelectEvent event) {
        cat = cejb.getCategoria(tb.getIdTipoBien());
        descripcion = tb.getNombreTipoBien();
        if (cat == 5) {
            pnlVh = true;
            pnlMb = false;
        } else {
            pnlVh = false;
            pnlMb = true;
        }
        RequestContext.getCurrentInstance().closeDialog(tb.getIdTipoBien());

    }

    public void tipoBienes() {
        lstTipoBienes = cejb.getDesTipo(valor);

    }

    public void imprimirSolvencia() throws IOException, JRException {
        HashMap param = new HashMap();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'dias del mes de' MMMM 'del año' yyyy", new Locale("es"));
        Calendar cal = Calendar.getInstance();

        //    String  year= String.valueOf(cal.get(Calendar.YEAR)); 
        String fecRep = formateador.format(cal.getTime());
        // String Responsable= usuDao.getNombres()+' '+usuDao.getApellidos();

        param.put("p_periodo", periodo);
        param.put("p_responsable", responsable);
        param.put("p_unidadAF", unidadAF);
        param.put("p_unidadAdm", unidadAdm);
        param.put("p_fecRep", fecRep);

        UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(), param, "rep_solvencia_CE.jasper", cejb.getEm());
    }

}
