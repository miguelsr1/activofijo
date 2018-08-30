/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.app.activofijo.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import sv.gob.mined.activofijo.ejb.BienesEJB;
import sv.gob.mined.activofijo.ejb.CatalogosEJB;
import sv.gob.mined.activofijo.ejb.ReportesEJB;
import sv.gob.mined.activofijo.model.AfBienesDepreciables;
import sv.gob.mined.activofijo.model.AfCalidadBien;
import sv.gob.mined.activofijo.model.AfCategoriasBien;
import sv.gob.mined.activofijo.model.AfDepreciaciones;
import sv.gob.mined.activofijo.model.AfEstatusBien;
import sv.gob.mined.activofijo.model.AfFormaAdquisicion;
import sv.gob.mined.activofijo.model.AfFuenteFinanciamiento;
import sv.gob.mined.activofijo.model.AfProyectos;
import sv.gob.mined.activofijo.model.AfSeccionesUnidad;
import sv.gob.mined.activofijo.model.AfTipoBienes;
import sv.gob.mined.activofijo.model.AfUnidadesActivoFijo;
import sv.gob.mined.activofijo.model.AfUnidadesAdministrativas;
import sv.gob.mined.activofijo.model.AfSolvencias;
import sv.gob.mined.activofijo.model.VwBienes;
import sv.gob.mined.activofijo.model.VwCorrelativos;
import sv.gob.mined.activofijo.model.VwDepreciaciones;
import sv.gob.mined.activofijo.model.VwDatosxCuentas;
import sv.gob.mined.activofijo.model.VwSolvencia;
import sv.gob.mined.activofijo.model.VwMunicipio;
import sv.gob.mined.app.activofijo.util.JsfUtil;
import sv.gob.mined.app.activofijo.util.UtilReport;
import static sv.gob.mined.app.activofijo.util.UtilReport.PATH_IMAGENES;
import sv.gob.mined.seguridad.model.Usuario;
import sv.gob.mined.seguridad.web.controller.LoginController;

/**
 *
 * @author JISTorres
 */
@ManagedBean
@ViewScoped

public class BienesController implements Serializable {

    @EJB
    public CatalogosEJB cejb;
    @EJB
    public BienesEJB bejb;
    @EJB
    public ReportesEJB reb;
    private AfBienesDepreciables bd = new AfBienesDepreciables();
    private AfTipoBienes tb = new AfTipoBienes();
    private AfSolvencias sol = new AfSolvencias();
    private String unidadAF;
    private String unidadAdm;
    private String codigoEntidad;
    private String tipoUsu;
    private String tipoUnidad = "UA";
    private String tipoSolvencia;
    private String periodo;
    private String periodo2 = "";
    private String mensaje;
    private Long cat;
    private Long tipo;
    private Long section;
    private Long calidad;
    private Long estatus = Long.valueOf(1);
    private String estadoSol;
    private String marcaM;
    private String marcaV;
    private String marca;
    private String fuente;
    private String responsable;
    private Long formaAdq;
    private String proyecto;
    private String modelo;
    private String serie;
    private String actFijo;
    private Date fecAdq1;
    private Date fecAdq2;
    private Date fecCrea1;
    private Date fecCrea2;
    private Date fecRep;
    private Date fecLimit;
    private String valor;
    private String asignadoA;
    private String anio;
    private String anioDepre;
    private String mes;
    private String desBien;
    private String lote = "N";
    private Integer numLote = 0;
    private Integer numNotificacion = 0;
    private Long idBien;
    private String correlativo;
    private String codigoInventario;
    private String municipio;
    private boolean activo = true;
    private boolean activoSol = true;
    private boolean pnlVh = false;
    private boolean esAdm = true;
    private boolean pnlMb = true;
    private boolean pnlLote = true;
    private boolean modifica = true;
    private boolean disableBtn = true;
    private String tmp;

    private Usuario usuDao = new Usuario();
    private BigDecimal valorAdquisicion;
    private List<AfBienesDepreciables> lstBienesDepreciable = new ArrayList();
    private List<AfUnidadesAdministrativas> lstUnidadAdm = new ArrayList();
    private List<AfTipoBienes> lstTipoBienes = new ArrayList();
    private List<AfCategoriasBien> lstCatBien = new ArrayList();
    private List<AfFormaAdquisicion> lstFormaAdq = new ArrayList();
    private List<AfDepreciaciones> lstDepre = new ArrayList();
    private List<VwBienes> lstBienes = new ArrayList();
    private List<VwCorrelativos> lstCorrelativos = new ArrayList();
    private List<VwSolvencia> lstSolvencia=new ArrayList();
    private List<VwMunicipio> lstMunicipio=new ArrayList();
    private List<VwDepreciaciones> lstDepreciaciones = new ArrayList();
    private List<VwDatosxCuentas> lstDatos = new ArrayList();
    private List<VwBienes> lstBientmp = new ArrayList();

    /**
     * Creates a new instance of BienesController
     */
    public BienesController() {
    }

