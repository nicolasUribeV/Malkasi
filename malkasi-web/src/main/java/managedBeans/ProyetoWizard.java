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

    private String rol;

    public ProyetoWizard() {
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Proyecto prepareCreate(Academico currentU) {
        proyecto = new Proyecto();
        academicos = academicoFacade.findAll();
        ArrayList<RolProyecto> listaAux = new ArrayList<RolProyecto>();
        roles = listaAux;
        initializeEmbeddableKey();
        JsfUtil.redirect("/faces/roles/academico/proyectos/crear.xhtml");
        return proyecto;
    }

    public List<Academico> getAcademicos() {
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

    public void crearRol(Academico academico, String rol) {
        boolean agregado = false;
        academicos = academicoFacade.findAll();
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getAcademico().getId() == academico.getId()) {
                agregado = true;
            }
        }
        if (agregado == false) {
            RolProyecto rp = new RolProyecto();
            rp.setAcademico(academico);
            rp.setProyecto(proyecto);
            rp.setRol(rol);
            roles.add(rp);
        }
        actualizarListaAcademicos();

    }
    
    public void eliminarRol(Academico academico){
        academicos = academicoFacade.findAll();
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getAcademico().getId() == academico.getId()) {
                System.out.println("Eliminé: " + roles.get(i).getAcademico().getNombres());
                roles.remove(i);
            }
        }
        actualizarListaAcademicos();
    }

    public void actualizarListaAcademicos(){
        for (int i = 0; i < roles.size(); i++) {
            for (int j = 0; j < academicos  .size(); j++) {
                if (roles.get(i).getAcademico().getId() == academicos.get(j).getId()) {
                    academicos.remove(j);
                }
            }
        }
    }
    
    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

    private void initializeEmbeddableKey() {
    }

}
