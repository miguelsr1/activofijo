/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.app.activofijo.controller;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
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
import org.primefaces.event.SelectEvent;
import sv.gob.mined.activofijo.ejb.BienesEJB;
import sv.gob.mined.activofijo.ejb.CatalogosEJB;
import sv.gob.mined.activofijo.ejb.ReportesEJB;
import sv.gob.mined.activofijo.model.AfBienesDepreciables;
import sv.gob.mined.activofijo.model.AfCategoriasBien;
import sv.gob.mined.activofijo.model.AfTipoDescargo;
import sv.gob.mined.activofijo.model.AfDescargos;
import sv.gob.mined.activofijo.model.AfDescargosDetalle;
import sv.gob.mined.activofijo.model.AfUnidadesActivoFijo;
import sv.gob.mined.activofijo.model.AfUnidadesAdministrativas;
import sv.gob.mined.activofijo.model.VwBienes;
import sv.gob.mined.activofijo.model.VwDescargos;
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
public class DescargoController implements Serializable {

    @EJB
    private CatalogosEJB cejb;
    @EJB
    private BienesEJB bejb;
    @EJB 
    private ReportesEJB reb;
    
    private AfDescargosDetalle td = new AfDescargosDetalle();
    private AfDescargos tras = new AfDescargos();
    private VwBienes Vb = new VwBienes();
    private Date fecDescargo;
    private Date fecFallo;
    private Date fec1;
    private Date fec2;
    
    private String tipDescargo;
    private String estSolicitud;
    private String tipoUsu;
    private String activo;
    private List<AfTipoDescargo> lstTipoDescargo = new ArrayList<AfTipoDescargo>();
    private List<AfDescargos> lstDescargos = new ArrayList<AfDescargos>();
    private List<AfUnidadesAdministrativas> lstUnidadAdm = new ArrayList<AfUnidadesAdministrativas>();
    private List<AfUnidadesAdministrativas> lstUnidadAdmDes = new ArrayList<AfUnidadesAdministrativas>();
    private List<AfBienesDepreciables> lstBienes = new ArrayList<AfBienesDepreciables>();
    private List<VwBienes> lstBienesDescargar = new ArrayList<VwBienes>();
    private List<VwDescargos> listaDescargos = new ArrayList<VwDescargos>();
    private List<AfCategoriasBien> lstCatBien = new ArrayList<AfCategoriasBien>();
    private int rowDrop = 0;
    private String unidadAdm;
    private String unidadAF;
    private String acta;
    private String serie;
    private boolean btnDesc = true;
    private boolean btnGuardar = false;
    private boolean btnEnviar = true;
    private boolean btnImp = true;
    private boolean pnlDest = false;
    private boolean pnlTras =true;
    private boolean actTipo=false;
    private boolean actAF=false;
    private boolean actAd=false;
    private boolean actCtrl=false;
    private String tipoUnidad="UA";
    private String observacion;
    private String obsFallo;
    private Long idBien;
    private Long cat;
    private AfBienesDepreciables Bienes;
    private String codigoInv;
    private String numSolicitud;
    private Long idDescargo;
    private Usuario usuDao = new Usuario();
    private List<Long> lstBientmp = new ArrayList<Long>();
    
    public DescargoController() {

    }

    public BienesEJB getBejb() {
        return bejb;
    }

    @PostConstruct
    public void cargarUnidad() {
        
          usuDao = ((LoginController) FacesContext.getCurrentInstance().getApplication().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "loginController")).getUsuario();
          tipoUsu = usuDao.getTipoUsuario().toString();
          
          
          if (tipoUsu.equals("I")){
              actTipo=false;
              actAF = false;
              actAd=false;
              tipoUnidad="UA";
            
          }else {
            if (tipoUsu.equals("D")){
                actTipo=false;
                actAF = true;
                actAd=false;
                tipoUnidad="UA";
            }else{
                if (tipoUsu.equals("A")){
                     actTipo=true;
                    actAF = true;
                    actAd=true;
                    tipoUnidad="UA";
                }
                else{
                    actTipo=true;
                    actAF = true;
                    actAd=true;
                    tipoUnidad="CE";
                }
          }
          }
             
        unidadAdm = usuDao.getCodigoEntidad();
        unidadAF = cejb.getUnidadAf(usuDao.getCodigoEntidad(), tipoUnidad);
        
