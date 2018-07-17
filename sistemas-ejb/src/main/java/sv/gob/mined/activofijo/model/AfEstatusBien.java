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
import javax.persistence.Id;
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
@Table(name = "AF_ESTATUS_BIEN", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfEstatusBien.findAll", query = "SELECT a FROM AfEstatusBien a"),
    @NamedQuery(name = "AfEstatusBien.findByIdEstatusBien", query = "SELECT a FROM AfEstatusBien a WHERE a.idEstatusBien = :idEstatusBien"),
    @NamedQuery(name = "AfEstatusBien.findByNombreEstatusBien", query = "SELECT a FROM AfEstatusBien a WHERE a.nombreEstatusBien = :nombreEstatusBien"),
    @NamedQuery(name = "AfEstatusBien.findByAbrevEstatusBien", query = "SELECT a FROM AfEstatusBien a WHERE a.abrevEstatusBien = :abrevEstatusBien"),
    @NamedQuery(name = "AfEstatusBien.findByFechaAdicion", query = "SELECT a FROM AfEstatusBien a WHERE a.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "AfEstatusBien.findByUsuarioAdicion", query = "SELECT a FROM AfEstatusBien a WHERE a.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "AfEstatusBien.findByFechaModifica", query = "SELECT a FROM AfEstatusBien a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfEstatusBien.findByUsuarioModifica", query = "SELECT a FROM AfEstatusBien a WHERE a.usuarioModifica = :usuarioModifica")})
public class AfEstatusBien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ESTATUS_BIEN")
    private Long idEstatusBien;
    @Column(name = "NOMBRE_ESTATUS_BIEN")
    private String nombreEstatusBien;
    @Column(name = "ABREV_ESTATUS_BIEN")
    private String abrevEstatusBien;
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
    @OneToMany(mappedBy = "idEstatusBien")
    private List<AfBienesDepreciables> afBienesDepreciablesList;

    public AfEstatusBien() {
    }

    public AfEstatusBien(Long idEstatusBien) {
        this.idEstatusBien = idEstatusBien;
    }

    public Long getIdEstatusBien() {
        return idEstatusBien;
    }

    public void setIdEstatusBien(Long idEstatusBien) {
        this.idEstatusBien = idEstatusBien;
    }

    public String getNombreEstatusBien() {
        return nombreEstatusBien;
    }

    public void setNombreEstatusBien(String nombreEstatusBien) {
        this.nombreEstatusBien = nombreEstatusBien;
    }

    public String getAbrevEstatusBien() {
        return abrevEstatusBien;
    }

    public void setAbrevEstatusBien(String abrevEstatusBien) {
        this.abrevEstatusBien = abrevEstatusBien;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstatusBien != null ? idEstatusBien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfEstatusBien)) {
            return false;
        }
        AfEstatusBien other = (AfEstatusBien) object;
        if ((this.idEstatusBien == null && other.idEstatusBien != null) || (this.idEstatusBien != null && !this.idEstatusBien.equals(other.idEstatusBien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfEstatusBien[ idEstatusBien=" + idEstatusBien + " ]";
    }
    
}
