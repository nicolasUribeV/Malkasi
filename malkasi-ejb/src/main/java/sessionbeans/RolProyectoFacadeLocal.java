/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.RolProyecto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author elias
 */
@Local
public interface RolProyectoFacadeLocal {

    void create(RolProyecto rolProyecto);

    void edit(RolProyecto rolProyecto);

    void remove(RolProyecto rolProyecto);

    RolProyecto find(Object id);

    List<RolProyecto> findAll();

    List<RolProyecto> findRange(int[] range);

    int count();
    
}
