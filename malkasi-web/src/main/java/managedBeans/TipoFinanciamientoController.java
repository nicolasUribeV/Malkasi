package managedBeans;

import entities.TipoFinanciamiento;
import managedBeans.util.JsfUtil;
import managedBeans.util.PaginationHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import sessionbeans.TipoFinanciamientoFacadeLocal;

@Named("tipoFinanciamientoController")
@SessionScoped
public class TipoFinanciamientoController implements Serializable {

    private TipoFinanciamiento current;
    private DataModel items = null;
    @EJB
    private TipoFinanciamientoFacadeLocal ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<TipoFinanciamiento> listaTipoFinanciamiento = null;

    public TipoFinanciamientoController() {
    }

    public List<TipoFinanciamiento> getListaTipoFinanciamiento() {
        if (listaTipoFinanciamiento == null) {
            listaTipoFinanciamiento = getFacade().findAll();
        }
        return listaTipoFinanciamiento;
    }

    public void setListaTipoFinanciamiento(List<TipoFinanciamiento> listaTipoFinanciamiento) {
        this.listaTipoFinanciamiento = listaTipoFinanciamiento;
    }

    public TipoFinanciamiento getSelected() {
        if (current == null) {
            current = new TipoFinanciamiento();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    public void refresh() {
        listaTipoFinanciamiento = getFacade().findAll();
    }

    private TipoFinanciamientoFacadeLocal getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (TipoFinanciamiento) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TipoFinanciamiento();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoFinanciamientoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TipoFinanciamiento) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoFinanciamientoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TipoFinanciamiento) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoFinanciamientoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

   public List<TipoFinanciamiento> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TipoFinanciamiento> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public TipoFinanciamiento getTipoFinanciamiento(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TipoFinanciamiento.class)
    public static class TipoFinanciamientoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoFinanciamientoController controller = (TipoFinanciamientoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoFinanciamientoController");
            return controller.getTipoFinanciamiento(getKey(value));
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
            if (object instanceof TipoFinanciamiento) {
                TipoFinanciamiento o = (TipoFinanciamiento) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoFinanciamiento.class.getName());
            }
        }

    }

}
