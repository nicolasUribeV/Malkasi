/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Academico;
import entities.Publicacion;
import javax.ejb.Local;

/**
 *
 * @author Nico_
 */
@Local
public interface AcademicoPublicacionFacadeLocal {

    void Create(Academico Academico, Publicacion Publicacion);

    void Delete(Academico Academico, Publicacion Publicacion);
    
}
