/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "USUARIO_APLICACION", schema = "SEGURIDAD_V2")
public class UsuarioAplicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USU_APP")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USU_APP")
    @SequenceGenerator(name = "USU_APP", sequenceName = "SEQ_USUARIO_APLICACION", allocationSize = 1, initialValue = 1)
    private BigDecimal idUsuApp;
    @Column(name = "CODIGO_DEPARTAMENTO")
    private String codigoDepartamento;
    @Column(name = "USU_GRUPO_ACTIVO")
    private Character usuGrupoActivo;
    @OneToMany(mappedBy = "idUsuApp", fetch = FetchType.LAZY)
    private List<UsuarioGrupoAplicacion> usuarioGrupoAplicacionList;
    @OneToMany(mappedBy = "idUsuGrupo", fetch = FetchType.LAZY)
    private List<BitacoraAcceso> bitacoraAccesoList;
    @JoinColumn(name = "LOGIN", referencedColumnName = "LOGIN")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario login;
    @JoinColumn(name = "ID_APLICACION", referencedColumnName = "ID_APLICACION")
    @ManyToOne(fetch = FetchType.EAGER)
    private Aplicacion idAplicacion;

    public UsuarioAplicacion() {
    }

    public UsuarioAplicacion(BigDecimal idUsuApp) {
        this.idUsuApp = idUsuApp;
    }

    public BigDecimal getIdUsuApp() {
        return idUsuApp;
    }

    public void setIdUsuApp(BigDecimal idUsuApp) {
        this.idUsuApp = idUsuApp;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Character getUsuGrupoActivo() {
        return usuGrupoActivo;
    }

    public void setUsuGrupoActivo(Character usuGrupoActivo) {
        this.usuGrupoActivo = usuGrupoActivo;
    }

    public List<UsuarioGrupoAplicacion> getUsuarioGrupoAplicacionList() {
        return usuarioGrupoAplicacionList;
    }

    public void setUsuarioGrupoAplicacionList(List<UsuarioGrupoAplicacion> usuarioGrupoAplicacionList) {
        this.usuarioGrupoAplicacionList = usuarioGrupoAplicacionList;
    }

    public List<BitacoraAcceso> getBitacoraAccesoList() {
        return bitacoraAccesoList;
    }

    public void setBitacoraAccesoList(List<BitacoraAcceso> bitacoraAccesoList) {
        this.bitacoraAccesoList = bitacoraAccesoList;
    }

    public Usuario getLogin() {
        return login;
    }

    public void setLogin(Usuario login) {
        this.login = login;
    }

    public Aplicacion getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(Aplicacion idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuApp != null ? idUsuApp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioAplicacion)) {
            return false;
        }
        UsuarioAplicacion other = (UsuarioAplicacion) object;
        if ((this.idUsuApp == null && other.idUsuApp != null) || (this.idUsuApp != null && !this.idUsuApp.equals(other.idUsuApp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.seguridad.model.UsuarioAplicacion[ idUsuApp=" + idUsuApp + " ]";
    }
    
}
