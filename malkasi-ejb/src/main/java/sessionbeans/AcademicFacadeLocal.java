/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Academic;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Nico_
 */
@Local
public interface AcademicFacadeLocal {

    void create(Academic academic);

    void edit(Academic academic);

    void remove(Academic academic);

    Academic find(Object id);

    List<Academic> findAll();

    List<Academic> findRange(int[] range);

    int count();
    
}
