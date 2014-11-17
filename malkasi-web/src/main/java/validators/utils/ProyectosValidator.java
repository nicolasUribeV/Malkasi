/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators.utils;

import entities.Academico;
import entities.RolProyecto;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ProyectosValidator")
public class ProyectosValidator implements Validator {

    

    public ProyectosValidator() {
    }

    @Override
   public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        ArrayList<RolProyecto> academicosSeleccionados;
        Object currentUObject;
        Academico currentU;
        try{
             academicosSeleccionados = (ArrayList<RolProyecto>) value;
             currentUObject = component.getAttributes().get("currentUserLA");
             currentU = (Academico) currentUObject;
        }
        catch(Exception e){
            currentU = null;
            currentUObject= null;
            academicosSeleccionados = null;
        }

        if(!validateList(academicosSeleccionados, currentU)) {
            FacesMessage msg = new FacesMessage( "Usted debe estar en la lista de Académicos", "Usted debe estar en la lista de Académicos");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(":PublicacionCreateForm:academicos", msg);
            throw new ValidatorException(msg);
        }
    }
    public boolean validateList(ArrayList<RolProyecto> ac, Academico cu){
        if (ac == null || cu == null){
            if(cu == null){
            }
            return false;
        }
        else{
            for(int i = 0; i < ac.size(); i++) {
                if(ac.get(i).getAcademico().getId() == cu.getId()){
                    return true;
                }
            }
        }
        return false;
    }
}
