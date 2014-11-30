/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author cristobal
 */
@Named
@RequestScoped
public class HomeController {
    
    private List<String> images;
    
    public HomeController(){}
    
    @PostConstruct
    public void init(){
        images = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            images.add("home" + i + ".jpg");
            System.out.println("imagen" + images.get(i));
        }
        
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
    
}
