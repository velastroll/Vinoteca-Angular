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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mario Torbado
 */
@Entity
@Table(name = "VINO_PARA_ANGULAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VinoParaAngular.findAll", query = "SELECT v FROM VinoParaAngular v"),
    @NamedQuery(name = "VinoParaAngular.findById", query = "SELECT v FROM VinoParaAngular v WHERE v.id = :id"),
    @NamedQuery(name = "VinoParaAngular.findByNombrecomercial", query = "SELECT v FROM VinoParaAngular v WHERE v.nombrecomercial = :nombrecomercial"),
    @NamedQuery(name = "VinoParaAngular.findByComentario", query = "SELECT v FROM VinoParaAngular v WHERE v.comentario = :comentario"),
    @NamedQuery(name = "VinoParaAngular.findByBodega", query = "SELECT v FROM VinoParaAngular v WHERE v.bodega = :bodega"),
    @NamedQuery(name = "VinoParaAngular.findByDenominacion", query = "SELECT v FROM VinoParaAngular v WHERE v.denominacion = :denominacion"),
    @NamedQuery(name = "VinoParaAngular.findByCategoria", query = "SELECT v FROM VinoParaAngular v WHERE v.categoria = :categoria")})
public class VinoParaAngular implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NOMBRECOMERCIAL")
    private String nombrecomercial;
    @Size(max = 200)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Size(max = 50)
    @Column(name = "BODEGA")
    private String bodega;
    @Size(max = 50)
    @Column(name = "DENOMINACION")
    private String denominacion;
    @Size(max = 50)
    @Column(name = "CATEGORIA")
    private String categoria;

    public VinoParaAngular() {
    }

    public VinoParaAngular(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrecomercial() {
        return nombrecomercial;
    }

    public void setNombrecomercial(String nombrecomercial) {
        this.nombrecomercial = nombrecomercial;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
        if (!(object instanceof VinoParaAngular)) {
            return false;
        }
        VinoParaAngular other = (VinoParaAngular) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.VinoParaAngular[ id=" + id + " ]";
    }
    
}