       // tipoUnidad = "UA";
        lstUnidadAdm = cejb.getUnidadAdm(unidadAF, tipoUnidad);
       
        lstTipoDescargo = cejb.getTipoDescargo();
       
        lstBientmp.add(0l);
        
         if (JsfUtil.getRequestParameter("idDescargo")!=null){
             tras = bejb.getDescargos(new Long(JsfUtil.getRequestParameter("idDescargo")));
             td =  bejb.getDescargoDet(new Long(JsfUtil.getRequestParameter("idDescargo")));
             unidadAF = tras.getUnidadActivoFijo();
             unidadAdm = td.getCodigoUnidad();
             tipoUnidad = cejb.getTipoUnidad(unidadAdm);
             activo = tras.getTipoDescargo().toString();
            lstBienesDescargar = bejb.buscarBien(" a.DESCARGO_id="+new Long(JsfUtil.getRequestParameter("idDescargo")));
            numSolicitud = tras.getCodigoDescargo();
            fec1 = tras.getFechaDescargo();
            tipDescargo = tras.getIdTipoDescargo();
            actTipo=true;
            actAF = true;
            actAd=true;
            actCtrl=true;
            if (tras.getEstado()=='S') {
                if (activo.equals("A")){
                    if (tipoUsu.equals("I")){
                        btnDesc=false;
                        btnGuardar=false;
                        btnEnviar =false;
                        btnImp=false;
                   }
                    else{ btnDesc=true;
                          btnGuardar=false;
                           btnEnviar =false;
                          btnImp=false;
                    
                    }
                }else{
                     if (tipoUsu.equals("I") || tipoUsu.equals("D") ){
                        btnDesc=true;
                        btnGuardar=false;
                         btnEnviar=false;
                        btnImp=false;
                   }
                    else{ btnDesc=true;
                          btnGuardar=false;
                          btnEnviar= false;
                          btnImp=false;
                     }
                }
                
            }else{
              pnlTras = false;
              if (tras.getEstado()=='D'){
               
                btnDesc=true;
                btnGuardar=true;
                btnEnviar=true;
                btnImp=false;
                
            } else{
               if (activo.equals("A")){
                       if (tipoUsu.equals("I")){
                         btnDesc=false;
                       }else{
                         btnDesc=true;  
                       }
                 btnGuardar=true;
                 btnEnviar=true;
                   btnImp=false;
                       
               }else{
                   if (tipoUsu.equals("A") || tipoUsu.equals("C")){
                        btnDesc=true;
                        btnGuardar=true;
                        btnEnviar =true;
                        btnImp=false;
                   }  else{ 
                         btnDesc=false;
                         btnGuardar=true;
                         btnEnviar=true;
                         btnImp=false;
                  }
               }
               
            }
            }
         }
         
      }

    public Date getFecFallo() {
        return fecFallo;
    }

    public void setFecFallo(Date fecFallo) {
        this.fecFallo = fecFallo;
    }

    public void bienesId(SelectEvent event) {
        lstDescargos = bejb.getDescargoById(tras.getDescargoId());
    }
    
    public void filtrarTipotraslado() {
        lstTipoDescargo = cejb.getTipoDescargo();
    }

    public Date getFecDescargo() {
        return fecDescargo;
    }

    public void setFecDescargo(Date fecDescargo) {
        this.fecDescargo = fecDescargo;
    }

    public Date getFec1() {
        return fec1;
    }

    public boolean isBtnDesc() {
        return btnDesc;
    }

    public boolean isActCtrl() {
        return actCtrl;
    }

    public void setActCtrl(boolean actCtrl) {
        this.actCtrl = actCtrl;
    }

    public boolean isBtnEnviar() {
        return btnEnviar;
    }

    public void setBtnEnviar(boolean btnEnviar) {
        this.btnEnviar = btnEnviar;
    }

    public boolean isBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(boolean btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public boolean isBtnImp() {
        return btnImp;
    }

    public void setBtnImp(boolean btnImp) {
        this.btnImp = btnImp;
    }

    public void setBtnDesc(boolean btnDesc) {
        this.btnDesc = btnDesc;
    }

    public void setFec1(Date fec1) {
        this.fec1 = fec1;
    }

    public Date getFec2() {
        return fec2;
    }

    public void setFec2(Date fec2) {
        this.fec2 = fec2;
    }

    public String getObsFallo() {
        return obsFallo;
    }

    public void setObsFallo(String obsFallo) {
        this.obsFallo = obsFallo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getActa() {
        return acta;
    }

    public void setActa(String acta) {
        this.acta = acta;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Long getCat() {
        return cat;
    }

    public void setCat(Long cat) {
        this.cat = cat;
    }

    public String getEstSolicitud() {
        return estSolicitud;
    }

    public void setEstSolicitud(String estSolicitud) {
        this.estSolicitud = estSolicitud;
    }

    public boolean isActTipo() {
        return actTipo;
    }

    public void setActTipo(boolean actTipo) {
        this.actTipo = actTipo;
    }

    public boolean isActAF() {
        return actAF;
    }

    public void setActAF(boolean actAF) {
        this.actAF = actAF;
    }

    public boolean isActAd() {
        return actAd;
    }

    public void setActAd(boolean actAd) {
        this.actAd = actAd;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getTipDescargo() {
        return tipDescargo;
    }

    public void setTipDescargo(String tipDescargo) {
        this.tipDescargo = tipDescargo;
    }

    public String getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(String tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

  
    public Long getIdDescargo() {
        return idDescargo;
    }

    public void setIdDescargo(Long idDescargo) {
        this.idDescargo = idDescargo;
    }

    public List<AfCategoriasBien> getLstCatBien() {
        return cejb.getCategoria();
    }

    public List<AfTipoDescargo> getLstTipoDescargo() {
        return lstTipoDescargo;
    }

    public void setLstTipoDescargo(List<AfTipoDescargo> lstTipoDescargo) {
        this.lstTipoDescargo = lstTipoDescargo;
    }

    public List<VwBienes> getLstBienesDescargar() {
        return lstBienesDescargar;
    }

    public void setLstBienesDescargar(List<VwBienes> lstBienesDescargar) {
        this.lstBienesDescargar = lstBienesDescargar;
    }

    public AfDescargosDetalle getTd() {
        return td;
    }

    public void setTd(AfDescargosDetalle td) {
        this.td = td;
    }

    public AfDescargos getTras() {
        return tras;
    }

    public void setTras(AfDescargos tras) {
        this.tras = tras;
    }

    public String getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(String numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

 

    public boolean isPnlTras() {
        return pnlTras;
    }

    public void setPnlTras(boolean pnlTras) {
        this.pnlTras = pnlTras;
    }

    public Long getIdBien() {
        return idBien;
    }

    public void setIdBien(Long idBien) {
        this.idBien = idBien;
    }

    public VwBienes getVb() {
        return Vb;
    }

    public void setVb(VwBienes Vb) {
        this.Vb = Vb;
    }



    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    
  

    public int getRowDrop() {
        return rowDrop;
    }

    public void setRowDrop(int rowDrop) {
        this.rowDrop = rowDrop;
    }

    public List<AfUnidadesAdministrativas> getLstUnidadAdmDes() {
        return lstUnidadAdmDes;
    }

    public void setLstUnidadAdmDes(List<AfUnidadesAdministrativas> lstUnidadAdmDes) {
        this.lstUnidadAdmDes = lstUnidadAdmDes;
    }

    public List<AfBienesDepreciables> getLstBienes() {
        return lstBienes;
    }

    public void setLstBienes(List<AfBienesDepreciables> lstBienes) {
        this.lstBienes = lstBienes;
    }

      
    public AfBienesDepreciables getBienes() {
        return Bienes;
    }

    public void setBienes(AfBienesDepreciables Bienes) {
        this.Bienes = Bienes;
    }

  
    public String getCodigoInv() {
        return codigoInv;
    }

    public void setCodigoInv(String codigoInv) {
        this.codigoInv = codigoInv;
    }

    public List<AfUnidadesAdministrativas> getLstUnidadAdm() {
        return cejb.getUnidadAdm(unidadAF, tipoUnidad);
    }

    public void setLstUnidadAdm(List<AfUnidadesAdministrativas> lstUnidadAdm) {
        this.lstUnidadAdm = lstUnidadAdm;
    }

    public boolean isPnlDest() {
        return pnlDest;
    }

    public void setPnlDest(boolean pnlDest) {
        this.pnlDest = pnlDest;
    }

    public String getUnidadAF() {
        return unidadAF;
    }

    public void setUnidadAF(String unidadAF) {
        this.unidadAF = unidadAF;
    }

      public String getUnidadAdm() {
        return unidadAdm;
    }

    public void setUnidadAdm(String unidadAdm) {
        this.unidadAdm = unidadAdm;
    }

    public List<VwDescargos> getListaDescargos() {
        return listaDescargos;
    }

    public void setListaDescargos(List<VwDescargos> listaDescargos) {
        this.listaDescargos = listaDescargos;
    }

    public List<AfDescargos> getLstDescargos() {
        return lstDescargos;
    }

    public void setLstDescargos(List<AfDescargos> lstDescargos) {
        this.lstDescargos = lstDescargos;
    }

  
    
    public List<AfBienesDepreciables> completarBienes(String query) {
        lstBienes = cejb.getBienesDescargo(unidadAdm, query,activo,  lstBientmp);
        return lstBienes;
    }

    public void filtrarUnidadesAdm(){
       lstUnidadAdm=cejb.getUnidadAdm(unidadAF,tipoUnidad);
    }
    
      public List<AfUnidadesActivoFijo> getLstUnidadAF(){
        return cejb.getUnidadAf();
    }
    
    public void bienesDescargar() {
        if (Bienes!=null){
            lstBienesDescargar.addAll(bejb.buscarBien(" id_bien="+Bienes.getIdBien())); //cejb.getBienesTrasladar());
            lstBientmp.add(Bienes.getIdBien());
            Bienes=null;
        }else{
           JsfUtil.mensajeError("Ingrese el bien a descargar");
        }
    }
    
    public void trasladosId(SelectEvent event) {
        lstDescargos  = bejb.getDescargoById(tras.getDescargoId());
    }
    
    public void descargoSeleccionado(SelectEvent event) {
       tras = bejb.getDescargos(idDescargo);
       numSolicitud = tras.getCodigoDescargo();
       fec1 = tras.getFechaDescargo();
       tipDescargo = tras.getIdTipoDescargo();
       
       lstBienesDescargar=bejb.buscarBien(" id_descargo="+idDescargo);
       
    }
    
     public void imprimirDescargo() throws IOException, JRException {
          List<JasperPrint> jasperPrintList = new ArrayList();
         HashMap param = new HashMap();
         param.put("p_codigoDescargo",tras.getDescargoId().toString());
        param.put("p_unidadAF",unidadAF);
        param.put("p_unidadAdm",unidadAdm);
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        param.put("p_RutaImg", ctx.getRealPath(PATH_IMAGENES));
        
        
         JasperPrint jp= reb.getRpt(param, DescargoController.class.getClassLoader().getResourceAsStream(("reportes" + File.separator + "rep_af10.jasper")));
       
        jasperPrintList.add(jp);
     
        UtilReport.generarReporte(jasperPrintList, "rptAF10");
    }   
   
     public void imprimirBienesDescargo() throws IOException, JRException {
        HashMap param = new HashMap();
        
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
        Calendar cal= Calendar.getInstance();
     
        String fecRep = formateador.format(cal.getTime());
        
        
   //     param.put("p_af",unidadAF);
   //     param.put("p_unidad",unidadAdm);
        param.put("p_usuario",usuDao.getLogin());
        param.put("p_fechaR",fecRep);
       
        
        UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(), bejb.getLst(unidadAF,unidadAdm, fecRep, lstBienesDescargar, usuDao.getLogin()), param, "rep_bienesDescargar.jasper");

        //  UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(),param, "rep_mobiliario.jasper", cejb.getEm());
    }
    
    public void buscarDescargos(){
        String condicion="where";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        if (!estSolicitud.equals("Y")){ condicion = condicion + " a.estado='"+estSolicitud.trim()+"' and ";}
        if(!numSolicitud.isEmpty()){ condicion=condicion +" a.codigo_descargo='"+numSolicitud.trim()+"' and "; }
        if (fec1!=null){ condicion=condicion+ " a.fecha_descargo>= '"+sdf.format(fec1)+"' and ";}
        if (fec2!=null){ condicion=condicion+ " a.fecha_descargo<= '"+sdf.format(fec2)+"' and ";}
        if (!activo.equals("0")){ condicion= condicion+ " trim(a.tipo_descargo)='"+activo+"' and "; }
        if (!tipoUnidad.equals("0")){ condicion=condicion+" a.tipo_unidad='"+tipoUnidad +"' and ";}
       if (!unidadAdm.equals("0")) { condicion=condicion+ " a.codigo_unidad="+unidadAdm+" and ";}
       if (!unidadAF.equals("0")) { condicion=condicion+ " a.unidad_activo_fijo="+unidadAF+" and ";}
        
        if (condicion.trim().equals("where")){condicion="";}
        else {   condicion =condicion.substring(0,condicion.length()-4);}
        
         listaDescargos = bejb.buscarDescargos(condicion);
    }
    
     public void buscarDescargos2(){
        String condicion=" a.codigo_descargo is not null and ";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
        if (!tipoUnidad.equals("0")){ condicion=condicion+" a.tipo_unidad='"+tipoUnidad +"' and ";}
        if (!unidadAdm.equals("0")) { condicion=condicion+ " a.codigo_unidad="+unidadAdm+" and ";}
        if (!unidadAF.equals("0")) { condicion=condicion+ " a.unidad_activo_fijo="+unidadAF+" and ";}
        if(!codigoInv.isEmpty()){ condicion=condicion +" a.codigo_inventario='"+codigoInv+"' and "; }
        if (!estSolicitud.equals("Y")){ condicion = condicion + " a.estado='"+estSolicitud.trim()+"' and ";}
        if (cat!=0){condicion = condicion + "a.id_cat_bien="+cat+" and ";}
         if(!numSolicitud.isEmpty()){ condicion=condicion +" a.codigo_descargo='"+numSolicitud.trim()+"' and "; }
     
        if (fec1!=null){ condicion=condicion+ " a.fecha_descargo>= '"+sdf.format(fec1)+"' and ";}
        if (fec2!=null){ condicion=condicion+ " a.fecha_descargo<= '"+sdf.format(fec2)+"' and ";}
        if (!activo.equals("X")){
           condicion= condicion+ " trim(a.tipo_descargo)='"+activo+"' and ";
        }
        if (!serie.isEmpty()){condicion = condicion +"a.numero_serie='"+serie+"' and "; }
         
         
        
        if (!condicion.trim().equals("")){   condicion =condicion.substring(0,condicion.length()-4);}
        
         lstBienesDescargar = bejb.buscarBien(condicion);
    }
    
     public void guardarDescargo(){
       if (!lstBienesDescargar.isEmpty()){
            if (tras.getDescargoId()==null){
                tras.setUnidadActivoFijo(unidadAF); 
                tras.setTipoDescargo(activo.charAt(0));
                tras.setIdTipoDescargo(tipDescargo); 
                tras.setEstado('S');
            }

                bejb.guardarDescargo(tras, usuDao.getLogin());


             tras.getDescargoId();
              if (tras.getDescargoId()!=null){

                  bejb.guardarDescargoDetalle(lstBienesDescargar,tras);
                  //bejb.actualizarEstadoBien(lstBienesDescargar,"S");
              }
              JsfUtil.mensajeInformacion("Solicitud Almacenada");
              JsfUtil.redireccionar("/app/mantenimientos/descargoBienes.mined?faces-redirect=true&idDescargo="+tras.getDescargoId());
       }else{
           JsfUtil.mensajeError("Descargo sin bienes asociados");
       }  
    }
     
    public void enviarDescargo(){
       
      if (tras.getDescargoId()!=null){
          tras.setEstado('P');
      }
        
          bejb.guardarDescargo(tras, usuDao.getLogin());
      
        if (tras.getDescargoId()!=null){
            
            bejb.guardarDescargoDetalle(lstBienesDescargar,tras);
            bejb.actualizarEstadoBien(lstBienesDescargar,"P");
        }
        pnlTras=true;
        JsfUtil.mensajeInformacion("Descargo enviado a Descargo");
       // JsfUtil.redireccionar("buscarDescargos.mined?faces-redirect=true");
    }
    
    
     public void realizarDescargo(){
  
        tras.setEstado('D');
        tras.setCodigoDescargo(numSolicitud);
        tras.setFechaDescargo(fecDescargo);
        tras.setNombreAutoriza(usuDao.getNombres()+" "+usuDao.getApellidos());
        tras.setFechaFallo(fecFallo);
        tras.setObservacionFallo(obsFallo);
                
        bejb.guardarDescargo(tras, usuDao.getLogin());
        tras.getDescargoId();
        if (tras.getDescargoId()!=null){
            bejb.actualizarEstadoBien(lstBienesDescargar,"D");
        }
        
       JsfUtil.redireccionar("buscarDescargos.mined?faces-redirect=true");
    }

      public void generarXls_reporteBienesDescargar() throws IOException {
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
        if (!unidadAdm.equals("0")){
           celdaEn2.setCellValue(cejb.NomUnidad(unidadAdm,tipoUnidad));
        }else{
           celdaEn2.setCellValue("");
       }
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 1, 5));
  
        
        fila = hoja.createRow((short) 3);
        Cell celdaEn3 = fila.createCell((short) 1);
        celdaEn3.setCellStyle(style);
        if (!unidadAF.equals("0")){
           celdaEn3.setCellValue(cejb.nomUnidadAf(unidadAF));
        }else{
           celdaEn3.setCellValue("");
       }   
        hoja.addMergedRegion(new CellRangeAddress(3, 3, 1, 5));

        fila = hoja.createRow((short) 5);
        Cell celdaEn4 = fila.createCell((short) 1);
        celdaEn4.setCellStyle(style);
        celdaEn4.setCellValue("LISTADO DE BIENES DESCARGADOS ");
        hoja.addMergedRegion(new CellRangeAddress(5, 5, 1, 15));

        
        fila = hoja.createRow((short) 7);
        Cell celdaEn5 = fila.createCell((short) 1);
        celdaEn5.setCellStyle(style);
        celdaEn5.setCellValue("INVENTARIO");
        
         Cell celdaEn6 = fila.createCell((short) 2);
        celdaEn6.setCellStyle(style);
        celdaEn6.setCellValue("CATEGORIA");

        Cell celdaEn7 = fila.createCell((short) 3);
        celdaEn7.setCellStyle(style);
        celdaEn7.setCellValue("DESCRIPCION");
        
        Cell celdaEn8 = fila.createCell((short) 4);
        celdaEn8.setCellStyle(style);
        celdaEn8.setCellValue("MARCA");
        
        Cell celdaEn9 = fila.createCell((short) 5);
        celdaEn9.setCellStyle(style);
        celdaEn9.setCellValue("MODELO");
        
        Cell celdaEn10 = fila.createCell((short) 6);
        celdaEn10.setCellStyle(style);
        celdaEn10.setCellValue("SERIE");
        
        Cell celdaEn11 = fila.createCell((short)7);
        celdaEn11.setCellStyle(style);
        celdaEn11.setCellValue("FECHA ADQUISICIÓN");
        
        Cell celdaEn12 = fila.createCell((short) 8);
        celdaEn12.setCellStyle(style);
        celdaEn12.setCellValue("COSTO ADQUISICIÓN");
        
        Cell celdaEn13 = fila.createCell((short) 9);
        celdaEn13.setCellStyle(style);
        celdaEn13.setCellValue(" FECHA DESCARGO");
        
        Cell celdaEn14 = fila.createCell((short) 10);
        celdaEn14.setCellStyle(style);
        celdaEn14.setCellValue("NUMERO ACTA");
        
        Cell celdaEn15 = fila.createCell((short) 11);
        celdaEn15.setCellStyle(style);
        celdaEn15.setCellValue("DEPRECIACION ACUMULADA");
     
        int x = 8;
        BigDecimal total= new java.math.BigDecimal("0.00");;
        DecimalFormat df = new DecimalFormat();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        df.setMaximumFractionDigits(2);
        for (VwBienes object : lstBienesDescargar ) {
            fila = hoja.createRow((short) x);

            Cell celda4 = fila.createCell((short) 1);
            celda4.setCellStyle(style2);
            celda4.setCellValue(object.getCodigoInventario());

            Cell celda5 = fila.createCell((short) 2);
            celda5.setCellStyle(style2);
            celda5.setCellValue(object.getCategoria());

            Cell celda6 = fila.createCell((short) 3);
            celda6.setCellStyle(style2);
            celda6.setCellValue(object.getDescripcionBien());

            Cell celda7 = fila.createCell((short) 4);
            celda7.setCellStyle(style2);
            celda7.setCellValue((object.getMarca()));

            Cell celda8 = fila.createCell((short) 5);
            celda8.setCellStyle(style2);
            celda8.setCellValue(object.getModelo());
            
            Cell celda9 = fila.createCell((short) 6);
            celda9.setCellStyle(style2);
            celda9.setCellValue(object.getNumeroSerie());
            
            Cell celda10 = fila.createCell((short) 7);
            celda10.setCellStyle(style2);
            Date getFechaAdq = object.getFechaAdquisicion();
            celda10.setCellValue(sdf.format(getFechaAdq));
            
            
            Cell celda11 = fila.createCell((short) 8);
            celda11.setCellStyle(style2);
            celda11.setCellType(celda11.CELL_TYPE_NUMERIC);
            celda11.setCellValue(object.getValorAdquisicion().doubleValue());
            
            
            Cell celda12 = fila.createCell((short) 9);
            celda12.setCellStyle(style2);
             Date getFechaDes = object.getFechaDescargo();
             if (getFechaDes!=null){  celda12.setCellValue("");}
             else {}
            
            Cell celda13 = fila.createCell((short) 10);
            celda13.setCellStyle(style2);
            celda13.setCellValue(object.getCodigoDescargo());
            
            total=total.add(object.getValorAdquisicion());
            
            Cell celda14 = fila.createCell((short) 11);
            celda14.setCellStyle(style2);
            celda14.setCellType(celda14.CELL_TYPE_NUMERIC);
            if (object.getMontoDepreciacion()!=null){
               celda14.setCellValue(object.getMontoDepreciacion().doubleValue());
            }else{
                celda14.setCellValue(0);
            }    
            x++;
        }
        fila = hoja.createRow((short) x);
        Cell celda13 = fila.createCell((short) 8);
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
        response.setHeader("Content-Disposition", "attachment;filename = ListadoBienesDescargar.xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        fc.responseComplete();
    }
     
     
     
       public void denegarDescargo(){
  
        tras.setEstado('X');
        tras.setFechaFallo(cejb.getFechaActual());
        tras.setObservacionFallo(observacion);
        tras.setNombreAutoriza(usuDao.getNombres()+" "+usuDao.getApellidos());
                
        bejb.guardarDescargo(tras, JsfUtil.getVariableSession("usuario").toString());
        tras.getDescargoId();
        if (tras.getDescargoId()!=null){
            bejb.actualizarEstadoBien(lstBienesDescargar,"D");
        }
        JsfUtil.mensajeInformacion("Descargo Realizado");
        
    }
    
    public void descargosId(SelectEvent event) {
        lstDescargos  = bejb.getDescargoById(tras.getDescargoId());
    }
    
    public List<VwBienes> dropBienesDescargado() {
         if(Vb.getDescargoId()==null){
            lstBienesDescargar.remove(rowDrop);
         } else{
             bejb.removeDetalleDescargo(Vb.getDescargoId(),idBien);
             lstBienesDescargar = bejb.buscarBien(" a. descargo_id="+Vb.getDescargoId());
         }   
          
        return lstBienesDescargar;
    }
    
  
    @FacesConverter(forClass = AfBienesDepreciables.class, value = "afBienesConverter")
    public static class AfBienesDepreciablesConverter implements Converter {

        @Override
        public AfBienesDepreciables getAsObject(FacesContext facesContext, UIComponent component, String string) {
            if (string == null || string.length() == 0) {
                return null;
            }
            Long id = new Long(string);
            DescargoController controller
                    = (DescargoController) facesContext.getApplication().getELResolver().
                            getValue(facesContext.getELContext(), null, "descargoController");
            return controller.getBejb().bienesId(id).get(0);
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AfBienesDepreciables) {
                AfBienesDepreciables o = (AfBienesDepreciables) object;
                return o.getIdBien() == null ? "" : o.getIdBien().toString();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: sv.gob.mined.activofijo.model.AfBienesDepreciables            ");
            }
        }
    }
    
    public void nuevoDescargo() {
        
        JsfUtil.redireccionar("/app/mantenimientos/descargoBienes.mined?faces-redirect=true");
    }
    public void ingresaDescargo() {
        JsfUtil.redireccionar("/app/mantenimientos/descargoBienes.mined?faces-redirect=true&idDescargo="+idDescargo);
    }
}
