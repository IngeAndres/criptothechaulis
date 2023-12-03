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
@Table(name = "operacionesotrascuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operacionesotrascuentas.findAll", query = "SELECT o FROM Operacionesotrascuentas o"),
    @NamedQuery(name = "Operacionesotrascuentas.findByIdOperacionOC", query = "SELECT o FROM Operacionesotrascuentas o WHERE o.idOperacionOC = :idOperacionOC"),
    @NamedQuery(name = "Operacionesotrascuentas.findByMontoOperacionOC", query = "SELECT o FROM Operacionesotrascuentas o WHERE o.montoOperacionOC = :montoOperacionOC"),
    @NamedQuery(name = "Operacionesotrascuentas.findByMonedaOperacionOC", query = "SELECT o FROM Operacionesotrascuentas o WHERE o.monedaOperacionOC = :monedaOperacionOC"),
    @NamedQuery(name = "Operacionesotrascuentas.findByFechaOperacionOC", query = "SELECT o FROM Operacionesotrascuentas o WHERE o.fechaOperacionOC = :fechaOperacionOC"),
    @NamedQuery(name = "Operacionesotrascuentas.listar", query = "SELECT t.idOperacionOC, cuo.numbCuenta, clo.apPaPersona, clo.apMaPersona, clo.nombPersona, "
            + "cud.numbCuenta, cld.apPaPersona, cld.apMaPersona, cld.nombPersona, t.montoOperacionOC, t.monedaOperacionOC, t.fechaOperacionOC "
            + "FROM Operacionesotrascuentas t "
            + "JOIN t.idCuentaOrigen cuo "
            + "JOIN cuo.idUsuario cio "
            + "JOIN cio.idPersona clo "
            + "JOIN t.idCuentaDestino cud "
            + "JOIN cud.idUsuario cid "
            + "JOIN cid.idPersona cld")})
public class Operacionesotrascuentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdOperacionOC")
    private Integer idOperacionOC;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MontoOperacionOC")
    private double montoOperacionOC;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MonedaOperacionOC")
    private String monedaOperacionOC;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaOperacionOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOperacionOC;
    @JoinColumn(name = "IdTipoOperacion", referencedColumnName = "IdTipoOperacion")
    @ManyToOne(optional = false)
    private Tipooperacion idTipoOperacion;
    @JoinColumn(name = "IdCuentaOrigen", referencedColumnName = "IdCuenta")
    @ManyToOne(optional = false)
    private Cuenta idCuentaOrigen;
    @JoinColumn(name = "IdCuentaDestino", referencedColumnName = "IdCuenta")
    @ManyToOne(optional = false)
    private Cuenta idCuentaDestino;

    public Operacionesotrascuentas() {
    }

    public Operacionesotrascuentas(Integer idOperacionOC) {
        this.idOperacionOC = idOperacionOC;
    }

    public Operacionesotrascuentas(Integer idOperacionOC, double montoOperacionOC, String monedaOperacionOC, Date fechaOperacionOC) {
        this.idOperacionOC = idOperacionOC;
        this.montoOperacionOC = montoOperacionOC;
        this.monedaOperacionOC = monedaOperacionOC;
        this.fechaOperacionOC = fechaOperacionOC;
    }

    public Integer getIdOperacionOC() {
        return idOperacionOC;
    }

    public void setIdOperacionOC(Integer idOperacionOC) {
        this.idOperacionOC = idOperacionOC;
    }

    public double getMontoOperacionOC() {
        return montoOperacionOC;
    }

    public void setMontoOperacionOC(double montoOperacionOC) {
        this.montoOperacionOC = montoOperacionOC;
    }

    public String getMonedaOperacionOC() {
        return monedaOperacionOC;
    }

    public void setMonedaOperacionOC(String monedaOperacionOC) {
        this.monedaOperacionOC = monedaOperacionOC;
    }

    public Date getFechaOperacionOC() {
        return fechaOperacionOC;
    }

    public void setFechaOperacionOC(Date fechaOperacionOC) {
        this.fechaOperacionOC = fechaOperacionOC;
    }

    public Tipooperacion getIdTipoOperacion() {
        return idTipoOperacion;
    }

    public void setIdTipoOperacion(Tipooperacion idTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
    }

    public Cuenta getIdCuentaOrigen() {
        return idCuentaOrigen;
    }

    public void setIdCuentaOrigen(Cuenta idCuentaOrigen) {
        this.idCuentaOrigen = idCuentaOrigen;
    }

    public Cuenta getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuentaDestino(Cuenta idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperacionOC != null ? idOperacionOC.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operacionesotrascuentas)) {
            return false;
        }
        Operacionesotrascuentas other = (Operacionesotrascuentas) object;
        if ((this.idOperacionOC == null && other.idOperacionOC != null) || (this.idOperacionOC != null && !this.idOperacionOC.equals(other.idOperacionOC))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Operacionesotrascuentas[ idOperacionOC=" + idOperacionOC + " ]";
    }

}
