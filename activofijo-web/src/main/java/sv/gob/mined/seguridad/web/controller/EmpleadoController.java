/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.web.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import sv.gob.mined.seguridad.ejb.SeguridadBean;
import sv.gob.mined.seguridad.model.rhevaluacion.Empleado;
import sv.gob.mined.seguridad.web.util.JsfUtil;

/**
 *
 * @author misanchez
 */
@ManagedBean(name = "empleadoController")
@ViewScoped
public class EmpleadoController implements Serializable {

    private String nombre;
    private String codEmp;
    private String apellido;
    private Empleado empleado = new Empleado();
    private List<Empleado> lstEmp = new ArrayList<Empleado>();
    @EJB
    private SeguridadBean seguridadBean;

    /**
     * Creates a new instance of EmpleadoController
     */
    public EmpleadoController() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(String codEmp) {
        this.codEmp = codEmp;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        if (empleado != null) {
            this.empleado = empleado;
        }
    }

    public List<Empleado> getLstEmp() {
        return lstEmp;
    }

    public void setLstEmp(List<Empleado> lstEmp) {
        this.lstEmp = lstEmp;
    }

    public void datosEmpleado() {
        lstEmp = seguridadBean.lstEmpleados(codEmp, nombre, apellido);
        if(lstEmp.isEmpty()){
            JsfUtil.mensajeInformacion("No se encontrarón registros con los valores de busqueda ingresados");
        }
    }
    
    public void guardarOpcMenuApp(){
        RequestContext.getCurrentInstance().closeDialog(empleado);
    }
    
    public void cerrarFiltroOpcMenuApp() {
        RequestContext.getCurrentInstance().closeDialog("/app/manttos/lstEmpleados");
    }
}