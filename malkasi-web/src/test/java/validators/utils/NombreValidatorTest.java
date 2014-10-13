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

/**
 *
 * @author ihc
 */
public class NombreValidatorTest {
    
    public NombreValidatorTest() {
    }


    @Test
    public void testValidarNombre() {
        System.out.println("validarNombre");
        String nombre = "";
        boolean result = NombreValidator.validarNombre(nombre);
        assertFalse("error",result);
        
        nombre = "Daniel";
        result = NombreValidator.validarNombre(nombre);
        assertTrue("error un nombre",result);
        
        nombre = "daniel esteban";
        result = NombreValidator.validarNombre(nombre);
        assertTrue("error dos nombre",result);
        
        nombre = "daniel34232423 esteban";
        result = NombreValidator.validarNombre(nombre);
        assertFalse("error dos nombre",result);
        
        nombre = "dani.....l esteban";
        result = NombreValidator.validarNombre(nombre);
        assertFalse("error dos nombre",result);
        
        nombre = "DiASFel Esteban";
        result = NombreValidator.validarNombre(nombre);
        assertTrue("error dos nombre",result);
    }
    
}
