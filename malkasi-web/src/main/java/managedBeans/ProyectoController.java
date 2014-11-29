package managedBeans;

import entities.Proyecto;
import entities.RolProyecto;
import managedBeans.util.JsfUtil;
import managedBeans.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessionbeans.ProyectoFacadeLocal;

@Named("proyectoController")
@SessionScoped
public class ProyectoController implements Serializable {

    @EJB
    private ProyectoFacadeLocal ejbFacade;
    private List<Proyecto> items = null;
    private Proyecto selected;

    public ProyectoController() {
    }

    public Proyecto getSelected() {
        return selected;
    }

    public void setSelected(Proyecto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProyectoFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Proyecto prepareCreate() {
        selected = new Proyecto();
        initializeEmbeddableKey();
        JsfUtil.redirect("/faces/roles/academico/proyectos/crear.xhtml");
        return selected;
    }

    public Proyecto prepareEdit(Proyecto proyecto) {
        JsfUtil.redirect("/faces/roles/academico/proyectos/editar.xhtml");
        return proyecto;
    }

    public Proyecto prepareCreateViewProyecto() {
        selected = null;
        //items = academico.getPublicaciones();
        JsfUtil.redirect("/faces/roles/academico/proyectos/List.xhtml");
        return selected;
    }

    public Proyecto viewProyecto(Proyecto proyecto) {
        selected = proyecto;
        JsfUtil.redirect("/faces/roles/academico/proyectos/ver.xhtml");
        return selected;
    }

    public Proyecto viewProyectoAdmin(Proyecto proyecto) {
        selected = proyecto;
        JsfUtil.redirect("/faces/roles/admin/proyectos/View.xhtml");
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProyectoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProyectoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProyectoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Proyecto> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Proyecto getProyecto(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Proyecto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Proyecto> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public Boolean tieneProyectos(List<RolProyecto> proyectos) {
        if (proyectos == null) {
            return false;
        } else if (proyectos.isEmpty()) {
            return false;
        }
        return true;
    }

    public ArrayList<String> referenciasProyectos(List<RolProyecto> proyectos) {
        ArrayList<String> pT = new ArrayList<>();
        String Aux;
        int cont = 1;
        for (int i = 0; i < proyectos.size(); i++) {
            Aux = cont + ". " + getStringProyecto(proyectos.get(i));
            pT.add(Aux);
            cont++;
        }
        if (proyectos.isEmpty()) {
            pT.add("");
        }
        return pT;
    }

    public String getStringProyecto(RolProyecto proyecto) {
        String rP = "";
        if (proyecto != null) {
            rP = "\"" + proyecto.getProyecto().getNombreProyecto() + "\", ";
            rP = rP + proyecto.getRol() + ", ";
            rP = rP + proyecto.getProyecto().getTipoFinanciamiento().getNombreFinanciamiento() + ", ";
            rP = rP + "codigo " + proyecto.getProyecto().getCodigoProyecto() + ".";
        }
        return rP;
    }

    @FacesConverter(forClass = Proyecto.class)
    public static class ProyectoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProyectoController controller = (ProyectoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "proyectoController");
            return controller.getProyecto(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Proyecto) {
                Proyecto o = (Proyecto) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Proyecto.class.getName()});
                return null;
            }
        }

    }

    public Proyecto prepareViewProyect(Proyecto proyecto) {
        JsfUtil.redirect("/faces/roles/admin/proyectos/View.xhtml");
        return proyecto;
    }
}
