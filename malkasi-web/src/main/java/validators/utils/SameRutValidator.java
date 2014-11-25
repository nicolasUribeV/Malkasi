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

@FacesValidator("SameRutValidator")
public class SameRutValidator implements Validator {

    @EJB
    private AcademicoFacadeLocal ejbFacade;
    
    private String rut;

    public SameRutValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        rut = value.toString();
        if (!validarRut(rut)) {
            FacesMessage msg = new FacesMessage("Rut ya ingresado en el sistema", "Invalid Rut format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public boolean validarRut(String rut) {
        
        boolean validacion = false;
        
        try {
            if(ejbFacade.FindWithRut(rut).size() == 0){
                validacion = true;
            }
        } catch (Exception e) {
            return false;
        }
        return validacion;
    }
}
