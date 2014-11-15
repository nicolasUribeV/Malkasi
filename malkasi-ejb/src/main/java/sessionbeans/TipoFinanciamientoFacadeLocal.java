/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.TipoFinanciamiento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cristobal
 */
@Local
public interface TipoFinanciamientoFacadeLocal {

    void create(TipoFinanciamiento tipoFinanciamiento);

    void edit(TipoFinanciamiento tipoFinanciamiento);

    void remove(TipoFinanciamiento tipoFinanciamiento);

    TipoFinanciamiento find(Object id);

    List<TipoFinanciamiento> findAll();

    List<TipoFinanciamiento> findRange(int[] range);

    int count();
    
}
