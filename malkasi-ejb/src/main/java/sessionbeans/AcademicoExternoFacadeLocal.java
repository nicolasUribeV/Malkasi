/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.AcademicoExterno;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roberto
 */
@Local
public interface AcademicoExternoFacadeLocal {

    void create(AcademicoExterno academicoExterno);

    void edit(AcademicoExterno academicoExterno);

    void remove(AcademicoExterno academicoExterno);

    AcademicoExterno find(Object id);

    List<AcademicoExterno> findAll();

    List<AcademicoExterno> findRange(int[] range);

    int count();
    
    boolean FindWithNombreApellido(String nombres, String apellidos, long id);
}