    @PostConstruct
    public void cargarUnidad() {

        usuDao = ((LoginController) FacesContext.getCurrentInstance().getApplication().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "loginController")).getUsuario();
        unidadAF = cejb.getUnidadAf(usuDao.getCodigoEntidad(), tipoUnidad);
        tipoUsu = usuDao.getTipoUsuario().toString();
        if (tipoUsu.equals("D")) {
            esAdm = true;
        } else {
            esAdm = false;
        }
        if (JsfUtil.getRequestParameter("idBien") != null) {
            bd = bejb.bienId(new Long(JsfUtil.getRequestParameter("idBien"))).get(0);
            tipoUnidad = cejb.getTipoUnidad(bd.getCodigoUnidad());
            tipo = bd.getIdTipoBien();// cejb.tipoBien(bd.getIdCatBien().getIdCatBien(), bd.getCodigoTipoBien());
            desBien = bd.getDescripcionBien();
            estatus = bd.getIdEstatusBien().getIdEstatusBien();
            correlativo = bd.getCorrelativo();
            codigoInventario = bd.getCodigoInventario();
            unidadAF = bd.getUnidadActivoFijo();
            lstUnidadAdm = cejb.getUnidadAdm(unidadAF, tipoUnidad);
            lstMunicipio = bejb.buscarMunicipios(unidadAF);
            unidadAdm = bd.getCodigoUnidad();
            calidad = bd.getCodigoCalidadBien().getCodigoCalidadBien();
            cat = bd.getIdCatBien().getIdCatBien();
            lstTipoBienes = cejb.getTipoBien(cat);
            if (bd.getIdFormaAdquisicion() != null) {
                formaAdq = bd.getIdFormaAdquisicion().getIdFormaAdquisicion();
            }
            fuente = bd.getIdFuente().toString();
            proyecto = bd.getIdProyecto().toString();
            valorAdquisicion = bd.getValorAdquisicion();

            //   esAdm = true;
            if (cat == 5) {
                marcaV = bd.getMarca();
                pnlVh = true;
                pnlMb = false;
            } else {
                marcaM = bd.getMarca();
                pnlVh = false;
                pnlMb = true;
            }
            pnlLote = false;
            if (tipoUsu.equals("D")) {
                modifica = true;
            } else {
                modifica = false;
            }

        } else {
            //   cat = getLstCatBien().get(0).getIdCatBien();
            lstTipoBienes = cejb.getTipoBien();
            lstUnidadAdm = cejb.getUnidadAdm(unidadAF, tipoUnidad);
            lstMunicipio = bejb.buscarMunicipios(unidadAF);
            pnlLote = true;
            modifica = false;
        }

    }

    public void existeCorre(AjaxBehaviorEvent event) {
        String tipoBien = cejb.getTBien(tipo).getCodigoTipoBien();
        if (!cejb.getBuscarCorrelativo(unidadAF, unidadAdm, tipo.toString(), correlativo)) {
            JsfUtil.mensajeCorrelativo();
        } else {
            codigoInventario = unidadAdm + "-" + tipoBien + "-" + correlativo;
        }

    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstadoSol() {
        return estadoSol;
    }

    public void setEstadoSol(String estadoSol) {
        this.estadoSol = estadoSol;
    }

    public List<VwSolvencia> getLstSolvencia() {
        return lstSolvencia;
    }

    public void setLstSolvencia(List<VwSolvencia> lstSolvencia) {
        this.lstSolvencia = lstSolvencia;
    }

    public List<VwMunicipio> getLstMunicipio() {
        return lstMunicipio;
    }

    public void setLstMunicipio(List<VwMunicipio> lstMunicipio) {
        this.lstMunicipio = lstMunicipio;
    }

    
    public List<VwBienes> getLstBientmp() {
        return lstBientmp;
    }

    public void setLstBientmp(List<VwBienes> lstBientmp) {
        this.lstBientmp = lstBientmp;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getNumNotificacion() {
        return numNotificacion;
    }

    public void setNumNotificacion(Integer numNotificacion) {
        this.numNotificacion = numNotificacion;
    }

    public String getAnioDepre() {
        return anioDepre;
    }

    public void setAnioDepre(String anioDepre) {
        this.anioDepre = anioDepre;
    }

    public String getTipoSolvencia() {
        return tipoSolvencia;
    }

    public void setTipoSolvencia(String tipoSolvencia) {
        this.tipoSolvencia = tipoSolvencia;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public boolean isActivoSol() {
        return activoSol;
    }

    public void setActivoSol(boolean activoSol) {
        this.activoSol = activoSol;
    }

    public String getPeriodo2() {
        return periodo2;
    }

    public void setPeriodo2(String periodo2) {
        this.periodo2 = periodo2;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isPnlLote() {
        return pnlLote;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public void setPnlLote(boolean pnlLote) {
        this.pnlLote = pnlLote;
    }

    public String getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(String tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

    public String getDesBien() {
        return desBien;
    }

    public void setDesBien(String desBien) {
        this.desBien = desBien;
    }

    public Date getFecRep() {
        return fecRep;
    }

    public void setFecRep(Date fecRep) {
        this.fecRep = fecRep;
    }

    public String getUnidadAF() {
        return unidadAF;
    }

    public boolean isPnlVh() {
        return pnlVh;
    }

    public void setPnlVh(boolean pnlVh) {
        this.pnlVh = pnlVh;
    }

    public boolean isPnlMb() {
        return pnlMb;
    }

    public boolean isEsAdm() {
        return esAdm;
    }

    public void setEsAdm(boolean esAdm) {
        this.esAdm = esAdm;
    }

    public boolean isModifica() {
        return modifica;
    }

    public void setModifica(boolean modifica) {
        this.modifica = modifica;
    }

    public String getAsignadoA() {
        return asignadoA;
    }

    public void setAsignadoA(String asignadoA) {
        this.asignadoA = asignadoA;
    }

    public void setPnlMb(boolean pnlMb) {
        this.pnlMb = pnlMb;
    }

    public String getValor() {
        return valor;
    }

    public BigDecimal getValorAdquisicion() {
        return valorAdquisicion;
    }

    public void setValorAdquisicion(BigDecimal valorAdquisicion) {
        this.valorAdquisicion = valorAdquisicion;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setUnidadAF(String unidadAF) {
        this.unidadAF = unidadAF;
    }

    public AfTipoBienes getTb() {
        return tb;
    }

    public void setTb(AfTipoBienes tb) {
        this.tb = tb;
    }

    public AfBienesDepreciables getBd() {
        return bd;
    }

    public void setBd(AfBienesDepreciables bd) {
        this.bd = bd;
    }

    public Long getFormaAdq() {
        return formaAdq;
    }

    public void setFormaAdq(Long formaAdq) {
        this.formaAdq = formaAdq;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public String getUnidadAdm() {
        return unidadAdm;
    }

    public void setUnidadAdm(String unidadAdm) {
        this.unidadAdm = unidadAdm;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getNumLote() {
        return numLote;
    }

    public void setNumLote(Integer numLote) {
        this.numLote = numLote;
    }

    public List<AfDepreciaciones> getLstDepre() {
        return lstDepre;
    }

    public void setLstDepre(List<AfDepreciaciones> lstDepre) {
        this.lstDepre = lstDepre;
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

    public Long getCat() {
        return cat;
    }

    public void setCat(Long cat) {
        this.cat = cat;
    }

    public Long getIdBien() {
        return idBien;
    }

    public void setIdBien(Long idBien) {
        this.idBien = idBien;
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

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public List<AfBienesDepreciables> getLstBienesDepreciable() {
        return lstBienesDepreciable;
    }

    public void setLstBienesDepreciable(List<AfBienesDepreciables> lstBienesDepreciable) {
        this.lstBienesDepreciable = lstBienesDepreciable;
    }

    public List<VwDatosxCuentas> getLstDatos() {
        return lstDatos;
    }

    public void setLstDatos(List<VwDatosxCuentas> lstDatos) {
        this.lstDatos = lstDatos;
    }

    public List<VwBienes> getLstBienes() {
        return lstBienes;
    }

    public void setLstBienes(List<VwBienes> lstBienes) {
        this.lstBienes = lstBienes;
    }

    public List<VwDepreciaciones> getLstDepreciaciones() {
        return lstDepreciaciones;
    }

    public void setLstDepreciaciones(List<VwDepreciaciones> lstDepreciaciones) {
        this.lstDepreciaciones = lstDepreciaciones;
    }

    public String getNombreUnidad(String codigo) {
        return (cejb.nomUnidadAf(codigo));
    }

    public String getNombreAdm(String codigo, String tipo) {
        return cejb.NomUnidad(codigo, tipo);
    }

    public String getTipoUnidad(String codigo) {
        return cejb.getTipoUnidad(codigo);
    }

    public List<AfUnidadesActivoFijo> getLstUnidadAF() {
        return cejb.getUnidadAf();
    }

    public List<AfUnidadesAdministrativas> getLstUnidadAdm() {
        //   activo=true;
        return lstUnidadAdm;
    }
    public List<VwMunicipio> getMunicipios(String codigo){
        return lstMunicipio = bejb.buscarMunicipios(codigo);
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
        return cejb.getSeccion(unidadAdm, unidadAF);
    }

    public List<AfCalidadBien> getCalidadBien() {
        return cejb.getCalidadBien();
    }

    public List<AfEstatusBien> getEstatuBien() {
        return cejb.getEstatusBien();
    }

    public List<AfFuenteFinanciamiento> getLstFuente() {
        return cejb.getFuente();
    }

    public List<AfProyectos> getLstProyecto() {
        return cejb.getProyectos();
    }

    public void filtrarUnidades() {
        // activo=false;
        lstUnidadAdm = cejb.getUnidadAdm(unidadAF, "CE");
        lstMunicipio = bejb.buscarMunicipios(unidadAF);
    }
    
    public void filtrarUnidadesMun() {
        // activo=false;
        lstUnidadAdm = cejb.getUnidadAdmMun(unidadAF, "CE",municipio);
    }
    
    
    public void filtrarUnidadesAdm() {
        // activo=false;
        lstUnidadAdm = cejb.getUnidadAdm(unidadAF, tipoUnidad);
    }

    public void bienesId(SelectEvent event) {
        lstBienesDepreciable = bejb.bienId(bd.getIdBien());
    }

    public void depreciacionAnio() {
        lstDepreciaciones = bejb.depreciacionAnio(anio, mes);
    }

    public void obtenerCorre() {
        String tipoBien = cejb.getTBien(tipo).getCodigoTipoBien();
        if (tipoUnidad.equals("UA")) {
            correlativo = String.format("%03d", cejb.getCorrelativo(unidadAF, unidadAdm, tipo.toString()));
        } else {
            correlativo = String.format("%04d", cejb.getCorrelativo(unidadAF, unidadAdm, tipo.toString()));
        }

        codigoInventario = unidadAdm + "-" + tipoBien + "-" + correlativo;
    }

    public void filtrarTipoBien() {
        lstTipoBienes = cejb.getTipoBien(cat);

    }

    public void onRowSelect(SelectEvent event) {
        cat = cejb.getCategoria(tb.getIdTipoBien());
        if (cat == 5) {

            pnlVh = true;
            pnlMb = false;
        } else {

            pnlVh = false;
            pnlMb = true;
        }
        RequestContext.getCurrentInstance().closeDialog(tb.getIdTipoBien());

    }

    public void guardarBitacoraSolvencia(String anio) {
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        sol = cejb.getSolvencias(anio, unidadAdm);
        if (sol.getIdSolvencia()== null ) {
            sol.setAnio(anio);
            sol.setCodigoUnidad(unidadAdm);
        }
        bejb.guardarSol(sol, usuDao.getLogin());
    }

    public void guardarBien() {
        int i;
        String co;
        if (tipo!=0) {
            String tipoBien = cejb.getTBien(tipo).getCodigoTipoBien();
            bd.setIdCatBien(cejb.getCatBien(cejb.getCategoria(tipo)));
            bd.setIdTipoBien(tipo);//CodigoTipoBien(cejb.getTBien(tipo).getCodigoTipoBien());
            bd.setCodigoCalidadBien(cejb.getCalidadBien(calidad));
            bd.setIdEstatusBien(cejb.getEstatusBien(estatus));
            bd.setDescripcionBien(desBien);
            if (fuente == null) {
                bd.setIdFuente(0l);
            } else {
                bd.setIdFuente(Long.valueOf(fuente));
            }
            bd.setEsValorEstimado(bd.getEsValorEstimado());
            if (!marcaM.equals("")|| !marcaM.isEmpty()) {
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
                bd.setIdProyecto(Long.valueOf(proyecto));
            }
            if (bd.getValorAdquisicion().intValue() >= 600) {
                bd.setActivoFijo('1');
            } else {
                bd.setActivoFijo('0');
            }
            bd.setValorResidual(bd.getValorAdquisicion().multiply(new BigDecimal(0.1)));
            if (bd.getIdBien() == null) {
                bd.setUnidadActivoFijo(unidadAF);
                bd.setCodigoUnidad(unidadAdm);

                if (lote.equals("S")) {
                    for (i = 1; i <= numLote; i++) {
                        bd.setIdBien(null);
                        if (i == 1) {
                            co = correlativo;
                        } else {
                            int sum = Integer.parseInt(correlativo) + 1;
                            if (tipoUnidad.equals("CE")) {
                                co = String.format("%04d", sum);
                            } else {
                                co = String.format("%03d", sum);
                            }
                            //co= String.format("%04d",sum);
                        }
                        if (cejb.getBuscarCorrelativo(unidadAF, unidadAdm, tipo.toString(), co))
                        {
                            bd.setCorrelativo(co);
                            codigoInventario = unidadAdm + "-" + tipoBien + "-" + co;
                        } else {
                            obtenerCorre();
                            co = correlativo;
                            bd.setCorrelativo(co);
                        }
                        bd.setCodigoInventario(codigoInventario);
                        bejb.guardarBien(bd, usuDao.getLogin());
                        correlativo = co;
                    }
                    JsfUtil.mensajeInsertLote(i - 1);
                } else {

                    // obtenerCorre();
                    if (!cejb.getBuscarCorrelativo(unidadAF, unidadAdm, tipo.toString(), correlativo)) {
                        bd.setCorrelativo(correlativo);
                        codigoInventario = unidadAdm + "-" + tipoBien + "-" + correlativo;
                    } else {
                        obtenerCorre();
                    }
                    bd.setCodigoInventario(codigoInventario);
                    bejb.guardarBien(bd, usuDao.getLogin());
                    // JsfUtil.mensajeInsert();
                }
            } else {
                 bd.setCorrelativo(correlativo);
                 bd.setCodigoInventario(codigoInventario);
                
                bejb.guardarBien(bd, usuDao.getLogin());
                //  JsfUtil.mensajeInsert();
            }
            if (!esAdm) {
                JsfUtil.redireccionar("/app/mantenimientos/buscarbienes.mined?faces-redirect=true");
            } else {
                JsfUtil.redireccionar("/app/mantenimientos/buscarbienesDep.mined?faces-redirect=true");
            }
        } else {
            JsfUtil.mensajeAlerta("Seleccione el Tipo de Bien");
        }
    }

    public void limpiarFiltro() {
        tipoUnidad = "UA";
        unidadAdm = "0";
        actFijo = null;
        codigoInventario = null;
        estatus = 0l;
        calidad = 0l;
        section = 0l;
        cat = 0l;
        fecAdq1 = null;
        fecAdq2 = null;
        marca = null;
        modelo = null;
        serie = null;
        fuente = "0";
        proyecto = "0";
        fecCrea1 = null;
        fecCrea2 = null;
        anio = null;
        mes = null;
        lstBienes = null;

    }

    public void buscarDepreBien() {
        String condicion;
        condicion = "";
        condicion = condicion + " where  ID_BIEN IN ( SELECT A.ID_BIEN FROM AF_BIENES_DEPRECIABLES A WHERE A.CODIGO_INVENTARIO like '%" + codigoInventario + "%') and ";

        if (!condicion.equals("")) {
            condicion = condicion.substring(0, condicion.length() - 4);
        }
        lstDepre = bejb.buscarDepre(condicion);

    }

    public void buscarDatosxCat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String condicion;
        condicion = "";

        if (!tipoUnidad.equals("0")) {
            condicion = condicion + " A.tipo_unidad='" + tipoUnidad + "' and ";
        }
        if (!unidadAF.equals("0")) {
            condicion = condicion + " A.UNIDAD_ACTIVO_FIJO='" + unidadAF + "' and ";
        }
        if (!unidadAdm.equals("0")) {
            condicion = condicion + " A.CODIGO_UNIDAD ='" + unidadAdm + "' and ";
        }
        if (cat != 0) {
            condicion = condicion + " A.ID_CAT_BIEN = '" + cat + "' and ";
        }
        if (!fuente.equals("0")) {
            condicion = condicion + " A.ID_FUENTE =" + fuente + " and ";
        }
        if (!proyecto.equals("0")) {
            condicion = condicion + " A.ID_PROYECTO =" + proyecto + " and ";
        }
        if (fecAdq1 != null) {
            condicion = condicion + " A.FECHA_ADQUISICION >= TO_DATE('" + sdf.format(fecAdq1) + "','DD/MM/YYYY') AND";
        }
        if (fecAdq2 != null) {
            condicion = condicion + " A.FECHA_ADQUISICION <= TO_DATE('" + sdf.format(fecAdq2) + "','DD/MM/YYYY') AND";
        }

        if (!condicion.equals("")) {
            condicion = condicion.substring(0, condicion.length() - 4);
        }

        lstDatos = bejb.buscarDatos(condicion);
        activo = false;
    }

    public void buscarBien() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String condicion;
        condicion = " A.CODIGO_UNIDAD in (select CODIGO_UNIDAD from AF_UNIDADES_ADMINISTRATIVAS ";

        if (tipoUnidad.equals("0")) {
            condicion = condicion + ") and ";
        } else {
            condicion = condicion + " where TIPO_UNIDAD='" + tipoUnidad + "') and ";
        }
        if (!unidadAF.equals("0")) {
            condicion = condicion + " A.UNIDAD_ACTIVO_FIJO='" + unidadAF + "' and ";
        }
        if (!unidadAdm.equals("0")) {
            condicion = condicion + " A.CODIGO_UNIDAD ='" + unidadAdm + "' and ";
        }
        if (cat != 0) {
            condicion = condicion + " A.ID_CAT_BIEN = '" + cat + "' and ";
        }
        if (tipo != 0) {
            condicion = condicion + " A.id_TIPO_BIEN = '" + tipo + "' and ";
        }
        if (codigoInventario != null && !codigoInventario.isEmpty()) {
            condicion = condicion + " A.CODIGO_INVENTARIO like '%" + codigoInventario + "%' and ";
        }
        if (asignadoA != null && !asignadoA.isEmpty()) {
            condicion = condicion + " UPPER(A.ASIGNADO) ='" + asignadoA + "' and ";
        }
        if (section != 0) {
            condicion = condicion + " A.CODIGO_SECCION = " + section + " and ";
        }
        if (calidad != 0) {
            condicion = condicion + " A.CODIGO_CALIDAD_BIEN =" + calidad + " and ";
        }
        if (estatus != 0) {
            condicion = condicion + " A.ID_ESTATUS_BIEN =" + estatus + " and ";
        }
        if (marca != null && !marca.isEmpty()) {
            condicion = condicion + " A.MARCA ='" + marca + "' and ";
        }

        if (modelo != null && !modelo.isEmpty()) {
            condicion = condicion + " A.modelo = '" + modelo + "' and ";
        }
        if (serie != null && !serie.isEmpty()) {
            condicion = condicion + " A.NUMERO_SERIE ='" + serie + "' and ";
        }
        if (actFijo != null && !actFijo.isEmpty()) {

            if (actFijo.equals("S")) {
                condicion = condicion + " A.VALOR_ADQUISICION >=600 AND";
            } else {
                condicion = condicion + " A.VALOR_ADQUISICION <600 AND";
            }
        }
        if (valorAdquisicion != null && valorAdquisicion.intValue() != 0) {
            condicion = condicion + "  A.VALOR_ADQUISICION=" + valorAdquisicion + " AND";
        }
        if (fuente != null && !fuente.isEmpty()) {
            condicion = condicion + " A.ID_FUENTE =" + fuente + " and ";
        }
        if (proyecto != null && !proyecto.isEmpty()) {
            condicion = condicion + " A.ID_PROYECTO =" + proyecto + " and ";
        }
        if (fecAdq1 != null) {
            condicion = condicion + " A.FECHA_ADQUISICION >= TO_DATE('" + sdf.format(fecAdq1) + "','DD/MM/YYYY') AND";
        }
        if (fecAdq2 != null) {
            condicion = condicion + " A.FECHA_ADQUISICION <= TO_DATE('" + sdf.format(fecAdq2) + "','DD/MM/YYYY') AND";
        }
        if (fecCrea1 != null) {
            condicion = condicion + " A.FECHA_CREACION >= TO_DATE('" + sdf.format(fecCrea1) + "','DD/MM/YYYY') AND ";
        }
        if (fecCrea2 != null) {
            condicion = condicion + " A.FECHA_CREACION <= TO_DATE('" + sdf.format(fecCrea2) + "','DD/MM/YYYY') AND";
        }
        condicion = condicion.substring(0, condicion.length() - 4);

        lstBienes = bejb.buscarBien(condicion);

        activo = false;
    }

    public void buscarTipoBien() {
        if (unidadAdm == null || unidadAdm.equals("0")) {
            JsfUtil.mensajeError("Seleccione la Unidad Administrativa");
        } else {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("resizable", true);
            options.put("draggable", true);
            options.put("width", 640);
            options.put("height", 340);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);
            RequestContext.getCurrentInstance().openDialog("/app/mantenimientos/buscarTipoBien", options, null);
        }
    }

    public void buscarTipoBienDep() {
        Map<String, Object> options = new HashMap();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/app/mantenimientos/buscarTipoBien", options, null);
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

    public Date getFecLimit() {
        return fecLimit;
    }

    public void setFecLimit(Date fecLimit) {
        this.fecLimit = fecLimit;
    }

    public List<VwCorrelativos> getLstCorrelativos() {
        return lstCorrelativos;
    }

    public void setLstCorrelativos(List<VwCorrelativos> lstCorrelativos) {
        this.lstCorrelativos = lstCorrelativos;
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void tipoBienes() {
        lstTipoBienes = cejb.getDesTipo(valor);

    }

    public void nuevoBienes() {

        JsfUtil.redireccionar("/app/mantenimientos/bienesDepreciables.mined?faces-redirect=true");
    }

    public void nuevoBien() {

        JsfUtil.redireccionar("/app/mantenimientos/bienesDepreciablesDep.mined?faces-redirect=true");
    }

    public void ingresoBien() {

        JsfUtil.redireccionar("/app/mantenimientos/bienesDepreciablesDep.mined?faces-redirect=true&idBien=" + idBien);
    }

    public void ingresaBienes() {

        JsfUtil.redireccionar("/app/mantenimientos/bienesDepreciables.mined?faces-redirect=true&idBien=" + idBien);
    }

    public Boolean getBtnEliminar() {
        if (tipoUsu.equals("D")) {
            return valorAdquisicion.intValue() < 600;
        } else {
            return false;
        }

    }

    public void eliminarBien() {
        for (VwBienes afBienesDepreciables : lstBientmp) {
            bejb.removeBien(afBienesDepreciables.getIdBien());
        }
        RequestContext.getCurrentInstance().execute("PF('dlg1').hide()");
        buscarBien();

        JsfUtil.mensajeEliminarBien();

    }

    public void imprimirSolvenCE() throws IOException, JRException {
       List<JasperPrint> jasperPrintList = new ArrayList();
      String descripcion="";
        HashMap param = new HashMap();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
        if (fecRep == null) {
            fecRep = cejb.getFechaActual();
        }
         if (estadoSol.equals("1")){
           descripcion =" QUE ACTUALIZARON INVENTARIO";
        }else{
            if (estadoSol.equals("2")){
                descripcion =" QUE NO ACTUALIZARON INVENTARIO";
            }
        }
        
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        param.put("p_RutaImg", ctx.getRealPath(PATH_IMAGENES));
        param.put("p_rotulo",descripcion);
         JasperPrint jp= reb.getRpt(param, BienesController.class.getClassLoader().getResourceAsStream(("reportes" + File.separator + "rep_solvencias.jasper")),bejb.getLstSol(unidadAF, unidadAdm, formateador.format(fecRep), lstSolvencia, usuDao.getLogin()));
       
        jasperPrintList.add(jp);
     
        UtilReport.generarReporte(jasperPrintList, "rptSolvencias");
    }
    
  
    public void imprimirConstancia() throws IOException, JRException {
        if (!unidadAdm.equals("0")) {
            HashMap param = new HashMap();
            SimpleDateFormat formateador = new SimpleDateFormat("dd ' dias del mes de ' MMMM ' del año ' yyyy", new Locale("es"));
            Calendar cal = Calendar.getInstance();
            //String nomReporte="rep_solvencia_CE.jasper";

            //    String  year= String.valueOf(cal.get(Calendar.YEAR)); 
            String fecRep = formateador.format(cal.getTime());
            //     String Responsable= usuDao.getNombres()+' '+usuDao.getApellidos();
            //  guardarBitacoraSolvencia();
           ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
           param.put("p_RutaImg", ctx.getRealPath(PATH_IMAGENES));
        
            param.put("p_periodo", periodo);
            param.put("p_responsable", responsable);
            param.put("p_unidadAF", unidadAF);
            param.put("p_unidadAdm", unidadAdm);
            param.put("p_fecRep", fecRep);
            
             UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(),
             reb.getRpt(UtilReport.class.getClassLoader().getResourceAsStream("reportes" + File.separator + "rep_solvencia_CE.jasper"), param));
   
            
            
        } else {
            JsfUtil.mensajeError("Seleccione Unidad Administrativa");
        }
    }

    public void imprimirSolvencia() throws IOException, JRException {
        if (!periodo2.equals("")) {
            if (!unidadAdm.equals("0")) {
                HashMap param = new HashMap();
                SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
                Calendar cal = Calendar.getInstance();
                //String nomReporte="rep_solvencia.jasper";
                String fecRep = formateador.format(cal.getTime());
                String Responsable = usuDao.getNombres() + ' ' + usuDao.getApellidos();
                ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                 param.put("p_RutaImg", ctx.getRealPath(PATH_IMAGENES));
        
                param.put("p_periodo", periodo2);
                param.put("p_responsable", Responsable);
                param.put("p_unidadAF", unidadAF);
                param.put("p_unidadAdm", unidadAdm);
                param.put("p_fecRep", fecRep);

                guardarBitacoraSolvencia(periodo2);
                
                UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(),
                reb.getRpt(UtilReport.class.getClassLoader().getResourceAsStream("reportes" + File.separator + "rep_solvencia.jasper"), param));

                //UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(), param, "rep_solvencia.jasper", cejb.getEm());
            } else {
                JsfUtil.mensajeError("Seleccione Unidad Administrativa");
            }
        } else {
            JsfUtil.mensajeError("Ingrese periodo");
        }
    }
    
    public void imprimirBienes() throws IOException, JRException {
       List<JasperPrint> jasperPrintList = new ArrayList();
     
        HashMap param = new HashMap();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
        if (fecRep == null) {
            fecRep = cejb.getFechaActual();
        }
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        param.put("p_RutaImg", ctx.getRealPath(PATH_IMAGENES));
        
         JasperPrint jp= reb.getRpt(param, BienesController.class.getClassLoader().getResourceAsStream(("reportes" + File.separator + "rep_mobiliario.jasper")),bejb.getLst(unidadAF, unidadAdm, formateador.format(fecRep), lstBienes, usuDao.getLogin()));
       
        jasperPrintList.add(jp);
     
        UtilReport.generarReporte(jasperPrintList, "rptAF8");

         //UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(), bejb.getLst(unidadAF, unidadAdm, formateador.format(fecRep), lstBienes, usuDao.getLogin()), param, "rep_mobiliario.jasper");

          //UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(),param, "rep_mobiliario.jasper", cejb.getEm());
    }

    public void imprimirBienesxsubcuentas() throws IOException, JRException {
        HashMap param = new HashMap();

        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
        fecRep = cejb.getFechaActual();
         
        UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(), bejb.getLstdatos(unidadAF, unidadAdm, formateador.format(fecRep), fuente, lstDatos, usuDao.getLogin()), param, "rep_bienesxsubcuentas.jasper");

        //  UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(),param, "rep_mobiliario.jasper", cejb.getEm());
    }

    public void imprimirDepreciacionBien() throws IOException, JRException {
        List<JasperPrint> jasperPrintList = new ArrayList();
     
   
        HashMap param = new HashMap();

        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
        fecRep = cejb.getFechaActual();
        if ((idBien == null) && (codigoInventario == null)) {
            JsfUtil.mensajeError("Debe Ingresar un id o codigo de Inventario a consultar");
        } else {
            if (idBien == null) {
                idBien = cejb.getIdbyInv(codigoInventario);
            }
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
           param.put("p_RutaImg", ctx.getRealPath(PATH_IMAGENES));
        
            JasperPrint jp= reb.getRpt(param, BienesController.class.getClassLoader().getResourceAsStream(("reportes" + File.separator + "rep_depreciacionBien.jasper")),bejb.getLstdepre(idBien, lstDepre, formateador.format(fecRep), usuDao.getLogin()));
       
        jasperPrintList.add(jp);
     
        UtilReport.generarReporte(jasperPrintList, "rptDepreciacion");
          //  UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(), bejb.getLstdepre(idBien, lstDepre, formateador.format(fecRep), usuDao.getLogin()), param, "rep_depreciacionBien.jasper");
        }
        //  UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(),param, "rep_mobiliario.jasper", cejb.getEm());
    }

    public void cerrarDialog() {
        RequestContext.getCurrentInstance().execute("PF('dlg1').close()");
    }

    public void abrirDialog() {
        if (!lstBientmp.isEmpty()) {
            RequestContext.getCurrentInstance().execute("PF('dlg1').show()");
        } else {
            JsfUtil.mensajeError("Seleccione Bienes a Eliminar");
        }
    }

    public void generarXls_reporteSolvencias() throws IOException {
        Workbook libro = new HSSFWorkbook();
        Sheet hoja = libro.createSheet("Reporte Solvencia");
        String descripcion="";
        HSSFFont font = (HSSFFont) libro.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);
        font.setFontName(HSSFFont.FONT_ARIAL);

        HSSFFont font1 = (HSSFFont) libro.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font1.setColor(HSSFColor.BLACK.index);
        font1.setFontName(HSSFFont.FONT_ARIAL);

        HSSFCellStyle style = (HSSFCellStyle) libro.createCellStyle();
        style.setFont(font);

        HSSFCellStyle style2 = (HSSFCellStyle) libro.createCellStyle();
        style2.setFont(font1);
        short alig = 2;
        style.setAlignment(alig);

        //fila = hoja.createRow((short) 1);
        Row fila = hoja.createRow((short) 1);
        Cell celdaEn1 = fila.createCell((short) 1);
        celdaEn1.setCellStyle(style);
        celdaEn1.setCellValue("MINISTERIO DE EDUCACIÓN");
        hoja.addMergedRegion(new CellRangeAddress(1, 1, 1, 5));

        fila = hoja.createRow((short) 2);
        Cell celdaEn2 = fila.createCell((short) 1);
        celdaEn2.setCellStyle(style);
        if (!unidadAF.equals("0")) {
            celdaEn2.setCellValue(cejb.nomUnidadAf(unidadAF));
        } else {
            celdaEn2.setCellValue("");
        }
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 1, 5));

        fila = hoja.createRow((short) 3);
        Cell celdaEn3 = fila.createCell((short) 1);
        celdaEn3.setCellStyle(style);
        if (estadoSol.equals("1")){
           descripcion ="QUE ACTUALIZARON INVENTARIO";
        }else{
            if (estadoSol.equals("2")){
                descripcion ="QUE NO ACTUALIZARON INVENTARIO";
            }
        }
        celdaEn3.setCellValue("DETALLE DE CENTROS ESCOLARES "+descripcion);
        hoja.addMergedRegion(new CellRangeAddress(3, 3, 1, 15));

        fila = hoja.createRow((short) 5);
        Cell celdaEn5 = fila.createCell((short) 1);
        celdaEn5.setCellStyle(style);
        celdaEn5.setCellValue("UNIDAD ACTIVO FIJO");

        Cell celdaEn6 = fila.createCell((short) 2);
        celdaEn6.setCellStyle(style);
        celdaEn6.setCellValue("MUNICIPIO");

        Cell celdaEn7 = fila.createCell((short) 3);
        celdaEn7.setCellStyle(style);
        celdaEn7.setCellValue("CODIGO ENTIDAD");

        Cell celdaEn8 = fila.createCell((short) 4);
        celdaEn8.setCellStyle(style);
        celdaEn8.setCellValue("NOMBRE CENTRO EDUCATIVO");

        Cell celdaEn9 = fila.createCell((short) 5);
        celdaEn9.setCellStyle(style);
        celdaEn9.setCellValue("FECHA ACTUALIZACIÓN");

        Cell celdaEn10 = fila.createCell((short) 6);
        celdaEn10.setCellStyle(style);
        celdaEn10.setCellValue("NUMERO BIENES");

        Cell celdaEn11 = fila.createCell((short) 7);
        celdaEn11.setCellStyle(style);
        celdaEn11.setCellValue("COSTO INVENTARIO");

        int x = 6;
        BigDecimal total = new java.math.BigDecimal("0.00");
        DecimalFormat df = new DecimalFormat();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        df.setMaximumFractionDigits(2);
        for (VwSolvencia object : lstSolvencia) {
            fila = hoja.createRow((short) x);

            Cell celda4 = fila.createCell((short) 1);
            celda4.setCellStyle(style2);
            celda4.setCellValue(object.getUnidadActivoFijo());

            Cell celda5 = fila.createCell((short) 2);
            celda5.setCellStyle(style2);
            celda5.setCellValue(object.getNombreMunicipio());

            Cell celda6 = fila.createCell((short) 3);
            celda6.setCellStyle(style2);
            celda6.setCellValue(object.getCodigoEntidad());

            Cell celda7 = fila.createCell((short) 4);
            celda7.setCellStyle(style2);
            celda7.setCellValue(object.getNombre());

            Cell celda8 = fila.createCell((short) 5);
            celda8.setCellStyle(style2);
            Date getFechaAct;
            if (object.getFechaActualizacion()!=null){
                getFechaAct = object.getFechaActualizacion();
                celda8.setCellValue(sdf.format(getFechaAct));
            }else{
                celda8.setCellValue("");
            }
            
            Cell celda9 = fila.createCell((short) 6);
            celda9.setCellStyle(style2);
            celda9.setCellValue(object.getNumBienes().doubleValue());
          
           
            Cell celda10 = fila.createCell((short) 7);
            celda10.setCellStyle(style2);
            celda10.setCellValue(object.getCosto().doubleValue());

            total = total.add(object.getCosto());
              x++;
        }
        fila = hoja.createRow((short) x);
        Cell celda11 = fila.createCell((short) 7);
        celda11.setCellStyle(style2);
        celda11.setCellValue(total.doubleValue());

        hoja.autoSizeColumn((short) 0);
        hoja.autoSizeColumn((short) 1);
        hoja.autoSizeColumn((short) 2);
        hoja.autoSizeColumn((short) 3);
        hoja.autoSizeColumn((short) 4);
        hoja.autoSizeColumn((short) 5);
        hoja.autoSizeColumn((short) 6);
        hoja.autoSizeColumn((short) 7);
       
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

        libro.write(outByteStream);

        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application / ms - excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        //String patron = "dd-MM-yyyy";
        //SimpleDateFormat formato = new SimpleDateFormat(patron);
        response.setHeader("Content-Disposition", "attachment;filename = ListadoBienes.xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        fc.responseComplete();
    }

    public void generarXls_reporteBienes() throws IOException {
        Workbook libro = new HSSFWorkbook();
        Sheet hoja = libro.createSheet("Reporte Listado Bienes");

        HSSFFont font = (HSSFFont) libro.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);
        font.setFontName(HSSFFont.FONT_ARIAL);

        HSSFFont font1 = (HSSFFont) libro.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font1.setColor(HSSFColor.BLACK.index);
        font1.setFontName(HSSFFont.FONT_ARIAL);

        HSSFCellStyle style = (HSSFCellStyle) libro.createCellStyle();
        style.setFont(font);

        HSSFCellStyle style2 = (HSSFCellStyle) libro.createCellStyle();
        style2.setFont(font1);
        short alig = 2;
        style.setAlignment(alig);

        //fila = hoja.createRow((short) 1);
        Row fila = hoja.createRow((short) 1);
        Cell celdaEn1 = fila.createCell((short) 1);
        celdaEn1.setCellStyle(style);
        celdaEn1.setCellValue("MINISTERIO DE EDUCACIÓN");
        hoja.addMergedRegion(new CellRangeAddress(1, 1, 1, 5));

        fila = hoja.createRow((short) 2);
        Cell celdaEn2 = fila.createCell((short) 1);
        celdaEn2.setCellStyle(style);
        if (!unidadAdm.equals("0")) {
            celdaEn2.setCellValue(cejb.NomUnidad(unidadAdm, tipoUnidad));
        } else {
            celdaEn2.setCellValue("");
        }
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 1, 5));

        fila = hoja.createRow((short) 3);
        Cell celdaEn3 = fila.createCell((short) 1);
        celdaEn3.setCellStyle(style);
        if (!unidadAF.equals("0")) {
            celdaEn3.setCellValue(cejb.nomUnidadAf(unidadAF));
        } else {
            celdaEn3.setCellValue("");
        }
        hoja.addMergedRegion(new CellRangeAddress(3, 3, 1, 5));

        fila = hoja.createRow((short) 5);
        Cell celdaEn4 = fila.createCell((short) 1);
        celdaEn4.setCellStyle(style);
        celdaEn4.setCellValue("LISTADO DE BIENES DEPRECIABLES ");
        hoja.addMergedRegion(new CellRangeAddress(5, 5, 1, 15));

        fila = hoja.createRow((short) 7);
        Cell celdaEn5 = fila.createCell((short) 1);
        celdaEn5.setCellStyle(style);
        celdaEn5.setCellValue("ID");

        Cell celdaEn6 = fila.createCell((short) 2);
        celdaEn6.setCellStyle(style);
        celdaEn6.setCellValue("INVENTARIO");

        Cell celdaEn7 = fila.createCell((short) 3);
        celdaEn7.setCellStyle(style);
        celdaEn7.setCellValue("CATEGORIA");

        Cell celdaEn8 = fila.createCell((short) 4);
        celdaEn8.setCellStyle(style);
        celdaEn8.setCellValue("DESCRIPCION");

        Cell celdaEn9 = fila.createCell((short) 5);
        celdaEn9.setCellStyle(style);
        celdaEn9.setCellValue("MARCA");

        Cell celdaEn10 = fila.createCell((short) 6);
        celdaEn10.setCellStyle(style);
        celdaEn10.setCellValue("MODELO");

        Cell celdaEn11 = fila.createCell((short) 7);
        celdaEn11.setCellStyle(style);
        celdaEn11.setCellValue("SERIE");

        Cell celdaEn12 = fila.createCell((short) 8);
        celdaEn12.setCellStyle(style);
        celdaEn12.setCellValue("ESTADO");

        Cell celdaEn13 = fila.createCell((short) 9);
        celdaEn13.setCellStyle(style);
        celdaEn13.setCellValue("FECHA ADQUISICIÓN");

        Cell celdaEn14 = fila.createCell((short) 10);
        celdaEn14.setCellStyle(style);
        celdaEn14.setCellValue("PRECIO");

        Cell celdaEn15 = fila.createCell((short) 11);
        celdaEn15.setCellStyle(style);
        celdaEn15.setCellValue("ACTIVO FIJO");

        Cell celdaEn16 = fila.createCell((short) 12);
        celdaEn16.setCellStyle(style);
        celdaEn16.setCellValue("FUENTE");

        Cell celdaEn17 = fila.createCell((short) 13);
        celdaEn17.setCellStyle(style);
        celdaEn17.setCellValue("PROYECTO");

        Cell celdaEn18 = fila.createCell((short) 14);
        celdaEn18.setCellStyle(style);
        celdaEn18.setCellValue("DOCUMENTO ADQUISICIÓN");

        Cell celdaEn19 = fila.createCell((short) 15);
        celdaEn19.setCellStyle(style);
        celdaEn19.setCellValue("PROVEEDOR");

        Cell celdaEn20 = fila.createCell((short) 16);
        celdaEn20.setCellStyle(style);
        celdaEn20.setCellValue("USUARIO");

        Cell celdaEn21 = fila.createCell((short) 17);
        celdaEn21.setCellStyle(style);
        celdaEn21.setCellValue("FECHA CREACIÓN");

        int x = 8;
        BigDecimal total = new java.math.BigDecimal("0.00");
        DecimalFormat df = new DecimalFormat();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        df.setMaximumFractionDigits(2);
        for (VwBienes object : lstBienes) {
            fila = hoja.createRow((short) x);

            Cell celda4 = fila.createCell((short) 1);
            celda4.setCellStyle(style2);
            celda4.setCellValue(object.getIdBien());

            Cell celda5 = fila.createCell((short) 2);
            celda5.setCellStyle(style2);
            celda5.setCellValue(object.getCodigoInventario());

            Cell celda6 = fila.createCell((short) 3);
            celda6.setCellStyle(style2);
            celda6.setCellValue(object.getCategoria());

            Cell celda7 = fila.createCell((short) 4);
            celda7.setCellStyle(style2);
            celda7.setCellValue((object.getDescripcionBien()));

            Cell celda8 = fila.createCell((short) 5);
            celda8.setCellStyle(style2);
            celda8.setCellValue(object.getMarca());

            Cell celda9 = fila.createCell((short) 6);
            celda9.setCellStyle(style2);
            celda9.setCellValue(object.getModelo());

            Cell celda10 = fila.createCell((short) 7);
            celda10.setCellStyle(style2);
            celda10.setCellValue(object.getNumeroSerie());

            Cell celda11 = fila.createCell((short) 8);
            celda11.setCellStyle(style2);
            celda11.setCellValue(object.getEstado());

            Cell celda12 = fila.createCell((short) 9);
            celda12.setCellStyle(style2);
            Date getFechaAdq = object.getFechaAdquisicion();
            celda12.setCellValue(sdf.format(getFechaAdq));

            Cell celda13 = fila.createCell((short) 10);
            celda13.setCellStyle(style2);
            celda13.setCellType(celda13.CELL_TYPE_NUMERIC);
            celda13.setCellValue(object.getValorAdquisicion().doubleValue());

            total = total.add(object.getValorAdquisicion());

            Cell celda14 = fila.createCell((short) 11);
            celda14.setCellStyle(style2);
            celda14.setCellValue(object.getActivoFijo().toString());

            Cell celda15 = fila.createCell((short) 12);
            celda15.setCellStyle(style2);
            celda15.setCellValue(object.getFuente());

            Cell celda16 = fila.createCell((short) 13);
            celda16.setCellStyle(style2);
            celda16.setCellValue(object.getNombreProyecto());

            Cell celda17 = fila.createCell((short) 14);
            celda17.setCellStyle(style2);
            celda17.setCellValue(object.getDocumentoAdquisicion());

            Cell celda18 = fila.createCell((short) 15);
            celda18.setCellStyle(style2);
            celda18.setCellValue(object.getProveedor());

            Cell celda19 = fila.createCell((short) 16);
            celda19.setCellStyle(style2);
            celda19.setCellValue(object.getUsuarioCreacion());

            Cell celda20 = fila.createCell((short) 17);
            celda20.setCellStyle(style2);
            Date getFechaCre = object.getFechaCreacion();
            celda20.setCellValue(sdf.format(getFechaCre));

            x++;
        }
        fila = hoja.createRow((short) x);
        Cell celda13 = fila.createCell((short) 10);
        celda13.setCellStyle(style2);
        celda13.setCellType(celda13.CELL_TYPE_NUMERIC);
        celda13.setCellValue(total.doubleValue());

        hoja.autoSizeColumn((short) 0);
        hoja.autoSizeColumn((short) 1);
        hoja.autoSizeColumn((short) 2);
        hoja.autoSizeColumn((short) 3);
        hoja.autoSizeColumn((short) 4);
        hoja.autoSizeColumn((short) 5);
        hoja.autoSizeColumn((short) 6);
        hoja.autoSizeColumn((short) 7);
        hoja.autoSizeColumn((short) 8);
        hoja.autoSizeColumn((short) 9);
        hoja.autoSizeColumn((short) 10);
        hoja.autoSizeColumn((short) 11);
        hoja.autoSizeColumn((short) 12);
        hoja.autoSizeColumn((short) 13);
        hoja.autoSizeColumn((short) 14);
        hoja.autoSizeColumn((short) 15);
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

        libro.write(outByteStream);

        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application / ms - excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        //String patron = "dd-MM-yyyy";
        //SimpleDateFormat formato = new SimpleDateFormat(patron);
        response.setHeader("Content-Disposition", "attachment;filename = ListadoBienes.xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        fc.responseComplete();
    }

    public void buscarCorrelativo() {
        String condicion = "WHERE A.UNIDAD_ACTIVO_FIJO='" + unidadAF + "' AND A.CODIGO_UNIDAD= '" + unidadAdm + "' ";
        lstCorrelativos = bejb.buscarCorrelativos(condicion);
    }
    public void buscarEntidades(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String condicion = "";
        String valores="";
       
        if (!unidadAF.equals("0")){ condicion= condicion+"where UNIDAD_ACTIVO_FIJO='" + unidadAF +"' AND " ;
            if (!tipoUnidad.equals("0")){
                condicion = condicion+" tipo_unidad='"+tipoUnidad+"' and ";
            } 
            if(!unidadAdm.equals("0")){
                 condicion= condicion +" codigo_entidad='"+unidadAdm+"' and ";
             }
             if (!estadoSol.equals("0")) {
                 if (estadoSol.equals("1")){   condicion= condicion +" fecha_solvencia is not null and "; }
                 else{condicion= condicion +" fecha_solvencia is null and "; }
             }
             if (!municipio.equals("0")){
                 condicion = condicion +" codigo_municipio= '"+municipio+"' and ";
             }
              if (fecAdq1 != null) {
                 valores = " b.FECHA_ADQUISICION >= TO_DATE('" + sdf.format(fecAdq1) + "','DD/MM/YYYY') ";
             }
             if (fecAdq2 != null) {
                 if (!valores.equals("")){
                     valores=valores +" AND ";
                 }
                 valores = valores +  " b.FECHA_ADQUISICION <= TO_DATE('" + sdf.format(fecAdq2) + "','DD/MM/YYYY') ";
                  
             }
            if (!valores.equals("")){
               condicion =condicion + " CODIGO_ENTIDAD in (select b.codigo_unidad from AF_BIENES_DEPRECIABLES b where "+valores+") AND ";
            }   
             
           if (!condicion.equals("")) {
              condicion = condicion.substring(0, condicion.length() - 4);
           }
            activo=false;
            lstSolvencia = bejb.buscarEntidades(condicion);
           
         }else{
            JsfUtil.mensajeError("Seleccione Unidad de Activo Fijo");
        }    
    }

     public void imprimirCorrelativos() throws IOException, JRException {
        
        List<JasperPrint> jasperPrintList = new ArrayList();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        HashMap param = new HashMap();
        param.put("p_unidadAF", unidadAF);
        param.put("p_unidadAdm", unidadAdm);
        param.put("p_RutaImg", ctx.getRealPath(PATH_IMAGENES));
        
        JasperPrint jp= reb.getRpt(param, BienesController.class.getClassLoader().getResourceAsStream(("reportes" + File.separator + "rep_control_correl.jasper")));
       
        jasperPrintList.add(jp);
     
        UtilReport.generarReporte(jasperPrintList, "rptControlCorrelativo");
    }
    
    public void imprimirNotificacion() throws IOException, JRException {
        
        List<JasperPrint> jasperPrintList = new ArrayList();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
        fecRep = cejb.getFechaActual();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        HashMap param = new HashMap();
        param.put("p_ActivoFijo", unidadAF);
        param.put("p_Unidad", codigoEntidad);
        param.put("p_noNotifica", numNotificacion.toString());
        param.put("p_RutaImg", ctx.getRealPath(PATH_IMAGENES));
        param.put("pFecha", formateador.format(fecRep));
        
        JasperPrint jp= reb.getRpt(param, BienesController.class.getClassLoader().getResourceAsStream(("reportes" + File.separator + "rep_notificacion.jasper")));
       
        jasperPrintList.add(jp);
     
        UtilReport.generarReporte(jasperPrintList, "rptNotificacion");
    }

    public void definirUnidad(String codEntidad){
        unidadAdm=codEntidad;
    }

    public void calcularDepreciacion() throws IOException, JRException {
        String condicion = " where d.anio='" + anioDepre + "' and ";
        if (idBien!=null) {
            condicion = condicion + " d.id_bien=" + idBien.toString() + " and ";
        }
        else{
            idBien=0l;
        }
        if (!mes.equals("0")) {
            condicion = condicion + " d.mes=" + mes + " and ";
        }
        if (fuente!=null) {
            condicion = condicion + " d.id_fuente=" + fuente + " and ";
        }
        if (proyecto!=null) {
            condicion = condicion + "d.codigo_proyecto=" + proyecto + " and";
        }

        condicion = condicion.substring(0, condicion.length() - 4);

// llevan valores idBien=0,mes=0,proyecto;
        Integer Bienes = bejb.calcularDepreciacion(idBien.toString(), anioDepre, mes, fuente, proyecto);

        //     lstDepreciaciones =bejb.repDepreciacion(condicion);
        mensaje = "Depreciaci&oacuten calculada para " + Bienes + " bienes";

        JsfUtil.mensajeInformacion("Depreciación calculada para " + Bienes + " bienes");

    }

    public void generarXls_reporteBienesxsubcuentas() throws IOException {
        Workbook libro = new HSSFWorkbook();
        Sheet hoja = libro.createSheet("Reporte");

        HSSFFont font = (HSSFFont) libro.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);
        font.setFontName(HSSFFont.FONT_ARIAL);

        HSSFFont font1 = (HSSFFont) libro.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font1.setColor(HSSFColor.BLACK.index);
        font1.setFontName(HSSFFont.FONT_ARIAL);

        HSSFCellStyle style = (HSSFCellStyle) libro.createCellStyle();
        style.setFont(font);

        HSSFCellStyle style2 = (HSSFCellStyle) libro.createCellStyle();
        style2.setFont(font1);
        short alig = 2;
        style.setAlignment(alig);

        //fila = hoja.createRow((short) 1);
        Row fila = hoja.createRow((short) 1);
        Cell celdaEn1 = fila.createCell((short) 1);
        celdaEn1.setCellStyle(style);
        celdaEn1.setCellValue("MINISTERIO DE EDUCACIÓN");
        hoja.addMergedRegion(new CellRangeAddress(1, 1, 1, 5));

        fila = hoja.createRow((short) 2);
        Cell celdaEn2 = fila.createCell((short) 1);
        celdaEn2.setCellStyle(style);
        if (!unidadAdm.equals("0")) {
            celdaEn2.setCellValue(cejb.NomUnidad(unidadAdm, tipoUnidad));
        } else {
            celdaEn2.setCellValue("");
        }
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 1, 5));

        fila = hoja.createRow((short) 3);
        Cell celdaEn3 = fila.createCell((short) 1);
        celdaEn3.setCellStyle(style);
        if (!unidadAF.equals("0")) {
            celdaEn3.setCellValue(cejb.nomUnidadAf(unidadAF));

        } else {
            celdaEn3.setCellValue("");
        }
        hoja.addMergedRegion(new CellRangeAddress(3, 3, 1, 5));
        fila = hoja.createRow((short) 5);
        Cell celdaEn4 = fila.createCell((short) 1);
        celdaEn4.setCellStyle(style);
        celdaEn4.setCellValue("DETALLE DE BIENES POR SUBCUENTAS CONTABLES ");
        hoja.addMergedRegion(new CellRangeAddress(5, 5, 1, 15));

        fila = hoja.createRow((short) 7);
        Cell celdaEn5 = fila.createCell((short) 1);
        celdaEn5.setCellStyle(style);
        celdaEn5.setCellValue("CATEGORIA");

        Cell celdaEn6 = fila.createCell((short) 2);
        celdaEn6.setCellStyle(style);
        celdaEn6.setCellValue("PRECIO");

        Cell celdaEn7 = fila.createCell((short) 3);
        celdaEn7.setCellStyle(style);
        celdaEn7.setCellValue("VALOR RESIDUAL");

        Cell celdaEn8 = fila.createCell((short) 4);
        celdaEn8.setCellStyle(style);
        celdaEn8.setCellValue("DEPRECIACIÓN ACUMULADA");

        Cell celdaEn9 = fila.createCell((short) 5);
        celdaEn9.setCellStyle(style);
        celdaEn9.setCellValue("VALOR ACTUAL");

        Cell celdaEn10 = fila.createCell((short) 6);
        celdaEn10.setCellStyle(style);
        celdaEn10.setCellValue("PENDIENTE DE DEPRECIAR");

        int x = 8;
        BigDecimal total = new java.math.BigDecimal("0.00");
        BigDecimal totalRes = new java.math.BigDecimal("0.00");
        BigDecimal totalDep = new java.math.BigDecimal("0.00");
        BigDecimal totalVal = new java.math.BigDecimal("0.00");
        BigDecimal totalPen = new java.math.BigDecimal("0.00");

        DecimalFormat df = new DecimalFormat();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        df.setMaximumFractionDigits(2);
        for (VwDatosxCuentas object : lstDatos) {
            fila = hoja.createRow((short) x);

            Cell celda4 = fila.createCell((short) 1);
            celda4.setCellStyle(style2);
            celda4.setCellValue(object.getCategoria());

            Cell celda5 = fila.createCell((short) 2);
            celda5.setCellStyle(style2);
            celda5.setCellType(celda5.CELL_TYPE_NUMERIC);
            celda5.setCellValue(object.getPrecio().doubleValue());

            total = total.add(object.getPrecio());

            Cell celda6 = fila.createCell((short) 3);
            celda6.setCellStyle(style2);
            celda6.setCellType(celda6.CELL_TYPE_NUMERIC);
            celda6.setCellValue(object.getValorRes().doubleValue());

            totalRes = totalRes.add(object.getValorRes());

            Cell celda7 = fila.createCell((short) 4);
            celda7.setCellStyle(style2);
            celda7.setCellType(celda7.CELL_TYPE_NUMERIC);
            celda7.setCellValue(object.getDepreAcumulada().doubleValue());

            totalDep = totalDep.add(object.getDepreAcumulada());

            Cell celda8 = fila.createCell((short) 5);
            celda8.setCellStyle(style2);
            celda8.setCellType(celda8.CELL_TYPE_NUMERIC);
            BigDecimal valActual = object.getPrecio().subtract(object.getDepreAcumulada());
            celda8.setCellValue(valActual.doubleValue());

            totalVal = totalVal.add(valActual);

            Cell celda9 = fila.createCell((short) 6);
            celda9.setCellStyle(style2);
            celda9.setCellType(celda9.CELL_TYPE_NUMERIC);
            celda9.setCellValue(object.getPendienteDepre().doubleValue());

            totalPen = totalPen.add(object.getPendienteDepre());

            x++;
        }
        fila = hoja.createRow((short) x);
        Cell celda10 = fila.createCell((short) 2);
        celda10.setCellStyle(style2);
        celda10.setCellType(celda10.CELL_TYPE_NUMERIC);
        celda10.setCellValue(total.doubleValue());

        Cell celda11 = fila.createCell((short) 3);
        celda11.setCellStyle(style2);
        celda11.setCellType(celda11.CELL_TYPE_NUMERIC);
        celda11.setCellValue(totalRes.doubleValue());

        Cell celda12 = fila.createCell((short) 4);
        celda12.setCellStyle(style2);
        celda12.setCellType(celda12.CELL_TYPE_NUMERIC);
        celda12.setCellValue(totalDep.doubleValue());

        Cell celda13 = fila.createCell((short) 5);
        celda13.setCellStyle(style2);
        celda13.setCellType(celda13.CELL_TYPE_NUMERIC);
        celda13.setCellValue(totalVal.doubleValue());

        Cell celda14 = fila.createCell((short) 6);
        celda14.setCellStyle(style2);
        celda14.setCellType(celda14.CELL_TYPE_NUMERIC);
        celda14.setCellValue(totalPen.doubleValue());

        hoja.autoSizeColumn((short) 0);
        hoja.autoSizeColumn((short) 1);
        hoja.autoSizeColumn((short) 2);
        hoja.autoSizeColumn((short) 3);
        hoja.autoSizeColumn((short) 4);
        hoja.autoSizeColumn((short) 5);
        hoja.autoSizeColumn((short) 6);

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

        libro.write(outByteStream);

        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application / ms - excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        //String patron = "dd-MM-yyyy";
        //SimpleDateFormat formato = new SimpleDateFormat(patron);
        response.setHeader("Content-Disposition", "attachment;filename = ListadoBienes.xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        fc.responseComplete();
    }

}
