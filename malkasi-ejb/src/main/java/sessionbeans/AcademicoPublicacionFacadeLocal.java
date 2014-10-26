/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Academico;
import entities.Publicacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Nico_
 */
@Local
public interface AcademicoPublicacionFacadeLocal {

    void Create(List<Academico> academicos, Publicacion Publicacion,Academico academico);
    
    void Update(List<Academico> academicos, Publicacion Publicacion,Academico academico);

    void Delete(List<Academico> academicos, Publicacion Publicacion,Academico academico);
    
    List<Academico> findAll();
    
}
