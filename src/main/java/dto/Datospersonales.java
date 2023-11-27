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
@Table(name = "datospersonales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datospersonales.findAll", query = "SELECT d FROM Datospersonales d"),
    @NamedQuery(name = "Datospersonales.findByIdPersona", query = "SELECT d FROM Datospersonales d WHERE d.idPersona = :idPersona"),
    @NamedQuery(name = "Datospersonales.findByDocuPersona", query = "SELECT d FROM Datospersonales d WHERE d.docuPersona = :docuPersona"),
    @NamedQuery(name = "Datospersonales.findByRuc", query = "SELECT d FROM Datospersonales d WHERE d.ruc = :ruc"),
    @NamedQuery(name = "Datospersonales.findByNombPersona", query = "SELECT d FROM Datospersonales d WHERE d.nombPersona = :nombPersona"),
    @NamedQuery(name = "Datospersonales.findByApPaPersona", query = "SELECT d FROM Datospersonales d WHERE d.apPaPersona = :apPaPersona"),
    @NamedQuery(name = "Datospersonales.findByApMaPersona", query = "SELECT d FROM Datospersonales d WHERE d.apMaPersona = :apMaPersona"),
    @NamedQuery(name = "Datospersonales.findByGenePersona", query = "SELECT d FROM Datospersonales d WHERE d.genePersona = :genePersona"),
    @NamedQuery(name = "Datospersonales.findByFechPersona", query = "SELECT d FROM Datospersonales d WHERE d.fechPersona = :fechPersona"),
    @NamedQuery(name = "Datospersonales.findByDirePersona", query = "SELECT d FROM Datospersonales d WHERE d.direPersona = :direPersona"),
    @NamedQuery(name = "Datospersonales.findByCeluPersona", query = "SELECT d FROM Datospersonales d WHERE d.celuPersona = :celuPersona"),
    @NamedQuery(name = "Datospersonales.findByEmailPersona", query = "SELECT d FROM Datospersonales d WHERE d.emailPersona = :emailPersona"),
    @NamedQuery(name = "Datospersonales.listar",
            query = "SELECT d.idPersona, t.denoTipoDocumento, d.docuPersona, d.apPaPersona, d.apMaPersona, d.nombPersona, d.celuPersona, d.emailPersona "
            + "FROM Datospersonales d "
            + "JOIN d.idTipoDocumento t"),
    @NamedQuery(name = "Datospersonales.obtenerDatosPersonalesPorId",
            query = "SELECT d.idPersona, t.denoTipoDocumento, d.docuPersona, d.ruc ,d.apPaPersona, d.apMaPersona ,d.nombPersona,d.genePersona, d.fechPersona, "
            + "d.direPersona, d.celuPersona, d.emailPersona, di.denoDistrito, p.denoProvincia, de.denoDepartamento "
            + "FROM Datospersonales d "
            + "JOIN d.idTipoDocumento t "
            + "JOIN d.idDistrito di "
            + "JOIN di.idProvincia p "
            + "JOIN p.idDepartamento de "
            + "WHERE d.idPersona = :idPersona")
})
public class Datospersonales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPersona")
    private Integer idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "DocuPersona")
    private String docuPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "RUC")
    private String ruc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NombPersona")
    private String nombPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ApPaPersona")
    private String apPaPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ApMaPersona")
    private String apMaPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GenePersona")
    private Character genePersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechPersona")
    @Temporal(TemporalType.DATE)
    private Date fechPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "DirePersona")
    private String direPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CeluPersona")
    private String celuPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "EmailPersona")
    private String emailPersona;
    @JoinColumn(name = "IdTipoDocumento", referencedColumnName = "IdTipoDocumento")
    @ManyToOne(optional = false)
    private Tipodocumento idTipoDocumento;
    @JoinColumn(name = "IdDistrito", referencedColumnName = "IdDistrito")
    @ManyToOne(optional = false)
    private Distrito idDistrito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<Usuario> usuarioList;

    public Datospersonales() {
    }

    public Datospersonales(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Datospersonales(Integer idPersona, String docuPersona, String ruc, String nombPersona, String apPaPersona, String apMaPersona, Character genePersona, Date fechPersona, String direPersona, String celuPersona, String emailPersona) {
        this.idPersona = idPersona;
        this.docuPersona = docuPersona;
        this.ruc = ruc;
        this.nombPersona = nombPersona;
        this.apPaPersona = apPaPersona;
        this.apMaPersona = apMaPersona;
        this.genePersona = genePersona;
        this.fechPersona = fechPersona;
        this.direPersona = direPersona;
        this.celuPersona = celuPersona;
        this.emailPersona = emailPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getDocuPersona() {
        return docuPersona;
    }

    public void setDocuPersona(String docuPersona) {
        this.docuPersona = docuPersona;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombPersona() {
        return nombPersona;
    }

    public void setNombPersona(String nombPersona) {
        this.nombPersona = nombPersona;
    }

    public String getApPaPersona() {
        return apPaPersona;
    }

    public void setApPaPersona(String apPaPersona) {
        this.apPaPersona = apPaPersona;
    }

    public String getApMaPersona() {
        return apMaPersona;
    }

    public void setApMaPersona(String apMaPersona) {
        this.apMaPersona = apMaPersona;
    }

    public Character getGenePersona() {
        return genePersona;
    }

    public void setGenePersona(Character genePersona) {
        this.genePersona = genePersona;
    }

    public Date getFechPersona() {
        return fechPersona;
    }

    public void setFechPersona(Date fechPersona) {
        this.fechPersona = fechPersona;
    }

    public String getDirePersona() {
        return direPersona;
    }

    public void setDirePersona(String direPersona) {
        this.direPersona = direPersona;
    }

    public String getCeluPersona() {
        return celuPersona;
    }

    public void setCeluPersona(String celuPersona) {
        this.celuPersona = celuPersona;
    }

    public String getEmailPersona() {
        return emailPersona;
    }

    public void setEmailPersona(String emailPersona) {
        this.emailPersona = emailPersona;
    }

    public Tipodocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Tipodocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Distrito getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(Distrito idDistrito) {
        this.idDistrito = idDistrito;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datospersonales)) {
            return false;
        }
        Datospersonales other = (Datospersonales) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Datospersonales[ idPersona=" + idPersona + " ]";
    }

}
