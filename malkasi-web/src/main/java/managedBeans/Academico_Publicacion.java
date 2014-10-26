/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBeans;

import entities.Academico;
import entities.Publicacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import managedBeans.util.JsfUtil;
import sessionbeans.AcademicoPublicacionFacadeLocal;
/**
 *
 * @author Nico_
 */
@Named(value = "academico_Publicacion")
@SessionScoped
public class Academico_Publicacion implements Serializable {
    @EJB
    private AcademicoPublicacionFacadeLocal ejbFacade;

    
    public Academico_Publicacion() {
    }

    public void NuevaDependecia(Publicacion publicacion,Academico academico){
        this.ejbFacade.Create(publicacion.getAcademicos(), publicacion,academico);
        JsfUtil.redirect("/faces/roles/academico/index.xhtml");
    }
    
    public void ModificarDependecia(Publicacion publicacion,Academico academico){
        this.ejbFacade.Update(publicacion.getAcademicos(), publicacion,academico);
        JsfUtil.redirect("/faces/roles/academico/index.xhtml");
    }
    
    public void Delete(Publicacion publicacion,Academico academico){
        this.ejbFacade.Delete(publicacion.getAcademicos(), publicacion,academico);
    }
    
    public List<Academico> getAcademicos() {
        return ejbFacade.findAll();
    }  
}
