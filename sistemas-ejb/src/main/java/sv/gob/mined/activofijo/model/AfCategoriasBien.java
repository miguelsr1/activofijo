/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_CATEGORIAS_BIEN", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfCategoriasBien.findAll", query = "SELECT a FROM AfCategoriasBien a"),
    @NamedQuery(name = "AfCategoriasBien.findByIdCatBien", query = "SELECT a FROM AfCategoriasBien a WHERE a.idCatBien = :idCatBien"),
    @NamedQuery(name = "AfCategoriasBien.findByDescripcionCatBien", query = "SELECT a FROM AfCategoriasBien a WHERE a.descripcionCatBien = :descripcionCatBien"),
    @NamedQuery(name = "AfCategoriasBien.findByDepreciable", query = "SELECT a FROM AfCategoriasBien a WHERE a.depreciable = :depreciable"),
    @NamedQuery(name = "AfCategoriasBien.findByEspecificoGasto", query = "SELECT a FROM AfCategoriasBien a WHERE a.especificoGasto = :especificoGasto"),
    @NamedQuery(name = "AfCategoriasBien.findByVidaUtil", query = "SELECT a FROM AfCategoriasBien a WHERE a.vidaUtil = :vidaUtil"),
    @NamedQuery(name = "AfCategoriasBien.findByFechaAdicion", query = "SELECT a FROM AfCategoriasBien a WHERE a.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "AfCategoriasBien.findByUsuarioAdicion", query = "SELECT a FROM AfCategoriasBien a WHERE a.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "AfCategoriasBien.findByFechaModifica", query = "SELECT a FROM AfCategoriasBien a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfCategoriasBien.findByUsuarioModifica", query = "SELECT a FROM AfCategoriasBien a WHERE a.usuarioModifica = :usuarioModifica")})
public class AfCategoriasBien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CAT_BIEN")
    private Long idCatBien;
    @Column(name = "DESCRIPCION_CAT_BIEN")
    private String descripcionCatBien;
    @Column(name = "DEPRECIABLE")
    private Character depreciable;
    @Column(name = "ESPECIFICO_GASTO")
    private String especificoGasto;
    @Column(name = "VIDA_UTIL")
    private BigInteger vidaUtil;
    @Column(name = "FECHA_ADICION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;
    @Column(name = "USUARIO_ADICION")
    private String usuarioAdicion;
    @Column(name = "FECHA_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModifica;
    @Column(name = "USUARIO_MODIFICA")
    private String usuarioModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatBien")
    private List<AfBienesDepreciables> afBienesDepreciablesList;
    @JoinColumn(name = "ID_CLASIF_BIEN", referencedColumnName = "ID_CLASIF_BIEN")
    @ManyToOne
    private AfClasificacionBien idClasifBien;
    @OneToMany(mappedBy = "idCatBien")
    private List<AfTipoBienes> afTipoBienesList;

    public AfCategoriasBien() {
    }

    public AfCategoriasBien(Long idCatBien) {
        this.idCatBien = idCatBien;
    }

    public Long getIdCatBien() {
        return idCatBien;
    }

    public void setIdCatBien(Long idCatBien) {
        this.idCatBien = idCatBien;
    }

    public String getDescripcionCatBien() {
        return descripcionCatBien;
    }

    public void setDescripcionCatBien(String descripcionCatBien) {
        this.descripcionCatBien = descripcionCatBien;
    }

    public Character getDepreciable() {
        return depreciable;
    }

    public void setDepreciable(Character depreciable) {
        this.depreciable = depreciable;
    }

    public String getEspecificoGasto() {
        return especificoGasto;
    }

    public void setEspecificoGasto(String especificoGasto) {
        this.especificoGasto = especificoGasto;
    }

    public BigInteger getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(BigInteger vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public Date getFechaAdicion() {
        return fechaAdicion;
    }

    public void setFechaAdicion(Date fechaAdicion) {
        this.fechaAdicion = fechaAdicion;
    }

    public String getUsuarioAdicion() {
        return usuarioAdicion;
    }

    public void setUsuarioAdicion(String usuarioAdicion) {
        this.usuarioAdicion = usuarioAdicion;
    }

    public Date getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public List<AfBienesDepreciables> getAfBienesDepreciablesList() {
        return afBienesDepreciablesList;
    }

    public void setAfBienesDepreciablesList(List<AfBienesDepreciables> afBienesDepreciablesList) {
        this.afBienesDepreciablesList = afBienesDepreciablesList;
    }

    public AfClasificacionBien getIdClasifBien() {
        return idClasifBien;
    }

    public void setIdClasifBien(AfClasificacionBien idClasifBien) {
        this.idClasifBien = idClasifBien;
    }

    public List<AfTipoBienes> getAfTipoBienesList() {
        return afTipoBienesList;
    }

    public void setAfTipoBienesList(List<AfTipoBienes> afTipoBienesList) {
        this.afTipoBienesList = afTipoBienesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatBien != null ? idCatBien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfCategoriasBien)) {
            return false;
        }
        AfCategoriasBien other = (AfCategoriasBien) object;
        if ((this.idCatBien == null && other.idCatBien != null) || (this.idCatBien != null && !this.idCatBien.equals(other.idCatBien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfCategoriasBien[ idCatBien=" + idCatBien + " ]";
    }
    
}
