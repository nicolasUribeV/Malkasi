package managedBeans;

import entities.Academico;
import entities.AcademicoExterno;
import entities.Publicacion;
import managedBeans.util.JsfUtil;
import managedBeans.util.JsfUtil.PersistAction;
import sessionbeans.AcademicoExternoFacadeLocal;

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
import sessionbeans.PublicacionFacadeLocal;

@Named("academicoExternoController")
@SessionScoped
public class AcademicoExternoController implements Serializable {

    @EJB
    private AcademicoExternoFacadeLocal ejbFacade;
    @EJB
    private PublicacionFacadeLocal publicacionFacade;
    private List<AcademicoExterno> items = null;
    private AcademicoExterno selected;

    public AcademicoExternoController() {
    }

    public AcademicoExterno getSelected() {
        return selected;
    }

    public void setSelected(AcademicoExterno selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AcademicoExternoFacadeLocal getFacade() {
        return ejbFacade;
    }

    public AcademicoExterno prepareCreate() {
        selected = new AcademicoExterno();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AcademicoExternoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {

        AcademicoExterno ae = ejbFacade.find(this.selected.getId());
        List<Publicacion> publicaciones = publicacionFacade.findAll();
        for (int i = 0; i < publicaciones.size(); i++) {
            ArrayList<String> orden = publicaciones.get(i).getAcademicoOrden();
            if (orden != null) {
                int index = -1; 
                for (int j = 0; j < orden.size(); j++) {
                    String[] name = orden.get(j).split("_");
                    if(ae.getNombres().equals(name[0]) && ae.getApellidos().equals(name[1])){
                        System.out.println("Encontré a " +name[0] +" "+name[1]);
                        index = j; 
                    }
                }
                if(index != -1){
                    String nuevo = this.selected.getNombres()+"_"+this.selected.getApellidos();
                    orden.set(index, nuevo);
                    publicaciones.get(i).setAcademicoOrden(orden);
                    publicacionFacade.edit(publicaciones.get(i));
                }
            }
        }
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AcademicoExternoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AcademicoExternoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AcademicoExterno> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public AcademicoExterno prepareCreateViewAcademicExternal(Academico academico) {
        selected = null;
        //items = academico.getPublicaciones();
        JsfUtil.redirect("/faces/roles/academico/externos/List.xhtml");
        return selected;
    }

    public void comeBack() {
        JsfUtil.redirect("/faces/roles/academico/index.xhtml");
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

    public AcademicoExterno getAcademicoExterno(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<AcademicoExterno> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AcademicoExterno> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AcademicoExterno.class)
    public static class AcademicoExternoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AcademicoExternoController controller = (AcademicoExternoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "academicoExternoController");
            return controller.getAcademicoExterno(getKey(value));
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
            if (object instanceof AcademicoExterno) {
                AcademicoExterno o = (AcademicoExterno) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AcademicoExterno.class.getName()});
                return null;
            }
        }

    }

}
