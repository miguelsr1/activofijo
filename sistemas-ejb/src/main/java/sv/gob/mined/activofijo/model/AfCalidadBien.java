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
@Table(name = "AF_CALIDAD_BIEN", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfCalidadBien.findAll", query = "SELECT a FROM AfCalidadBien a"),
    @NamedQuery(name = "AfCalidadBien.findByCodigoCalidadBien", query = "SELECT a FROM AfCalidadBien a WHERE a.codigoCalidadBien = :codigoCalidadBien"),
    @NamedQuery(name = "AfCalidadBien.findByNombreCalidadBien", query = "SELECT a FROM AfCalidadBien a WHERE a.nombreCalidadBien = :nombreCalidadBien"),
    @NamedQuery(name = "AfCalidadBien.findByFechaAdicion", query = "SELECT a FROM AfCalidadBien a WHERE a.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "AfCalidadBien.findByUsuarioAdicion", query = "SELECT a FROM AfCalidadBien a WHERE a.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "AfCalidadBien.findByFechaModifica", query = "SELECT a FROM AfCalidadBien a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfCalidadBien.findByUsuarioModifica", query = "SELECT a FROM AfCalidadBien a WHERE a.usuarioModifica = :usuarioModifica")})
public class AfCalidadBien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO_CALIDAD_BIEN")
    private Long codigoCalidadBien;
    @Column(name = "NOMBRE_CALIDAD_BIEN")
    private String nombreCalidadBien;
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
    @OneToMany(mappedBy = "codigoCalidadBien")
    private List<AfBienesDepreciables> afBienesDepreciablesList;

    public AfCalidadBien() {
    }

    public AfCalidadBien(Long codigoCalidadBien) {
        this.codigoCalidadBien = codigoCalidadBien;
    }

    public Long getCodigoCalidadBien() {
        return codigoCalidadBien;
    }

    public void setCodigoCalidadBien(Long codigoCalidadBien) {
        this.codigoCalidadBien = codigoCalidadBien;
    }

    public String getNombreCalidadBien() {
        return nombreCalidadBien;
    }

    public void setNombreCalidadBien(String nombreCalidadBien) {
        this.nombreCalidadBien = nombreCalidadBien;
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
        hash += (codigoCalidadBien != null ? codigoCalidadBien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfCalidadBien)) {
            return false;
        }
        AfCalidadBien other = (AfCalidadBien) object;
        if ((this.codigoCalidadBien == null && other.codigoCalidadBien != null) || (this.codigoCalidadBien != null && !this.codigoCalidadBien.equals(other.codigoCalidadBien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfCalidadBien[ codigoCalidadBien=" + codigoCalidadBien + " ]";
    }
    
}
