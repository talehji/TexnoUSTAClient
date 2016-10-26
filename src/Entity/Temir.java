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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pallas
 */
@Entity
@Table(name = "temir")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Temir.findAll", query = "SELECT t FROM Temir t")
    , @NamedQuery(name = "Temir.findByIdMutexesisler", query = "SELECT t FROM Temir t WHERE t.idMutexesis = :idMutexesis")
    , @NamedQuery(name = "Temir.findByIdTemir", query = "SELECT t FROM Temir t WHERE t.idTemir = :idTemir")
})
public class Temir implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTemir")
    private Integer idTemir;
    @JoinColumn(name = "idMutexesis", referencedColumnName = "idMutexesisler")
    @ManyToOne(optional = false)
    private Mutexesisler idMutexesis;
    @JoinColumn(name = "idDaxilOlan", referencedColumnName = "idDaxilOlan")
    @ManyToOne(optional = false)
    private Daxilolan idDaxilOlan;

    public Temir() {
    }

    public Temir(Integer idTemir) {
        this.idTemir = idTemir;
    }

    public Integer getIdTemir() {
        return idTemir;
    }

    public void setIdTemir(Integer idTemir) {
        this.idTemir = idTemir;
    }

    public Mutexesisler getIdMutexesis() {
        return idMutexesis;
    }

    public void setIdMutexesis(Mutexesisler idMutexesis) {
        this.idMutexesis = idMutexesis;
    }

    public Daxilolan getIdDaxilOlan() {
        return idDaxilOlan;
    }

    public void setIdDaxilOlan(Daxilolan idDaxilOlan) {
        this.idDaxilOlan = idDaxilOlan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTemir != null ? idTemir.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Temir)) {
            return false;
        }
        Temir other = (Temir) object;
        if ((this.idTemir == null && other.idTemir != null) || (this.idTemir != null && !this.idTemir.equals(other.idTemir))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Temir[ idTemir=" + idTemir + " ]";
    }
    
}
