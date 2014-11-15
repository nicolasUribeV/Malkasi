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
import javax.ejb.Local;

/**
 *
 * @author cristobal
 */
@Local
public interface ProyectoFacadeLocal {

    void create(Proyecto proyecto);

    void edit(Proyecto proyecto);

    void remove(Proyecto proyecto);

    Proyecto find(Object id);

    List<Proyecto> findAll();

    List<Proyecto> findRange(int[] range);

    int count();
    
    public void Create(Proyecto proyecto, List<RolProyecto> roles);
}
