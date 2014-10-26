/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBeans.coverters;

import entities.Academico;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessionbeans.AcademicoFacadeLocal;
 
@FacesConverter("academicoConverter")
public class AcademicoConverter implements Converter {
 
    @EJB
    private AcademicoFacadeLocal ejbFacade;
    
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            String acAux[] = value.split(" ");
            String rutAcademico[] = acAux[1].split("=");
            System.out.println("rut: " + rutAcademico[1]);
            return ejbFacade.FindWithRut(rutAcademico[1]).get(0);
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null)
            return ((Academico) object).toString();
        else 
            return null;
    }   
} 

