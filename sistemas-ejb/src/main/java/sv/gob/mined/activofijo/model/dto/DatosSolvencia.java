/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model.dto;


import java.util.ArrayList;
import java.util.List;
import sv.gob.mined.activofijo.model.VwSolvencia;



/**
 *
 * @author JISTorres
 */
public class DatosSolvencia {
     private String institucion;
    private String unidad;
    private String fuente;
    private String fecReporte;
    private String usuario;
    
    private List <VwSolvencia> lstDatos= new ArrayList<VwSolvencia>();

    

    public DatosSolvencia() {
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getFecReporte() {
        return fecReporte;
    }

    public void setFecReporte(String fecReporte) {
        this.fecReporte = fecReporte;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<VwSolvencia> getLstDatos() {
        return lstDatos;
    }

    public void setLstDatos(List<VwSolvencia> lstDatos) {
        this.lstDatos = lstDatos;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

   
    
}
