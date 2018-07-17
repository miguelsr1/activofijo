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
@Table(name = "AF_CLASIFICACION_BIEN", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfClasificacionBien.findAll", query = "SELECT a FROM AfClasificacionBien a"),
    @NamedQuery(name = "AfClasificacionBien.findByIdClasifBien", query = "SELECT a FROM AfClasificacionBien a WHERE a.idClasifBien = :idClasifBien"),
    @NamedQuery(name = "AfClasificacionBien.findByNombreClasifBien", query = "SELECT a FROM AfClasificacionBien a WHERE a.nombreClasifBien = :nombreClasifBien"),
    @NamedQuery(name = "AfClasificacionBien.findByFechaAdicion", query = "SELECT a FROM AfClasificacionBien a WHERE a.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "AfClasificacionBien.findByUsuarioAdicion", query = "SELECT a FROM AfClasificacionBien a WHERE a.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "AfClasificacionBien.findByFechaModifica", query = "SELECT a FROM AfClasificacionBien a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfClasificacionBien.findByUsuarioModifica", query = "SELECT a FROM AfClasificacionBien a WHERE a.usuarioModifica = :usuarioModifica")})
public class AfClasificacionBien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CLASIF_BIEN")
    private Long idClasifBien;
    @Column(name = "NOMBRE_CLASIF_BIEN")
    private String nombreClasifBien;
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
    @OneToMany(mappedBy = "idClasifBien")
    private List<AfCategoriasBien> afCategoriasBienList;

    public AfClasificacionBien() {
    }

    public AfClasificacionBien(Long idClasifBien) {
        this.idClasifBien = idClasifBien;
    }

    public Long getIdClasifBien() {
        return idClasifBien;
    }

    public void setIdClasifBien(Long idClasifBien) {
        this.idClasifBien = idClasifBien;
    }

    public String getNombreClasifBien() {
        return nombreClasifBien;
    }

    public void setNombreClasifBien(String nombreClasifBien) {
        this.nombreClasifBien = nombreClasifBien;
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

    public List<AfCategoriasBien> getAfCategoriasBienList() {
        return afCategoriasBienList;
    }

    public void setAfCategoriasBienList(List<AfCategoriasBien> afCategoriasBienList) {
        this.afCategoriasBienList = afCategoriasBienList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClasifBien != null ? idClasifBien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfClasificacionBien)) {
            return false;
        }
        AfClasificacionBien other = (AfClasificacionBien) object;
        if ((this.idClasifBien == null && other.idClasifBien != null) || (this.idClasifBien != null && !this.idClasifBien.equals(other.idClasifBien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfClasificacionBien[ idClasifBien=" + idClasifBien + " ]";
    }
    
}
