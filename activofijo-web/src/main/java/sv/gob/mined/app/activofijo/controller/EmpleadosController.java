package sv.gob.mined.app.activofijo.controller;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import sv.gob.mined.activofijo.ejb.BienesEJB;
import sv.gob.mined.activofijo.ejb.CatalogosEJB;
import sv.gob.mined.activofijo.model.AfEmpleados;

import sv.gob.mined.activofijo.model.AfUnidadesActivoFijo;
import sv.gob.mined.activofijo.model.AfUnidadesAdministrativas;
import sv.gob.mined.app.activofijo.util.JsfUtil;
import sv.gob.mined.seguridad.model.Usuario;
import sv.gob.mined.seguridad.web.controller.LoginController;

/**
 *
 * @author JISTorres
 */
@ManagedBean
@ViewScoped
public class EmpleadosController implements Serializable {

    @EJB
    public CatalogosEJB cejb;
    @EJB
    public BienesEJB bejb;
    //public List<AfUnidadesAdministrativas> lstUnidadAdm = new ArrayList<AfUnidadesAdministrativas>();
    public AfEmpleados emp= new AfEmpleados();
    
    private int rowDrop = 0;
    public String unidadAdm;
    public String nombres;
    public String apellidos;
    public String cargo;
    public String unidadAF;
    public String tipoUnidad="UA";
    private String tipoUsu;
    private Long idEmpleado;
    private boolean actAF=false;
    private boolean actAd=false;
    private Usuario usuDao = new Usuario();
    private List<AfEmpleados> lstEmpleados = new ArrayList<>();
    private List<AfUnidadesAdministrativas> lstUnidadAdm = new ArrayList();
    public EmpleadosController() {

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
              actAF = false;
              actAd=false;
              tipoUnidad="UA";
            
          }else {
            if (tipoUsu.equals("D")){
                actAF = true;
                actAd=false;
                tipoUnidad="UA";
            }else{
                if (tipoUsu.equals("A")){
                    actAF = true;
                    actAd=true;
                    tipoUnidad="UA";
                }
                else{
                    actAF = true;
                    actAd=true;
                    tipoUnidad="CE";
                }
          }
        }
        unidadAdm = usuDao.getCodigoEntidad();
        unidadAF = cejb.getUnidadAf(usuDao.getCodigoEntidad(), tipoUnidad); 
        lstUnidadAdm = cejb.getUnidadAdm(unidadAF, tipoUnidad);
        
        if (JsfUtil.getRequestParameter("idEmpleado")!=null){ 
            emp= bejb.getEmpAdmin(JsfUtil.getRequestParameter("idEmpleado"));
            unidadAF =emp.getUnidadActivoFijo();
            unidadAdm = emp.getCodigoUnidad();
            cargo=emp.getCargo();
            nombres =emp.getNombres();
            apellidos=emp.getApellidos();
        }
      
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

  
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(String tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

  

   public String getNombreUnidad(String codigo){
        return (cejb.nomUnidadAf(codigo)) ;   
   }
   
   public String getTipoTraslado(String codigo){
       return (cejb.tipoTraslado(codigo));
   }
   
   public String getNombreAdm(String codigo,String tipo){
          return cejb.NomUnidad(codigo,tipo);
   }
   
   public String getTipoUnidad(String codigo){
       return cejb.getTipoUnidad(codigo);
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

  
 public List<AfUnidadesActivoFijo> getLstUnidadAF() {
        return cejb.getUnidadAf();
    }

    public List<AfUnidadesAdministrativas> getLstUnidadAdm() {
        //   activo=true;
        return lstUnidadAdm;
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

    public void filtrarUnidadesAdm(){
       lstUnidadAdm=cejb.getUnidadAdm(unidadAF,tipoUnidad);
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void empleadoId(SelectEvent event) {
        lstEmpleados = bejb.empId(emp.getIdEmpleado());
    }
    public List<AfEmpleados> getLstEmpleados() {
        return lstEmpleados;
    }

    public void setLstEmpleados(List<AfEmpleados> lstEmpleados) {
        this.lstEmpleados = lstEmpleados;
    }
    
    
    public void buscarEmpleados(){
        String condicion="";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
         if (!unidadAF.equals("0")) { condicion=condicion+ " a.unidad_activo_fijo='"+unidadAF+"' and ";}
        if (!unidadAdm.equals("0")) { condicion=condicion+ " a.codigo_unidad='"+unidadAdm+"' and ";}
        if (!cargo.isEmpty() && !cargo.equals("")) { condicion=condicion+ " a.cargo='"+cargo+"' and ";}
        if (!nombres.isEmpty() && !nombres.equals("")) { condicion=condicion+ " a.nombres like '%"+nombres+"%' and ";}
        if (!apellidos.isEmpty() && !apellidos.equals("")) { condicion=condicion+ " a.apellidos like '%"+apellidos+"%' and ";}
        if (!condicion.equals("")){
            condicion =condicion.substring(0,condicion.length()-4);
        }
        
        lstEmpleados = bejb.buscarEmpleados(condicion);
    }
    
    public void limpiarFiltro(){
        nombres="";
        apellidos="";
        cargo="";
        
           
    }    
    public String obtenerNombreUnidad(String codigo){
        return cejb.NomUnidad(codigo,"UA");
    }
    
  
    
    public void guardarEmpleado(){
     

         emp.setUnidadActivoFijo(unidadAF);
         emp.setCodigoUnidad(unidadAdm);
         emp.setNombres(nombres);
         emp.setApellidos(apellidos);
         emp.setCargo(cargo);
         bejb.guardarEmpleado(emp, JsfUtil.getVariableSession("usuario").toString());

         JsfUtil.redireccionar("/app/manttoAf/EmpleadosAdministrativos.mined?faces-redirect=true");
       //   JsfUtil.redireccionar("buscarTrasladosCE.mined?faces-redirect=true");
     }
        
     public void nuevoEmpleado() {
       
        JsfUtil.redireccionar("/app/manttoAf/NuevosEmpleados.mined?faces-redirect=true");
    }
    public void editarEmpleado() {
        JsfUtil.redireccionar("/app/manttoAf/NuevosEmpleados.mined?faces-redirect=true&idEmpleado="+idEmpleado);
    }
}
