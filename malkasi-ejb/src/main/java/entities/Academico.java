/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    private String resegna;
    
    @OneToMany
    private List<GradoAcademico> Grados;
    
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
    
}
