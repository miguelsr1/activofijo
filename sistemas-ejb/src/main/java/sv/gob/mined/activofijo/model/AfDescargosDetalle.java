/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_DESCARGOS_DETALLE", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfDescargosDetalle.findAll", query = "SELECT a FROM AfDescargosDetalle a"),
    @NamedQuery(name = "AfDescargosDetalle.findByIdBien", query = "SELECT a FROM AfDescargosDetalle a WHERE a.idBien = :idBien"),
    @NamedQuery(name = "AfDescargosDetalle.findByIdTipoDescargo", query = "SELECT a FROM AfDescargosDetalle a WHERE a.idTipoDescargo = :idTipoDescargo"),
    @NamedQuery(name = "AfDescargosDetalle.findByIdDetalleDescargo", query = "SELECT a FROM AfDescargosDetalle a WHERE a.idDetalleDescargo = :idDetalleDescargo")})
public class AfDescargosDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ID_BIEN")
    private BigInteger idBien;
    @Column(name = "ID_TIPO_DESCARGO")
    private String idTipoDescargo;
    @Column(name = "CODIGO_UNIDAD")
    private String codigoUnidad;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DETALLE_DESCARGO")
      @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_DESCARGODET")
    @SequenceGenerator(name="SEQ_DESCARGODET", sequenceName="SEQ_DESCARGODET",initialValue = 1,allocationSize = 1)
    private Long idDetalleDescargo;
    @JoinColumn(name = "DESCARGO_ID", referencedColumnName = "DESCARGO_ID")
    @ManyToOne(optional = false)
    private AfDescargos descargoId;

    public AfDescargosDetalle() {
    }

    public AfDescargosDetalle(Long idDetalleDescargo) {
        this.idDetalleDescargo = idDetalleDescargo;
    }

    public AfDescargosDetalle(Long idDetalleDescargo, BigInteger idBien) {
        this.idDetalleDescargo = idDetalleDescargo;
        this.idBien = idBien;
    }

    public BigInteger getIdBien() {
        return idBien;
    }

    public void setIdBien(BigInteger idBien) {
        this.idBien = idBien;
    }

    public String getIdTipoDescargo() {
        return idTipoDescargo;
    }

    public void setIdTipoDescargo(String idTipoDescargo) {
        this.idTipoDescargo = idTipoDescargo;
    }

    public Long getIdDetalleDescargo() {
        return idDetalleDescargo;
    }

    public void setIdDetalleDescargo(Long idDetalleDescargo) {
        this.idDetalleDescargo = idDetalleDescargo;
    }

    public AfDescargos getDescargoId() {
        return descargoId;
    }

    public String getCodigoUnidad() {
        return codigoUnidad;
    }

    public void setCodigoUnidad(String codigoUnidad) {
        this.codigoUnidad = codigoUnidad;
    }

    public void setDescargoId(AfDescargos descargoId) {
        this.descargoId = descargoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleDescargo != null ? idDetalleDescargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfDescargosDetalle)) {
            return false;
        }
        AfDescargosDetalle other = (AfDescargosDetalle) object;
        if ((this.idDetalleDescargo == null && other.idDetalleDescargo != null) || (this.idDetalleDescargo != null && !this.idDetalleDescargo.equals(other.idDetalleDescargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfDescargosDetalle[ idDetalleDescargo=" + idDetalleDescargo + " ]";
    }
    
}
