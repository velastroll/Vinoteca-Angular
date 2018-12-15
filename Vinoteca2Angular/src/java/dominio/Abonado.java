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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ABONADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Abonado.findAll", query = "SELECT a FROM Abonado a"),
    @NamedQuery(name = "Abonado.findByAbLogin", query = "SELECT a FROM Abonado a WHERE a.abLogin = :abLogin"),
    @NamedQuery(name = "Abonado.findByAbPasswd", query = "SELECT a FROM Abonado a WHERE a.abPasswd = :abPasswd")})
public class Abonado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "AB_LOGIN")
    private String abLogin;
    @Size(max = 8)
    @Column(name = "AB_PASSWD")
    private String abPasswd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peNif")
    private Collection<Pedido> pedidoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nifabonado")
    private Collection<Preferencia> preferenciaCollection;
    @JoinColumn(name = "AB_NIF", referencedColumnName = "NIF")
    @ManyToOne
    private Persona abNif;

    public Abonado() {
    }

    public Abonado(String abLogin) {
        this.abLogin = abLogin;
    }

    public String getAbLogin() {
        return abLogin;
    }

    public void setAbLogin(String abLogin) {
        this.abLogin = abLogin;
    }

    public String getAbPasswd() {
        return abPasswd;
    }

    public void setAbPasswd(String abPasswd) {
        this.abPasswd = abPasswd;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @XmlTransient
    public Collection<Preferencia> getPreferenciaCollection() {
        return preferenciaCollection;
    }

    public void setPreferenciaCollection(Collection<Preferencia> preferenciaCollection) {
        this.preferenciaCollection = preferenciaCollection;
    }

    public Persona getAbNif() {
        return abNif;
    }

    public void setAbNif(Persona abNif) {
        this.abNif = abNif;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (abLogin != null ? abLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Abonado)) {
            return false;
        }
        Abonado other = (Abonado) object;
        if ((this.abLogin == null && other.abLogin != null) || (this.abLogin != null && !this.abLogin.equals(other.abLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Abonado[ abLogin=" + abLogin + " ]";
    }
    
}
