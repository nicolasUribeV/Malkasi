/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBeans.util;

import entities.Academico;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import sessionbeans.AcademicoFacadeLocal;

/**
 *
 * @author Nico_
 */
@Named(value = "sessionUtilTest")
@SessionScoped
public class SessionUtilTest implements Serializable {

    @EJB
    private AcademicoFacadeLocal ejbFacade;
    
    private String rut;
    
    private Academico currentUser;

    public SessionUtilTest() {
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Academico getCurrentUser() {
        currentUser = ejbFacade.FindWithRut(rut).get(0);
        return currentUser;
    }

    public void setCurrentUser(Academico currentUser) {
        this.currentUser = currentUser;
    }
    
    public boolean login(String rut) {
        if(ejbFacade.FindWithRut(rut) == null){
            return false;
        }
        else{
            this.rut = rut;
            return true;
        }
    }
    
    public String logout() {
        this.rut = null;
        this.currentUser = null;
        System.out.println("SessionUtil: Logout for " + rut);
        return "/faces/index.xhtml";
    }
    
        public String route66() {
        System.out.println("Calling SessionUtil.route66");
        
        Academico currentAccount = ejbFacade.FindWithRut(rut).get(0);
        if(currentAccount.getTipoCuenta().compareTo("admin") == 0) {
            return "/faces/roles/admin/index.xhtml";
        }
        if(currentAccount.getTipoCuenta().compareTo("academico") == 0) {
            return "/faces/roles/academico/index.xhtml";
        }
        return "/faces/roles/index.xhtml";    
    }
    
}
