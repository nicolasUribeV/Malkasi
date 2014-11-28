/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author elias
 */
@Entity
public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Size(min = 1, message="El código del proyecto no puede estar vacío")
    private String codigoProyecto;
    
    @Size(min = 1, message="El nombre del proyecto no puede estar vacío")
    private String nombreProyecto;
    
    @Column(length=1500)
    private String descripcionProyecto;
    
    
    @Temporal(TemporalType.DATE)
    private Date fechaInicioProyecto;
    
    private int duracionProyecto;
    
    @Size(min = 1, message="El estado del proyecto no puede estar vacío")
    private String estadoProyecto;
    
    @ManyToOne(optional= false)
    private TipoFinanciamiento tipoFinanciamiento;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private List<RolProyecto> academicos;

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
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "id=" + id + ", codigoProyecto=" + codigoProyecto + ", nombreProyecto=" + nombreProyecto + ", estadoProyecto=" + estadoProyecto + ", tipoFinanciamiento=" + tipoFinanciamiento + '}';
    }

    

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public List<RolProyecto> getAcademicos() {
        return academicos;
    }

    public void setAcademicos(List<RolProyecto> academicos) {
        this.academicos = academicos;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    public Date getFechaInicioProyecto() {
        return fechaInicioProyecto;
    }

    public void setFechaInicioProyecto(Date fechaInicioProyecto) {
        this.fechaInicioProyecto = fechaInicioProyecto;
    }

    public int getDuracionProyecto() {
        return duracionProyecto;
    }

    public void setDuracionProyecto(int duracionProyecto) {
        this.duracionProyecto = duracionProyecto;
    }

    public String getEstadoProyecto() {
        return estadoProyecto;
    }

    public void setEstadoProyecto(String estadoProyecto) {
        this.estadoProyecto = estadoProyecto;
    }

    public TipoFinanciamiento getTipoFinanciamiento() {
        return tipoFinanciamiento;
    }

    public void setTipoFinanciamiento(TipoFinanciamiento tipoFinanciamiento) {
        this.tipoFinanciamiento = tipoFinanciamiento;
    }
    
    
    
}
