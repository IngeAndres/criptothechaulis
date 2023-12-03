package dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ing. Andres Gomez
 */
@Entity
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findByIdCuenta", query = "SELECT c FROM Cuenta c WHERE c.idCuenta = :idCuenta"),
    @NamedQuery(name = "Cuenta.findByNumbCuenta", query = "SELECT c FROM Cuenta c WHERE c.numbCuenta = :numbCuenta"),
    @NamedQuery(name = "Cuenta.findByCci", query = "SELECT c FROM Cuenta c WHERE c.cci = :cci"),
    @NamedQuery(name = "Cuenta.findBySaldoDisponible", query = "SELECT c FROM Cuenta c WHERE c.saldoDisponible = :saldoDisponible"),
    @NamedQuery(name = "Cuenta.findBySaldoContable", query = "SELECT c FROM Cuenta c WHERE c.saldoContable = :saldoContable"),
    @NamedQuery(name = "Cuenta.findByEstadoCuenta", query = "SELECT c FROM Cuenta c WHERE c.estadoCuenta = :estadoCuenta"),
    @NamedQuery(name = "Cuenta.findByFechaApertura", query = "SELECT c FROM Cuenta c WHERE c.fechaApertura = :fechaApertura"),
    @NamedQuery(name = "Cuenta.findByFechaCierre", query = "SELECT c FROM Cuenta c WHERE c.fechaCierre = :fechaCierre"),
    @NamedQuery(name = "Cuenta.listarCuentasActivas", query = "SELECT c.idCuenta, dp.apPaPersona, dp.apMaPersona, dp.nombPersona ,tc.denoTipoCuenta ,c.numbCuenta, c.saldoDisponible, c.saldoContable, c.estadoCuenta, c.fechaApertura "
            + "FROM Cuenta c "
            + "JOIN c.idUsuario u "
            + "JOIN c.idTipoCuenta tc "
            + "JOIN u.idPersona dp "
            + "WHERE c.estadoCuenta= :estadoCuenta"),
    @NamedQuery(name = "Cuenta.findLastNumbCuenta", query = "SELECT c.numbCuenta FROM Cuenta c ORDER BY c.numbCuenta DESC")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCuenta")
    private Integer idCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "NumbCuenta")
    private String numbCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CCI")
    private String cci;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SaldoDisponible")
    private double saldoDisponible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SaldoContable")
    private double saldoContable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "EstadoCuenta")
    private String estadoCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaApertura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaApertura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaCierre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaOrigen")
    private List<Operacionesotrascuentas> operacionesotrascuentasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaDestino")
    private List<Operacionesotrascuentas> operacionesotrascuentasList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuenta")
    private List<Operacionescuentaspropias> operacionescuentaspropiasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuenta")
    private List<Prestamo> prestamoList;
    @JoinColumn(name = "IdUsuario", referencedColumnName = "IdUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "IdTipoCuenta", referencedColumnName = "IdTipoCuenta")
    @ManyToOne(optional = false)
    private Tipocuenta idTipoCuenta;

    public Cuenta() {
    }

    public Cuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cuenta(Integer idCuenta, String numbCuenta, String cci, double saldoDisponible, double saldoContable, String estadoCuenta, Date fechaApertura, Date fechaCierre) {
        this.idCuenta = idCuenta;
        this.numbCuenta = numbCuenta;
        this.cci = cci;
        this.saldoDisponible = saldoDisponible;
        this.saldoContable = saldoContable;
        this.estadoCuenta = estadoCuenta;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNumbCuenta() {
        return numbCuenta;
    }

    public void setNumbCuenta(String numbCuenta) {
        this.numbCuenta = numbCuenta;
    }

    public String getCci() {
        return cci;
    }

    public void setCci(String cci) {
        this.cci = cci;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public double getSaldoContable() {
        return saldoContable;
    }

    public void setSaldoContable(double saldoContable) {
        this.saldoContable = saldoContable;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @XmlTransient
    public List<Operacionesotrascuentas> getOperacionesotrascuentasList() {
        return operacionesotrascuentasList;
    }

    public void setOperacionesotrascuentasList(List<Operacionesotrascuentas> operacionesotrascuentasList) {
        this.operacionesotrascuentasList = operacionesotrascuentasList;
    }

    @XmlTransient
    public List<Operacionesotrascuentas> getOperacionesotrascuentasList1() {
        return operacionesotrascuentasList1;
    }

    public void setOperacionesotrascuentasList1(List<Operacionesotrascuentas> operacionesotrascuentasList1) {
        this.operacionesotrascuentasList1 = operacionesotrascuentasList1;
    }

    @XmlTransient
    public List<Operacionescuentaspropias> getOperacionescuentaspropiasList() {
        return operacionescuentaspropiasList;
    }

    public void setOperacionescuentaspropiasList(List<Operacionescuentaspropias> operacionescuentaspropiasList) {
        this.operacionescuentaspropiasList = operacionescuentaspropiasList;
    }

    @XmlTransient
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Tipocuenta getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(Tipocuenta idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuenta != null ? idCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.idCuenta == null && other.idCuenta != null) || (this.idCuenta != null && !this.idCuenta.equals(other.idCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Cuenta[ idCuenta=" + idCuenta + " ]";
    }

}
