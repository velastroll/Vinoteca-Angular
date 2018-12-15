/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mario Torbado
 */
@Entity
@Table(name = "PREFERENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preferencia.findAll", query = "SELECT p FROM Preferencia p"),
    @NamedQuery(name = "Preferencia.findById", query = "SELECT p FROM Preferencia p WHERE p.id = :id")})
public class Preferencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "NIFABONADO", referencedColumnName = "AB_NIF")
    @ManyToOne(optional = false)
    private Abonado nifabonado;
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "CLAVE")
    @ManyToOne(optional = false)
    private Categoria categoria;
    @JoinColumn(name = "IDDENOMINACION", referencedColumnName = "DO_ID")
    @ManyToOne(optional = false)
    private DenominacionOrigen iddenominacion;

    public Preferencia() {
    }

    public Preferencia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Abonado getNifabonado() {
        return nifabonado;
    }

    public void setNifabonado(Abonado nifabonado) {
        this.nifabonado = nifabonado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public DenominacionOrigen getIddenominacion() {
        return iddenominacion;
    }

    public void setIddenominacion(DenominacionOrigen iddenominacion) {
        this.iddenominacion = iddenominacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preferencia)) {
            return false;
        }
        Preferencia other = (Preferencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Preferencia[ id=" + id + " ]";
    }
    
}
