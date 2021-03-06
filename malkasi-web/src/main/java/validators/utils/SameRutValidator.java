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
        String nuevoRut = rut.replace("-", "");
        nuevoRut = nuevoRut.replace(".", "");
        nuevoRut = nuevoRut.replace("k", "K");
        if (!validarRutDistinto(nuevoRut)) {
            FacesMessage msg = new FacesMessage("Ya existe un usuario con el rut ingresado");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public boolean validarRutDistinto(String rut) {
        
        boolean validacion = false;
        try {
            
            if(ejbFacade.FindWithRut(rut)==null){
                validacion = true;
            }
        } catch (Exception e) {
            return false;
        }
        return validacion;
    }
}
