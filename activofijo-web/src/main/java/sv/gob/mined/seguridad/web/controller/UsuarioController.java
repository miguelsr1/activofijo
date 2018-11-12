/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import sv.gob.mined.seguridad.ejb.SeguridadBean;
import sv.gob.mined.seguridad.model.Aplicacion;
import sv.gob.mined.seguridad.model.Grupo;
import sv.gob.mined.seguridad.model.Usuario;
import sv.gob.mined.seguridad.model.rhevaluacion.Empleado;
import sv.gob.mined.seguridad.model.view.VwCatalogoEntidadEducativa;
import sv.gob.mined.seguridad.web.util.JsfUtil;

/**
 *
 * @author misanchez
 */
@ManagedBean(name = "usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

    private String nombres = "";
    private String apellidos = "";
    private String correoElectronico = "";
    private String codigoEmpleado = "";
    private String codigoEntidad = "";
    private Empleado empleado = new Empleado();
    private Boolean deshabilitar = true;
    private Boolean modifUsuario = false;
    private Usuario usuario = new Usuario();
    private VwCatalogoEntidadEducativa entidadEducativa = new VwCatalogoEntidadEducativa();
    private List<Usuario> lstUsuarios = new ArrayList<Usuario>();
    private List<Aplicacion> lstAppSource = new ArrayList<Aplicacion>();
    private List<Aplicacion> lstAppTarget = new ArrayList<Aplicacion>();
    private DualListModel<Grupo> modelGrupo = new DualListModel();
    @EJB
    private SeguridadBean seguridadBean;

    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
    }

    @PostConstruct
    public void init() {
        lstUsuarios = seguridadBean.getLstUsuarios();
        usuario.setTipoUsuario('I');
        usuario.setCambiarClave('N');
        usuario.setActivo('A');
    }

    public DualListModel<Grupo> getModelGrupo() {
        return modelGrupo;
    }

    public void setModelGrupo(DualListModel<Grupo> modelGrupo) {
        this.modelGrupo = modelGrupo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if (usuario != null) {
            this.usuario = usuario;
        }
    }

    public Boolean getModifUsuario() {
        return modifUsuario;
    }

    public void setModifUsuario(Boolean modifUsuario) {
        this.modifUsuario = modifUsuario;
    }

    public Boolean getDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(Boolean deshabilitar) {
        this.deshabilitar = deshabilitar;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String prepareCreate() {
        usuario = new Usuario();
        deshabilitar = false;
        modifUsuario = false;
        return "";
    }

    public void guardar() {
        boolean validarUsuario;

        if (modifUsuario) {
            validarUsuario = true;
        } else {
            validarUsuario = seguridadBean.find(Usuario.class, usuario.getLogin()) == null;
        }

        if (validarUsuario) {
            if(usuario.getTipoUsuario().equals("C")){
                usuario.setCodigoEntidad(codigoEntidad);
            }
            usuario.setLogin(usuario.getLogin().toUpperCase());
            usuario.setNombres(nombres);
            usuario.setApellidos(apellidos);
            usuario.setCodigoEmpleado((codigoEmpleado == null) ? null : (codigoEmpleado.trim().isEmpty() ? null : codigoEmpleado));
            usuario.setCorreElectronico(correoElectronico.trim().isEmpty() ? null : correoElectronico);
            seguridadBean.guardarUsuario(usuario, modifUsuario);

            if (modifUsuario) {
                JsfUtil.mensajeUpdate();
            } else {
                JsfUtil.mensajeInsert();
            }

            lstUsuarios = seguridadBean.getLstUsuarios();
            usuario = new Usuario();
            usuario.setTipoUsuario('I');
            modifUsuario = false;
            correoElectronico = "";
            codigoEmpleado = "";
            nombres = "";
            apellidos = "";
            codigoEmpleado = "";
        } else {
            JsfUtil.mensajeAlerta("Ya existe un usuario: " + usuario.getLogin());
        }
    }

    public void findEmpleados() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentHeight", 400);
        options.put("contentWidth", 800);
        RequestContext.getCurrentInstance().openDialog("/app/mantto/lstEmpleados", options, null);
    }

    public void selectEmpleado(SelectEvent event) {
        if (event != null) {
            empleado = (Empleado) event.getObject();
            usuario.setNombres(empleado.getNombreEmpleado());
            usuario.setApellidos(empleado.getApellidoEmpleado());
            usuario.setDui(empleado.getDui());
            usuario.setCorreElectronico(empleado.getCorreo());
            usuario.setCodigoEmpleado(empleado.getCodigoEmpleado());
            usuario.setSexo(empleado.getSexo());

            nombres = empleado.getNombreEmpleado();
            apellidos = empleado.getApellidoEmpleado();
            codigoEmpleado = empleado.getCodigoEmpleado();
            correoElectronico = empleado.getCorreo();
        }
    }

    public void actualizarModel() {
        lstAppSource = seguridadBean.getLstAppNotUsu(usuario);
        lstAppTarget = seguridadBean.getLstAppByUsu(usuario);
        modelGrupo = new DualListModel(lstAppSource, lstAppTarget);
    }

    public List<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(List<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public void editarUsuario() {
        deshabilitar = false;
        nombres = usuario.getNombres();
        apellidos = usuario.getApellidos();
        correoElectronico = usuario.getCorreElectronico();
        codigoEmpleado = usuario.getCodigoEmpleado();
        codigoEntidad = usuario.getCodigoEntidad();
        modifUsuario = true;
    }

    public void buscarEntidadEducativa() {
        if (codigoEntidad.length() == 5) {
            entidadEducativa = seguridadBean.getEntidadEducativa(codigoEntidad);
            if (entidadEducativa == null) {
                JsfUtil.mostarMensaje(FacesMessage.SEVERITY_WARN, "Alerta", "No se ha encontrado el centro escolar con código: " + getCodigoEntidad());
            }
        }
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public VwCatalogoEntidadEducativa getEntidadEducativa() {
        return entidadEducativa;
    }

    public void setEntidadEducativa(VwCatalogoEntidadEducativa entidadEducativa) {
        this.entidadEducativa = entidadEducativa;
    }
}
