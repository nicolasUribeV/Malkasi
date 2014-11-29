/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Proyecto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import sessionbeans.ProyectoFacadeLocal;

/**
 *
 * @author cristobal
 */
@Named
@SessionScoped
public class BuscadorController implements Serializable {

    @EJB
    private ProyectoFacadeLocal ejbFacade;
    private List<Proyecto> buscados;
    private String buscado;
    private String opcion;

    public BuscadorController() {
    }

    @PostConstruct
    public void init() {
        buscados = ejbFacade.findAll();
    }

    public void refresh() {
        buscado = null;
        opcion = null;
        buscados = ejbFacade.findAll();
    }

    public void searchProyect() {

        System.out.println("opcion " + opcion + "buscado " + buscado);

        switch (opcion) {
            case "todos":
                buscados = ejbFacade.findAll();

                break;

            case "codigo":

                buscados = ejbFacade.busquedaProyectoCodigo(buscado);

                break;

            case "nombreProyecto":

                buscados = ejbFacade.busquedaProyectoNombre(buscado);

                break;

            default:

                break;
        }

    }

    public List<Proyecto> getBuscados() {
        return buscados;
    }

    public void setBuscados(List<Proyecto> buscados) {
        this.buscados = buscados;
    }

    public String getBuscado() {
        return buscado;
    }

    public void setBuscado(String buscado) {
        this.buscado = buscado;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }



}
