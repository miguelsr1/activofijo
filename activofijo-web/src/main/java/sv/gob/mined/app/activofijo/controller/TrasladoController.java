/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.app.activofijo.controller;


import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import sv.gob.mined.activofijo.ejb.BienesEJB;
import sv.gob.mined.activofijo.ejb.CatalogosEJB;
import sv.gob.mined.activofijo.model.AfBienesDepreciables;
import sv.gob.mined.activofijo.model.AfTipoTraslados;
import sv.gob.mined.activofijo.model.AfTraslados;
import sv.gob.mined.activofijo.model.AfTrasladosDetalle;
import sv.gob.mined.activofijo.model.AfUnidadesActivoFijo;
import sv.gob.mined.activofijo.model.AfUnidadesAdministrativas;
import sv.gob.mined.activofijo.model.VwBienes;
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
public class TrasladoController implements Serializable {

    @EJB
    public CatalogosEJB cejb;
    @EJB
    public BienesEJB bejb;
    public AfTrasladosDetalle td = new AfTrasladosDetalle();
    public AfTraslados tras = new AfTraslados();
    public VwBienes Vb = new VwBienes();
    public Date fecTraslado;
    public Character tipTraslado;
    public List<AfTipoTraslados> lstTipoTraslado = new ArrayList<>();
    public List<AfTraslados> lstTraslados = new ArrayList<>();
    public List<AfTraslados> lstTdtmp = new ArrayList();
    public List<AfUnidadesActivoFijo> lstAFOrg = new ArrayList<>();
    public List<AfUnidadesActivoFijo> lstAFDes = new ArrayList<>();
    public List<AfUnidadesAdministrativas> lstUnidadAdm = new ArrayList<>();
    public List<AfUnidadesAdministrativas> lstUnidadAdmDes = new ArrayList<>();
    public List<AfBienesDepreciables> lstBienes = new ArrayList<>();
    public List<VwBienes> lstBienesTrasladar = new ArrayList<>();
    
    private int rowDrop = 0;
    public String unidadAdm;
    public String unidadAdmOrg;
    public String unidadAdmDes;
    public String unidadAFOrig;
    public String unidadAfDest;
    public String nombreAutoriza;
    public String nombreRecibe;
    public String observacion;
    public String cargoAutoriza;
    public String cargoRecibe;
    public String AdmDestino;
    public String unidadAF;
    private List<Long> lstBientmp = new ArrayList<>();
    public boolean pnlDest = false;
    public boolean pnlTras =true;
    public boolean activarCampos=true;
    public boolean btnGuardar =false;
    public boolean btnEnviar =true;
    public boolean btnAuto =true;
    public boolean btnImprime =true;
    public String tipUniOrg;
    public String tipUniDes;
    public String tipoUnidad="UA";
    public String tipoUnidad1;
    public String estado;   
    private String tipoUsu;
    public Long idBien;
    public Date fec1;
    public Date fec2;
    public String codigoInv;
    public String codigoTras;
    public Long idTraslado;
    private Usuario usuDao = new Usuario();
    public TrasladoController() {

    }

    public BienesEJB getBejb() {
        return bejb;
    }
    
    private DualListModel<VwBienes> bienes;
    List<VwBienes> bienesSource = new ArrayList<>();
    List<VwBienes> bienesTarget = new ArrayList<>();
   
    @PostConstruct
    public void cargarUnidad() {
        
         bienes = new DualListModel<>(bienesSource, bienesTarget);

         usuDao = ((LoginController) FacesContext.getCurrentInstance().getApplication().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "loginController")).getUsuario();
          tipoUsu = usuDao.getTipoUsuario().toString();
             
        unidadAdm = usuDao.getCodigoEntidad();
        unidadAF = cejb.getUnidadAf(usuDao.getCodigoEntidad(), tipoUnidad);  
        
         lstAFOrg =cejb.getUnidadAf();
         
