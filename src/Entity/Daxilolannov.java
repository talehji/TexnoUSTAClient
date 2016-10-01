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
@Table(name = "daxilolannov")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Daxilolannov.findAll", query = "SELECT d FROM Daxilolannov d"),
    @NamedQuery(name = "Daxilolannov.findByIdDaxilOlanNov", query = "SELECT d FROM Daxilolannov d WHERE d.idDaxilOlanNov = :idDaxilOlanNov"),
    @NamedQuery(name = "Daxilolannov.findByAd", query = "SELECT d FROM Daxilolannov d WHERE d.ad = :ad")})
public class Daxilolannov implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDaxilOlanNov")
    private Integer idDaxilOlanNov;
    @Basic(optional = false)
    @Column(name = "Ad")
    private String ad;

    public Daxilolannov() {
    }

    public Daxilolannov(Integer idDaxilOlanNov) {
        this.idDaxilOlanNov = idDaxilOlanNov;
    }

    public Daxilolannov(Integer idDaxilOlanNov, String ad) {
        this.idDaxilOlanNov = idDaxilOlanNov;
        this.ad = ad;
    }

    public Integer getIdDaxilOlanNov() {
        return idDaxilOlanNov;
    }

    public void setIdDaxilOlanNov(Integer idDaxilOlanNov) {
        this.idDaxilOlanNov = idDaxilOlanNov;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDaxilOlanNov != null ? idDaxilOlanNov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Daxilolannov)) {
            return false;
        }
        Daxilolannov other = (Daxilolannov) object;
        if ((this.idDaxilOlanNov == null && other.idDaxilOlanNov != null) || (this.idDaxilOlanNov != null && !this.idDaxilOlanNov.equals(other.idDaxilOlanNov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Daxilolannov[ idDaxilOlanNov=" + idDaxilOlanNov + " ]";
    }
    
}
