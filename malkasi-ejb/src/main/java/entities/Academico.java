/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

/**
 *
 * @author Nico_
 */
@Entity
public class Academico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Size(min=1, message="El campo RUT no puede estar vacío")
    private String rut;
    
    @Size(min=1, message="El campo Nombre no puede estar vacío")
    private String nombres;
    
    @Size(min=1, message="El campo Apellido no puede estar vacío")
    private String apellidos;
    
    @Size(min=1, message="El campo Nombre no puede estar vacío")
    private String mail;
    
    @Column(length=1500)
    private String resegna;
    
    private String tipoCuenta;
    
    private String userName;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miAcademico")
    private List<GradoAcademico> Grados;
    
    @ManyToMany(mappedBy = "Academicos")
    @JoinTable(name="ACADEMICO_PUBLICACION",
        joinColumns={@JoinColumn(name="publicaciones_ID")}, 
        inverseJoinColumns={@JoinColumn(name="academicos_ID")})
    private List<Publicacion> Publicaciones;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academico")
    private List<RolProyecto> proyectos;
   
    @ManyToOne(optional= false)
    private Categoria categoria;
    
    private boolean permisoAdmin;
    
    private String userAlias;

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }
    
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rut != null ? rut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Academico)) {
            return false;
        }
        Academico other = (Academico) object;
        if ((this.rut == null && other.rut != null) || (this.rut != null && !this.rut.equals(other.rut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Academico[ rut=" + rut + " ]";
    }  


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getResegna() {
        return resegna;
    }

    public void setResegna(String resegna) {
        this.resegna = resegna;
    }

    public List<GradoAcademico> getGrados() {
        return Grados;
    }

    public void setGrados(List<GradoAcademico> Grados) {
        this.Grados = Grados;
    }

    public long getId() {
        return id;
    }

    public void setId(long Id) {
        this.id = Id;
    }

    public List<Publicacion> getPublicaciones() {
        return Publicaciones;
    }

    public void setPublicaciones(List<Publicacion> Publicaciones) {
        this.Publicaciones = Publicaciones;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<RolProyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<RolProyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public boolean isPermisoAdmin() {
        return permisoAdmin;
    }

    public void setPermisoAdmin(boolean permisoAdmin) {
        this.permisoAdmin = permisoAdmin;
    }
    
    
    
}
