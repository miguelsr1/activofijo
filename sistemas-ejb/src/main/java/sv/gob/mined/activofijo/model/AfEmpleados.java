/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_EMPLEADOS", catalog = "", schema = "ACTIVO_FIJO_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfEmpleados.findAll", query = "SELECT a FROM AfEmpleados a")
    , @NamedQuery(name = "AfEmpleados.findByIdEmpleado", query = "SELECT a FROM AfEmpleados a WHERE a.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "AfEmpleados.findByNombres", query = "SELECT a FROM AfEmpleados a WHERE a.nombres = :nombres")
    , @NamedQuery(name = "AfEmpleados.findByApellidos", query = "SELECT a FROM AfEmpleados a WHERE a.apellidos = :apellidos")
    , @NamedQuery(name = "AfEmpleados.findByCargo", query = "SELECT a FROM AfEmpleados a WHERE a.cargo = :cargo")
    , @NamedQuery(name = "AfEmpleados.findByUsuarioCrea", query = "SELECT a FROM AfEmpleados a WHERE a.usuarioCrea = :usuarioCrea")
    , @NamedQuery(name = "AfEmpleados.findByFechaCrea", query = "SELECT a FROM AfEmpleados a WHERE a.fechaCrea = :fechaCrea")
    , @NamedQuery(name = "AfEmpleados.findByUsuarioMod", query = "SELECT a FROM AfEmpleados a WHERE a.usuarioMod = :usuarioMod")
    , @NamedQuery(name = "AfEmpleados.findByFechaMod", query = "SELECT a FROM AfEmpleados a WHERE a.fechaMod = :fechaMod")})
public class AfEmpleados implements Serializable {

   
    
    private static final long serialVersionUID = 1L;
    @Id
  @Basic(optional = false)
     @Column(name = "ID_EMPLEADO")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_EMPLEADO")
    @SequenceGenerator(name="SEQ_EMPLEADO", sequenceName="SEQ_EMPLEADO",initialValue = 1,allocationSize = 1)
    private Long idEmpleado;
    @Size(max = 100)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 100)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Size(max = 200)
    @Column(name = "CARGO")
    private String cargo;
    @Size(max = 20)
    @Column(name = "USUARIO_CREA")
    private String usuarioCrea;
    @Column(name = "FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCrea;
    @Size(max = 20)
    @Column(name = "USUARIO_MOD")
    private String usuarioMod;
    @Column(name = "FECHA_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMod;
   @Column(name = "UNIDAD_ACTIVO_FIJO")
    private String unidadActivoFijo;
    @Column(name = "CODIGO_UNIDAD")
    private String codigoUnidad;

    
    public AfEmpleados() {
    }

    public AfEmpleados(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public String getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(String usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public Date getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Date fechaMod) {
        this.fechaMod = fechaMod;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfEmpleados)) {
            return false;
        }
        AfEmpleados other = (AfEmpleados) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.activofijo.model.AfEmpleados[ idEmpleado=" + idEmpleado + " ]";
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
    
}
