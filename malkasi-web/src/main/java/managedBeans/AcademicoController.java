package managedBeans;

import entities.Academico;
import entities.Categoria;
import entities.TipoPublicacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import managedBeans.util.JsfUtil;
import managedBeans.util.JsfUtil.PersistAction;
import org.primefaces.model.DualListModel;
import sessionbeans.AcademicoFacadeLocal;

@Named("academicoController")
@SessionScoped
public class AcademicoController implements Serializable {

    @EJB
    private AcademicoFacadeLocal ejbFacade;
    private List<Academico> items = null;
    private Academico selected;
    private ArrayList<Academico> academicsCategory;

    public ArrayList<Academico> getAcademicsCategory() {
        return academicsCategory;
    }

    public void listCategory(Categoria categ) {
        academicsCategory = new ArrayList<Academico>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getCategoria().getId() == categ.getId()) {
                academicsCategory.add(items.get(i));
            }
        }
    }

    public void setAcademicsCategory(ArrayList<Academico> academicsCategory) {
        this.academicsCategory = academicsCategory;
    }

    public AcademicoController() {
    }

    public Academico getSelected() {
        return selected;
    }

    public void setSelected(Academico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AcademicoFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Academico prepareCreate() {
        selected = new Academico();
        initializeEmbeddableKey();
        return selected;
    }

    public String rutFinal() {
        String nuevoRut = selected.getRut().replace("-", "");
        nuevoRut = nuevoRut.replace(".", "");
        nuevoRut = nuevoRut.replace("k", "K");
        return nuevoRut;
    }

    public void create() {
        String rutNuevo = rutFinal();
        selected.setRut(rutNuevo);
        selected.setTipoCuenta("academico");
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AcademicoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        String rutNuevo = rutFinal();
        selected.setRut(rutNuevo);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AcademicoUpdated"));
    }

    public void updateProfile(Academico academico) {
        Academico a = ejbFacade.find(academico.getId());
        System.out.println("Editando: " + a.getNombres() + " " + a.getApellidos());
        ejbFacade.edit(a);
    }
    
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AcademicoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    public List<Academico> getItems() {
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

    public Academico getAcademico(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Academico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Academico> getItemsAvailableSelectMany2() {
        List<Academico> Aux = getFacade().findAll();
        ArrayList<Integer> Index = new ArrayList<>();
        for (int i = 0; i < Aux.size(); i++) {
            if (Aux.get(i).getTipoCuenta().equals("admin")) {
                Index.add(i);
            }
        }
        int count = 0;
        for (int i = 0; i < Index.size(); i++) {
            Aux.remove(Index.get(i) - count);
            count++;
        }
        return Aux;
    }

    public List<Academico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public void refresh() {
        selected = null;
        items = getFacade().findAll();
    }

    @FacesConverter(forClass = Academico.class)
    public static class AcademicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AcademicoController controller = (AcademicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "academicoController");
            return controller.getAcademico(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Academico) {
                Academico o = (Academico) object;
                return getStringKey(o.getRut());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Academico.class.getName()});
                return null;
            }
        }
    }
}
