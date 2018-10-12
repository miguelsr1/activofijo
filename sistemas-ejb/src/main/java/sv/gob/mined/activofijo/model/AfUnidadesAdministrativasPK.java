/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author JISTorres
 */
@Embeddable
public class AfUnidadesAdministrativasPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "UNIDAD_ACTIVO_FIJO")
    private String unidadActivoFijo;
    @Basic(optional = false)
    @Column(name = "CODIGO_UNIDAD")
    private String codigoUnidad;

    public AfUnidadesAdministrativasPK() {
    }

    public AfUnidadesAdministrativasPK(String unidadActivoFijo) {
        this.unidadActivoFijo = unidadActivoFijo;
    }

    public AfUnidadesAdministrativasPK(String unidadActivoFijo, String codigoUnidad) {
        this.unidadActivoFijo = unidadActivoFijo;
        this.codigoUnidad = codigoUnidad;
    }

    public String getUnidadActivoFijo() {
        return unidadActivoFijo;
    }

    public void setUnidadActivoFijo(String unidadActivoFijo) {
        this.unidadActivoFijo = unidadActivoFijo;
    }

    public String getCodigoUnidad() {
        return codigoUnidad;
    }

    public void setCodigoUnidad(String codigoUnidad) {
        this.codigoUnidad = codigoUnidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unidadActivoFijo != null ? unidadActivoFijo.hashCode() : 0);
        hash += (codigoUnidad != null ? codigoUnidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfUnidadesAdministrativasPK)) {
            return false;
        }
        AfUnidadesAdministrativasPK other = (AfUnidadesAdministrativasPK) object;
        if ((this.unidadActivoFijo == null && other.unidadActivoFijo != null) || (this.unidadActivoFijo != null && !this.unidadActivoFijo.equals(other.unidadActivoFijo))) {
            return false;
        }
        if ((this.codigoUnidad == null && other.codigoUnidad != null) || (this.codigoUnidad != null && !this.codigoUnidad.equals(other.codigoUnidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  unidadActivoFijo +" "+ codigoUnidad ;
    }

}
