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
@FacesValidator("AcademicoExternoNameValidator")
public class AcademicoExternoNameValidator implements Validator{
    @EJB
    private AcademicoExternoFacadeLocal ejbFacade;
    
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object n) throws ValidatorException {
        
     

        String nombre = (String) n;
        Object a;
        String apellido=null;
        try{
            a = component.getAttributes().get("apellidos");
            apellido = (String) a;
        }
        catch (Exception e){
            a = null;
        }
        if(!validate(nombre, apellido)){
            FacesMessage msg = new FacesMessage("Este académico ya fue ingresado al sistema");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
    public boolean validate(String nombre, String apellido){
        
        System.out.println("Debo revisar:" + nombre + " y " + apellido);
        if(ejbFacade.FindWithNombreApellido(nombre, apellido)){
            return true;
        }
        else{
            return false;
        }
        
    }
    
}

