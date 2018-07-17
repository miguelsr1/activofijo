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
public class VwDepreciaciones implements Serializable {
@Id    
private String anio;
private String mes;
private Integer BienesDepreciados;


public VwDepreciaciones() {
    }  

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getBienesDepreciados() {
        return BienesDepreciados;
    }

    public void setBienesDepreciados(Integer BienesDepreciados) {
        this.BienesDepreciados = BienesDepreciados;
    }

    @Override
    public String toString() {
        return "VwDepreciaciones{" + "anio=" + anio + ", mes=" + mes + ", BienesDepreciados=" + BienesDepreciados + '}';
    }

   




}
