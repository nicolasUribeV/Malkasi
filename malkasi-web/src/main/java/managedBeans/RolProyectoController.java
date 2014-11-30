/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Academico;
import entities.RolProyecto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import sessionbeans.AcademicoFacadeLocal;
import sessionbeans.RolProyectoFacadeLocal;

/**
 *
 * @author elias
 */
@Named(value = "rolProyectoController")
@SessionScoped
public class RolProyectoController implements Serializable {

    @EJB
    private RolProyectoFacadeLocal ejbFacade;
    private AcademicoFacadeLocal ejbFacadeAcademic;
    private List<Academico> items = null;

    public RolProyectoController() {
    }

    public ArrayList<String> listaAcademicosProyecto(long id) {
        System.out.println("ID es: " + id);
        ArrayList<String> academicos = new ArrayList<>();
        List<RolProyecto> proyectos = ejbFacade.findAll();
        for (int i = 0; i < proyectos.size(); i++) {
            if (proyectos.get(i).getProyecto().getId() == id) {
                String a = proyectos.get(i).getRol() + ": " + proyectos.get(i).getAcademico().getNombres() + " " + proyectos.get(i).getAcademico().getApellidos();
                academicos.add(a);
            }
        }
        return academicos;
    }

}
