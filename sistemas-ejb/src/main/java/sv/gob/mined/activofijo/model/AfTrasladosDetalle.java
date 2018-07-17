/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_TRASLADOS_DETALLE", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfTrasladosDetalle.findAll", query = "SELECT a FROM AfTrasladosDetalle a"),
    @NamedQuery(name = "AfTrasladosDetalle.findByIdTraslado", query = "SELECT a FROM AfTrasladosDetalle a WHERE a.idTraslado = :idTraslado"),
    @NamedQuery(name = "AfTrasladosDetalle.findByIdBien", query = "SELECT a FROM AfTrasladosDetalle a WHERE a.idBien = :idBien"),
    @NamedQuery(name = "AfTrasladosDetalle.findByIdTrasladoDetalle", query = "SELECT a FROM AfTrasladosDetalle a WHERE a.idTrasladoDetalle = :idTrasladoDetalle")})
public class AfTrasladosDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ID_TRASLADO")
    private long idTraslado;
    @Column(name = "ID_BIEN")
    private Long idBien;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TRASLADO_DETALLE")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TRASDETALLE")
    @SequenceGenerator(name="SEQ_TRASDETALLE", sequenceName="SEQ_TRASDETALLE",initialValue = 1,allocationSize = 1)
    private Long idTrasladoDetalle;

    public AfTrasladosDetalle() {
    }

    public AfTrasladosDetalle(Long idTrasladoDetalle) {
        this.idTrasladoDetalle = idTrasladoDetalle;
    }

    public AfTrasladosDetalle(Long idTrasladoDetalle, long idTraslado) {
        this.idTrasladoDetalle = idTrasladoDetalle;
        this.idTraslado = idTraslado;
    }

    public long getIdTraslado() {
        return idTraslado;
    }

    public void setIdTraslado(long idTraslado) {
        this.idTraslado = idTraslado;
    }

   
    public Long getIdBien() {
        return idBien;
    }

    public void setIdBien(Long idBien) {
        this.idBien = idBien;
    }

    public Long getIdTrasladoDetalle() {
        return idTrasladoDetalle;
    }

    public void setIdTrasladoDetalle(Long idTrasladoDetalle) {
        this.idTrasladoDetalle = idTrasladoDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrasladoDetalle != null ? idTrasladoDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfTrasladosDetalle)) {
            return false;
        }
        AfTrasladosDetalle other = (AfTrasladosDetalle) object;
        if ((this.idTrasladoDetalle == null && other.idTrasladoDetalle != null) || (this.idTrasladoDetalle != null && !this.idTrasladoDetalle.equals(other.idTrasladoDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfTrasladosDetalle[ idTrasladoDetalle=" + idTrasladoDetalle + " ]";
    }
    
}
