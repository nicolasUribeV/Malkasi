/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Academico;
import entities.Publicacion;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import managedBeans.util.JsfUtil;
import org.primefaces.event.FlowEvent;
import sessionbeans.AcademicoPublicacionFacadeLocal;

@SessionScoped
@Named(value = "wizardController")
public class WizardController implements Serializable {
    
    @EJB
    private AcademicoPublicacionFacadeLocal ejbFacade;
    
    private Publicacion publicacion;

    private boolean skip;
    
    public Publicacion prepareCreateWithAcademic(Academico academico) {
        this.publicacion = new Publicacion();
        this.publicacion.getAcademicos().add(academico);
        this.publicacion.setDoi("");
        this.publicacion.setEditorial("");
        this.publicacion.setInstitucion("");
        this.publicacion.setNombreCongreso("");
        this.publicacion.setNombrePublicacion("");
        this.publicacion.setNombreLibro("");
        this.publicacion.setPais("");
        this.publicacion.setReferencia("");
        this.publicacion.setAgno(0);
        this.publicacion.setUrl("");
        initializeEmbeddableKey();
        JsfUtil.redirect("/faces/roles/academico/publicacion/wizardCreate.xhtml");
        return publicacion;
    }
    
    public Publicacion prepareEditWhitAcademic(Publicacion publicacion){
        this.publicacion = publicacion;
        JsfUtil.redirect("/faces/roles/academico/publicacion/wizardEdit.xhtml");
        return this.publicacion;
    }
    
    public void comeBack(){
        JsfUtil.redirect("/faces/roles/academico/index.xhtml");
    }
        
    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public void save(Publicacion publicacion,Academico academico) {
        this.ejbFacade.Create(publicacion.getAcademicos(), publicacion,academico);
        JsfUtil.redirect("/faces/roles/academico/index.xhtml");
    }
    
    public void edit(Publicacion publicacion,Academico academico){
        this.ejbFacade.Update(publicacion.getAcademicos(), publicacion,academico);
        JsfUtil.redirect("/faces/roles/academico/index.xhtml");
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

}
