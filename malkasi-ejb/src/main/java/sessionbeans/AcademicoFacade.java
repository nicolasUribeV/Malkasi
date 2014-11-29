/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

//import com.sun.istack.internal.logging.Logger;
import entities.Academico;
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
public class AcademicoFacade extends AbstractFacade<Academico> implements AcademicoFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_malkasi-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcademicoFacade() {
        super(Academico.class);
    }
    
    @Override
    public List<Academico> FindWithUserName(String userName){
        Query q = em.createQuery("SELECT c FROM Academico c WHERE c.userName = :userName");
        q.setParameter("userName", userName);
        if(q.getResultList().size() == 0){
            return null;
        }
        else{
            return q.getResultList();
        }
    }
    
    @Override
    public List<Academico> FindWithRut(String rut){
        Query q = em.createQuery("SELECT c FROM Academico c WHERE c.rut = :rut");
        q.setParameter("rut", rut);
        if(q.getResultList().size() == 0){
            return null;
        }
        else{
            return q.getResultList();
        }
    }
    
    @Override
    public void actualizarPerfil(Academico academico){
        em.persist(academico);
    }
}
