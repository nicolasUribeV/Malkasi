/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Academico;
import entities.GradoAcademico;
import entities.Publicacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Nico_
 */
@Stateless
public class AcademicoPublicacionFacade extends AbstractFacade<Academico> implements AcademicoPublicacionFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_malkasi-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public AcademicoPublicacionFacade() {
        super(Academico.class);
    }

    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public void Create(List<Academico> academicos, Publicacion publicacion){
        this.em.persist(publicacion);
        for (int i = 0; i < academicos.size(); i++) {
            Academico academicoAuxiliar = em.find(Academico.class, academicos.get(i).getId());
            if(academicoAuxiliar == null){
                System.out.println("EL FEROZ ERROR");
            }
            else{
                academicoAuxiliar.getPublicaciones().add(publicacion);
                this.em.merge(academicoAuxiliar);
            }
        }
    }
    
    @Override
    public void Delete(List<Academico> academicos, Publicacion publicacion){
        for (int j = 0; j < academicos.size(); j++) {
            
            int Index = -1;
            for (int i = 0; i < publicacion.getAcademicos().get(j).getPublicaciones().size(); i++) {
                if(publicacion.getId() == publicacion.getAcademicos().get(j).getPublicaciones().get(i).getId()){
                    Index = i;
                    break;
                }
            }
            if(Index == -1){
                System.out.println("No se pudo Eliminar");
            }
            else{
                publicacion.getAcademicos().get(j).getPublicaciones().remove(Index);
                Academico Auxiliar = publicacion.getAcademicos().get(j);
                this.em.merge(Auxiliar);
            }
            
        }
        this.em.remove(em.merge(publicacion));
    }
}
