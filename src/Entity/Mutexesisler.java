/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pallas
 */
@Entity
@Table(name = "mutexesisler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mutexesisler.findAll", query = "SELECT m FROM Mutexesisler m"),
    @NamedQuery(name = "Mutexesisler.findByIdMutexesisler", query = "SELECT m FROM Mutexesisler m WHERE m.idMutexesisler = :idMutexesisler"),
    @NamedQuery(name = "Mutexesisler.findByAd", query = "SELECT m FROM Mutexesisler m WHERE m.ad = :ad"),
    @NamedQuery(name = "Mutexesisler.findBySoyad", query = "SELECT m FROM Mutexesisler m WHERE m.soyad = :soyad"),
    @NamedQuery(name = "Mutexesisler.findByTelefon", query = "SELECT m FROM Mutexesisler m WHERE m.telefon = :telefon"),
    @NamedQuery(name = "Mutexesisler.findByQeyd", query = "SELECT m FROM Mutexesisler m WHERE m.qeyd = :qeyd"),
    @NamedQuery(name = "Mutexesisler.findByIsActive", query = "SELECT m FROM Mutexesisler m WHERE m.isActive = :isActive")})
public class Mutexesisler implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMutexesisler")
    private Integer idMutexesisler;
    @Basic(optional = false)
    @Column(name = "Ad")
    private String ad;
    @Basic(optional = false)
    @Column(name = "Soyad")
    private String soyad;
    @Basic(optional = false)
    @Column(name = "Telefon")
    private String telefon;
    @Basic(optional = false)
    @Column(name = "Qeyd")
    private String qeyd;
    @Basic(optional = false)
    @Column(name = "isActive")
    private String isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMutexesisler")
    private Collection<Mutexesiswork> mutexesisworkCollection;

    public Mutexesisler() {
    }

    public Mutexesisler(Integer idMutexesisler) {
        this.idMutexesisler = idMutexesisler;
    }

    public Mutexesisler(Integer idMutexesisler, String ad, String soyad, String telefon, String qeyd, String isActive) {
        this.idMutexesisler = idMutexesisler;
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
        this.qeyd = qeyd;
        this.isActive = isActive;
    }

    public Integer getIdMutexesisler() {
        return idMutexesisler;
    }

    public void setIdMutexesisler(Integer idMutexesisler) {
        Integer oldIdMutexesisler = this.idMutexesisler;
        this.idMutexesisler = idMutexesisler;
        changeSupport.firePropertyChange("idMutexesisler", oldIdMutexesisler, idMutexesisler);
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        String oldAd = this.ad;
        this.ad = ad;
        changeSupport.firePropertyChange("ad", oldAd, ad);
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        String oldSoyad = this.soyad;
        this.soyad = soyad;
        changeSupport.firePropertyChange("soyad", oldSoyad, soyad);
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        String oldTelefon = this.telefon;
        this.telefon = telefon;
        changeSupport.firePropertyChange("telefon", oldTelefon, telefon);
    }

    public String getQeyd() {
        return qeyd;
    }

    public void setQeyd(String qeyd) {
        String oldQeyd = this.qeyd;
        this.qeyd = qeyd;
        changeSupport.firePropertyChange("qeyd", oldQeyd, qeyd);
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        String oldIsActive = this.isActive;
        this.isActive = isActive;
        changeSupport.firePropertyChange("isActive", oldIsActive, isActive);
    }

    @XmlTransient
    public Collection<Mutexesiswork> getMutexesisworkCollection() {
        return mutexesisworkCollection;
    }

    public void setMutexesisworkCollection(Collection<Mutexesiswork> mutexesisworkCollection) {
        this.mutexesisworkCollection = mutexesisworkCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMutexesisler != null ? idMutexesisler.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mutexesisler)) {
            return false;
        }
        Mutexesisler other = (Mutexesisler) object;
        if ((this.idMutexesisler == null && other.idMutexesisler != null) || (this.idMutexesisler != null && !this.idMutexesisler.equals(other.idMutexesisler))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ad+" "+soyad;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
