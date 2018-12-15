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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mario Torbado
 */
@Entity
@Table(name = "PEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByPeId", query = "SELECT p FROM Pedido p WHERE p.peId = :peId"),
    @NamedQuery(name = "Pedido.findByImporte", query = "SELECT p FROM Pedido p WHERE p.importe = :importe"),
    @NamedQuery(name = "Pedido.findByFecha", query = "SELECT p FROM Pedido p WHERE p.fecha = :fecha")})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PE_ID")
    private Integer peId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "IMPORTE")
    private Float importe;
    @Size(max = 20)
    @Column(name = "FECHA")
    private String fecha;
    @JoinColumn(name = "PE_NIF", referencedColumnName = "AB_NIF")
    @ManyToOne(optional = false)
    private Abonado peNif;
    @JoinColumn(name = "PE_ESTADO", referencedColumnName = "EP_CLAVE")
    @ManyToOne(optional = false)
    private EstadoPedido peEstado;
    @JoinColumn(name = "PE_REFERENCIA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Referencia peReferencia;

    public Pedido() {
    }

    public Pedido(Integer peId) {
        this.peId = peId;
    }

    public Integer getPeId() {
        return peId;
    }

    public void setPeId(Integer peId) {
        this.peId = peId;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Abonado getPeNif() {
        return peNif;
    }

    public void setPeNif(Abonado peNif) {
        this.peNif = peNif;
    }

    public EstadoPedido getPeEstado() {
        return peEstado;
    }

    public void setPeEstado(EstadoPedido peEstado) {
        this.peEstado = peEstado;
    }

    public Referencia getPeReferencia() {
        return peReferencia;
    }

    public void setPeReferencia(Referencia peReferencia) {
        this.peReferencia = peReferencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peId != null ? peId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.peId == null && other.peId != null) || (this.peId != null && !this.peId.equals(other.peId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Pedido[ peId=" + peId + " ]";
    }
    
}
