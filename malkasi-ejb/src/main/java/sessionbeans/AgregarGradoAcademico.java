/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Academico;
import entities.GradoAcademico;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Nico_
 */
@Stateless
public class AgregarGradoAcademico implements AgregarGradoAcademicoLocal {
    @PersistenceContext(unitName = "com.mycompany_malkasi-ejb_ejb_1.0-SNAPSHOTPU")
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public void Create(Academico Academico, GradoAcademico GradoAcademico){
        this.em.persist(GradoAcademico);
        Academico.getGrados().add(GradoAcademico);
        this.em.merge(Academico);
    }
    
    @Override
    public void Delete(Academico Academico, GradoAcademico GradoAcademico){
        
        System.out.println("Academico: "+Academico.getNombres()+", Grado A Borrar: "+GradoAcademico.getTitulo());
        int Index = -1;
        for (int i = 0; i < Academico.getGrados().size(); i++) {
            if(GradoAcademico.getId() == Academico.getGrados().get(i).getId()){
                Index = i;
                break;
            }
        }
        if(Index == -1){
            System.out.println("No se pudo Eliminar");
        }
        else{
            System.out.println("Encontrado: "+Academico.getGrados().get(Index).getTitulo());
            Academico.getGrados().remove(Index);
            this.em.remove(em.merge(GradoAcademico));
            this.em.merge(Academico);
        }
    }
}
