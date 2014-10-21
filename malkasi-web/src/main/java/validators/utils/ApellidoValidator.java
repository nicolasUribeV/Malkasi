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
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ihc
 */
@FacesValidator("ApellidoValidator")
public class ApellidoValidator implements Validator{
    
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String nombre = (String) o;

        if(!validarApellido(nombre)) {
            FacesMessage msg = new FacesMessage("Apellido inválido.", "Invalid Last Name format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
    public static boolean validarApellido(String nombre){
        Pattern p = Pattern.compile("^[a-zA-ZñÑáéíóúÁÉÍÓÚ -]+$");
        return nombre.matches(p.pattern());
    }
}
