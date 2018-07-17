/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "APLICACION", schema = "SEGURIDAD_V2")
public class Aplicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_APLICACION")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP")
    @SequenceGenerator(name = "APP", sequenceName = "SEQ_APLICACION", allocationSize = 1, initialValue = 1)
    private BigDecimal idAplicacion;
    @Basic(optional = false)
    @Column(name = "NOMBRE_APLICACION")
    private String nombreAplicacion;
    @Column(name = "FECHA_INICIO_PRODUCCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioProduccion;
    @Column(name = "ESTADO_APLICACION")
    private Character estadoAplicacion;
    @Column(name = "UNIDAD_DUENA")
    private String unidadDuena;
    @Column(name = "ADMINISTRADOR_APLICACION")
    private String administradorAplicacion;
    @OneToMany(mappedBy = "idAplicacion", fetch = FetchType.LAZY)
    private List<UsuarioAplicacion> usuarioAplicacionList;
    @OneToMany(mappedBy = "idAplicacion", fetch = FetchType.LAZY)
    private List<GrupoAplicacion> grupoAplicacionList;
    @OneToMany(mappedBy = "idAplicacion", fetch = FetchType.LAZY)
    private List<AplicacionOpcMenu> aplicacionOpcMenuList;

    public Aplicacion() {
    }

    public Aplicacion(BigDecimal idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public Aplicacion(BigDecimal idAplicacion, String nombreAplicacion) {
        this.idAplicacion = idAplicacion;
        this.nombreAplicacion = nombreAplicacion;
    }

    public BigDecimal getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(BigDecimal idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        this.nombreAplicacion = nombreAplicacion;
    }

    public Date getFechaInicioProduccion() {
        return fechaInicioProduccion;
    }

    public void setFechaInicioProduccion(Date fechaInicioProduccion) {
        this.fechaInicioProduccion = fechaInicioProduccion;
    }

    public Character getEstadoAplicacion() {
        return estadoAplicacion;
    }

    public void setEstadoAplicacion(Character estadoAplicacion) {
        this.estadoAplicacion = estadoAplicacion;
    }

    public String getUnidadDuena() {
        return unidadDuena;
    }

    public void setUnidadDuena(String unidadDuena) {
        this.unidadDuena = unidadDuena;
    }

    public String getAdministradorAplicacion() {
        return administradorAplicacion;
    }

    public void setAdministradorAplicacion(String administradorAplicacion) {
        this.administradorAplicacion = administradorAplicacion;
    }

    public List<UsuarioAplicacion> getUsuarioAplicacionList() {
        return usuarioAplicacionList;
    }

    public void setUsuarioAplicacionList(List<UsuarioAplicacion> usuarioAplicacionList) {
        this.usuarioAplicacionList = usuarioAplicacionList;
    }

    public List<GrupoAplicacion> getGrupoAplicacionList() {
        return grupoAplicacionList;
    }

    public void setGrupoAplicacionList(List<GrupoAplicacion> grupoAplicacionList) {
        this.grupoAplicacionList = grupoAplicacionList;
    }

    public List<AplicacionOpcMenu> getAplicacionOpcMenuList() {
        return aplicacionOpcMenuList;
    }

    public void setAplicacionOpcMenuList(List<AplicacionOpcMenu> aplicacionOpcMenuList) {
        this.aplicacionOpcMenuList = aplicacionOpcMenuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAplicacion != null ? idAplicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aplicacion)) {
            return false;
        }
        Aplicacion other = (Aplicacion) object;
        if ((this.idAplicacion == null && other.idAplicacion != null) || (this.idAplicacion != null && !this.idAplicacion.equals(other.idAplicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreAplicacion;
    }
    
}
