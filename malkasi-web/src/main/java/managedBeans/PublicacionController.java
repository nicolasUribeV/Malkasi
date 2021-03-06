package managedBeans;

import entities.Academico;
import entities.Publicacion;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import managedBeans.util.JsfUtil;
import managedBeans.util.JsfUtil.PersistAction;
import sessionbeans.AcademicoFacadeLocal;
import sessionbeans.PublicacionFacadeLocal;

@Named("publicacionController")
@SessionScoped
public class PublicacionController implements Serializable {

    @EJB
    private PublicacionFacadeLocal ejbFacade;
    @EJB
    private AcademicoFacadeLocal ejbFacadeAcademic;
    private List<Publicacion> items = null;
    private Publicacion selected;

    public PublicacionController() {
    }

    public Publicacion getSelected() {
        return selected;
    }

    public void deleteSelected() {
        this.selected = null;
    }

    public void setSelected(Publicacion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PublicacionFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Publicacion prepareCreate() {
        selected = new Publicacion();
        initializeEmbeddableKey();
        return selected;
    }

    public Publicacion prepareCreateWithAcademic(Academico academico) {
        this.selected = new Publicacion();
        this.selected.getAcademicos().add(academico);
        this.selected.setDoi("");
        this.selected.setEditorial("");
        this.selected.setInstitucion("");
        this.selected.setNombreCongreso("");
        this.selected.setNombrePublicacion("");
        this.selected.setNombreLibro("");
        this.selected.setPais("");
        this.selected.setReferencia("");
        this.selected.setUrl("");
        initializeEmbeddableKey();
        JsfUtil.redirect("/faces/roles/academico/publicacion/wizardCreate.xhtml");
        return selected;
    }

    public void prepareCreateViewAcademic(Academico academico) {
        selected = null;
        items = academico.getPublicaciones();
        JsfUtil.redirect("/faces/roles/academico/publicacion/List.xhtml");
    }

    public void prepareEditWithAcademic(Publicacion publicacion) {
        setSelected(ejbFacade.find(publicacion.getId()));
        JsfUtil.redirect("/faces/roles/academico/publicacion/Editar.xhtml");
    }

    public Publicacion EliminarPublicacion(Publicacion publicacion) {
        this.items.remove(publicacion);
        return publicacion;
    }

    public List<Academico> updateAcademicos() {
        return this.selected.getAcademicos();
    }

    public ArrayList<String> referenciasPorTipo(List<Publicacion> publicaciones, String Tipo) {
        ArrayList<String> pT = new ArrayList<>();
        String Aux;
        int cont = 1;
        for (int i = 0; i < publicaciones.size(); i++) {
            if (publicaciones.get(i).getTipoPublicacion().getNombreTipo().equals(Tipo)) {
                Aux = cont + ". " + getStringPublicacion(publicaciones.get(i));
                pT.add(Aux);
                cont++;
            }
        }
        if (publicaciones.isEmpty()) {
            pT.add("");
        }
        return pT;
    }

    public ArrayList<String> usuariosDePublicacion(Publicacion publicacion) {
        ArrayList<String> usuarios = new ArrayList<>();
        if (publicacion != null) {
            for (int i = 0; i < publicacion.getAcademicos().size(); i++) {
                usuarios.add(publicacion.getAcademicos().get(i).getNombres() + " " + publicacion.getAcademicos().get(i).getApellidos());
            }
        }
        return usuarios;
    }

    public ArrayList<String> usuariosExternosDePublicacion(Publicacion publicacion) {
        ArrayList<String> usuarios = new ArrayList<>();
        if (publicacion != null && publicacion.getAcademicosExternos() != null) {
            for (int i = 0; i < publicacion.getAcademicosExternos().size(); i++) {
                usuarios.add(publicacion.getAcademicosExternos().get(i).getNombres() + " " + publicacion.getAcademicosExternos().get(i).getApellidos());
            }
        }
        return usuarios;
    }

    public ArrayList<String> usuariosDePublicacion2() {
        ArrayList<String> usuarios = new ArrayList<>();
        for (int i = 0; i < selected.getAcademicos().size(); i++) {
            usuarios.add(selected.getAcademicos().get(i).getNombres() + " " + selected.getAcademicos().get(i).getApellidos());
        }
        return usuarios;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PublicacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        prepareCreate();
        //JsfUtil.redirect("/faces/roles/academico/index.xhtml");
    }

    public void comeBack() {
        JsfUtil.redirect("/faces/roles/academico/index.xhtml");
    }

    public void comeBackEdit() {
        selected = null;
        JsfUtil.redirect("/faces/roles/academico/publicacion/List.xhtml");
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PublicacionUpdated"));
    }

    public Publicacion CrearNueva(Publicacion publicacion) {
        this.selected = null;
        return publicacion;
    }

    public String getStringPublicacion(Publicacion publicacion) {
        String rP = "";
        if (publicacion != null) {
            if (publicacion.getAcademicoOrden() != null) {
                for (int i = 0; i < publicacion.getAcademicoOrden().size(); i++) {
                    String[] academico = publicacion.getAcademicoOrden().get(i).split("_");
                    String[] aux = academico[1].split(" ");
                    String name = academico[0];
                    String lastName = academico[1];
                    List<Academico> academicoRef = ejbFacadeAcademic.FindWithName(name, lastName);
                    String ref = "";
                    //identificación alias
                    int flag = 0;
                    if (academicoRef != null) {
                        if (academicoRef.get(0).getUserAlias() != null) {
                            if (!academicoRef.get(0).getUserAlias().equals("")) {
                                flag = 1;
                                ref = academicoRef.get(0).getUserAlias();
                            }
                        }
                    }
                    if(flag == 0){
                        ref = aux[0] + ", " + academico[0].charAt(0);
                    }
                    //Llenado de Ref
                    if (i == 0) {
                        rP = ref + ".";
                    } else {
                        if (i == publicacion.getAcademicoOrden().size() - 1) {
                            rP = rP + "& " + ref + ".";
                        } else {
                            rP = rP + ", " + ref + ".";
                        }
                    }
                }
            }
            rP = rP + " (" + publicacion.getAgno() + ").";
            rP = rP + ", " + publicacion.getNombrePublicacion();
            rP = rP + ", " + publicacion.getNombreLibro();
            rP = rP + ", " + publicacion.getEditorial();
            rP = rP + ", " + publicacion.getReferencia();
            rP = rP + ", " + publicacion.getNombreCongreso();
            rP = rP + ", " + publicacion.getInstitucion();
            rP = rP + ", " + publicacion.getPais();
            rP = rP + ", " + publicacion.getUrl();
            rP = rP + ", " + publicacion.getDoi();
            rP = rP + ".";
        }
        return eliminaComas(rP);
    }

    public String eliminaComas(String palabra) {
        String palabraNueva;
        String palabraAntigua = palabra;
        while (true) {
            palabraNueva = palabraAntigua.replace(", ,", ",");
            if (palabraAntigua.length() == palabraNueva.length()) {
                break;
            }
            palabraAntigua = palabraNueva;
        }
        palabraNueva = palabraAntigua.replace(", .", ".");
        return palabraNueva;
    }

    public boolean academicoCheck(Academico academico, Academico current) {
        if (academico.getId() == current.getId()) {
            return true;
        }
        return false;
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PublicacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Publicacion> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void refresh() {
        prepareCreate();
        items = getFacade().findAll();
    }

    public void refresh2(Publicacion p) {
        if (p != null) {
            selected = p;
        }
        items = getFacade().findAll();
    }

    public boolean isEmptySelected() {
        return selected == null;
    }

    public Boolean tienePublicaciones(List<Publicacion> publicaciones, String Tipo) {
        for (int i = 0; i < publicaciones.size(); i++) {
            if (publicaciones.get(i).getTipoPublicacion().getNombreTipo().equals(Tipo)) {
                return false;
            }
        }
        return true;
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

    public Publicacion getPublicacion(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Publicacion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Publicacion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    private void addMessage(String ha_agregado_correctamente_la_actividad_ac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FacesConverter(forClass = Publicacion.class)
    public static class PublicacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PublicacionController controller = (PublicacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "publicacionController");
            return controller.getPublicacion(getKey(value));
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
            if (object instanceof Publicacion) {
                Publicacion o = (Publicacion) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Publicacion.class.getName()});
                return null;
            }
        }

    }

}
