/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pallas
 */
@Entity
@Table(name = "kataloq")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kataloq.findAll", query = "SELECT k FROM Kataloq k"),
    @NamedQuery(name = "Kataloq.findByIdKataloq", query = "SELECT k FROM Kataloq k WHERE k.idKataloq = :idKataloq"),
    @NamedQuery(name = "Kataloq.findByName", query = "SELECT k FROM Kataloq k WHERE k.name = :name")})
public class Kataloq implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKataloq")
    private Integer idKataloq;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;

    public Kataloq() {
    }

    public Kataloq(Integer idKataloq) {
        this.idKataloq = idKataloq;
    }

    public Kataloq(Integer idKataloq, String name) {
        this.idKataloq = idKataloq;
        this.name = name;
    }

    public Integer getIdKataloq() {
        return idKataloq;
    }

    public void setIdKataloq(Integer idKataloq) {
        this.idKataloq = idKataloq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKataloq != null ? idKataloq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kataloq)) {
            return false;
        }
        Kataloq other = (Kataloq) object;
        if ((this.idKataloq == null && other.idKataloq != null) || (this.idKataloq != null && !this.idKataloq.equals(other.idKataloq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kataloq[ idKataloq=" + idKataloq + " ]";
    }
    
}
