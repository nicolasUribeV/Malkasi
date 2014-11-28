/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators.utils;

import entities.Academico;
import java.util.List;

/**
 *
 * @author cristobal
 */
public class ValidationUtils {
    
    private ValidationUtils(){
    }
    
    public static boolean existUserName(String academico, List<Academico> academicos){
        
        if (academicos == null) return false;
        
        for(Academico a : academicos){
            if (a.getUserName().equals(academico))
                return true;
        
        }
        return false;
        
    }
    
}
