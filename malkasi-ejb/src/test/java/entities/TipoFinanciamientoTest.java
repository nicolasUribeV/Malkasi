/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cristobal
 */
public class TipoFinanciamientoTest {
    
    public TipoFinanciamientoTest() {
    }

    /**
     * Test of getNombreFinanciamiento method, of class TipoFinanciamiento.
     */
    @Test
    public void testGetNombreFinanciamiento() {
        System.out.println("getNombreFinanciamiento");
        TipoFinanciamiento instance = new TipoFinanciamiento();
        instance.setId(Long.parseLong("1"));
        instance.setNombreFinanciamiento("Corfo");
        String expResult = "Corfo";
        String result = instance.getNombreFinanciamiento();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
}
