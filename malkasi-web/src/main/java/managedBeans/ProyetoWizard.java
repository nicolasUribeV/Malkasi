/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Academico;
import entities.Proyecto;
import entities.RolProyecto;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import managedBeans.util.JsfUtil;
import org.primefaces.event.FlowEvent;
import sessionbeans.AcademicoFacadeLocal;
import sessionbeans.ProyectoFacadeLocal;

/**
 *
 * @author cristobal
 */
@Named(value = "proyetoWizard")
@SessionScoped
public class ProyetoWizard implements Serializable {

    private Proyecto proyecto;
    
    private Academico academico;
    
    private List<RolProyecto> roles;
    
    private RolProyecto rolSeleccionado;
    
    private List<Academico> academicos = null;
   
    @EJB
    private ProyectoFacadeLocal proyectoFacade;
    
    @EJB
    private AcademicoFacadeLocal academicoFacade;
    
    
    public ProyetoWizard() {
    }
    


    
    public Proyecto prepareCreate() {
        proyecto = new Proyecto();
        academicos = academicoFacade.findAll();
        initializeEmbeddableKey();
        JsfUtil.redirect("/faces/roles/academico/proyectos/crear.xhtml");
        return proyecto;
    }
    
    public List<Academico> getAcademicos(){
        return academicoFacade.findAll();
    }

    public RolProyecto getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(RolProyecto rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }
    
    
    public void save() {
        FacesMessage msg = new FacesMessage("Exito", "Proyecto :" + proyecto.getNombreProyecto() + "creado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Academico getAcademico() {
        return academico;
    }

    public void setAcademico(Academico academico) {
        this.academico = academico;
    }

    public List<RolProyecto> getRoles() {
        return roles;
    }

    public void setRoles(List<RolProyecto> roles) {
        this.roles = roles;
    }
    
    
    
    

    public String onFlowProcess(FlowEvent event) {    
            return event.getNewStep();
    }

    private void initializeEmbeddableKey() {
    }

}
