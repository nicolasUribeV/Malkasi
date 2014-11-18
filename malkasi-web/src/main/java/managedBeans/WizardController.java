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
import java.util.ArrayList;
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

    private ArrayList<String> listaOrden;

    public Publicacion prepareCreateWithAcademic(Academico academico) {
        this.publicacion = new Publicacion();
        this.listaOrden = new ArrayList<>();
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

    public Publicacion prepareEditWhitAcademic(Publicacion publicacion) {
        this.publicacion = publicacion;
        JsfUtil.redirect("/faces/roles/academico/publicacion/wizardEdit.xhtml");
        return this.publicacion;
    }

    public void comeBack() {
        JsfUtil.redirect("/faces/roles/academico/proyectos/List.xhtml");
    }
    
    public void comeBackIndex() {
        JsfUtil.redirect("/faces/roles/academico/index.xhtml");
    }


    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public void save(Publicacion publicacion, Academico academico) {
        this.ejbFacade.Create(publicacion.getAcademicos(), publicacion, academico);
        JsfUtil.redirect("/faces/roles/academico/index.xhtml");
    }

    public void edit(Publicacion publicacion, Academico academico) {
        
        int flag = 0;
        for (int i = 0; i < publicacion.getAcademicos().size(); i++) {
            if(academico.getId()==publicacion.getAcademicos().get(i).getId()){
                flag = 1;
            }
        }
        if(flag == 1){   
            this.ejbFacade.Update(publicacion.getAcademicos(), publicacion, academico);
            JsfUtil.redirect("/faces/roles/academico/index.xhtml");
        }
        else{
            FacesMessage msg = new FacesMessage("Error", "Usted debe estar incluido dentro de la publicación");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        
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

    public ArrayList<String> getListaOrden(){
        
        return listaOrden;
    }
    
    public ArrayList<String> getListaOrdenNoUpdate(){
        return listaOrden;
    }

    public void setListaOrden(ArrayList<String> listaOrden) {
        this.listaOrden = listaOrden;
    }

    public void updateOrden() {
        ArrayList<String> listaActual = new ArrayList<>();
        for (int i = 0; i < this.publicacion.getAcademicos().size(); i++) {
            String s = this.publicacion.getAcademicos().get(i).getNombres() + "_" + this.publicacion.getAcademicos().get(i).getApellidos();
            listaActual.add(s);

        }
        if (this.publicacion.getAcademicosExternos() != null) {
            for (int i = 0; i < this.publicacion.getAcademicosExternos().size(); i++) {
                String s = this.publicacion.getAcademicosExternos().get(i).getNombres() + "_" + this.publicacion.getAcademicosExternos().get(i).getApellidos();
                listaActual.add(s);

            }
        }
        this.listaOrden.removeAll(this.listaOrden);
        this.listaOrden.addAll(listaActual);
        this.publicacion.setAcademicoOrden(listaActual);
    }
    
    public void updateListOrden(){
        this.publicacion.setAcademicoOrden(listaOrden);
    }
    
    public String cleanName(String name){
        String[] a = name.split("_");
        return a[0] + " " + a[1];
     }

}
