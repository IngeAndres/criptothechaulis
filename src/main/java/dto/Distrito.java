package dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ing. Andres Gomez
 */
@Entity
@Table(name = "distrito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distrito.findAll", query = "SELECT d FROM Distrito d"),
    @NamedQuery(name = "Distrito.findByIdDistrito", query = "SELECT d FROM Distrito d WHERE d.idDistrito = :idDistrito"),
    @NamedQuery(name = "Distrito.findByDenoDistrito", query = "SELECT d FROM Distrito d WHERE d.denoDistrito = :denoDistrito"),
    @NamedQuery(name = "Distrito.findByProvDistrito", query = "SELECT d FROM Distrito d WHERE d.provDistrito = :provDistrito"),
    @NamedQuery(name = "Distrito.findByDepaDistrito", query = "SELECT d FROM Distrito d WHERE d.depaDistrito = :depaDistrito")})
public class Distrito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdDistrito")
    private Integer idDistrito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DenoDistrito")
    private String denoDistrito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ProvDistrito")
    private String provDistrito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DepaDistrito")
    private String depaDistrito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDistrito")
    private List<Datospersonales> datospersonalesList;

    public Distrito() {
    }

    public Distrito(Integer idDistrito) {
        this.idDistrito = idDistrito;
    }

    public Distrito(Integer idDistrito, String denoDistrito, String provDistrito, String depaDistrito) {
        this.idDistrito = idDistrito;
        this.denoDistrito = denoDistrito;
        this.provDistrito = provDistrito;
        this.depaDistrito = depaDistrito;
    }

    public Integer getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(Integer idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getDenoDistrito() {
        return denoDistrito;
    }

    public void setDenoDistrito(String denoDistrito) {
        this.denoDistrito = denoDistrito;
    }

    public String getProvDistrito() {
        return provDistrito;
    }

    public void setProvDistrito(String provDistrito) {
        this.provDistrito = provDistrito;
    }

    public String getDepaDistrito() {
        return depaDistrito;
    }

    public void setDepaDistrito(String depaDistrito) {
        this.depaDistrito = depaDistrito;
    }

    @XmlTransient
    public List<Datospersonales> getDatospersonalesList() {
        return datospersonalesList;
    }

    public void setDatospersonalesList(List<Datospersonales> datospersonalesList) {
        this.datospersonalesList = datospersonalesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDistrito != null ? idDistrito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distrito)) {
            return false;
        }
        Distrito other = (Distrito) object;
        if ((this.idDistrito == null && other.idDistrito != null) || (this.idDistrito != null && !this.idDistrito.equals(other.idDistrito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Distrito[ idDistrito=" + idDistrito + " ]";
    }
    
}
