package sv.gob.mined.seguridad.web.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

public class JsfUtil {

    private static FacesMessage msg;

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    /**
     *
     * @param severity Grado de severidad
     * @param indicadorMensaje si es mensaje de Información, Alerta o Error
     * @param detalle detalle del mensaje a desplegar
     */
    public static void mostarMensaje(FacesMessage.Severity severity, String indicadorMensaje, String detalle) {
        msg = new FacesMessage(severity, indicadorMensaje, detalle);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static void mensajeUpdate() {
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "<big>Información</big>", "<big>Actualización exitosa.</big>");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static void mensajeInsert() {
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "<big>Información</big>", "<big>Registro almacenado satisfactoriamente</big>");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static void mensajeAlerta(String mensaje) {
        msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "<big>Alerta</big>", "<big>" + mensaje + "</big>");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static void mensajeError(String mensaje) {
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "<big>Error</big>", "<big>" + mensaje + "</big>");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static void mensajeInformacion(String mensaje) {
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "<big>Información</big>", "<big>" + mensaje + "</big>");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static void redireccionar(String url) {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

        configurableNavigationHandler.performNavigation(url);
    }

    public static <T extends Object> T getValuePK(Object t) {
        T value = null;
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(javax.persistence.Id.class) != null) {
                try {
                    Class[] sinArgumentos = new Class[0];
                    Object[] sinParametros = new Object[0];
                    Method getter = new PropertyDescriptor(field.getName(), t.getClass()).getReadMethod();
                    value = (T) t.getClass().getMethod(getter.getName(), sinArgumentos).invoke(t, sinParametros);
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return value;
    }

    public static Object newInstanceValuePK(Class t, Object o) {
        Object value = null;
        Field[] fields = t.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(javax.persistence.Id.class) != null) {
                try {
                    if (Integer.class.isAssignableFrom(field.getType())) {
                        try{
                            value = Integer.parseInt(o.toString());
                        }catch(Exception e){
                            value = null;
                        }
                    } else if (BigDecimal.class.isAssignableFrom(field.getType())) {
                        try{
                            value = new BigDecimal(o.toString());
                        }catch(Exception e){
                            value = null;
                        }
                    } else if (BigInteger.class.isAssignableFrom(field.getType())) {
                       try{
                            value = new BigInteger(o.toString());
                        }catch(Exception e){
                            value = null;
                        }
                    } else if (String.class.isAssignableFrom(field.getType())) {
                        value = o.toString();
                    }

                    break;
                } catch (Exception ex) {
                    Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return value;
    }
}