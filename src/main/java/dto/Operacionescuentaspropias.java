package dto;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ing. Andres Gomez
 */
@Entity
@Table(name = "operacionescuentaspropias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operacionescuentaspropias.findAll", query = "SELECT o FROM Operacionescuentaspropias o"),
    @NamedQuery(name = "Operacionescuentaspropias.findByIdOperacionCP", query = "SELECT o FROM Operacionescuentaspropias o WHERE o.idOperacionCP = :idOperacionCP"),
    @NamedQuery(name = "Operacionescuentaspropias.findByMontoOperacionCP", query = "SELECT o FROM Operacionescuentaspropias o WHERE o.montoOperacionCP = :montoOperacionCP"),
    @NamedQuery(name = "Operacionescuentaspropias.findByMonedaOperacionCP", query = "SELECT o FROM Operacionescuentaspropias o WHERE o.monedaOperacionCP = :monedaOperacionCP"),
    @NamedQuery(name = "Operacionescuentaspropias.findByFechaOperacionCP", query = "SELECT o FROM Operacionescuentaspropias o WHERE o.fechaOperacionCP = :fechaOperacionCP")})
public class Operacionescuentaspropias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdOperacionCP")
    private Integer idOperacionCP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MontoOperacionCP")
    private double montoOperacionCP;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MonedaOperacionCP")
    private String monedaOperacionCP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaOperacionCP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOperacionCP;
    @JoinColumn(name = "IdTipoOperacion", referencedColumnName = "IdTipoOperacion")
    @ManyToOne(optional = false)
    private Tipooperacion idTipoOperacion;
    @JoinColumn(name = "IdCuenta", referencedColumnName = "IdCuenta")
    @ManyToOne(optional = false)
    private Cuenta idCuenta;

    public Operacionescuentaspropias() {
    }

    public Operacionescuentaspropias(Integer idOperacionCP) {
        this.idOperacionCP = idOperacionCP;
    }

    public Operacionescuentaspropias(Integer idOperacionCP, double montoOperacionCP, String monedaOperacionCP, Date fechaOperacionCP) {
        this.idOperacionCP = idOperacionCP;
        this.montoOperacionCP = montoOperacionCP;
        this.monedaOperacionCP = monedaOperacionCP;
        this.fechaOperacionCP = fechaOperacionCP;
    }

    public Integer getIdOperacionCP() {
        return idOperacionCP;
    }

    public void setIdOperacionCP(Integer idOperacionCP) {
        this.idOperacionCP = idOperacionCP;
    }

    public double getMontoOperacionCP() {
        return montoOperacionCP;
    }

    public void setMontoOperacionCP(double montoOperacionCP) {
        this.montoOperacionCP = montoOperacionCP;
    }

    public String getMonedaOperacionCP() {
        return monedaOperacionCP;
    }

    public void setMonedaOperacionCP(String monedaOperacionCP) {
        this.monedaOperacionCP = monedaOperacionCP;
    }

    public Date getFechaOperacionCP() {
        return fechaOperacionCP;
    }

    public void setFechaOperacionCP(Date fechaOperacionCP) {
        this.fechaOperacionCP = fechaOperacionCP;
    }

    public Tipooperacion getIdTipoOperacion() {
        return idTipoOperacion;
    }

    public void setIdTipoOperacion(Tipooperacion idTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
    }

    public Cuenta getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Cuenta idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperacionCP != null ? idOperacionCP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operacionescuentaspropias)) {
            return false;
        }
        Operacionescuentaspropias other = (Operacionescuentaspropias) object;
        if ((this.idOperacionCP == null && other.idOperacionCP != null) || (this.idOperacionCP != null && !this.idOperacionCP.equals(other.idOperacionCP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Operacionescuentaspropias[ idOperacionCP=" + idOperacionCP + " ]";
    }

}
