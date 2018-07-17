/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_EJERCICIO_FISCAL", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfEjercicioFiscal.findAll", query = "SELECT a FROM AfEjercicioFiscal a"),
    @NamedQuery(name = "AfEjercicioFiscal.findByAnio", query = "SELECT a FROM AfEjercicioFiscal a WHERE a.anio = :anio"),
    @NamedQuery(name = "AfEjercicioFiscal.findByEstado", query = "SELECT a FROM AfEjercicioFiscal a WHERE a.estado = :estado"),
    @NamedQuery(name = "AfEjercicioFiscal.findByUsuarioEjecutaDeprec", query = "SELECT a FROM AfEjercicioFiscal a WHERE a.usuarioEjecutaDeprec = :usuarioEjecutaDeprec"),
    @NamedQuery(name = "AfEjercicioFiscal.findByFechaEjecutaDeprec", query = "SELECT a FROM AfEjercicioFiscal a WHERE a.fechaEjecutaDeprec = :fechaEjecutaDeprec"),
    @NamedQuery(name = "AfEjercicioFiscal.findByMes", query = "SELECT a FROM AfEjercicioFiscal a WHERE a.mes = :mes"),
    @NamedQuery(name = "AfEjercicioFiscal.findByIdEjercicio", query = "SELECT a FROM AfEjercicioFiscal a WHERE a.idEjercicio = :idEjercicio")})
public class AfEjercicioFiscal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ANIO")
    private short anio;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "USUARIO_EJECUTA_DEPREC")
    private String usuarioEjecutaDeprec;
    @Column(name = "FECHA_EJECUTA_DEPREC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEjecutaDeprec;
    @Basic(optional = false)
    @Column(name = "MES")
    private short mes;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_EJERCICIO")
    private Short idEjercicio;

    public AfEjercicioFiscal() {
    }

    public AfEjercicioFiscal(Short idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public AfEjercicioFiscal(Short idEjercicio, short anio, short mes) {
        this.idEjercicio = idEjercicio;
        this.anio = anio;
        this.mes = mes;
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getUsuarioEjecutaDeprec() {
        return usuarioEjecutaDeprec;
    }

    public void setUsuarioEjecutaDeprec(String usuarioEjecutaDeprec) {
        this.usuarioEjecutaDeprec = usuarioEjecutaDeprec;
    }

    public Date getFechaEjecutaDeprec() {
        return fechaEjecutaDeprec;
    }

    public void setFechaEjecutaDeprec(Date fechaEjecutaDeprec) {
        this.fechaEjecutaDeprec = fechaEjecutaDeprec;
    }

    public short getMes() {
        return mes;
    }

    public void setMes(short mes) {
        this.mes = mes;
    }

    public Short getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Short idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEjercicio != null ? idEjercicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfEjercicioFiscal)) {
            return false;
        }
        AfEjercicioFiscal other = (AfEjercicioFiscal) object;
        if ((this.idEjercicio == null && other.idEjercicio != null) || (this.idEjercicio != null && !this.idEjercicio.equals(other.idEjercicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfEjercicioFiscal[ idEjercicio=" + idEjercicio + " ]";
    }
    
}
