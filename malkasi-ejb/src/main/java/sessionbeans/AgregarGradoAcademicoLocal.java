/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Academico;
import entities.GradoAcademico;
import javax.ejb.Local;

/**
 *
 * @author Nico_
 */
@Local
public interface AgregarGradoAcademicoLocal {
    
     void Create(Academico Academico, GradoAcademico GradoAcademico);

     void Delete(Academico Academico, GradoAcademico GradoAcademico);
     
}
