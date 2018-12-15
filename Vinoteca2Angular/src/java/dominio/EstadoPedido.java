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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mario Torbado
 */
@Entity
@Table(name = "ESTADO_PEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoPedido.findAll", query = "SELECT e FROM EstadoPedido e"),
    @NamedQuery(name = "EstadoPedido.findByEpClave", query = "SELECT e FROM EstadoPedido e WHERE e.epClave = :epClave"),
    @NamedQuery(name = "EstadoPedido.findByEstado", query = "SELECT e FROM EstadoPedido e WHERE e.estado = :estado")})
public class EstadoPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "EP_CLAVE")
    private String epClave;
    @Size(max = 20)
    @Column(name = "ESTADO")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peEstado")
    private Collection<Pedido> pedidoCollection;

    public EstadoPedido() {
    }

    public EstadoPedido(String epClave) {
        this.epClave = epClave;
    }

    public String getEpClave() {
        return epClave;
    }

    public void setEpClave(String epClave) {
        this.epClave = epClave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (epClave != null ? epClave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPedido)) {
            return false;
        }
        EstadoPedido other = (EstadoPedido) object;
        if ((this.epClave == null && other.epClave != null) || (this.epClave != null && !this.epClave.equals(other.epClave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.EstadoPedido[ epClave=" + epClave + " ]";
    }
    
}
