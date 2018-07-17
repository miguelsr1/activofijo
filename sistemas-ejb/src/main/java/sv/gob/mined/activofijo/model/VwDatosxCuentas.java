/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author JISTorres
 */
@Entity
public class VwDatosxCuentas implements Serializable {
    @Id
    private String categoria;
    private BigDecimal precio;
    private BigDecimal valorRes;
    private BigDecimal valorActual;
    private BigDecimal depreAcumulada;
    private BigDecimal pendienteDepre;
    
   
    
    public VwDatosxCuentas() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getValorRes() {
        return valorRes;
    }

    public void setValorRes(BigDecimal valorRes) {
        this.valorRes = valorRes;
    }

    public BigDecimal getDepreAcumulada() {
        return depreAcumulada;
    }

    public void setDepreAcumulada(BigDecimal depreAcumulada) {
        this.depreAcumulada = depreAcumulada;
    }

    public BigDecimal getPendienteDepre() {
        return pendienteDepre;
    }

    public void setPendienteDepre(BigDecimal pendienteDepre) {
        this.pendienteDepre = pendienteDepre;
    }

    public BigDecimal getValorActual() {
        return valorActual;
    }

    public void setValorActual(BigDecimal valorActual) {
        this.valorActual = valorActual;
    }

    @Override
    public String toString() {
        return "VwDatosxCuentas{" + "categoria=" + categoria + ", precio=" + precio + ", valorRes=" + valorRes + ", depreAcumulada=" + depreAcumulada + ", pendienteDepre=" + pendienteDepre + '}';
    }

   
  

  
    
    
}
