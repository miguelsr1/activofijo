/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.web.controller;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import sv.gob.mined.seguridad.ejb.SeguridadBean;
import sv.gob.mined.seguridad.model.OpcionMenu;
import sv.gob.mined.seguridad.model.Usuario;
import sv.gob.mined.seguridad.web.util.JsfUtil;
import sv.gob.mined.seguridad.web.util.VarSession;

/**
 *
 * @author misanchez
 */
@ManagedBean
@ViewScoped
public class OpcionMenuController implements Serializable {
    
    private String idApp = "";
    private String usuario = "";
    private String codigoDepartamento = "";
    private String codigoEntidad = "";
    private Boolean deshabilitar = true;
    private OpcionMenu opcionMenu = new OpcionMenu();
    private List<OpcionMenu> lstOpcionMenu = new ArrayList();
    @EJB
    private SeguridadBean seguridadBean;

    /**
     * Creates a new instance of OpcionMenuController
     */
    public OpcionMenuController() {
    }
    
    @PostConstruct
    public void init() {
        lstOpcionMenu = seguridadBean.getLstOpcionMenu();
        Usuario usuDao = ((LoginController) FacesContext.getCurrentInstance().getApplication().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "loginController")).getUsuario();

        usuario = usuDao.getLogin();
        codigoDepartamento = usuDao.getCodigoDepartamento();
        codigoEntidad = usuDao.getCodigoEntidad();
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public Boolean getDeshabilitar() {
        return deshabilitar;
    }
    
    public void setDeshabilitar(Boolean deshabilitar) {
        this.deshabilitar = deshabilitar;
    }
    
    public OpcionMenu getOpcionMenu() {
        return opcionMenu;
    }
    
    public void setOpcionMenu(OpcionMenu opcionMenu) {
        if (opcionMenu != null) {
            this.opcionMenu = opcionMenu;
        }
    }
    
    public List<OpcionMenu> getLstOpcionMenu() {
        return lstOpcionMenu;
    }
    
    public String prepareCreate() {
        opcionMenu = new OpcionMenu();
        deshabilitar = false;
        return "";
    }
    
    public void guardar() {
        if (opcionMenu.getIdOpcMenu() == null) {
            opcionMenu.setFechaInsercion(new Date());
            opcionMenu.setEstadoEliminacion(BigInteger.ZERO);
            opcionMenu.setUsuarioInsercion(VarSession.getVariableSessionUsuario());
            if (seguridadBean.guardar(opcionMenu)) {
                JsfUtil.mensajeError("Error en la operación realizada");
            } else {
                lstOpcionMenu = seguridadBean.getLstOpcionMenu();
                JsfUtil.mensajeInsert();
            }
        } else {
            opcionMenu.setFechaModificacion(new Date());
            opcionMenu.setUsuarioModificacion(VarSession.getVariableSessionUsuario());
            if (seguridadBean.actualizar(opcionMenu)) {
                JsfUtil.mensajeError("Error en la operación realizada");
            } else {
                lstOpcionMenu = seguridadBean.getLstOpcionMenu();
                JsfUtil.mensajeInsert();
            }
        }
    }
    
    public void filtrarOpcionesMenu() {
        lstOpcionMenu = seguridadBean.getLstOpcionMenuByOpcPadre(opcionMenu.getIdOpcMenuPadre());
    }
    
    public void editarOpcMenu() {
        deshabilitar = false;
    }
    
    public void guardarOpcMenuApp() {
        RequestContext.getCurrentInstance().closeDialog(opcionMenu);
    }
    
    public void cerrarFiltroOpcMenuApp() {
        RequestContext.getCurrentInstance().closeDialog("/app/manttos/lstOpcionesMenu");
    }
    
    public void seleccionApp() {
        try {
            String url = seguridadBean.usuarioValidoApp(usuario, new BigDecimal(idApp));
            String param = "?usuario=" + usuario + "&idApp=" + idApp;
            if (codigoDepartamento != null) {
                param += "&codigo_departamento=" + codigoDepartamento;
            }
            if (codigoEntidad != null) {
                param += "&codigo_entidad=" + codigoEntidad;
            }
            
            ((LoginController) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "loginController")).crearArbolMenu(idApp);
            FacesContext.getCurrentInstance().getExternalContext().redirect(url.concat(param));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(OpcionMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getIdApp() {
        return idApp;
    }
    
    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }
}
