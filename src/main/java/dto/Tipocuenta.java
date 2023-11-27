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
@Table(name = "tipocuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipocuenta.findAll", query = "SELECT t FROM Tipocuenta t"),
    @NamedQuery(name = "Tipocuenta.findByIdTipoCuenta", query = "SELECT t FROM Tipocuenta t WHERE t.idTipoCuenta = :idTipoCuenta"),
    @NamedQuery(name = "Tipocuenta.findByDenoTipoCuenta", query = "SELECT t FROM Tipocuenta t WHERE t.denoTipoCuenta = :denoTipoCuenta")})
public class Tipocuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTipoCuenta")
    private Integer idTipoCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DenoTipoCuenta")
    private String denoTipoCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCuenta")
    private List<Cuenta> cuentaList;

    public Tipocuenta() {
    }

    public Tipocuenta(Integer idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public Tipocuenta(Integer idTipoCuenta, String denoTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
        this.denoTipoCuenta = denoTipoCuenta;
    }

    public Integer getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(Integer idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public String getDenoTipoCuenta() {
        return denoTipoCuenta;
    }

    public void setDenoTipoCuenta(String denoTipoCuenta) {
        this.denoTipoCuenta = denoTipoCuenta;
    }

    @XmlTransient
    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCuenta != null ? idTipoCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocuenta)) {
            return false;
        }
        Tipocuenta other = (Tipocuenta) object;
        if ((this.idTipoCuenta == null && other.idTipoCuenta != null) || (this.idTipoCuenta != null && !this.idTipoCuenta.equals(other.idTipoCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Tipocuenta[ idTipoCuenta=" + idTipoCuenta + " ]";
    }

}
