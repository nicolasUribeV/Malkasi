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
public class AgnoValidatorTest {
    
    public AgnoValidatorTest() {
    }

    /**
     * Test of validateYear method, of class AgnoValidator.
     */
    @Test
    @Ignore
    public void testValidateYear() {
        //System.out.println("validateYear");
        int Year = 0;
        boolean result = AgnoValidator.validateYear(Year);
        assertFalse("año menor", result);
        
        Year = 2000;
        result = AgnoValidator.validateYear(Year);
        assertTrue("año mayor", result);
    }
    
}
