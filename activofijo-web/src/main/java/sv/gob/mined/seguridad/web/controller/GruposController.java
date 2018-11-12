/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.web.controller;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;
import sv.gob.mined.seguridad.ejb.SeguridadBean;
import sv.gob.mined.seguridad.model.Aplicacion;
import sv.gob.mined.seguridad.model.AplicacionOpcMenu;
import sv.gob.mined.seguridad.model.Grupo;
import sv.gob.mined.seguridad.model.GrupoAplicacion;
import sv.gob.mined.seguridad.model.OpcionMenu;
import sv.gob.mined.seguridad.model.Usuario;
import sv.gob.mined.seguridad.model.UsuarioAplicacion;
import sv.gob.mined.seguridad.model.UsuarioGrupoAplicacion;
import sv.gob.mined.seguridad.web.util.JsfUtil;

/**
 *
 * @author misanchez
 */
@ManagedBean(name = "gruposController")
@ViewScoped
public class GruposController implements Serializable {

    private Boolean deshabilitar = true;
    private Boolean deshabilitarDepartamento = true;
    private Aplicacion aplicacion;
    private Grupo grupo = new Grupo();
    private Usuario usuario = new Usuario();
    private UsuarioAplicacion usuarioApp = new UsuarioAplicacion();
    private GrupoAplicacion grupoAplicacion;
    private GrupoAplicacion grupoApp = new GrupoAplicacion();
    private TreeNode root = new DefaultTreeNode();
    private List<Aplicacion> lstAplicacion = new ArrayList();
    private List<Grupo> lstGrupo = new ArrayList();
    private List<GrupoAplicacion> lstGrupoAplicacion = new ArrayList();
    private List<Grupo> lstGruAppSource = new ArrayList();
    private List<Grupo> lstGruAppTarget = new ArrayList();
    private List<UsuarioGrupoAplicacion> lstUsuarioGrupoApp = new ArrayList();
    private DualListModel<Grupo> modelGrupo = new DualListModel();
    private TreeNode[] selectedOpcion;
    @EJB
    private SeguridadBean seguridadBean;

    /**
     * Creates a new instance of GruposController
     */
    public GruposController() {
    }

    @PostConstruct
    public void init() {
        usuarioApp.setCodigoDepartamento("00");
        lstGrupo = seguridadBean.getLstGrupo();
        lstAplicacion = seguridadBean.getLstAplicacion();
    }

    public void actualizarModel() {
        if (aplicacion != null && aplicacion.getIdAplicacion() != null) {
            lstGruAppSource = seguridadBean.getLstGruAppNotApp(aplicacion);
            lstGruAppTarget = seguridadBean.getLstGruAppByApp(aplicacion);
        } else {
            lstGruAppSource.clear();
            lstGruAppTarget.clear();
        }
        modelGrupo = new DualListModel(lstGruAppSource, lstGruAppTarget);
    }

    public void updGrupoApp() {
        lstGrupoAplicacion = seguridadBean.getLstGrupoAplicacionByApp(aplicacion.getIdAplicacion());
    }

    public void updUsuarioApp() {
        usuarioApp = seguridadBean.getUsuarioApp(usuario, aplicacion);
        updGrupoApp();
    }

