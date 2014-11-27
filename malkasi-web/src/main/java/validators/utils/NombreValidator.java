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
 * @author Rodrigo
 */
@FacesValidator("NombreValidator")
public class NombreValidator implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String nombre = (String) o;

        if(!validarNombre(nombre)) {
            FacesMessage msg = new FacesMessage("Nombre inválido");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
    public static boolean validarNombre(String nombre){
        Pattern p = Pattern.compile("^[a-zA-ZñÑáéíóúÁÉÍÓÚ -]+$");
        return nombre.matches(p.pattern());
    }
}
