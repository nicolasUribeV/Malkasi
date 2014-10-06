/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Academic;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Nico_
 */
@Stateless
public class AcademicFacade extends AbstractFacade<Academic> implements AcademicFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_malkasi-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcademicFacade() {
        super(Academic.class);
    }
    
}
