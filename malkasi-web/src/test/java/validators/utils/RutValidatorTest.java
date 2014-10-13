/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ihc
 */
public class RutValidatorTest {
    
    public RutValidatorTest() {
        
    }
    
    @Test
    public void testValidator(){
        String test1 = "16416-7";                
        boolean restTest1 = RutValidator.validarRut(test1);
        assertFalse("Error prueba con guion", restTest1);
        test1 = "1....7.6.1.5.5.1..3.2.......";
        restTest1 = RutValidator.validarRut(test1);
        assertTrue("Error prueba con puntos", restTest1);
        test1 = "1-7-6-1-5-5-1--3-2-";
        restTest1 = RutValidator.validarRut(test1);
        assertTrue("Error prueba guiones", restTest1);
        test1 = "176155132";
        restTest1 = RutValidator.validarRut(test1);
        assertTrue("Error prueba normal", restTest1);
        test1 = "19";
        restTest1 = RutValidator.validarRut(test1);
        assertTrue("Error prueba basica", restTest1);
        test1 = "1.7.6...1..55----1--3..2-..--..-.--.";
        restTest1 = RutValidator.validarRut(test1);
        assertTrue("Error prueba basica", restTest1);
        test1 = "777777777777";
        restTest1 = RutValidator.validarRut(test1);
        assertFalse("Error prueba full 7", restTest1);
    }

    
}
