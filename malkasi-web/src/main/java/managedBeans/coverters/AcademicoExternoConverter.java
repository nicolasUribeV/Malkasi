/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.coverters;

import entities.AcademicoExterno;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import managedBeans.AcademicoExternoController;
import sessionbeans.AcademicoExternoFacadeLocal;
import sessionbeans.AcademicoFacadeLocal;

@FacesConverter("academicoExternoConverter")
public class AcademicoExternoConverter implements Converter {

    @EJB
    private AcademicoExternoFacadeLocal ejbFacade;

    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        AcademicoExternoController controller = (AcademicoExternoController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "academicoExternoController");
        return controller.getAcademicoExterno(getKey(value));
    }

    java.lang.Long getKey(String value) {
        java.lang.Long key;
        key = Long.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Long value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof AcademicoExterno) {
            AcademicoExterno o = (AcademicoExterno) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AcademicoExterno.class.getName()});
            return null;
        }
    }
}
