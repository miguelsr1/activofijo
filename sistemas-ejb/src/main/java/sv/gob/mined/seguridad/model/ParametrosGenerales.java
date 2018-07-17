/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "PARAMETROS_GENERALES", schema = "SEGURIDAD_V2")
public class ParametrosGenerales implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARAMETRO")
    private BigDecimal idParametro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_INTENTOS_CLAVE")
    private short numIntentosClave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIODICIDAD_CAMBIO_CLAVE")
    private short periodicidadCambioClave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO_PARAMETRO")
    private char activoParametro;

    public ParametrosGenerales() {
    }

    public ParametrosGenerales(BigDecimal idParametro) {
        this.idParametro = idParametro;
    }

    public ParametrosGenerales(BigDecimal idParametro, short numIntentosClave, short periodicidadCambioClave, char activoParametro) {
        this.idParametro = idParametro;
        this.numIntentosClave = numIntentosClave;
        this.periodicidadCambioClave = periodicidadCambioClave;
        this.activoParametro = activoParametro;
    }

    public BigDecimal getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(BigDecimal idParametro) {
        this.idParametro = idParametro;
    }

    public short getNumIntentosClave() {
        return numIntentosClave;
    }

    public void setNumIntentosClave(short numIntentosClave) {
        this.numIntentosClave = numIntentosClave;
    }

    public short getPeriodicidadCambioClave() {
        return periodicidadCambioClave;
    }

    public void setPeriodicidadCambioClave(short periodicidadCambioClave) {
        this.periodicidadCambioClave = periodicidadCambioClave;
    }

    public char getActivoParametro() {
        return activoParametro;
    }

    public void setActivoParametro(char activoParametro) {
        this.activoParametro = activoParametro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametro != null ? idParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrosGenerales)) {
            return false;
        }
        ParametrosGenerales other = (ParametrosGenerales) object;
        if ((this.idParametro == null && other.idParametro != null) || (this.idParametro != null && !this.idParametro.equals(other.idParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.seguridad.model.ParametrosGenerales[ idParametro=" + idParametro + " ]";
    }
    
}
