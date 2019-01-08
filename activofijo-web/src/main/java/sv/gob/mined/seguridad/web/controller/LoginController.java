/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import sv.gob.mined.seguridad.ejb.SeguridadBean;
import sv.gob.mined.seguridad.model.OpcionMenu;
import sv.gob.mined.seguridad.model.Usuario;
import sv.gob.mined.seguridad.util.Constantes;
import sv.gob.mined.seguridad.web.util.JsfUtil;
import sv.gob.mined.seguridad.web.util.VarSession;

/**
 *
 * @author misanchez
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private DefaultMenuModel model;
    private List<OpcionMenu> lstOpcionMenu = new ArrayList();
    private Usuario usuario = new Usuario();
    @EJB
    private SeguridadBean seguridadBean;
    private String login;
    private String clave1;
    private String clave2;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }

    
    public <T extends Object> T find(Class<T> type, Object o) {
        return seguridadBean.find(type, o);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getClave1() {
        return clave1;
    }

    public void setClave1(String clave1) {
        this.clave1 = clave1;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String validarUsuario() {
        Map param = seguridadBean.getUsuario(usuario.getLogin(), usuario.getClaveAcceso());
        String url = "";

        if ((Boolean) param.get(Constantes.USUARIO_VALIDO)) {
            usuario = (Usuario) param.get(Constantes.USUARIO);
            if ((Boolean) param.get(Constantes.CLAVE_VALIDA)) {
            if (!(Boolean) param.get(Constantes.USUARIO_EXPIRADO)) {
                if ((Boolean) param.get(Constantes.USUARIO_CAMBIAR_CLAVE)) {
                    url = "contrasena?faces-redirect=true";
                } else {
                    url = "/app/principal?faces-redirect=true";
                    VarSession.setVariableSession(Constantes.USUARIO, usuario);
                    if (usuario.getTipoUsuario().equals("C")) {
                        VarSession.setVariableSession(Constantes.COD_ENTIDAD, usuario.getCodigoEntidad());
                    }
                }
            } else {
                JsfUtil.mensajeAlerta("Ha expirado su contraseña");
            }
            }
            else{
                    
             JsfUtil.mensajeAlerta("Contraseña inválida!");       
                    }        
        } else {
            JsfUtil.mensajeAlerta("Usuario inválido!");
        }

        return url;
    }

    public void logout2() throws IOException {
        try {
            VarSession.limpiarVariableSession();

            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect(((ServletContext) externalContext.getContext()).getContextPath() + "/index.mined");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout() {
        try {
            VarSession.limpiarVariableSession();
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect(((ServletContext) externalContext.getContext()).getContextPath() + "/index.mined");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultMenuModel getModel() {
        return model;
    }

    public void setModel(DefaultMenuModel model) {
        this.model = model;
    }

    public void crearArbolMenu(String idApp) {
        DefaultMenuModel menu = new DefaultMenuModel();
        DefaultSubMenu subMenu = new DefaultSubMenu();
        Object obj = null;

        lstOpcionMenu.clear();
        lstOpcionMenu = seguridadBean.getLstOpcionMenu(usuario.getLogin(), new BigDecimal(idApp));

        try {
            if (!lstOpcionMenu.isEmpty()) {
                OpcionMenu opcMenu = lstOpcionMenu.get(0);

                if (lstOpcionMenu.size() == 1) {
                    DefaultMenuItem itemMenu = new DefaultMenuItem();

                    itemMenu.setValue(" " + opcMenu.getNombreOpcion());
                    itemMenu.setUrl(opcMenu.getUrl() + "?faces-redirect=true");
                    itemMenu.setIcon(opcMenu.getIcono() != null ? opcMenu.getIcono() : null);
                    itemMenu.setAjax(true);
                    itemMenu.setId("item" + opcMenu.getIdOpcMenu().toBigInteger().toString());

                    subMenu.addElement(itemMenu);
                    obj = subMenu;
                } else {
                    subMenu.setLabel(opcMenu.getNombreOpcion());
                    subMenu.setId("sub" + opcMenu.getIdOpcMenu().toBigInteger().toString());

                    if (!lstOpcionMenu.subList(1, lstOpcionMenu.size()).isEmpty()) {
                        obj = getHijo(subMenu, lstOpcionMenu.subList(1, lstOpcionMenu.size()));
                    }
                }
            } else {

                subMenu.setLabel(" Sin opciones");
                subMenu.setId("item0");
                obj = subMenu;
            }
            menu.addElement((DefaultSubMenu) obj);
            model = menu;

        } catch (Exception ex) {
            JsfUtil.mensajeError("Ocurrio una excepción en el proceso de creación del arbol del menu. Contactese con el administrador del sistema.");
            Logger.getLogger(LoginController.class.getName()).log(Level.WARNING, "\n\n===============================================\n"
                    + "Ocurrio un error inesperado en el proceso de creación del arbol del menu para el usuario: " + usuario.getLogin()
                    + "\nClase: " + LoginController.class.getSimpleName()
                    + "\nMetodo: crearArbolMenu() \n", ex);
        }
    }

    private Object getHijo(DefaultSubMenu opPadre, List<OpcionMenu> resultado) {
        DefaultSubMenu subMenu;
        DefaultMenuItem itemMenu;
        Boolean hijo = true;
        OpcionMenu opcionM;

        for (int j = 0; j < resultado.size(); j++) {
            opcionM = resultado.get(j);

            if (opPadre.getId().replace("sub", "").equals(opcionM.getIdOpcMenuPadre().getIdOpcMenu().toString())) { //Hijo del padre
                subMenu = new DefaultSubMenu();
                subMenu.setLabel(opcionM.getOrden().toString() + ". " + opcionM.getNombreOpcion());
                subMenu.setId("sub" + opcionM.getIdOpcMenu().toBigInteger().toString());

                Object obj = getHijo(subMenu, resultado.subList(j, resultado.size()));

                if (obj instanceof DefaultMenuItem) {
                    opPadre.getElements().add((DefaultMenuItem) obj);
                } else {
                    opPadre.getElements().add((DefaultSubMenu) obj);
                }
                hijo = false;
            }
        }

        if (hijo) {
            opcionM = resultado.get(0);
            itemMenu = new DefaultMenuItem();
            itemMenu.setValue(" " + opcionM.getNombreOpcion());
            itemMenu.setUrl(opcionM.getUrl() + "?faces-redirect=true");
            itemMenu.setIcon(opcionM.getIcono() != null ? opcionM.getIcono() : null);
            itemMenu.setAjax(true);
            itemMenu.setId("item" + opcionM.getIdOpcMenu().toBigInteger().toString());

            return itemMenu;
        } else {
            return opPadre;
        }
    }

    public void guardarContrasena() {
        if (clave1.equals(clave2)) {
            if (seguridadBean.cambiarContrasena(usuario.getLogin().toUpperCase(), clave1)) {
                usuario.setLogin(usuario.getLogin().toUpperCase());
                usuario.setClaveAcceso(clave1);
                JsfUtil.redireccionar(validarUsuario());
            }
        } else {
            JsfUtil.mensajeAlerta("Contraseña son Diferentes");
        }

    }

    public void resetearContrasena() {
        
         Map param = seguridadBean.getUsuario(login);
        String url = "";

       if ((Boolean) param.get(Constantes.USUARIO_VALIDO)) {
            usuario = (Usuario) param.get(Constantes.USUARIO);

            if (!(Boolean) param.get(Constantes.USUARIO_EXPIRADO)) {
       
            if (seguridadBean.resetearContrasena(login.toUpperCase(), "mined")) {
                JsfUtil.mensajeInsert();
                url = "/index?faces-redirect=true";
                JsfUtil.redireccionar(url);
            }
        }else {
                JsfUtil.mensajeAlerta("Ha expirado su contraseña");
            }
        } else {
            JsfUtil.mensajeAlerta("Usuario o Clave de Acceso inválidos!");
       }
    }
}