/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Academico;
import entities.Proyecto;
import entities.RolProyecto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cristobal
 */
@Stateless
public class ProyectoFacade extends AbstractFacade<Proyecto> implements ProyectoFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_malkasi-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProyectoFacade() {
        super(Proyecto.class);
    }
    
    @Override
    public void Create(Proyecto proyecto, List<RolProyecto> roles){
        proyecto.setAcademicos(roles);
        this.em.persist(proyecto);
        for(int i = 0; i < roles.size(); i++){
            Academico academicoAuxiliar = em.find(Academico.class, roles.get(i).getAcademico().getId());
            this.em.persist(roles.get(i));
            academicoAuxiliar.getProyectos().add(roles.get(i));
            this.em.merge(academicoAuxiliar);
        }        
    }
    
    @Override
    public void Update(Proyecto proyecto, List<RolProyecto> roles){
        Proyecto anterior = em.find(Proyecto.class, proyecto.getId());
        proyecto.setAcademicos(roles);
        for (int i = 0; i < roles.size(); i++) {
            em.merge(roles.get(i));
        }
        for (int i = 0; i < anterior.getAcademicos().size(); i++) {
            Academico academicoAuxiliar = em.find(Academico.class, anterior.getAcademicos().get(i).getAcademico().getId());
            int index = -1;
            for (int j = 0; j < academicoAuxiliar.getProyectos().size(); j++) {
                if(anterior.getAcademicos().get(i).getId() == academicoAuxiliar.getProyectos().get(j).getId()){
                    index = j;
                    break;
                }
            }
            if(index != -1){
                academicoAuxiliar.getProyectos().remove(index);
            }
            em.merge(academicoAuxiliar);
        }
        for(int i = 0; i < roles.size(); i++){
            Academico academicoAuxiliar = em.find(Academico.class, roles.get(i).getAcademico().getId());
            academicoAuxiliar.getProyectos().add(roles.get(i));
            this.em.merge(academicoAuxiliar);
        }
        this.em.merge(proyecto);
    }
}
