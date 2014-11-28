/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators.utils;

import entities.Academico;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private final static Logger logger= Logger.getLogger(SameUserNameValidator.class.getName());
    
    private String userName;

    public SameUserNameValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        userName = value.toString();
        if (validarUserName(userName)) {
            FacesMessage msg = new FacesMessage("Nombre de usuario ya ingresado en el sistema");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public boolean validarUserName(String userName) {                
        List<Academico> academicos = ejbFacade.FindWithUserName(userName);
        return ValidationUtils.existUserName(userName,academicos);
    }
    
}
