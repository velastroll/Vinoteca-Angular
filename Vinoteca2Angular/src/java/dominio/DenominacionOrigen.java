/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mario Torbado
 */
@Entity
@Table(name = "DENOMINACION_ORIGEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DenominacionOrigen.findAll", query = "SELECT d FROM DenominacionOrigen d"),
    @NamedQuery(name = "DenominacionOrigen.findByDoId", query = "SELECT d FROM DenominacionOrigen d WHERE d.doId = :doId"),
    @NamedQuery(name = "DenominacionOrigen.findByNombre", query = "SELECT d FROM DenominacionOrigen d WHERE d.nombre = :nombre")})
public class DenominacionOrigen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DO_ID")
    private Integer doId;
    @Size(max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddenominacion")
    private Collection<Preferencia> preferenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddenominacion")
    private Collection<Vino> vinoCollection;

    public DenominacionOrigen() {
    }

    public DenominacionOrigen(Integer doId) {
        this.doId = doId;
    }

    public Integer getDoId() {
        return doId;
    }

    public void setDoId(Integer doId) {
        this.doId = doId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Preferencia> getPreferenciaCollection() {
        return preferenciaCollection;
    }

    public void setPreferenciaCollection(Collection<Preferencia> preferenciaCollection) {
        this.preferenciaCollection = preferenciaCollection;
    }

    @XmlTransient
    public Collection<Vino> getVinoCollection() {
        return vinoCollection;
    }

    public void setVinoCollection(Collection<Vino> vinoCollection) {
        this.vinoCollection = vinoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (doId != null ? doId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DenominacionOrigen)) {
            return false;
        }
        DenominacionOrigen other = (DenominacionOrigen) object;
        if ((this.doId == null && other.doId != null) || (this.doId != null && !this.doId.equals(other.doId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.DenominacionOrigen[ doId=" + doId + " ]";
    }
    
}
