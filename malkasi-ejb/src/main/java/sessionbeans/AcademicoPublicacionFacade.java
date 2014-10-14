/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Academico;
import entities.GradoAcademico;
import entities.Publicacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Nico_
 */
@Stateless
public class AcademicoPublicacionFacade implements AcademicoPublicacionFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_malkasi-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public void Create(Academico academico, Publicacion publicacion){
        this.em.persist(publicacion);
        Academico academicoAuxiliar = em.find(Academico.class, academico.getId());
        if(academicoAuxiliar == null){
            System.out.println("EL FEROZ ERROR");
        }
        else{
            academicoAuxiliar.getPublicaciones().add(publicacion);
            this.em.merge(academicoAuxiliar);
        }
    }
    
    @Override
    public void Delete(Academico Academico, Publicacion Publicacion){
        
        //System.out.println("Academico: "+Academico.getNombres()+", Grado A Borrar: "+GradoAcademico.getTitulo());
        int Index = -1;
        for (int i = 0; i < Academico.getPublicaciones().size(); i++) {
            if(Publicacion.getId() == Academico.getPublicaciones().get(i).getId()){
                Index = i;
                break;
            }
        }
        if(Index == -1){
            System.out.println("No se pudo Eliminar");
        }
        else{
            //System.out.println("Encontrado: "+Academico.getGrados().get(Index).getTitulo());
            Academico.getPublicaciones().remove(Index);
            this.em.remove(em.merge(Publicacion));
            this.em.merge(Academico);
        }
    }
}
