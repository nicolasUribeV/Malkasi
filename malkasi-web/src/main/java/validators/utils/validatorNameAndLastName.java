/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.validation.Payload;
import sessionbeans.AcademicoExternoFacadeLocal;

/**
 *
 * @author roberto
 */
@FacesValidator("validatorNameAndLastName")
public class validatorNameAndLastName implements Validator {

    @EJB
    private AcademicoExternoFacadeLocal ejbFacade;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
//        UIInput input1 = (UIInput) context.getViewRoot().findComponent((String) component.getAttributes().get("nombres"));
//        UIInput input2 = (UIInput) context.getViewRoot().findComponent((String) component.getAttributes().get("apellidoss"));
//        UIInput input3 = (UIInput) context.getViewRoot().findComponent(Long.toString((long) component.getAttributes().get("ids")));

        String nombre = null;
        String apellido = null;
        long id = 0;
        try {
            nombre = (String) component.getAttributes().get("nombres");;
            apellido = (String) component.getAttributes().get("apellidoss");
            id = (long) component.getAttributes().get("ids");;
        } catch (Exception e) {
            nombre = null;
            apellido = null;
        }
        if (!validate(nombre, apellido, id)) {
            FacesMessage msg = new FacesMessage("Este académico ya existe en el sistema");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

    public boolean validate(String nombre, String apellido, long id) {
        if (ejbFacade.FindWithNombreApellido(nombre, apellido, id)) {
            return true;
        } else {
            return false;
        }

    }

}
