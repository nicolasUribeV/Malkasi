    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name = "Publicacion.findAll", query = "SELECT a FROM Publicacion a")
})

public class Publicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombrePublicacion;

    private String pais;

    private int agno;

    private String nombreLibro;

    private String referencia;

    private String editorial;

    private String url;

    private String nombreCongreso;

    private String institucion;

    @Column(length = 1500)
    private String abstractP;

    @ManyToOne(optional = false)
    private TipoPublicacion tipoPublicacion;

    @ManyToMany
    @JoinTable(name = "ACADEMICO_PUBLICACION",
            joinColumns = {
                @JoinColumn(name = "publicaciones_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "academicos_ID")})
    private List<Academico> academicos;

    private String doi;

    @ManyToMany
    @JoinTable(name = "ACADEMICOE_PUBLICACION",
            joinColumns = {
                @JoinColumn(name = "publicaciones_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "academicosExternos_ID")})
    private List<AcademicoExterno> academicosExternos;

    private String academicoOrden;

    public ArrayList<String> getAcademicoOrden() {
        ArrayList<String> listaOrden = new ArrayList<String>();
        if(academicoOrden == null){
            return null;
        }
        String palabra[] = academicoOrden.split(";");
        for (int i = 0; i < palabra.length; i++) {
            listaOrden.add(palabra[i]);
        }
        return listaOrden;
    }
    
    public void setAcademicoOrden(ArrayList<String> listaAcademicos) {
        academicoOrden = "";
        for (int i = 0; i < listaAcademicos.size(); i++) {
            if(i == 0){
                academicoOrden = listaAcademicos.get(i);
            }
            else{
                academicoOrden = academicoOrden + ";" + listaAcademicos.get(i);
            }
        }
    }

    public Publicacion() {
        if (academicos == null) {
            academicos = new ArrayList<Academico>();
        }
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public List<AcademicoExterno> getAcademicosExternos() {
        return academicosExternos;
    }

    public void setAcademicosExternos(List<AcademicoExterno> academicosExternos) {
        this.academicosExternos = academicosExternos;
    }

    @PersistenceContext(unitName = "com.mycompany_malkasi-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public void edit(Object object) {
        em.merge(object);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicacion)) {
            return false;
        }
        Publicacion other = (Publicacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Publicacion[ id=" + id + " ]";
    }

    public String getNombrePublicacion() {
        return nombrePublicacion;
    }

    public int getAgno() {
        return agno;
    }

    public void setAgno(int agno) {
        this.agno = agno;
    }

    public void setNombrePublicacion(String nombrePublicacion) {
        this.nombrePublicacion = nombrePublicacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombreCongreso() {
        return nombreCongreso;
    }

    public void setNombreCongreso(String nombreCongreso) {
        this.nombreCongreso = nombreCongreso;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public List<Academico> getAcademicos() {
        return academicos;
    }

    public void setAcademicos(List<Academico> academicos) {
        this.academicos = academicos;
    }

    public String getAbstractP() {
        return abstractP;
    }

    public void setAbstractP(String abstractP) {
        this.abstractP = abstractP;
    }

}
