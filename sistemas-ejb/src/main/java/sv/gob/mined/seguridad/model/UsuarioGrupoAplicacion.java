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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "USUARIO_GRUPO_APLICACION", schema = "SEGURIDAD_V2")
public class UsuarioGrupoAplicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USU_GRUPO_APP")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USU_GRUPO_APP")
    @SequenceGenerator(name = "USU_GRUPO_APP", sequenceName = "SEQ_USU_GRUPO_APP", allocationSize = 1, initialValue = 1)
    private BigDecimal idUsuGrupoApp;
    @Column(name = "USU_GRUPO_APP")
    private Character usuGrupoApp;
    @Column(name = "ACTIVO_GRUPO_APP")
    private Character activoGrupoApp;
    @JoinColumn(name = "ID_USU_APP", referencedColumnName = "ID_USU_APP")
    @ManyToOne(fetch = FetchType.EAGER)
    private UsuarioAplicacion idUsuApp;
    @JoinColumn(name = "ID_GRUPO_APP", referencedColumnName = "ID_GRUPO_APP")
    @ManyToOne(fetch = FetchType.EAGER)
    private GrupoAplicacion idGrupoApp;

    public UsuarioGrupoAplicacion() {
    }

    public UsuarioGrupoAplicacion(BigDecimal idUsuGrupoApp) {
        this.idUsuGrupoApp = idUsuGrupoApp;
    }

    public BigDecimal getIdUsuGrupoApp() {
        return idUsuGrupoApp;
    }

    public void setIdUsuGrupoApp(BigDecimal idUsuGrupoApp) {
        this.idUsuGrupoApp = idUsuGrupoApp;
    }

    public Character getUsuGrupoApp() {
        return usuGrupoApp;
    }

    public void setUsuGrupoApp(Character usuGrupoApp) {
        this.usuGrupoApp = usuGrupoApp;
    }

    public Character getActivoGrupoApp() {
        return activoGrupoApp;
    }

    public void setActivoGrupoApp(Character activoGrupoApp) {
        this.activoGrupoApp = activoGrupoApp;
    }

    public UsuarioAplicacion getIdUsuApp() {
        return idUsuApp;
    }

    public void setIdUsuApp(UsuarioAplicacion idUsuApp) {
        this.idUsuApp = idUsuApp;
    }

    public GrupoAplicacion getIdGrupoApp() {
        return idGrupoApp;
    }

    public void setIdGrupoApp(GrupoAplicacion idGrupoApp) {
        this.idGrupoApp = idGrupoApp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuGrupoApp != null ? idUsuGrupoApp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioGrupoAplicacion)) {
            return false;
        }
        UsuarioGrupoAplicacion other = (UsuarioGrupoAplicacion) object;
        if ((this.idUsuGrupoApp == null && other.idUsuGrupoApp != null) || (this.idUsuGrupoApp != null && !this.idUsuGrupoApp.equals(other.idUsuGrupoApp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.seguridad.model.UsuarioGrupoAplicacion[ idUsuGrupoApp=" + idUsuGrupoApp + " ]";
    }
    
}
