/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators.utils;

import entities.Academico;
import entities.Publicacion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import sessionbeans.PublicacionFacadeLocal;

@FacesValidator(value = "academicoEditValidator")
public class AcademicoEditValidator implements Validator {
    
    @EJB
    private PublicacionFacadeLocal ejbFacade;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        List<Academico> academicosSeleccionados = null;
        Object currentUObject;
        Academico currentU;
        try {
            currentUObject = component.getAttributes().get("currentUserE");
            currentU = (Academico) currentUObject;
            Object currentUObjectList = component.getAttributes().get("listaE");
            academicosSeleccionados = (List<Academico>) currentUObjectList;
        } catch (Exception e) {
            currentU = null;
            currentUObject = null;
            academicosSeleccionados = null;
        }

        if (!validateList(academicosSeleccionados, currentU)) {
            FacesMessage msg = new FacesMessage("Usted debe estar en la lista de Académicos", "Usted debe estar en la lista de Académicos");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(":PublicacionEditForm:academicos", msg);
            throw new ValidatorException(msg);
        }
    }

    public boolean validateList(List<Academico> ac, Academico cu) {
        if (ac == null || cu == null) {
            if (cu == null) {
                System.out.println("Lista Vacia u.u -----------------------------");
            }
            return false;
        } else {
            for (int i = 0; i < ac.size(); i++) {
                System.out.println("ID1: "+ cu.getId() + " ID2: "+ ac.get(i).getId());
                if (ac.get(i).getId() == cu.getId()) {
                    return true;
                }
            }
        }
        return false;
    }
}
