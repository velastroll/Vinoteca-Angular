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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "VINO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vino.findAll", query = "SELECT v FROM Vino v"),
    @NamedQuery(name = "Vino.findById", query = "SELECT v FROM Vino v WHERE v.id = :id"),
    @NamedQuery(name = "Vino.findByNombrecomercial", query = "SELECT v FROM Vino v WHERE v.nombrecomercial = :nombrecomercial"),
    @NamedQuery(name = "Vino.findByComentario", query = "SELECT v FROM Vino v WHERE v.comentario = :comentario")})
public class Vino implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vinoid")
    private Collection<Referencia> referenciaCollection;
    @JoinColumn(name = "IDBODEGA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Bodega idbodega;
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "CLAVE")
    @ManyToOne(optional = false)
    private Categoria categoria;
    @JoinColumn(name = "IDDENOMINACION", referencedColumnName = "DO_ID")
    @ManyToOne(optional = false)
    private DenominacionOrigen iddenominacion;

    public Vino() {
    }

    public Vino(Integer id) {
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

    @XmlTransient
    public Collection<Referencia> getReferenciaCollection() {
        return referenciaCollection;
    }

    public void setReferenciaCollection(Collection<Referencia> referenciaCollection) {
        this.referenciaCollection = referenciaCollection;
    }

    public Bodega getIdbodega() {
        return idbodega;
    }

    public void setIdbodega(Bodega idbodega) {
        this.idbodega = idbodega;
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
        if (!(object instanceof Vino)) {
            return false;
        }
        Vino other = (Vino) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Vino[ id=" + id + " ]";
    }
    
}
