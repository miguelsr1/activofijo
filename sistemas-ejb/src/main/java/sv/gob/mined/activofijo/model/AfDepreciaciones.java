/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "AF_DEPRECIACIONES", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfDepreciaciones.findAll", query = "SELECT a FROM AfDepreciaciones a"),
    @NamedQuery(name = "AfDepreciaciones.findByIdBien", query = "SELECT a FROM AfDepreciaciones a WHERE a.idBien = :idBien"),
    @NamedQuery(name = "AfDepreciaciones.findByMontoDepreciacion", query = "SELECT a FROM AfDepreciaciones a WHERE a.montoDepreciacion = :montoDepreciacion"),
    @NamedQuery(name = "AfDepreciaciones.findByNoDiasDeprec", query = "SELECT a FROM AfDepreciaciones a WHERE a.noDiasDeprec = :noDiasDeprec"),
    @NamedQuery(name = "AfDepreciaciones.findByFechaCalculo", query = "SELECT a FROM AfDepreciaciones a WHERE a.fechaCalculo = :fechaCalculo"),
    @NamedQuery(name = "AfDepreciaciones.findByAnio", query = "SELECT a FROM AfDepreciaciones a WHERE a.anio = :anio"),
    @NamedQuery(name = "AfDepreciaciones.findByMes", query = "SELECT a FROM AfDepreciaciones a WHERE a.mes = :mes"),
    @NamedQuery(name = "AfDepreciaciones.findByIdFuente", query = "SELECT a FROM AfDepreciaciones a WHERE a.idFuente = :idFuente"),
    @NamedQuery(name = "AfDepreciaciones.findByCodigoProyecto", query = "SELECT a FROM AfDepreciaciones a WHERE a.codigoProyecto = :codigoProyecto"),
    @NamedQuery(name = "AfDepreciaciones.findByMontoDepre2", query = "SELECT a FROM AfDepreciaciones a WHERE a.montoDepre2 = :montoDepre2"),
    @NamedQuery(name = "AfDepreciaciones.findByIdDepreciacion", query = "SELECT a FROM AfDepreciaciones a WHERE a.idDepreciacion = :idDepreciacion")})
public class AfDepreciaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ID_BIEN")
    private long idBien;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO_DEPRECIACION")
    private BigDecimal montoDepreciacion;
    @Column(name = "NO_DIAS_DEPREC")
    private Long noDiasDeprec;
    @Column(name = "FECHA_CALCULO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCalculo;
    @Basic(optional = false)
    @Column(name = "ANIO")
    private short anio;
    @Basic(optional = false)
    @Column(name = "MES")
    private short mes;
    @Column(name = "ID_FUENTE")
    private Long idFuente;
    @Column(name = "CODIGO_PROYECTO")
    private String codigoProyecto;
    @Column(name = "MONTO_DEPRE2")
    private BigDecimal montoDepre2;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DEPRECIACION")
    private Long idDepreciacion;

    public AfDepreciaciones() {
    }

    public AfDepreciaciones(Long idDepreciacion) {
        this.idDepreciacion = idDepreciacion;
    }

    public AfDepreciaciones(Long idDepreciacion, long idBien, short anio, short mes) {
        this.idDepreciacion = idDepreciacion;
        this.idBien = idBien;
        this.anio = anio;
        this.mes = mes;
    }

    public long getIdBien() {
        return idBien;
    }

    public void setIdBien(long idBien) {
        this.idBien = idBien;
    }

    public BigDecimal getMontoDepreciacion() {
        return montoDepreciacion;
    }

    public void setMontoDepreciacion(BigDecimal montoDepreciacion) {
        this.montoDepreciacion = montoDepreciacion;
    }

    public Long getNoDiasDeprec() {
        return noDiasDeprec;
    }

    public void setNoDiasDeprec(Long noDiasDeprec) {
        this.noDiasDeprec = noDiasDeprec;
    }

    public Date getFechaCalculo() {
        return fechaCalculo;
    }

    public void setFechaCalculo(Date fechaCalculo) {
        this.fechaCalculo = fechaCalculo;
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public short getMes() {
        return mes;
    }

    public void setMes(short mes) {
        this.mes = mes;
    }

    public Long getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(Long idFuente) {
        this.idFuente = idFuente;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public BigDecimal getMontoDepre2() {
        return montoDepre2;
    }

    public void setMontoDepre2(BigDecimal montoDepre2) {
        this.montoDepre2 = montoDepre2;
    }

    public Long getIdDepreciacion() {
        return idDepreciacion;
    }

    public void setIdDepreciacion(Long idDepreciacion) {
        this.idDepreciacion = idDepreciacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepreciacion != null ? idDepreciacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfDepreciaciones)) {
            return false;
        }
        AfDepreciaciones other = (AfDepreciaciones) object;
        if ((this.idDepreciacion == null && other.idDepreciacion != null) || (this.idDepreciacion != null && !this.idDepreciacion.equals(other.idDepreciacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfDepreciaciones[ idDepreciacion=" + idDepreciacion + " ]";
    }
    
}
