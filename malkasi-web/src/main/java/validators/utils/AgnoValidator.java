/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators.utils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author ihc
 */
@FacesValidator("AgnoValidator")
public class AgnoValidator implements Validator{
    
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        int Year = 0; 
        try{
             Year = (int) o;
        }
        catch(Exception e){
             
        }
        //
        //int Year = Calendar.getInstance().get(Calendar.YEAR);
        
//        @Min(10) @Max(20)
//        int year;
        if(!validateYear(Year)) {
            FacesMessage msg = new FacesMessage( "Invalid Year format.","Año inválido.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(":PublicacionCreateForm:agno", msg);
            throw new ValidatorException(msg);
        }
        
    }
    
    public static boolean validateYear(int Year){
        int i = Calendar.getInstance().get(Calendar.YEAR);
        if(Year >= (i-80) && Year <= i){
            return true;
        }
        else{
            return false;
        }
    }
    
}
