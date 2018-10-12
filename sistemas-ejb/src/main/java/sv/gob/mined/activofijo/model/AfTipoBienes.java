/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_TIPO_BIENES", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfTipoBienes.findAll", query = "SELECT a FROM AfTipoBienes a"),
    @NamedQuery(name = "AfTipoBienes.findByCodigoTipoBien", query = "SELECT a FROM AfTipoBienes a WHERE a.codigoTipoBien = :codigoTipoBien"),
    @NamedQuery(name = "AfTipoBienes.findByNombreTipoBien", query = "SELECT a FROM AfTipoBienes a WHERE a.nombreTipoBien = :nombreTipoBien"),
    @NamedQuery(name = "AfTipoBienes.findByIdClasifBien", query = "SELECT a FROM AfTipoBienes a WHERE a.idClasifBien = :idClasifBien"),
    @NamedQuery(name = "AfTipoBienes.findByFechaAdicion", query = "SELECT a FROM AfTipoBienes a WHERE a.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "AfTipoBienes.findByUsuarioAdicion", query = "SELECT a FROM AfTipoBienes a WHERE a.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "AfTipoBienes.findByFechaModifica", query = "SELECT a FROM AfTipoBienes a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfTipoBienes.findByUsuarioModifica", query = "SELECT a FROM AfTipoBienes a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AfTipoBienes.findByEstadoTipo", query = "SELECT a FROM AfTipoBienes a WHERE a.estadoTipo = :estadoTipo"),
    @NamedQuery(name = "AfTipoBienes.findByIdTipoBien", query = "SELECT a FROM AfTipoBienes a WHERE a.idTipoBien = :idTipoBien")})
public class AfTipoBienes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "CODIGO_TIPO_BIEN")
    private String codigoTipoBien;
    @Column(name = "NOMBRE_TIPO_BIEN")
    private String nombreTipoBien;
    @Column(name = "ID_CLASIF_BIEN")
    private Long idClasifBien;
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
    @Column(name = "ESTADO_TIPO")
    private Character estadoTipo;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_BIEN")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TIPO_BIEN")
    @SequenceGenerator(name="SEQ_TIPO_BIEN", sequenceName="SEQ_TIPO_BIEN",initialValue = 1,allocationSize = 1)
    private Long idTipoBien;
    @JoinColumn(name = "ID_CAT_BIEN", referencedColumnName = "ID_CAT_BIEN")
    @ManyToOne
    private AfCategoriasBien idCatBien;

    public AfTipoBienes() {
    }

    public AfTipoBienes(Long idTipoBien) {
        this.idTipoBien = idTipoBien;
    }

    public AfTipoBienes(Long idTipoBien, String codigoTipoBien) {
        this.idTipoBien = idTipoBien;
        this.codigoTipoBien = codigoTipoBien;
    }

    public String getCodigoTipoBien() {
        return codigoTipoBien;
    }

    public void setCodigoTipoBien(String codigoTipoBien) {
        this.codigoTipoBien = codigoTipoBien;
    }

    public String getNombreTipoBien() {
        return nombreTipoBien;
    }

    public void setNombreTipoBien(String nombreTipoBien) {
        this.nombreTipoBien = nombreTipoBien;
    }

    public Long getIdClasifBien() {
        return idClasifBien;
    }

    public void setIdClasifBien(Long idClasifBien) {
        this.idClasifBien = idClasifBien;
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

    public Character getEstadoTipo() {
        return estadoTipo;
    }

    public void setEstadoTipo(Character estadoTipo) {
        this.estadoTipo = estadoTipo;
    }

    public Long getIdTipoBien() {
        return idTipoBien;
    }

    public void setIdTipoBien(Long idTipoBien) {
        this.idTipoBien = idTipoBien;
    }

     public AfCategoriasBien getIdCatBien() {
        return idCatBien;
    }

    public void setIdCatBien(AfCategoriasBien idCatBien) {
        this.idCatBien = idCatBien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoBien != null ? idTipoBien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfTipoBienes)) {
            return false;
        }
        AfTipoBienes other = (AfTipoBienes) object;
        if ((this.idTipoBien == null && other.idTipoBien != null) || (this.idTipoBien != null && !this.idTipoBien.equals(other.idTipoBien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfTipoBienes[ idTipoBien=" + idTipoBien + " ]";
    }
    
}
