/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.GradoAcademico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Nico_
 */
@Local
public interface GradoAcademicoFacadeLocal {

    void create(GradoAcademico gradoAcademico);

    void edit(GradoAcademico gradoAcademico);

    void remove(GradoAcademico gradoAcademico);

    GradoAcademico find(Object id);

    List<GradoAcademico> findAll();

    List<GradoAcademico> findRange(int[] range);

    int count();
    
}
