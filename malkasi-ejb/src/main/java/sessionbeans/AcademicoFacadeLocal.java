/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Academico;
import java.util.List;
import javax.ejb.Local;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Nico_
 */
@Local
public interface AcademicoFacadeLocal {

    void create(Academico academico);

    void edit(Academico academico);

    void remove(Academico academico);

    Academico find(Object id);

    List<Academico> findAll();

    List<Academico> findRange(int[] range);

    int count();

    List<Academico> FindWithUserName(String userName);
}
