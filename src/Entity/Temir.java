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
@Table(name = "temir")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Temir.findAll", query = "SELECT t FROM Temir t"),
    @NamedQuery(name = "Temir.findByIdTemir", query = "SELECT t FROM Temir t WHERE t.idTemir = :idTemir"),
    @NamedQuery(name = "Temir.findByIdMutexesis", query = "SELECT t FROM Temir t WHERE t.idMutexesis = :idMutexesis"),
    @NamedQuery(name = "Temir.findByIdDaxilOlan", query = "SELECT t FROM Temir t WHERE t.idDaxilOlan = :idDaxilOlan")})
public class Temir implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTemir")
    private Integer idTemir;
    @Basic(optional = false)
    @Column(name = "idMutexesis")
    private String idMutexesis;
    @Basic(optional = false)
    @Column(name = "idDaxilOlan")
    private String idDaxilOlan;

    public Temir() {
    }

    public Temir(Integer idTemir) {
        this.idTemir = idTemir;
    }

    public Temir(Integer idTemir, String idMutexesis, String idDaxilOlan) {
        this.idTemir = idTemir;
        this.idMutexesis = idMutexesis;
        this.idDaxilOlan = idDaxilOlan;
    }

    public Integer getIdTemir() {
        return idTemir;
    }

    public void setIdTemir(Integer idTemir) {
        this.idTemir = idTemir;
    }

    public String getIdMutexesis() {
        return idMutexesis;
    }

    public void setIdMutexesis(String idMutexesis) {
        this.idMutexesis = idMutexesis;
    }

    public String getIdDaxilOlan() {
        return idDaxilOlan;
    }

    public void setIdDaxilOlan(String idDaxilOlan) {
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
