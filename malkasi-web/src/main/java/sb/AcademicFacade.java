/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sb;

import entities.Academic;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Nico_
 */
@Stateless
public class AcademicFacade extends AbstractFacade<Academic> {
    @PersistenceContext(unitName = "com.mycompany_malkasi-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcademicFacade() {
        super(Academic.class);
    }
    
}
