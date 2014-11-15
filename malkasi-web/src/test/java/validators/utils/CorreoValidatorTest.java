/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author ihc
 */
@Ignore
public class CorreoValidatorTest {
    
    public CorreoValidatorTest() {
    }

    /**
     * Test of validateEmail method, of class CorreoValidator.
     */
    @Test
    @Ignore
    public void testValidateEmail() {
        System.out.println("validateEmail");
        String email = "";
        boolean result = CorreoValidator.validateEmail(email);
        assertFalse("error vacio", result);
                
        email = "d.mora91@hotmail.com";
        result = CorreoValidator.validateEmail(email);
        assertTrue("error normal", result);
                
        email = "nicolas.uribe@usach.cl";
        result = CorreoValidator.validateEmail(email);
        assertTrue("error normal 2", result);
                
        email = "este correo esta malo";
        result = CorreoValidator.validateEmail(email);
        assertFalse("error oracion", result);
        
        email = "nicolas.uribeusach.cl";
        result = CorreoValidator.validateEmail(email);
        assertFalse("error falta @", result);
        
                
        email = "nicolas.uribe@usachcl";
        result = CorreoValidator.validateEmail(email);
        assertFalse("error falta punto", result);
        
                
        email = "nicolas.uribe@hotmail";
        result = CorreoValidator.validateEmail(email);
        assertFalse("error raro despues punto", result);
    }
    
}
