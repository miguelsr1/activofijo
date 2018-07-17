/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.web.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.gob.mined.seguridad.ejb.SeguridadBean;
import sv.gob.mined.seguridad.model.Aplicacion;
import sv.gob.mined.seguridad.web.util.JsfUtil;

/**
 *
 * @author misanchez
 */
@ManagedBean
@ViewScoped
public class AplicacionController implements Serializable{

    private Boolean deshabilitar = true;
    private Aplicacion aplicacion;
    private List<Aplicacion> lstAplicacion = new ArrayList<Aplicacion>();
    @EJB
    private SeguridadBean seguridadBean;

    /**
     * Creates a new instance of AplicacionController
     */
    public AplicacionController() {
    }

    @PostConstruct
    public void init() {
        lstAplicacion = seguridadBean.getLstAplicacion();
    }

    public Boolean getDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(Boolean deshabilitar) {
        this.deshabilitar = deshabilitar;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        if (aplicacion != null) {
            this.aplicacion = aplicacion;
        }
    }

    public List<Aplicacion> getLstAplicacion() {
        return lstAplicacion;
    }

    public void setLstAplicacion(List<Aplicacion> lstAplicacion) {
        this.lstAplicacion = lstAplicacion;
    }

    public void editarAplicacion() {
        deshabilitar = false;
    }

    public void guardar() {
        if (aplicacion.getIdAplicacion() == null) {
            if (seguridadBean.guardar(aplicacion)) {
                JsfUtil.mensajeError("Error en la operación realizada");
            } else {
                lstAplicacion = seguridadBean.getLstAplicacion();
                JsfUtil.mensajeInsert();
            }
        } else {
            if (seguridadBean.actualizar(aplicacion)) {
                JsfUtil.mensajeError("Error en la operación realizada");
            } else {
                lstAplicacion = seguridadBean.getLstAplicacion();
                JsfUtil.mensajeInsert();
            }
        }
    }

    public void prepareCreate() {
        aplicacion = new Aplicacion();
        deshabilitar = false;
    }
}