/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBeans;

import entities.Academico;
import entities.Publicacion;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
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
    public void NuevaDependecia(Publicacion publicacion){
        //System.out.println("Academico: "+Academico.getNombres()+", Grado: "+GradoAcademico.getTitulo());
        this.ejbFacade.Create(publicacion.getMiAcademico(), publicacion);
    }
    
}
