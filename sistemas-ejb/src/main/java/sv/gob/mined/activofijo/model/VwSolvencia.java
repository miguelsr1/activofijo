/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author JISTorres
 */
@Entity
public class VwSolvencia implements Serializable {

@Id
private BigDecimal idRow; 
private String codigoEntidad;
private String unidadActivoFijo;
private String nombreMunicipio;
private String nombre;
@Temporal(javax.persistence.TemporalType.DATE)
private Date fechaSolvencia;
private String anio;
private Long numBienes;
private BigDecimal costo;
private String codigoMunicipio;
@Temporal(javax.persistence.TemporalType.DATE)
private Date fechaActualizacion;
private String tipoUnidad;

public VwSolvencia() {
    }  

    public BigDecimal getIdRow() {
        return idRow;
    }

    public void setIdRow(BigDecimal idRow) {
        this.idRow = idRow;
    }


    public String getUnidadActivoFijo() {
        return unidadActivoFijo;
    }

    public void setUnidadActivoFijo(String unidadActivoFijo) {
        this.unidadActivoFijo = unidadActivoFijo;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaSolvencia() {
        return fechaSolvencia;
    }

    public void setFechaSolvencia(Date fechaSolvencia) {
        this.fechaSolvencia = fechaSolvencia;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Long getNumBienes() {
        return numBienes;
    }

    public void setNumBienes(Long numBienes) {
        this.numBienes = numBienes;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    @Override
    public String toString() {
        return "VwSolvencia{" + "codigoEntidad=" + codigoEntidad + ", unidadActivoFijo=" + unidadActivoFijo + ", nombreMunicipio=" + nombreMunicipio + ", nombre=" + nombre + ", fechaSolvencia=" + fechaSolvencia + ", anio=" + anio + ", numBienes=" + numBienes + ", costo=" + costo + '}';
    }

  



}
