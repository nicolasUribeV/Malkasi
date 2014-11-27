/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.AcademicoExterno;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author roberto
 */
@Stateless
public class AcademicoExternoFacade extends AbstractFacade<AcademicoExterno> implements AcademicoExternoFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_malkasi-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcademicoExternoFacade() {
        super(AcademicoExterno.class);
    }
    
    @Override
    public boolean FindWithNombreApellido(String nombres, String apellidos, long id){
        Query q = em.createQuery("SELECT c FROM AcademicoExterno c WHERE c.nombres = :nombres AND c.apellidos = :apellidos AND c.id <> :id");
        q.setParameter("nombres", nombres);
        q.setParameter("apellidos", apellidos);
        q.setParameter("id", id);
        System.out.println("Resultados encontrados: " + q.getResultList().size());
        if(q.getResultList().size() == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
