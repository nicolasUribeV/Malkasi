
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
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

    private String userName = null;

    private Academico currentUser;

    public SessionUtilTest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Academico getCurrentUser() {
        currentUser = ejbFacade.FindWithUserName(userName).get(0);
        return currentUser;
    }

    public void setCurrentUser(Academico currentUser) {
        this.currentUser = currentUser;
    }

    public boolean login(String username, String password) {
        
        //FacesContext context = FacesContext.getCurrentInstance();
        //ExternalContext externalContext = context.getExternalContext();
        //HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            //request.login(username, password);
            System.out.println("Usuario" + userName);
            if (ejbFacade.FindWithUserName(username) == null) {
                JsfUtil.addErrorMessage("Usuario no registrado en el sistema");
                logout();
                return false;
            }
            else{
                this.userName = username;
                return true;
            }
        } catch (Exception e) {
            //arriba iba un ServletException
            // Handle unknown username/password in request.login().
//            context.addMessage(null, new FacesMessage("Nombre usuario o contraseña incorrecto"));
            
            return false;
        }
    }

    public String logout() {
        this.userName = null;
        this.currentUser = null;
        //ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        //externalContext.invalidateSession();
        System.out.println("SessionUtil: Logout for " + userName);
        return "/faces/index.xhtml";
    }

    public String route66() {
        System.out.println("Calling SessionUtil.route66");

        Academico currentAccount = ejbFacade.FindWithUserName(userName).get(0);
        if (currentAccount.getTipoCuenta().compareTo("admin") == 0) {
            return "/faces/roles/admin/index.xhtml";
        }
        if (currentAccount.getTipoCuenta().compareTo("academico") == 0) {
            return "/faces/roles/academico/index.xhtml";
        }
        return "/faces/roles/index.xhtml";
    }

    public boolean hasIdentity() {
        return this.userName != null;
    }
    
    public boolean isAdmin(){
        Academico currentAccount = ejbFacade.FindWithUserName(userName).get(0);
        return currentAccount.getTipoCuenta().compareTo("admin") == 0;
    }

}
