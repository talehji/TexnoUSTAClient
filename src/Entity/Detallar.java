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
@Table(name = "detallar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallar.findAll", query = "SELECT d FROM Detallar d"),
    @NamedQuery(name = "Detallar.findByIdDetallar", query = "SELECT d FROM Detallar d WHERE d.idDetallar = :idDetallar"),
    @NamedQuery(name = "Detallar.findByKataloq", query = "SELECT d FROM Detallar d WHERE d.kataloq = :kataloq"),
    @NamedQuery(name = "Detallar.findByKataloqNovu", query = "SELECT d FROM Detallar d WHERE d.kataloqNovu = :kataloqNovu"),
    @NamedQuery(name = "Detallar.findByModel", query = "SELECT d FROM Detallar d WHERE d.model = :model"),
    @NamedQuery(name = "Detallar.findByMarka", query = "SELECT d FROM Detallar d WHERE d.marka = :marka"),
    @NamedQuery(name = "Detallar.findByQeyd", query = "SELECT d FROM Detallar d WHERE d.qeyd = :qeyd"),
    @NamedQuery(name = "Detallar.findBySayi", query = "SELECT d FROM Detallar d WHERE d.sayi = :sayi"),
    @NamedQuery(name = "Detallar.findByQiymet", query = "SELECT d FROM Detallar d WHERE d.qiymet = :qiymet"),
    @NamedQuery(name = "Detallar.findByYeniKohne", query = "SELECT d FROM Detallar d WHERE d.yeniKohne = :yeniKohne"),
    @NamedQuery(name = "Detallar.findByIsActive", query = "SELECT d FROM Detallar d WHERE d.isActive = :isActive")})
public class Detallar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetallar")
    private Integer idDetallar;
    @Basic(optional = false)
    @Column(name = "Kataloq")
    private String kataloq;
    @Basic(optional = false)
    @Column(name = "KataloqNovu")
    private String kataloqNovu;
    @Basic(optional = false)
    @Column(name = "Model")
    private String model;
    @Basic(optional = false)
    @Column(name = "Marka")
    private String marka;
    @Basic(optional = false)
    @Column(name = "Qeyd")
    private String qeyd;
    @Basic(optional = false)
    @Column(name = "Sayi")
    private String sayi;
    @Basic(optional = false)
    @Column(name = "Qiymet")
    private String qiymet;
    @Basic(optional = false)
    @Column(name = "YeniKohne")
    private String yeniKohne;
    @Basic(optional = false)
    @Column(name = "isActive")
    private String isActive;

    public Detallar() {
    }

    public Detallar(Integer idDetallar) {
        this.idDetallar = idDetallar;
    }

    public Detallar(Integer idDetallar, String kataloq, String kataloqNovu, String model, String marka, String qeyd, String sayi, String qiymet, String yeniKohne, String isActive) {
        this.idDetallar = idDetallar;
        this.kataloq = kataloq;
        this.kataloqNovu = kataloqNovu;
        this.model = model;
        this.marka = marka;
        this.qeyd = qeyd;
        this.sayi = sayi;
        this.qiymet = qiymet;
        this.yeniKohne = yeniKohne;
        this.isActive = isActive;
    }

    public Integer getIdDetallar() {
        return idDetallar;
    }

    public void setIdDetallar(Integer idDetallar) {
        this.idDetallar = idDetallar;
    }

    public String getKataloq() {
        return kataloq;
    }

    public void setKataloq(String kataloq) {
        this.kataloq = kataloq;
    }

    public String getKataloqNovu() {
        return kataloqNovu;
    }

    public void setKataloqNovu(String kataloqNovu) {
        this.kataloqNovu = kataloqNovu;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getQeyd() {
        return qeyd;
    }

    public void setQeyd(String qeyd) {
        this.qeyd = qeyd;
    }

    public String getSayi() {
        return sayi;
    }

    public void setSayi(String sayi) {
        this.sayi = sayi;
    }

    public String getQiymet() {
        return qiymet;
    }

    public void setQiymet(String qiymet) {
        this.qiymet = qiymet;
    }

    public String getYeniKohne() {
        return yeniKohne;
    }

    public void setYeniKohne(String yeniKohne) {
        this.yeniKohne = yeniKohne;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetallar != null ? idDetallar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallar)) {
            return false;
        }
        Detallar other = (Detallar) object;
        if ((this.idDetallar == null && other.idDetallar != null) || (this.idDetallar != null && !this.idDetallar.equals(other.idDetallar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Detallar[ idDetallar=" + idDetallar + " ]";
    }
    
}
