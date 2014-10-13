/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ihc
 */
@FacesValidator("AgnoValidator")
public class AgnoValidator implements Validator{
    
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        int Year = (int) o;
        
        if(!validateYear(Year)) {
            FacesMessage msg = new FacesMessage("Año inválido.", "Invalid Year format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        
    }
    
    public static boolean validateYear(int Year){
        return Year>=1900;
    }
    
}