         lstAFDes =cejb.getUnidadAf();
         activarCampos=false;
        lstTipoTraslado = cejb.getTipoTraslado();
        lstBientmp.add(0l);
         if (JsfUtil.getRequestParameter("idTraslado")!=null){
            activarCampos=true; 
            tras = bejb.getTraslado(new Long(JsfUtil.getRequestParameter("idTraslado")));
            lstBienesTrasladar = bejb.buscarBien(" a.id_traslado="+new Long(JsfUtil.getRequestParameter("idTraslado")));
            tipTraslado = tras.getIdTipoTraslado();
            
            
            
            unidadAFOrig = tras.getUnidadAfOrigen();
            unidadAdmOrg = tras.getCodigoUnidadOrigen();
            tipUniOrg = cejb.getTipoUnidad(unidadAdmOrg);
            lstUnidadAdm = cejb.getUnidadAdm(unidadAdmOrg, tipUniOrg);
            
            
            unidadAfDest = tras.getUnidadAfDest();
            unidadAdmDes = tras.getCodigoUnidadDest();
            tipUniDes = cejb.getTipoUnidad(unidadAdmDes);
            lstUnidadAdmDes = cejb.getUnidadAdm(unidadAfDest, tipUniDes);
            observacion= tras.getObservacion();
            
            if (tras.getEstado()=='0'){
                pnlTras = true;
                btnGuardar=false;
                btnEnviar=false;
                btnAuto=true;
                 btnImprime=true;
               
            }else{
                if (tras.getEstado()=='2'){
                 pnlTras = false;
                btnGuardar=true;
                btnEnviar=true;
                btnAuto=false;
                btnImprime=false;
                
               
                }
                else{
                    pnlTras = false;
                    btnGuardar=true;
                    btnEnviar=true;
                    btnAuto=true;
                    btnImprime=false;
                }
            }
            bienesTarget =lstBienesTrasladar; 
            buscarBienes();
         }
         
      }

    
     public DualListModel<VwBienes> getBienes() {
        return bienes;
    }

    public void setBienes(DualListModel<VwBienes> bienes) {
        this.bienes = bienes;
    }
    
    public List<AfTraslados> getLstTdtmp() {
        return lstTdtmp;
    }

    public void setLstTdtmp(List<AfTraslados> lstTdtmp) {
        this.lstTdtmp = lstTdtmp;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isBtnImprime() {
        return btnImprime;
    }

    public void setBtnImprime(boolean btnImprime) {
        this.btnImprime = btnImprime;
    }

    public boolean getActivarCampos() {
        return activarCampos;
    }

    public void setActivarCampos(boolean activarCampos) {
        this.activarCampos = activarCampos;
    }

    public Date getFec1() {
        return fec1;
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

    
    public String getUnidadAdmOrg() {
        return unidadAdmOrg;
    }

    public void setUnidadAdmOrg(String unidadAdmOrg) {
        this.unidadAdmOrg = unidadAdmOrg;
    }

    public String getUnidadAdmDes() {
        return unidadAdmDes;
    }

    public void setUnidadAdmDes(String unidadAdmDes) {
        this.unidadAdmDes = unidadAdmDes;
    }

    public String getUnidadAFOrig() {
        return unidadAFOrig;
    }

    public void setUnidadAFOrig(String unidadAFOrig) {
        this.unidadAFOrig = unidadAFOrig;
    }

    public String getTipUniOrg() {
        return tipUniOrg;
    }

    public void setTipUniOrg(String tipUniOrg) {
        this.tipUniOrg = tipUniOrg;
    }

    public List<AfUnidadesActivoFijo> getLstAFOrg() {
        return lstAFOrg;
    }

    public void setLstAFOrg(List<AfUnidadesActivoFijo> lstAFOrg) {
        this.lstAFOrg = lstAFOrg;
    }

    public List<AfUnidadesActivoFijo> getLstAFDes() {
        return lstAFDes;
    }

    public void setLstAFDes(List<AfUnidadesActivoFijo> lstAFDes) {
        this.lstAFDes = lstAFDes;
    }

    public List<Long> getLstBientmp() {
        return lstBientmp;
    }

    public void setLstBientmp(List<Long> lstBientmp) {
        this.lstBientmp = lstBientmp;
    }

    public String getTipUniDes() {
        return tipUniDes;
    }

    public void setTipUniDes(String tipUniDes) {
        this.tipUniDes = tipUniDes;
    }

    public String getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(String tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

    public boolean isBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(boolean btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public boolean isBtnEnviar() {
        return btnEnviar;
    }

    public void setBtnEnviar(boolean btnEnviar) {
        this.btnEnviar = btnEnviar;
    }

    public boolean isBtnAuto() {
        return btnAuto;
    }

    public void setBtnAuto(boolean btnAuto) {
        this.btnAuto = btnAuto;
    }

   public String getNombreUnidad(String codigo){
        return (cejb.nomUnidadAf(codigo)) ;   
   }
   public String getTipoTraslado(String codigo){
       return (cejb.tipoTraslado(codigo));
   }
   
   public String getNombreAdm(String codigo,String tipo){
       if (codigo.isEmpty() || tipo.isEmpty()){
           return null;
       }else {
          return cejb.NomUnidad(codigo,tipo);
       }   
   }
   
   public String getTipoUnidad(String codigo){
       return cejb.getTipoUnidad(codigo);
   }
   
    public String getNombreAutoriza() {
        return nombreAutoriza;
    }

    public void setNombreAutoriza(String nombreAutoriza) {
        this.nombreAutoriza = nombreAutoriza;
    }

    public String getNombreRecibe() {
        return nombreRecibe;
    }

    public void setNombreRecibe(String nombreRecibe) {
        this.nombreRecibe = nombreRecibe;
    }

    public String getCargoAutoriza() {
        return cargoAutoriza;
    }

    public void setCargoAutoriza(String cargoAutoriza) {
        this.cargoAutoriza = cargoAutoriza;
    }

    public String getCargoRecibe() {
        return cargoRecibe;
    }

    public void setCargoRecibe(String cargoRecibe) {
        this.cargoRecibe = cargoRecibe;
    }

    public void bienesId(SelectEvent event) {
        lstTraslados = bejb.getTrasladoById(tras.getIdTraslado());
    }
    
    public void filtrarTipotraslado() {
        lstTipoTraslado = cejb.getTipoTraslado();
    }

    public Date getFecTraslado() {
        return fecTraslado;
    }

    public void setFecTraslado(Date fecTraslado) {
        this.fecTraslado = fecTraslado;
    }

    public List<AfTraslados> getLstTraslados() {
        return lstTraslados;
    }

    public void setLstTraslados(List<AfTraslados> lstTraslados) {
        this.lstTraslados = lstTraslados;
    }

    public List<AfTipoTraslados> getLstTipoTraslado() {
        return lstTipoTraslado;
    }

    public void setLstTipoTraslado(List<AfTipoTraslados> lstTipoTraslado) {
        this.lstTipoTraslado = lstTipoTraslado;
    }

    public Long getIdTraslado() {
        return idTraslado;
    }

    public void setIdTraslado(Long idTraslado) {
        this.idTraslado = idTraslado;
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

    public String getUnidadAfDest() {
        return unidadAfDest;
    }

    public void setUnidadAfDest(String unidadAfDest) {
        this.unidadAfDest = unidadAfDest;
    }

    public String getCodigoTras() {
        return codigoTras;
    }

    public void setCodigoTras(String codigoTras) {
        this.codigoTras = codigoTras;
    }

    public String getTipoUnidad1() {
        return tipoUnidad1;
    }

    public void setTipoUnidad1(String tipoUnidad1) {
        this.tipoUnidad1 = tipoUnidad1;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    
    public AfTrasladosDetalle getTd() {
        return td;
    }

    public void setTd(AfTrasladosDetalle td) {
        this.td = td;
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

    public String getAdmDestino() {
        return AdmDestino;
    }

    public void setAdmDestino(String AdmDestino) {
        this.AdmDestino = AdmDestino;
    }

    public AfTraslados getTras() {
        return tras;
    }

    public void setTras(AfTraslados tras) {
        this.tras = tras;
    }

  

  
    public String getCodigoInv() {
        return codigoInv;
    }

    public void setCodigoInv(String codigoInv) {
        this.codigoInv = codigoInv;
    }

    public List<AfUnidadesAdministrativas> getLstUnidadAdm() {
        return cejb.getUnidadAdm(unidadAFOrig, tipUniOrg);
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

    public Character getTipTraslado() {
        return tipTraslado;
    }

    public void setTipTraslado(Character tipTraslado) {
        this.tipTraslado = tipTraslado;
    }

    public String getUnidadAdm() {
        return unidadAdm;
    }

    public void setUnidadAdm(String unidadAdm) {
        this.unidadAdm = unidadAdm;
    }
 public List<VwBienes> getLstBienesTrasladar() {
        return lstBienesTrasladar;
    }

    public void setLstBienesTrasladar(List<VwBienes> lstBienesTrasladar) {
        this.lstBienesTrasladar = lstBienesTrasladar;
    }

    public List<AfBienesDepreciables> completarBienes(String query) {
        lstBienes = cejb.getBienesTraslado(unidadAdmOrg, query,lstBientmp);
        return lstBienes;
    }
    

    
    public void filtrarUnidadesAdm(){
       lstUnidadAdm=cejb.getUnidadAdm(unidadAFOrig,tipUniOrg);
    }
    
    public void filtrarUnidadesAdmDestino(){
       lstUnidadAdmDes=cejb.getUnidadAdm(unidadAfDest,tipUniDes);
    }
    
    public List<AfUnidadesActivoFijo> getLstUnidadAF(){
        return cejb.getUnidadAf();
    }
    
     public void abrir() {
        buscarBienes();
        PrimeFaces.current().ajax().update("bienesPickList");
        btnGuardar=false;
    }
     
     public void abrirDialog() {
        if (!lstTdtmp.isEmpty()) {
            RequestContext.getCurrentInstance().execute("PF('dlg1').show()");
        } else {
            JsfUtil.mensajeError("Seleccione Traslado a Eliminar");
        }
    }
 
     
     public void eliminarTraslado() {
         if (!lstTdtmp.isEmpty()){
        for (AfTraslados td : lstTdtmp) {
            
            bejb.removeTraslado(td.getIdTraslado());
        }
        RequestContext.getCurrentInstance().execute("PF('dlg1').hide()");
        buscarTraslados();
        JsfUtil.mensajeEliminarTraslado();
         }else{
             
         }
    }
    
    public void trasladosId(SelectEvent event) {
        lstTraslados  = bejb.getTrasladoById(tras.getIdTraslado());
    }
    
    public void trasladoSeleccionado(SelectEvent event) {
       tras = bejb.getTraslado(idTraslado);
       lstBienesTrasladar=bejb.buscarBien(" a.id_traslado="+idTraslado);
    }
    
    public void buscarBienes() {
        String condicion;
        condicion = "A.ID_ESTATUS_BIEN=1 AND A.ID_BIEN NOT IN (SELECT ID_BIEN FROM AF_TRASLADOS_DETALLE) AND A.CODIGO_UNIDAD in (select CODIGO_UNIDAD from AF_UNIDADES_ADMINISTRATIVAS ";

        if (tipUniOrg.equals("0")) {
            condicion = condicion + ") and ";
        } else {
            condicion = condicion + " where TIPO_UNIDAD='" + tipUniOrg + "') and ";
        }
        if (!unidadAFOrig.equals("0")) { condicion=condicion+ " a.unidad_activo_fijo='"+unidadAFOrig+"' and ";}
        if (!unidadAdmOrg.equals("0")) { condicion=condicion+ " a.codigo_unidad='"+unidadAdmOrg+"' and ";}
        condicion = condicion.substring(0, condicion.length() - 4);

        bienesSource = bejb.buscarBien(condicion);

        bienes = new DualListModel<>(bienesSource, bienesTarget);
    }

      public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((VwBienes) item).getCodigoInventario()).append("<br />");
            lstBienesTrasladar.add(((VwBienes) item));
        }
    }

    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

    
    public void buscarTraslados(){
        String condicion="";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        if(!codigoTras.isEmpty() && codigoTras!=null){ condicion=condicion +" a.codigo_traslado='"+codigoTras+"' and "; }
        if (fec1!=null){ condicion=condicion+ " a.fecha_traslado>= to_date('"+sdf.format(fec1)+"','dd/mm/yyyy') and ";}
        if (fec2!=null){ condicion=condicion+ " a.fecha_traslado<= to_date('"+sdf.format(fec2)+"','dd/mm/yyyy') and ";}
       if (!unidadAFOrig.equals("0")) { condicion=condicion+ " a.unidad_af_origen='"+unidadAFOrig+"' and ";}
        if (!unidadAdmOrg.equals("0")) { condicion=condicion+ " a.codigo_unidad_origen='"+unidadAdmOrg+"' and ";}
        if (!unidadAfDest.equals("0")) { condicion=condicion+ " a.unidad_af_dest='"+unidadAfDest+"' and ";}
        if (!unidadAdmDes.equals("0")) { condicion=condicion+ " a.codigo_unidad_dest='"+unidadAdmDes+"' and ";}
        if (!estado.equals("3")){ condicion=condicion+ " a.estado='"+estado+"' and ";   }
        
        
        if (!condicion.equals("")){
            condicion=" where "+condicion;
            condicion =condicion.substring(0,condicion.length()-4);
        }
        
         lstTraslados = bejb.buscarTraslado(condicion);
    }
    
    public void limpiarFiltro(){
           tipUniOrg="UA";
           unidadAFOrig ="0";
           unidadAdmOrg="0";
           tipUniDes="UA";
           unidadAfDest="0";
           unidadAdmDes="0";
           codigoTras="";
           fecTraslado=null;
           idTraslado=null;
           
    }    
    
    /*public void imprimirBienes() throws IOException, JRException {
        HashMap param = new HashMap();

        UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(), bejb.getTras(idTraslado,lstTraslados, JsfUtil.getVariableSession("usuario").toString()), param, "rep_mobiliario.jasper");

        //  UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(),param, "rep_mobiliario.jasper", cejb.getEm());
    }*/
    public void enviarTraslado(){
        tras.setEstado('2');
        tras.setCargoAutoriza(cargoAutoriza);
        tras.setNombreAutoriza(nombreAutoriza);
        tras.setCargoRecibe(cargoRecibe);
        tras.setNombreRecibe(nombreRecibe);
        tras.setObservacion(observacion);
        bejb.guardarTraslado(tras, JsfUtil.getVariableSession("usuario").toString());
       
        JsfUtil.redireccionar("buscarTraslados.mined?faces-redirect=true");   
    }
    
    public void guardarTraslado(){
        if (!lstBienesTrasladar.isEmpty()){
          if (tras.getIdTraslado()==null){
            tras.setUnidadAfOrigen(unidadAFOrig);
            tras.setCodigoUnidadOrigen(unidadAdmOrg);
            tras.setIdTipoTraslado(tipTraslado);
            tras.setEstado('0');
        
            if (tipTraslado.equals('3')){
                tras.setUnidadAfDest(unidadAfDest);
                tras.setCodigoUnidadDest(unidadAdmDes);
                tras.setIdTipoTraslado(tipTraslado);
               
            }    
        }
            bejb.guardarTraslado(tras, JsfUtil.getVariableSession("usuario").toString());
            tras.getIdTraslado();
          if (tras.getIdTraslado()!=null){
                bejb.guardarTrasladoDetalle(lstBienesTrasladar,tras);
            }
          JsfUtil.mensajeAlerta("Traslado Almacenado");
          JsfUtil.redireccionar("/app/mantenimientos/trasladoBienes.mined?faces-redirect=true&idTraslado="+tras.getIdTraslado());
          
        }
        else{
           JsfUtil.mensajeError("Traslado sin bienes asociados");
        }
    }

    public List<VwBienes> dropBienesTrasladar() {
         if(Vb.getIdTraslado()==null){
            lstBienesTrasladar.remove(rowDrop);
         } else{
             bejb.removeDetalleTraslado(Vb.getIdTraslado(),idBien);
             lstBienesTrasladar = bejb.buscarBien(" a. id_traslado="+Vb.getIdTraslado());
         }   
          
        return lstBienesTrasladar;
    }
    
    public void autorizarTraslado() {
        tras.setEstado('1');
        tras.setObservacion(observacion);
        bejb.guardarTraslado(tras, usuDao.getLogin());
        tras.getIdTraslado();
       if (tras.getIdTraslado()!=null){
            bejb.actualizarBienTraslado(lstBienesTrasladar,tras.getCodigoUnidadDest(),tras.getUnidadAfDest());
        }
        
       JsfUtil.redireccionar("buscarTraslados.mined?faces-redirect=true");
        
        
    }

    public void imprimirTraslado() throws IOException, JRException {
        HashMap param = new HashMap();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));

        Date fecRep = cejb.getFechaActual();
        
        param.put("CodUnidadDes",tras.getCodigoUnidadDest());
        param.put("UnidadDes",getNombreAdm(tras.getCodigoUnidadDest(),tipUniDes));
        param.put("codigoTraslado",tras.getCodigoTraslado());
        param.put("fecSolicitud", formateador.format(tras.getFechaSolicitud()));
        param.put("nomAutoriza",tras.getNombreAutoriza());
        param.put("nomRecibe",tras.getNombreRecibe());
        param.put("tipoTraslado",getTipoTraslado(tras.getIdTipoTraslado().toString()));
        param.put("cargoAutoriza",tras.getCargoAutoriza());
        param.put("cargoRecibe",tras.getCargoRecibe());
        param.put("Observa",tras.getObservacion());
        
                
        UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(), bejb.getLst(unidadAFOrig,unidadAdmOrg, formateador.format(fecRep), lstBienesTrasladar, usuDao.getLogin()), param, "rep_af9.jasper");

        //  UtilReport.rptGenerico((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(),param, "rep_mobiliario.jasper", cejb.getEm());
    }
    

    public void visualizarPnl() {
        if(tipTraslado.equals('3')){
            pnlDest = true;
        }else{
            pnlDest = false;
        }    
    }

      public void nuevoTraslado() {
        
        JsfUtil.redireccionar("/app/mantenimientos/trasladoBienes.mined?faces-redirect=true");
    }
    public void ingresaTraslado() {
        JsfUtil.redireccionar("/app/mantenimientos/trasladoBienes.mined?faces-redirect=true&idTraslado="+idTraslado);
    }
}
