/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_SOLVENCIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfSolvencias.findAll", query = "SELECT a FROM AfSolvencias a")
    , @NamedQuery(name = "AfSolvencias.findByAnio", query = "SELECT a FROM AfSolvencias a WHERE a.anio = :anio")
    , @NamedQuery(name = "AfSolvencias.findByCodigoUnidad", query = "SELECT a FROM AfSolvencias a WHERE a.codigoUnidad = :codigoUnidad")
    , @NamedQuery(name = "AfSolvencias.findByFechaSolvencia", query = "SELECT a FROM AfSolvencias a WHERE a.fechaSolvencia = :fechaSolvencia")
    , @NamedQuery(name = "AfSolvencias.findByUsuarioCreo", query = "SELECT a FROM AfSolvencias a WHERE a.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "AfSolvencias.findByFechaModifica", query = "SELECT a FROM AfSolvencias a WHERE a.fechaModifica = :fechaModifica")
    , @NamedQuery(name = "AfSolvencias.findByUsuarioModifica", query = "SELECT a FROM AfSolvencias a WHERE a.usuarioModifica = :usuarioModifica")
    , @NamedQuery(name = "AfSolvencias.findByIdSolvencia", query = "SELECT a FROM AfSolvencias a WHERE a.idSolvencia = :idSolvencia")})
public class AfSolvencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "ANIO")
    private String anio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "CODIGO_UNIDAD")
    private String codigoUnidad;
    @Column(name = "FECHA_SOLVENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolvencia;
    @Size(max = 10)
    @Column(name = "USUARIO_CREO")
    private String usuarioCreo;
    @Column(name = "FECHA_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModifica;
    @Size(max = 10)
    @Column(name = "USUARIO_MODIFICA")
    private String usuarioModifica;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SOLVENCIA")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SOLVENCIA")
    @SequenceGenerator(name="SEQ_SOLVENCIA", sequenceName="SEQ_SOLVENCIA",initialValue = 1,allocationSize = 1)
    private BigDecimal idSolvencia;

    public AfSolvencias() {
    }

    public AfSolvencias(BigDecimal idSolvencia) {
        this.idSolvencia = idSolvencia;
    }

    public AfSolvencias(BigDecimal idSolvencia, String anio, String codigoUnidad) {
        this.idSolvencia = idSolvencia;
        this.anio = anio;
        this.codigoUnidad = codigoUnidad;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCodigoUnidad() {
        return codigoUnidad;
    }

    public void setCodigoUnidad(String codigoUnidad) {
        this.codigoUnidad = codigoUnidad;
    }

    public Date getFechaSolvencia() {
        return fechaSolvencia;
    }

    public void setFechaSolvencia(Date fechaSolvencia) {
        this.fechaSolvencia = fechaSolvencia;
    }

    public String getUsuarioCreo() {
        return usuarioCreo;
    }

    public void setUsuarioCreo(String usuarioCreo) {
        this.usuarioCreo = usuarioCreo;
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

    public BigDecimal getIdSolvencia() {
        return idSolvencia;
    }

    public void setIdSolvencia(BigDecimal idSolvencia) {
        this.idSolvencia = idSolvencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolvencia != null ? idSolvencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfSolvencias)) {
            return false;
        }
        AfSolvencias other = (AfSolvencias) object;
        if ((this.idSolvencia == null && other.idSolvencia != null) || (this.idSolvencia != null && !this.idSolvencia.equals(other.idSolvencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.activofijo.model.AfSolvencias[ idSolvencia=" + idSolvencia + " ]";
    }
    
}
