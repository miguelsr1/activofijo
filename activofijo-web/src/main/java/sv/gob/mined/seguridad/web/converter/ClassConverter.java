/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.web.converter;

/**
 *
 * @author misanchez
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sv.gob.mined.app.activofijo.controller.UtilController;
import sv.gob.mined.seguridad.web.util.JsfUtil;

/**
 *
 * @author misanchez
 */
@FacesConverter(value = "classConverter")
public class ClassConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || value.contains("Seleccione")) {
            return null;
        }
        UtilController controller = (UtilController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "utilController");

        return controller.findVwBienByIdBien(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null || JsfUtil.getValuePK(object) == null) {
            return null;
        }
        return JsfUtil.getValuePK(object).toString();
    }
}
