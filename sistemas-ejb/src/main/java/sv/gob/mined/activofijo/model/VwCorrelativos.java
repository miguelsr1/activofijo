/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author JISTorres
 */
@Entity
public class VwCorrelativos implements Serializable {
    
private String correlativo;
@Id
private Long idTipoBien;
private String codigoTipoBien;
private String nombreTipoBien;
private Long idCatBien;
private String codigoUnidad;
private String unidadActivoFijo;
private String nombreUnidadAf;
private String nombreUnidad;


public VwCorrelativos() {
    }  

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public Long getIdTipoBien() {
        return idTipoBien;
    }

    public void setIdTipoBien(Long idTipoBien) {
        this.idTipoBien = idTipoBien;
    }

    public String getNombreTipoBien() {
        return nombreTipoBien;
    }

    public void setNombreTipoBien(String nombreTipoBien) {
        this.nombreTipoBien = nombreTipoBien;
    }

    public String getCodigoTipoBien() {
        return codigoTipoBien;
    }

    public void setCodigoTipoBien(String codigoTipoBien) {
        this.codigoTipoBien = codigoTipoBien;
    }

    public Long getIdCatBien() {
        return idCatBien;
    }

    public void setIdCatBien(Long idCatBien) {
        this.idCatBien = idCatBien;
    }

    public String getCodigoUnidad() {
        return codigoUnidad;
    }

    public void setCodigoUnidad(String codigoUnidad) {
        this.codigoUnidad = codigoUnidad;
    }

    public String getUnidadActivoFijo() {
        return unidadActivoFijo;
    }

    public void setUnidadActivoFijo(String unidadActivoFijo) {
        this.unidadActivoFijo = unidadActivoFijo;
    }

    public String getNombreUnidadAf() {
        return nombreUnidadAf;
    }

    public void setNombreUnidadAf(String nombreUnidadAf) {
        this.nombreUnidadAf = nombreUnidadAf;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

 
    @Override
    public String toString() {
        return "VwCorrelativos{" + "correlativo=" + correlativo + ", idTipoBien=" + idTipoBien + ", nombreTipoBien=" + nombreTipoBien + ", idCatBien=" + idCatBien + ", codigoUnidad=" + codigoUnidad + ", unidadActivoFijo=" + unidadActivoFijo + ", nombreUnidadAf=" + nombreUnidadAf + ", nombreUnidad=" + nombreUnidad +  '}';
    }




}
