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
import javax.persistence.Query;

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
    public void Delete(Academico academico, Publicacion publicacion){
        
        System.out.println("Academico: "+academico.getNombres()+", publicacion A Borrar: "+publicacion.getNombrePublicacion());
        System.out.println("Mi Academico: "+publicacion.getMiAcademico().getNombres());
        int Index = -1;
        for (int i = 0; i < publicacion.getMiAcademico().getPublicaciones().size(); i++) {
            System.out.println("Publicación Encontrada: " + academico.getPublicaciones().get(i).getNombrePublicacion());
            if(publicacion.getId() == publicacion.getMiAcademico().getPublicaciones().get(i).getId()){
                Index = i;
                break;
            }
        }
        if(Index == -1){
            System.out.println("No se pudo Eliminar");
        }
        else{
            //System.out.println("Encontrado: "+Academico.getGrados().get(Index).getTitulo());
            publicacion.getMiAcademico().getPublicaciones().remove(Index);
            Academico Auxiliar = publicacion.getMiAcademico();
            this.em.remove(em.merge(publicacion));
            this.em.merge(Auxiliar);
        }
    }
}
