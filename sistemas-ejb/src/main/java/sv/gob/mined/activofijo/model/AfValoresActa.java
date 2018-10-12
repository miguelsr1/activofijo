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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "AF_VALORES_ACTA", catalog = "", schema = "ACTIVO_FIJO_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfValoresActa.findAll", query = "SELECT a FROM AfValoresActa a")
    , @NamedQuery(name = "AfValoresActa.findByUnidadActivoFijo", query = "SELECT a FROM AfValoresActa a WHERE a.unidadActivoFijo = :unidadActivoFijo")
    , @NamedQuery(name = "AfValoresActa.findByAutoriza1", query = "SELECT a FROM AfValoresActa a WHERE a.autoriza1 = :autoriza1")
    , @NamedQuery(name = "AfValoresActa.findByAutoriza2", query = "SELECT a FROM AfValoresActa a WHERE a.autoriza2 = :autoriza2")
    , @NamedQuery(name = "AfValoresActa.findByAutoriza3", query = "SELECT a FROM AfValoresActa a WHERE a.autoriza3 = :autoriza3")
    , @NamedQuery(name = "AfValoresActa.findByNumeroAcuerdo", query = "SELECT a FROM AfValoresActa a WHERE a.numeroAcuerdo = :numeroAcuerdo")
    , @NamedQuery(name = "AfValoresActa.findByFechaAcuerdo", query = "SELECT a FROM AfValoresActa a WHERE a.fechaAcuerdo = :fechaAcuerdo")})
public class AfValoresActa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "UNIDAD_ACTIVO_FIJO")
    private String unidadActivoFijo;
    @Size(max = 200)
    @Column(name = "AUTORIZA_1")
    private String autoriza1;
    @Size(max = 200)
    @Column(name = "AUTORIZA_2")
    private String autoriza2;
    @Size(max = 200)
    @Column(name = "AUTORIZA_3")
    private String autoriza3;
    @Size(max = 20)
    @Column(name = "NUMERO_ACUERDO")
    private String numeroAcuerdo;
    @Column(name = "FECHA_ACUERDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAcuerdo;

    public AfValoresActa() {
    }

    public AfValoresActa(String unidadActivoFijo) {
        this.unidadActivoFijo = unidadActivoFijo;
    }

    public String getUnidadActivoFijo() {
        return unidadActivoFijo;
    }

    public void setUnidadActivoFijo(String unidadActivoFijo) {
        this.unidadActivoFijo = unidadActivoFijo;
    }

    public String getAutoriza1() {
        return autoriza1;
    }

    public void setAutoriza1(String autoriza1) {
        this.autoriza1 = autoriza1;
    }

    public String getAutoriza2() {
        return autoriza2;
    }

    public void setAutoriza2(String autoriza2) {
        this.autoriza2 = autoriza2;
    }

    public String getAutoriza3() {
        return autoriza3;
    }

    public void setAutoriza3(String autoriza3) {
        this.autoriza3 = autoriza3;
    }

    public String getNumeroAcuerdo() {
        return numeroAcuerdo;
    }

    public void setNumeroAcuerdo(String numeroAcuerdo) {
        this.numeroAcuerdo = numeroAcuerdo;
    }

    public Date getFechaAcuerdo() {
        return fechaAcuerdo;
    }

    public void setFechaAcuerdo(Date fechaAcuerdo) {
        this.fechaAcuerdo = fechaAcuerdo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unidadActivoFijo != null ? unidadActivoFijo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfValoresActa)) {
            return false;
        }
        AfValoresActa other = (AfValoresActa) object;
        if ((this.unidadActivoFijo == null && other.unidadActivoFijo != null) || (this.unidadActivoFijo != null && !this.unidadActivoFijo.equals(other.unidadActivoFijo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.activofijo.model.AfValoresActa[ unidadActivoFijo=" + unidadActivoFijo + " ]";
    }
    
}
