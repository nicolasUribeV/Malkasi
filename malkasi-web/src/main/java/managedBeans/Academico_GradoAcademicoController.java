/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Academico;
import entities.GradoAcademico;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import managedBeans.util.JsfUtil;
import sessionbeans.AgregarGradoAcademicoLocal;

@Named(value = "academico_GradoAcademicoController")
@SessionScoped
public class Academico_GradoAcademicoController implements Serializable {

    @EJB
    private AgregarGradoAcademicoLocal ejbFacade;

    public Academico_GradoAcademicoController() {
    }

    public void NuevaDependecia(Academico Academico, GradoAcademico GradoAcademico) {
        //System.out.println("Academico: "+Academico.getNombres()+", Grado: "+GradoAcademico.getTitulo());
        this.ejbFacade.Create(Academico, GradoAcademico);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Grado académico agregado con éxito"));

    }

    public void Delete(Academico Academico, GradoAcademico GradoAcademico) {
        this.ejbFacade.Delete(Academico, GradoAcademico);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Grado académico eliminado"));

    }
}
