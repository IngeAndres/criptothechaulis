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
@Table(name = "detalleprestamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleprestamo.findAll", query = "SELECT d FROM Detalleprestamo d"),
    @NamedQuery(name = "Detalleprestamo.findByIdDetallePrestamo", query = "SELECT d FROM Detalleprestamo d WHERE d.idDetallePrestamo = :idDetallePrestamo"),
    @NamedQuery(name = "Detalleprestamo.findByCuotasAdicionales", query = "SELECT d FROM Detalleprestamo d WHERE d.cuotasAdicionales = :cuotasAdicionales"),
    @NamedQuery(name = "Detalleprestamo.findByFechaPago", query = "SELECT d FROM Detalleprestamo d WHERE d.fechaPago = :fechaPago"),
    @NamedQuery(name = "Detalleprestamo.findByMonto", query = "SELECT d FROM Detalleprestamo d WHERE d.monto = :monto"),
    @NamedQuery(name = "Detalleprestamo.findByMoneda", query = "SELECT d FROM Detalleprestamo d WHERE d.moneda = :moneda"),
    @NamedQuery(name = "Detalleprestamo.findByTasa", query = "SELECT d FROM Detalleprestamo d WHERE d.tasa = :tasa"),
    @NamedQuery(name = "Detalleprestamo.findByTiempo", query = "SELECT d FROM Detalleprestamo d WHERE d.tiempo = :tiempo")})
public class Detalleprestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdDetallePrestamo")
    private Integer idDetallePrestamo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CuotasAdicionales")
    private Character cuotasAdicionales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaPago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Monto")
    private double monto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Moneda")
    private String moneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tasa")
    private double tasa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tiempo")
    private int tiempo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetallePrestamo")
    private List<Prestamo> prestamoList;
    @JoinColumn(name = "IdTipoInformacionBien", referencedColumnName = "IdTipoInformacionBien")
    @ManyToOne(optional = false)
    private Tipoinformacionbien idTipoInformacionBien;

    public Detalleprestamo() {
    }

    public Detalleprestamo(Integer idDetallePrestamo) {
        this.idDetallePrestamo = idDetallePrestamo;
    }

    public Detalleprestamo(Integer idDetallePrestamo, Character cuotasAdicionales, Date fechaPago, double monto, String moneda, double tasa, int tiempo) {
        this.idDetallePrestamo = idDetallePrestamo;
        this.cuotasAdicionales = cuotasAdicionales;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.moneda = moneda;
        this.tasa = tasa;
        this.tiempo = tiempo;
    }

    public Integer getIdDetallePrestamo() {
        return idDetallePrestamo;
    }

    public void setIdDetallePrestamo(Integer idDetallePrestamo) {
        this.idDetallePrestamo = idDetallePrestamo;
    }

    public Character getCuotasAdicionales() {
        return cuotasAdicionales;
    }

    public void setCuotasAdicionales(Character cuotasAdicionales) {
        this.cuotasAdicionales = cuotasAdicionales;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @XmlTransient
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    public Tipoinformacionbien getIdTipoInformacionBien() {
        return idTipoInformacionBien;
    }

    public void setIdTipoInformacionBien(Tipoinformacionbien idTipoInformacionBien) {
        this.idTipoInformacionBien = idTipoInformacionBien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetallePrestamo != null ? idDetallePrestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleprestamo)) {
            return false;
        }
        Detalleprestamo other = (Detalleprestamo) object;
        if ((this.idDetallePrestamo == null && other.idDetallePrestamo != null) || (this.idDetallePrestamo != null && !this.idDetallePrestamo.equals(other.idDetallePrestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Detalleprestamo[ idDetallePrestamo=" + idDetallePrestamo + " ]";
    }
    
}