    public void updGruposAppByUsuario() {
        seguridadBean.getUsuarioGrupoAplicacion(usuario, aplicacion, grupoApp, usuarioApp);
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    public DualListModel<Grupo> getModelGrupo() {
        return modelGrupo;
    }

    public void setModelGrupo(DualListModel<Grupo> modelGrupo) {
        this.modelGrupo = modelGrupo;
    }

    public Boolean getDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(Boolean deshabilitar) {
        this.deshabilitar = deshabilitar;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        if (grupo != null) {
            this.grupo = grupo;
        }
    }

    public GrupoAplicacion getGrupoAplicacion() {
        return grupoAplicacion;
    }

    public void setGrupoAplicacion(GrupoAplicacion grupoAplicacion) {
        this.grupoAplicacion = grupoAplicacion;
    }

    public List<Aplicacion> getLstAplicacion() {
        return lstAplicacion;
    }

    public void setLstAplicacion(List<Aplicacion> lstAplicacion) {
        this.lstAplicacion = lstAplicacion;
    }

    public List<Grupo> getLstGrupo() {
        return lstGrupo;
    }

    public void setLstGrupo(List<Grupo> lstGrupo) {
        this.lstGrupo = lstGrupo;
    }

    public List<GrupoAplicacion> getLstGrupoAplicacion() {
        return lstGrupoAplicacion;
    }

    public void setLstGrupoAplicacion(List<GrupoAplicacion> lstGrupoAplicacion) {
        this.lstGrupoAplicacion = lstGrupoAplicacion;
    }

    public void guardarGrupo() {
        if (grupo.getIdGrupo() == null) {
            if (seguridadBean.guardar(grupo)) {
                JsfUtil.mensajeError("Error en la operación realizada");
            } else {
                lstGrupo = seguridadBean.getLstGrupo();
                JsfUtil.mensajeInsert();
            }
        } else {
            if (seguridadBean.actualizar(grupo)) {
                JsfUtil.mensajeError("Error en la operación realizada");
            } else {
                lstGrupo = seguridadBean.getLstGrupo();
                JsfUtil.mensajeUpdate();
            }
        }
    }

    public void prepareCreate() {
        grupo = new Grupo();
        deshabilitar = false;
    }

    public void editarGrupo() {
        deshabilitar = false;
    }

    public void onTransfer(TransferEvent event) {
        List lst = event.getItems();
        HashMap<String, String> value = new HashMap();
        if (event.isAdd()) {
            for (Object object : lst) {
                GrupoAplicacion gp = new GrupoAplicacion();
                gp.setIdGrupo(seguridadBean.find(Grupo.class, new BigDecimal(object.toString())));
                gp.setIdAplicacion(aplicacion);
                seguridadBean.guardar(gp);
            }
        } else if (event.isRemove()) {
            for (Object object : lst) {
                value = seguridadBean.removerGrupoApp(new BigDecimal(object.toString()), aplicacion.getIdAplicacion());
                if (!value.isEmpty()) {
                    JsfUtil.mensajeError(value.get("error"));
                    actualizarModel();
                }
            }
        }
        if (value.isEmpty()) {
            JsfUtil.mensajeUpdate();
        }
    }

    public TreeNode getRoot() {
        root = new DefaultTreeNode();

        List<AplicacionOpcMenu> lstAplicacionOpcMenu = seguridadBean.getLstAplicacionOpcMenu(aplicacion, grupoAplicacion);

        for (AplicacionOpcMenu aplicacionOpcMenu : lstAplicacionOpcMenu) {
            TreeNode nodo = new DefaultTreeNode(aplicacionOpcMenu.getIdOpcMenu(), root);
            List<AplicacionOpcMenu> lstAplicacionOpcMenuHijos = seguridadBean.getLstAplicacionOpcMenuHijos(aplicacion, grupoAplicacion, aplicacionOpcMenu.getIdOpcMenu());
            for (AplicacionOpcMenu aplicacionOpcMenu1 : lstAplicacionOpcMenuHijos) {
                TreeNode nodo2 = new DefaultTreeNode(aplicacionOpcMenu1.getIdOpcMenu(), nodo);
            }
        }
        for (TreeNode hoja : root.getChildren()) {
            hoja.setExpanded(true);
        }

        return root;
    }

    public void setArbolMenuOpc(TreeNode root, AplicacionOpcMenu aom) {
        for (AplicacionOpcMenu aplicacionOpcMenu : aom.getIdOpcMenu().getAplicacionOpcMenuList()) {
            TreeNode nodo = new DefaultTreeNode(aplicacionOpcMenu.getIdOpcMenu().getNombreOpcion(), aplicacionOpcMenu.getIdOpcMenu().getUrl(), root);
            if (aplicacionOpcMenu.getIdOpcMenu().getIdOpcMenuPadre() != null) {
                setArbolMenuOpc(root, aom);
            }
        }
    }

    public void opcionesMenu() {
        Map<String, Object> options = new HashMap();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 400);
        options.put("contentWidth", 800);
        RequestContext.getCurrentInstance().openDialog("/app/mantto/lstOpcionesMenu", options, null);
    }

    public void addOpcionMenuApp(SelectEvent event) {
        if (event != null && event.getObject() instanceof OpcionMenu) {
            OpcionMenu opcionMenu = (OpcionMenu) event.getObject();
            AplicacionOpcMenu aplicacionOpcMenu = new AplicacionOpcMenu();
            //aplicacionOpcMenu.setIdAplicacion(aplicacion);
            aplicacionOpcMenu.setIdGrupoApp(grupoAplicacion);
            aplicacionOpcMenu.setIdOpcMenu(opcionMenu);

            if (seguridadBean.existeOpcionMenuGrupo(aplicacionOpcMenu)) {
                JsfUtil.mensajeAlerta("Esta opción ya existe en el grupo: " + grupoAplicacion.getIdGrupo().getNombreGrupo());
            } else {
                seguridadBean.guardarAppMenuOpc(aplicacionOpcMenu);
            }
        }
    }

    public void guardarAppOpcMenu() {
        for (TreeNode node : selectedOpcion) {
            System.out.println(node.getData());
        }
    }

    public void udpUsuarioAppGrupo() {
        if (usuario.getLogin() != null && !usuario.getLogin().isEmpty()) {
            deshabilitar = false;
            deshabilitarDepartamento = usuario.getTipoUsuario().equals("C");
            if (deshabilitarDepartamento) {
                usuarioApp.setCodigoDepartamento(seguridadBean.getEntidadEducativa(usuario.getCodigoEntidad()).getCodigoDepartamento());
            }
        }
    }

    public TreeNode[] getSelectedOpcion() {
        return selectedOpcion;
    }

    public void setSelectedOpcion(TreeNode[] selectedOpcion) {
        this.selectedOpcion = selectedOpcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<UsuarioGrupoAplicacion> getLstUsuarioGrupoApp() {
        return lstUsuarioGrupoApp;
    }

    public void setLstUsuarioGrupoApp(List<UsuarioGrupoAplicacion> lstUsuarioGrupoApp) {
        this.lstUsuarioGrupoApp = lstUsuarioGrupoApp;
    }

    public GrupoAplicacion getGrupoApp() {
        return grupoApp;
    }

    public void setGrupoApp(GrupoAplicacion grupoApp) {
        this.grupoApp = grupoApp;
    }

    public UsuarioAplicacion getUsuarioApp() {
        return usuarioApp;
    }

    public void setUsuarioApp(UsuarioAplicacion usuarioApp) {
        this.usuarioApp = usuarioApp;
    }

    public Boolean getDeshabilitarDepartamento() {
        return deshabilitarDepartamento;
    }

    public void setDeshabilitarDepartamento(Boolean deshabilitarDepartamento) {
        this.deshabilitarDepartamento = deshabilitarDepartamento;
    }
}
