/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators.utils;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ihc
 */
public class AgnoValidator implements Validator{
    
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        int Year = (int) o;
        
        if(!validateYear(Year)) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Año inválido");
            message.setDetail("Año inválido");
            fc.addMessage(":formAgregar:Año", message);
            throw new ValidatorException(message);
        }
        
    }
    
    public static boolean validateYear(int Year){
        return Year>=1900;
    }
    
}
