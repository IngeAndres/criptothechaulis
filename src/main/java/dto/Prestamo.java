package dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ing. Andres Gomez
 */
@Entity
@Table(name = "prestamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p"),
    @NamedQuery(name = "Prestamo.findByIdPrestamo", query = "SELECT p FROM Prestamo p WHERE p.idPrestamo = :idPrestamo")})
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPrestamo")
    private Integer idPrestamo;
    @JoinColumn(name = "IdTipoPrestamo", referencedColumnName = "IdTipoPrestamo")
    @ManyToOne(optional = false)
    private Tipoprestamo idTipoPrestamo;
    @JoinColumn(name = "IdCuenta", referencedColumnName = "IdCuenta")
    @ManyToOne(optional = false)
    private Cuenta idCuenta;
    @JoinColumn(name = "IdTipoComprobante", referencedColumnName = "IdTipoComprobante")
    @ManyToOne(optional = false)
    private Tipocomprobante idTipoComprobante;
    @JoinColumn(name = "IdDetallePrestamo", referencedColumnName = "IdDetallePrestamo")
    @ManyToOne(optional = false)
    private Detalleprestamo idDetallePrestamo;

    public Prestamo() {
    }

    public Prestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Tipoprestamo getIdTipoPrestamo() {
        return idTipoPrestamo;
    }

    public void setIdTipoPrestamo(Tipoprestamo idTipoPrestamo) {
        this.idTipoPrestamo = idTipoPrestamo;
    }

    public Cuenta getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Cuenta idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Tipocomprobante getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Tipocomprobante idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public Detalleprestamo getIdDetallePrestamo() {
        return idDetallePrestamo;
    }

    public void setIdDetallePrestamo(Detalleprestamo idDetallePrestamo) {
        this.idDetallePrestamo = idDetallePrestamo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrestamo != null ? idPrestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.idPrestamo == null && other.idPrestamo != null) || (this.idPrestamo != null && !this.idPrestamo.equals(other.idPrestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Prestamo[ idPrestamo=" + idPrestamo + " ]";
    }
    
}
