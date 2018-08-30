/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.ejb;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import sv.gob.mined.activofijo.model.AfBienesDepreciables;
import sv.gob.mined.seguridad.model.Aplicacion;
import sv.gob.mined.seguridad.model.AplicacionOpcMenu;
import sv.gob.mined.seguridad.model.Grupo;
import sv.gob.mined.seguridad.model.GrupoAplicacion;
import sv.gob.mined.seguridad.model.OpcionMenu;
import sv.gob.mined.seguridad.model.Usuario;
import sv.gob.mined.seguridad.model.UsuarioAplicacion;
import sv.gob.mined.seguridad.model.UsuarioGrupoAplicacion;
import sv.gob.mined.seguridad.model.rhevaluacion.Empleado;
import sv.gob.mined.seguridad.model.view.VwCatalogoEntidadEducativa;
import sv.gob.mined.seguridad.util.Constantes;
import sv.gob.mined.seguridad.util.UtilSeguridad;

/**
 *
 * @author misanchez
 */
@Stateless
@LocalBean
public class SeguridadBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "seguridadV2PU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    //@Resource(mappedName = "java:app/env/AFij")
    private String urlAFijo;
    //@Resource(mappedName = "java:app/env/Segv2")
    private String urlSeg;

    public SeguridadBean() {
    }

    /**
     * Método que retorna un mapa<String, Object> en el cual se establecen los
     * valores para las claves USUARIO, USUARIO_VALIDO, USUARIO_EXPIRADO y
     * USUARIO_CAMBIAR_CLAVE
     *
     * @param usuario registrado en el esquema de seguridad
     * @param claveAcesso password del usuario que se esta validando
     * @return mapa<String, Object>
     */
    public Map<String, Object> getUsuario(String usuario, String claveAcesso) {
        Map<String, Object> param = new HashMap();
        String claveMd5 = UtilSeguridad.getMD5(claveAcesso).toUpperCase();
        try {

            Query q = em.createNativeQuery("SELECT * FROM Usuario WHERE activo='A' AND login='" + usuario + "' AND clave_acceso='" + claveMd5 + "'", Usuario.class);
            q.setParameter("usuario", usuario);
            q.setParameter("claveAcceso", claveMd5);
            param.put(Constantes.USUARIO_VALIDO, !q.getResultList().isEmpty());

            if ((Boolean) param.get(Constantes.USUARIO_VALIDO)) {
                Usuario usu = (Usuario) q.getResultList().get(0);
                param.put(Constantes.USUARIO, usu);
                param.put(Constantes.USUARIO_EXPIRADO, usu.getFechaExpiracion().before(new Date()));
                param.put(Constantes.USUARIO_CAMBIAR_CLAVE, usu.getCambiarClave().equals('S'));
            }

        } catch (Exception e) {
            Logger.getLogger(SeguridadBean.class.getName()).log(Level.WARNING, "Error en autenticación del usuario", e);
        }

        return param;
    }

    public Usuario getUsu(String usuario) {
        Query q = em.createNativeQuery("SELECT * FROM Usuario WHERE activo='A' AND login='" + usuario + "' ", Usuario.class);
        q.setParameter("usuario", usuario);
        if (!q.getResultList().isEmpty()) {
            return (Usuario) q.getResultList().get(0);
        } else {
            return new Usuario();
        }
    }

    public Map<String, Object> getUsuario(String usuario) {
        Map<String, Object> param = new HashMap();

        try {

            Query q = em.createNativeQuery("SELECT * FROM Usuario WHERE activo='A' AND login='" + usuario + "' ", Usuario.class);
            q.setParameter("usuario", usuario);

            param.put(Constantes.USUARIO_VALIDO, !q.getResultList().isEmpty());
            if ((Boolean) param.get(Constantes.USUARIO_VALIDO)) {
                Usuario usu = (Usuario) q.getResultList().get(0);
                param.put(Constantes.USUARIO, usu);
                param.put(Constantes.USUARIO_EXPIRADO, usu.getFechaExpiracion().before(new Date()));
                param.put(Constantes.USUARIO_CAMBIAR_CLAVE, usu.getCambiarClave().equals('S'));
            }

        } catch (Exception e) {
            Logger.getLogger(SeguridadBean.class.getName()).log(Level.WARNING, "Error en autenticación del usuario", e);
        } finally {
            //System.out.println("Usuario: " + usuario + " - Clave: " + claveMd5);
        }

        return param;
    }

    public HashMap<String, String> removerGrupoApp(BigDecimal idGrupo, BigDecimal idApp) {
        HashMap<String, String> value = new HashMap();
        try {
            Query q = em.createQuery("SELECT g FROM GrupoAplicacion g WHERE g.idGrupo.idGrupo=:idGrupo and g.idAplicacion.idAplicacion=:idApp", GrupoAplicacion.class);
            q.setParameter("idGrupo", idGrupo);
            q.setParameter("idApp", idApp);
            GrupoAplicacion gp = (GrupoAplicacion) q.getSingleResult();
            em.remove(gp);
        } catch (Exception exp) {
            Throwable e = exp;
            while (e.getCause() != null) {
                e = e.getCause();
            }

            if (e instanceof SQLIntegrityConstraintViolationException
                    && ((SQLIntegrityConstraintViolationException) e).getErrorCode() == 2292) {
                value.put("error", "Primero de eliminar las opciones del menu registradas al grupo seleccionado.");
            }
            Logger.getLogger(SeguridadBean.class.getName()).log(Level.WARNING, "Error en la eliminación de los grupos por aplicación", e);
        }

        return value;
    }

    public Boolean guardar(Object obj) {
        Boolean error = false;
        try {
            em.persist(obj);
        } catch (Exception e) {
            Logger.getLogger(SeguridadBean.class.getName()).log(Level.WARNING, "Error guardando un objeto", e);
            error = true;
        }
        return error;
    }

    /**
     * Devuelve true si se logra actualizar la contraseña
     * @param login
     * @param clave
     * @return 
     */
    public Boolean resetearContrasena(String login, String clave) {
        String claveMd5 = UtilSeguridad.getMD5(clave).toUpperCase();
        Usuario user = getUsu(login.toUpperCase());
        user.setClaveAcceso(claveMd5);
        user.setCambiarClave('S');

        try {
            em.merge(user);
            return true;
        } catch (Exception e) {
            Logger.getLogger(SeguridadBean.class.getName()).log(Level.WARNING, "Error en reseteo de contraseña", e);
            return false;
        }
    }

    public Boolean cambiarContrasena(String login, String clave) {
        String claveMd5 = UtilSeguridad.getMD5(clave).toUpperCase();
        Usuario user = getUsu(login.toUpperCase());
        user.setClaveAcceso(claveMd5);
        user.setCambiarClave('N');
        try {
            em.merge(user);
            return true;
        } catch (Exception e) {
            Logger.getLogger(SeguridadBean.class.getName()).log(Level.WARNING, "Error en cambio de contraseña", e);
            return false;
        }

    }

    public Boolean guardarUsuario(Usuario usu, Boolean modif) {
        String claveMd5 = UtilSeguridad.getMD5(usu.getClaveAcceso()).toUpperCase();
        usu.setClaveAcceso(claveMd5);
        if (modif) {
            return actualizar(usu);
        } else {
            return guardar(usu);
        }
    }

    public void bitacoraAcceso(String usuario, String ip) {
    }

    public Boolean actualizar(Object obj) {
        Boolean error = false;
        try {
            em.merge(obj);
        } catch (Exception e) {
            Logger.getLogger(SeguridadBean.class.getName()).log(Level.WARNING, "Error actualizando un objeto", e);
            error = true;
        }
        return error;
    }

    public List<OpcionMenu> getLstOpcionMenu(String login, BigDecimal app) {
        List<OpcionMenu> lst = new ArrayList();

        Query q = em.createQuery("SELECT u.idGrupoApp FROM UsuarioGrupoAplicacion u WHERE u.activoGrupoApp='A' AND u.idUsuApp.login.login=:login AND u.idUsuApp.idAplicacion.idAplicacion=:app", GrupoAplicacion.class);

        q.setParameter("login", login);
        q.setParameter("app", app);

        if (q.getResultList().isEmpty()) {
            //vacio
        } else {
            GrupoAplicacion grupoApp = (GrupoAplicacion) q.getResultList().get(0);
            q = em.createQuery("SELECT a.idOpcMenu FROM AplicacionOpcMenu a WHERE a.idGrupoApp=:idGrupoApp ORDER BY a.idOpcMenu.orden", OpcionMenu.class);
            q.setParameter("idGrupoApp", grupoApp);

            q.setHint("javax.persistence.cache.storeMode", "REFRESH");
            lst = q.getResultList();
        }

        return lst;
    }

    public List<OpcionMenu> getLstOpcionMenu() {
        return em.createQuery("SELECT o FROM OpcionMenu o ORDER BY o.idOpcMenu", OpcionMenu.class).getResultList();
    }

    public List<OpcionMenu> getLstOpcionMenuByOpcPadre(OpcionMenu opcPadre) {
        Query q = em.createQuery("SELECT o FROM OpcionMenu o WHERE o.idOpcMenu=:opc OR o.idOpcMenuPadre=:opcPadre ORDER BY o.idOpcMenu", OpcionMenu.class);
        q.setParameter("opc", opcPadre.getIdOpcMenu());
        q.setParameter("opcPadre", opcPadre);
        return q.getResultList();
    }

    public List<Aplicacion> getLstAplicacion() {
        return em.createQuery("SELECT a FROM Aplicacion a ORDER BY a.idAplicacion", Aplicacion.class).getResultList();
    }

    public List<Grupo> getLstGrupo() {
        return em.createQuery("SELECT g FROM Grupo g ORDER BY g.idGrupo", Grupo.class).getResultList();
    }

    public List<GrupoAplicacion> getLstGrupoAplicacion() {
        return em.createQuery("SELECT g FROM GrupoAplicacion g ORDER BY g.idGrupoApp", GrupoAplicacion.class).getResultList();
    }

    public List<GrupoAplicacion> getLstGrupoAplicacionByApp(BigDecimal idApp) {
        Query q = em.createQuery("SELECT g FROM GrupoAplicacion g WHERE g.idAplicacion.idAplicacion=:idApp ORDER BY g.idGrupoApp", GrupoAplicacion.class);
        q.setParameter("idApp", idApp);
        return q.getResultList();
    }

    public void getUsuarioGrupoAplicacion(Usuario login, Aplicacion app, GrupoAplicacion grupoAplicacion, UsuarioAplicacion usuarioAplicacion) {
        UsuarioGrupoAplicacion usuarioGrupoAplicacion;

        Query q = em.createQuery("SELECT u FROM UsuarioGrupoAplicacion u WHERE u.idUsuApp.login=:login AND u.idGrupoApp.idAplicacion=:idApp", UsuarioGrupoAplicacion.class);
        q.setParameter("login", login);
        q.setParameter("idApp", app);

        if (q.getResultList().isEmpty()) {
            usuarioGrupoAplicacion = new UsuarioGrupoAplicacion();
            usuarioGrupoAplicacion.setIdGrupoApp(grupoAplicacion);
            usuarioGrupoAplicacion.setIdUsuApp(usuarioAplicacion);
            usuarioGrupoAplicacion.setUsuGrupoApp('1');
            usuarioGrupoAplicacion.setActivoGrupoApp('A');

            em.persist(usuarioGrupoAplicacion);
        } else {
            em.merge(usuarioAplicacion);

            usuarioGrupoAplicacion = (UsuarioGrupoAplicacion) q.getResultList().get(0);
            usuarioGrupoAplicacion.setIdGrupoApp(grupoAplicacion);
            usuarioGrupoAplicacion.setIdUsuApp(usuarioAplicacion);
            usuarioGrupoAplicacion.setUsuGrupoApp('1');
            usuarioGrupoAplicacion.setActivoGrupoApp('A');

            em.merge(usuarioGrupoAplicacion);
        }
    }

    public UsuarioAplicacion getUsuarioApp(Usuario login, Aplicacion app) {
        UsuarioAplicacion usuarioAplicacion;
        Query q = em.createQuery("SELECT u FROM UsuarioAplicacion u WHERE u.login=:login AND u.idAplicacion=:idApp", UsuarioAplicacion.class);
        q.setParameter("login", login);
        q.setParameter("idApp", app);

        if (q.getResultList().isEmpty()) {
            usuarioAplicacion = new UsuarioAplicacion();
            usuarioAplicacion.setIdAplicacion(app);
            usuarioAplicacion.setLogin(login);
            usuarioAplicacion.setUsuGrupoActivo('A');
            usuarioAplicacion.setCodigoDepartamento("00");

            em.persist(usuarioAplicacion);

        } else {
            usuarioAplicacion = (UsuarioAplicacion) q.getResultList().get(0);
        }

        return usuarioAplicacion;
    }

    public List<Grupo> getLstGruAppByApp(Aplicacion app) {
        Query q = em.createQuery("SELECT g.idGrupo FROM GrupoAplicacion g WHERE g.idAplicacion=:app ORDER BY g.idGrupoApp", Grupo.class);
        q.setParameter("app", app);
        return q.getResultList();
    }

    public List<Grupo> getLstGruAppNotApp(Aplicacion app) {
        List<Grupo> lst = new ArrayList();
        try {
            Query q = em.createNativeQuery("SELECT * FROM Grupo WHERE id_grupo not in (SELECT id_grupo FROM GRUPO_APLICACION WHERE id_aplicacion=" + app.getIdAplicacion().intValue() + ")", Grupo.class);
            lst = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(SeguridadBean.class.getName()).log(Level.WARNING, "Error en obteniendo el listado de grupos por aplicación", e);
        }
        return lst;
    }

    public List<Aplicacion> getLstAppByUsu(Usuario usu) {
        Query q = em.createQuery("SELECT u.idAplicacion FROM UsuarioAplicacion u WHERE u.login=:login ORDER BY u.idAplicacion.nombreAplicacion", Aplicacion.class);
        q.setParameter("login", usu);
        return q.getResultList();
    }

    public List<Aplicacion> getLstAppNotUsu(Usuario usu) {
        Query q = em.createQuery("SELECT u.idAplicacion FROM UsuarioAplicacion u WHERE u.login <> :login ORDER BY u.idAplicacion.nombreAplicacion", Aplicacion.class);
        q.setParameter("login", usu);
        return q.getResultList();
    }

    public List<Usuario> getLstUsuarios() {
        Query q = em.createQuery("SELECT u FROM Usuario u ORDER BY u.login", Usuario.class);
        return q.getResultList();
    }

    public <T extends Object> T find(Class<T> type, Object o) {
        if (o == null) {
            return null;
        } else {
            return em.find(type, o);
        }
    }

    public AfBienesDepreciables find(Long id) {
        Query q = em.createQuery("SELECT a FROM AfBienesDepreciables a WHERE a.idBien=:idBien", AfBienesDepreciables.class);
        q.setParameter("idBien", id);
        return (AfBienesDepreciables) q.getSingleResult();
    }

    /**
     * Verifica que la opción seleccionada, no este agregada en el grupo
     * seleccionado
     *
     * @param aplicacionOpcMenu
     * @return falso si no existe
     */
    public boolean existeOpcionMenuGrupo(AplicacionOpcMenu aplicacionOpcMenu) {
        Query q;
        q = em.createQuery("SELECT a FROM AplicacionOpcMenu a WHERE a.idAplicacion=:idApp and a.idOpcMenu=:idOpc and a.idGrupoApp=:idGrupo", AplicacionOpcMenu.class);
        q.setParameter("idApp", aplicacionOpcMenu.getIdAplicacion());
        q.setParameter("idOpc", aplicacionOpcMenu.getIdOpcMenu());
        q.setParameter("idGrupo", aplicacionOpcMenu.getIdGrupoApp());

        return !q.getResultList().isEmpty();
    }

    public void guardarAppMenuOpc(AplicacionOpcMenu aplicacionOpcMenu) {
        Query q;
        OpcionMenu opcionMenu = aplicacionOpcMenu.getIdOpcMenu().getIdOpcMenuPadre();

        //se verifica que la opción seleccionado sea un hijo o un padre
        if (opcionMenu != null) {
            q = em.createQuery("SELECT a FROM AplicacionOpcMenu a WHERE a.idAplicacion=:idApp and a.idOpcMenu=:idOpc and a.idGrupoApp=:idGrupo", AplicacionOpcMenu.class);
            q.setParameter("idApp", aplicacionOpcMenu.getIdAplicacion());
            q.setParameter("idOpc", opcionMenu);
            q.setParameter("idGrupo", aplicacionOpcMenu.getIdGrupoApp());

            //si es un hijo, se verifica que el padre ya este agregado al grupo seleccionado
            if (q.getResultList().isEmpty()) {
                q = em.createQuery("SELECT a FROM AplicacionOpcMenu a WHERE a.idOpcMenu=:idOpcMenu", OpcionMenu.class);
                q.setParameter("idOpcMenu", opcionMenu);
                if (!q.getResultList().isEmpty()) {
                    AplicacionOpcMenu aom = new AplicacionOpcMenu();
                    aom.setIdOpcMenu(opcionMenu);
                    aom.setIdAplicacion(aplicacionOpcMenu.getIdAplicacion());
                    aom.setIdGrupoApp(aplicacionOpcMenu.getIdGrupoApp());
                    em.persist(aom);

                }
            }

            em.persist(aplicacionOpcMenu);

        } else {

            em.persist(aplicacionOpcMenu);

        }
    }

    public List<AplicacionOpcMenu> getLstAplicacionOpcMenu(Aplicacion aplicacion, GrupoAplicacion grupoApp) {
        Query q = em.createQuery("SELECT a FROM AplicacionOpcMenu a WHERE a.idAplicacion=:idAplicacion AND a.idGrupoApp=:idGrupoApp and a.idOpcMenu.idOpcMenuPadre is null ORDER BY a.idOpcMenu.idOpcMenu", AplicacionOpcMenu.class);
        q.setParameter("idGrupoApp", grupoApp);
        q.setParameter("idAplicacion", aplicacion);

        return q.getResultList();
    }

    public List<AplicacionOpcMenu> getLstAplicacionOpcMenuHijos(Aplicacion aplicacion, GrupoAplicacion grupoApp, OpcionMenu opcPadre) {
        Query q = em.createQuery("SELECT a FROM AplicacionOpcMenu a WHERE a.idAplicacion=:idAplicacion AND a.idGrupoApp=:idGrupoApp AND a.idOpcMenu.idOpcMenuPadre=:idOpcPadre ORDER BY a.idOpcMenu.idOpcMenu", AplicacionOpcMenu.class);
        q.setParameter("idGrupoApp", grupoApp);
        q.setParameter("idAplicacion", aplicacion);
        q.setParameter("idOpcPadre", opcPadre);

        return q.getResultList();
    }

    public List<Empleado> lstEmpleados(String codigoEmpleado, String nombreEmpleado, String apeEmpleado) {
        String whereSql = "";

        if (!codigoEmpleado.equals("")) {
            whereSql = "e.codigoEmpleado=:codEmp ";
        }
        if (!nombreEmpleado.equals("")) {
            if (!whereSql.equals("")) {
                whereSql = whereSql + " and e.nombreEmpleado like :nombreEmp ";
            } else {
                whereSql = " e.nombreEmpleado like :nombreEmp ";
            }
        }
        if (!apeEmpleado.equals("")) {
            if (!whereSql.equals("")) {
                whereSql = whereSql + " and e.apellidoEmpleado like :apellidoEmp ";
            } else {
                whereSql = " e.apellidoEmpleado like :apellidoEmp";
            }
        }

        if (!whereSql.equals("")) {
            whereSql = " WHERE " + whereSql;
        }
        Query q = em.createQuery("SELECT e FROM Empleado e  " + whereSql, Empleado.class);
        if (!codigoEmpleado.equals("")) {
            q.setParameter("codEmp", "%" + codigoEmpleado.toUpperCase() + "%");
        }
        if (!nombreEmpleado.equals("")) {
            q.setParameter("nombreEmp", "%" + nombreEmpleado.toUpperCase() + "%");
        }
        if (!apeEmpleado.equals("")) {
            q.setParameter("apellidoEmp", "%" + apeEmpleado.toUpperCase() + "%");
        }
        return q.getResultList();
    }

    public VwCatalogoEntidadEducativa getEntidadEducativa(String codigoEntidad) {
        Query q = em.createQuery("SELECT v FROM VwCatalogoEntidadEducativa v WHERE v.codigoEntidad=:codigoEntidad", VwCatalogoEntidadEducativa.class);
        q.setParameter("codigoEntidad", codigoEntidad);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (VwCatalogoEntidadEducativa) q.getSingleResult();
        }
    }

    public String usuarioValidoApp(String usuario, BigDecimal idApp) {
        Query q = em.createQuery("SELECT u FROM UsuarioAplicacion u WHERE u.login.login=:login AND u.idAplicacion.idAplicacion=:idApp", UsuarioAplicacion.class);
        q.setParameter("login", usuario);
        q.setParameter("idApp", idApp);
        urlSeg = "/sistemas-web/app/inicial.mined";
        urlAFijo = "/activofijo-web/app/inicial.mined";
        if (!q.getResultList().isEmpty()) {
            switch (idApp.intValue()) {
                case 1:
                    return urlSeg;
                case 5:
                    return urlAFijo;
                default:
                    return "";
            }
        } else {
            return "/activofijo-web/app/inicial.mined";
        }
    }
}
