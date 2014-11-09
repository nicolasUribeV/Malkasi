/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validators.utils;

import entities.Academico;
import java.util.ArrayList;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "academicoEditValidator")
public class AcademicoEditValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        ArrayList<Academico> academicosSeleccionados = null;
        Object currentUObject;
        Object listAcademicObject;
        Academico currentU;
        try{
             listAcademicObject = component.getAttributes().get("academicList");
             System.out.println("Booleano: " + listAcademicObject.toString());
             academicosSeleccionados = (ArrayList<Academico>) listAcademicObject;
             System.out.println("Largo es: " + academicosSeleccionados.size());
             currentUObject = component.getAttributes().get("currentUserEdit");
             currentU = (Academico) currentUObject;
        }
        catch(Exception e){
            System.out.println("NO PUDE");
            currentU = null;
            currentUObject= null;
            academicosSeleccionados = null;
        }

        if(!validateList(academicosSeleccionados, currentU)) {
            FacesMessage msg = new FacesMessage( "Usted debe estar en la lista de Académicos", "Usted debe estar en la lista de Académicos");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(":PublicacionEditForm:academicos", msg);
            throw new ValidatorException(msg);
        }
    }
    public boolean validateList(ArrayList<Academico> ac, Academico cu){
        if (ac == null || cu == null){
            if(cu == null){
            }
            return false;
        }
        else{
            for (int i = 0; i < ac.size(); i++) {
                if(ac.get(i).getId() == cu.getId()){
                    return true;
                }
            }
        }
        return false;
    }
}
