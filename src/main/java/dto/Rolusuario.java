/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "rolusuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolusuario.findAll", query = "SELECT r FROM Rolusuario r"),
    @NamedQuery(name = "Rolusuario.findByIdCodRol", query = "SELECT r FROM Rolusuario r WHERE r.idCodRol = :idCodRol"),
    @NamedQuery(name = "Rolusuario.findByCodRol", query = "SELECT r FROM Rolusuario r WHERE r.codRol = :codRol"),
    @NamedQuery(name = "Rolusuario.findByIdUsuario", query = "SELECT r FROM Rolusuario r WHERE r.idUsuario = :idUsuario"),
    @NamedQuery(name = "Rolusuario.findByActvRol", query = "SELECT r FROM Rolusuario r WHERE r.actvRol = :actvRol")})
public class Rolusuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCodRol")
    private Integer idCodRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codRol")
    private int codRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "idUsuario")
    private String idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actvRol")
    private boolean actvRol;

    public Rolusuario() {
    }

    public Rolusuario(Integer idCodRol) {
        this.idCodRol = idCodRol;
    }

    public Rolusuario(Integer idCodRol, int codRol, String idUsuario, boolean actvRol) {
        this.idCodRol = idCodRol;
        this.codRol = codRol;
        this.idUsuario = idUsuario;
        this.actvRol = actvRol;
    }

    public Integer getIdCodRol() {
        return idCodRol;
    }

    public void setIdCodRol(Integer idCodRol) {
        this.idCodRol = idCodRol;
    }

    public int getCodRol() {
        return codRol;
    }

    public void setCodRol(int codRol) {
        this.codRol = codRol;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean getActvRol() {
        return actvRol;
    }

    public void setActvRol(boolean actvRol) {
        this.actvRol = actvRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCodRol != null ? idCodRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rolusuario)) {
            return false;
        }
        Rolusuario other = (Rolusuario) object;
        if ((this.idCodRol == null && other.idCodRol != null) || (this.idCodRol != null && !this.idCodRol.equals(other.idCodRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Rolusuario[ idCodRol=" + idCodRol + " ]";
    }
    
}
