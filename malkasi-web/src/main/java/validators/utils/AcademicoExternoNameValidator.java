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
        Object b;
        String apellido=null;
        long id = 0;
        try{
            a = component.getAttributes().get("apellidos");
            apellido = (String) a;
            b = component.getAttributes().get("ids");
            id = (long) b;
        }
        catch (Exception e){
            a = null;
            b = null;
        }
        if(!validate(nombre, apellido, id)){
            FacesMessage msg = new FacesMessage("Este académico ya fue ingresado al sistema");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
    public boolean validate(String nombre, String apellido, long id){
        
        
        if(ejbFacade.findWithNombreApellido(nombre, apellido, id)){
            return true;
        }
        else{
            return false;
        }
        
    }
    
}
