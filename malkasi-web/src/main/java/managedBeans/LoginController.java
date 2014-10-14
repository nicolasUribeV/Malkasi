/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBeans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import managedBeans.util.JsfUtil;
import managedBeans.util.SessionUtilTest;

/**
 *
 * @author Nico_
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @Inject 
    SessionUtilTest sessionUtilTest;
    
    private String userName;
    
    private String password;
    
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void login() {
        System.out.println("Calling login for " + userName);
        if (sessionUtilTest.login(userName)) {
            System.out.println("SessionController: Usuaro logeado de forma exitosa: " + userName);
            JsfUtil.redirect(sessionUtilTest.route66());
        } 
        else {
            System.out.println("SessionController: Login fail");
            JsfUtil.addErrorMessage("El RUT y/o contraseña no coinciden");
        }
    }
    
    public void logout() {
        sessionUtilTest.logout();
        JsfUtil.redirect(sessionUtilTest.logout());
    }
    
}
