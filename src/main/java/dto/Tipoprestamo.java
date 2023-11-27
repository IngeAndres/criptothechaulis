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
@Table(name = "tipoprestamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoprestamo.findAll", query = "SELECT t FROM Tipoprestamo t"),
    @NamedQuery(name = "Tipoprestamo.findByIdTipoPrestamo", query = "SELECT t FROM Tipoprestamo t WHERE t.idTipoPrestamo = :idTipoPrestamo"),
    @NamedQuery(name = "Tipoprestamo.findByDenoTipoPrestamo", query = "SELECT t FROM Tipoprestamo t WHERE t.denoTipoPrestamo = :denoTipoPrestamo"),
    @NamedQuery(name = "Tipoprestamo.findByCategoriaTipoPrestamo", query = "SELECT t FROM Tipoprestamo t WHERE t.categoriaTipoPrestamo = :categoriaTipoPrestamo")})
public class Tipoprestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTipoPrestamo")
    private Integer idTipoPrestamo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "DenoTipoPrestamo")
    private String denoTipoPrestamo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "CategoriaTipoPrestamo")
    private String categoriaTipoPrestamo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPrestamo")
    private List<Prestamo> prestamoList;

    public Tipoprestamo() {
    }

    public Tipoprestamo(Integer idTipoPrestamo) {
        this.idTipoPrestamo = idTipoPrestamo;
    }

    public Tipoprestamo(Integer idTipoPrestamo, String denoTipoPrestamo, String categoriaTipoPrestamo) {
        this.idTipoPrestamo = idTipoPrestamo;
        this.denoTipoPrestamo = denoTipoPrestamo;
        this.categoriaTipoPrestamo = categoriaTipoPrestamo;
    }

    public Integer getIdTipoPrestamo() {
        return idTipoPrestamo;
    }

    public void setIdTipoPrestamo(Integer idTipoPrestamo) {
        this.idTipoPrestamo = idTipoPrestamo;
    }

    public String getDenoTipoPrestamo() {
        return denoTipoPrestamo;
    }

    public void setDenoTipoPrestamo(String denoTipoPrestamo) {
        this.denoTipoPrestamo = denoTipoPrestamo;
    }

    public String getCategoriaTipoPrestamo() {
        return categoriaTipoPrestamo;
    }

    public void setCategoriaTipoPrestamo(String categoriaTipoPrestamo) {
        this.categoriaTipoPrestamo = categoriaTipoPrestamo;
    }

    @XmlTransient
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPrestamo != null ? idTipoPrestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoprestamo)) {
            return false;
        }
        Tipoprestamo other = (Tipoprestamo) object;
        if ((this.idTipoPrestamo == null && other.idTipoPrestamo != null) || (this.idTipoPrestamo != null && !this.idTipoPrestamo.equals(other.idTipoPrestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Tipoprestamo[ idTipoPrestamo=" + idTipoPrestamo + " ]";
    }

}
