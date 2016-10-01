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
@Table(name = "tehvil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tehvil.findAll", query = "SELECT t FROM Tehvil t"),
    @NamedQuery(name = "Tehvil.findByIdTehvil", query = "SELECT t FROM Tehvil t WHERE t.idTehvil = :idTehvil"),
    @NamedQuery(name = "Tehvil.findByIdMutexesis", query = "SELECT t FROM Tehvil t WHERE t.idMutexesis = :idMutexesis"),
    @NamedQuery(name = "Tehvil.findByIdDaxilOlan", query = "SELECT t FROM Tehvil t WHERE t.idDaxilOlan = :idDaxilOlan")})
public class Tehvil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTehvil")
    private Integer idTehvil;
    @Basic(optional = false)
    @Column(name = "idMutexesis")
    private String idMutexesis;
    @Basic(optional = false)
    @Column(name = "idDaxilOlan")
    private String idDaxilOlan;

    public Tehvil() {
    }

    public Tehvil(Integer idTehvil) {
        this.idTehvil = idTehvil;
    }

    public Tehvil(Integer idTehvil, String idMutexesis, String idDaxilOlan) {
        this.idTehvil = idTehvil;
        this.idMutexesis = idMutexesis;
        this.idDaxilOlan = idDaxilOlan;
    }

    public Integer getIdTehvil() {
        return idTehvil;
    }

    public void setIdTehvil(Integer idTehvil) {
        this.idTehvil = idTehvil;
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
        hash += (idTehvil != null ? idTehvil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tehvil)) {
            return false;
        }
        Tehvil other = (Tehvil) object;
        if ((this.idTehvil == null && other.idTehvil != null) || (this.idTehvil != null && !this.idTehvil.equals(other.idTehvil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Tehvil[ idTehvil=" + idTehvil + " ]";
    }
    
}
