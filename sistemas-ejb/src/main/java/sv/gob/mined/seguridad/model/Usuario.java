/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "USUARIO", schema = "SEGURIDAD_V2")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "CLAVE_ACCESO")
    private String claveAcceso;
    @Column(name = "CAMBIAR_CLAVE")
    private Character cambiarClave;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "SEXO")
    private Character sexo;
    @Column(name = "FECHA_EXPIRACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpiracion;
    @Column(name = "ACTIVO")
    private Character activo;
    @Column(name = "DUI")
    private String dui;
    @Column(name = "CORRE_ELECTRONICO")
    private String correElectronico;
    @Column(name = "USUARIO_BLOQUEADO")
    private Character usuarioBloqueado;
    @Column(name = "CODIGO_EMPLEADO")
    private String codigoEmpleado;
    @Column(name = "TIPO_USUARIO")
    private Character tipoUsuario;
    @Column(name = "CODIGO_ENTIDAD")
    private String codigoEntidad;
    @Column(name = "CODIGO_DEPARTAMENTO")
    private String codigoDepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login", fetch = FetchType.LAZY)
    private List<HistorialClavesAcceso> historialClavesAccesoList;
    @OneToMany(mappedBy = "login", fetch = FetchType.LAZY)
    private List<UsuarioAplicacion> usuarioAplicacionList;

    public Usuario() {
    }

    public Usuario(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public Character getCambiarClave() {
        return cambiarClave;
    }

    public void setCambiarClave(Character cambiarClave) {
        this.cambiarClave = cambiarClave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Character getActivo() {
        return activo;
    }

    public void setActivo(Character activo) {
        this.activo = activo;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getCorreElectronico() {
        return correElectronico;
    }

    public void setCorreElectronico(String correElectronico) {
        this.correElectronico = correElectronico;
    }

    public Character getUsuarioBloqueado() {
        return usuarioBloqueado;
    }

    public void setUsuarioBloqueado(Character usuarioBloqueado) {
        this.usuarioBloqueado = usuarioBloqueado;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public Character getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Character tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<HistorialClavesAcceso> getHistorialClavesAccesoList() {
        return historialClavesAccesoList;
    }

    public void setHistorialClavesAccesoList(List<HistorialClavesAcceso> historialClavesAccesoList) {
        this.historialClavesAccesoList = historialClavesAccesoList;
    }

    public List<UsuarioAplicacion> getUsuarioAplicacionList() {
        return usuarioAplicacionList;
    }

    public void setUsuarioAplicacionList(List<UsuarioAplicacion> usuarioAplicacionList) {
        this.usuarioAplicacionList = usuarioAplicacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        return !((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login)));
    }

    @Override
    public String toString() {
        return login;
    }

    public String getTipoUsu() {
        return tipoUsuario.toString();
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }
}
