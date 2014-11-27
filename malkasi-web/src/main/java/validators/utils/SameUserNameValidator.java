/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators.utils;

import entities.Academico;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import sessionbeans.AcademicoFacadeLocal;

@FacesValidator("SameUserNameValidator")
public class SameUserNameValidator implements Validator {

    @EJB
    private AcademicoFacadeLocal ejbFacade;
    
    private String userName;

    public SameUserNameValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        userName = value.toString();
        if (!validarUserName(userName)) {
            FacesMessage msg = new FacesMessage("Nombre de usuario ya ingresado en el sistema");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public boolean validarUserName(String rut) {
        
        boolean validacion = false;
        
        try {
            if(ejbFacade.FindWithUserName(userName) == null){
                validacion = true;
            }
        } catch (Exception e) {
            return false;
        }
        return validacion;
    }
}
