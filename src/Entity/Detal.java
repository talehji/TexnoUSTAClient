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
@Table(name = "detal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detal.findAll", query = "SELECT d FROM Detal d"),
    @NamedQuery(name = "Detal.findByIdDetal", query = "SELECT d FROM Detal d WHERE d.idDetal = :idDetal"),
    @NamedQuery(name = "Detal.findByKataloq", query = "SELECT d FROM Detal d WHERE d.kataloq = :kataloq"),
    @NamedQuery(name = "Detal.findByKataloqNovu", query = "SELECT d FROM Detal d WHERE d.kataloqNovu = :kataloqNovu"),
    @NamedQuery(name = "Detal.findByModel", query = "SELECT d FROM Detal d WHERE d.model = :model"),
    @NamedQuery(name = "Detal.findByMarka", query = "SELECT d FROM Detal d WHERE d.marka = :marka"),
    @NamedQuery(name = "Detal.findByQeyd", query = "SELECT d FROM Detal d WHERE d.qeyd = :qeyd"),
    @NamedQuery(name = "Detal.findBySayi", query = "SELECT d FROM Detal d WHERE d.sayi = :sayi"),
    @NamedQuery(name = "Detal.findByAlisQiymeti", query = "SELECT d FROM Detal d WHERE d.alisQiymeti = :alisQiymeti"),
    @NamedQuery(name = "Detal.findBySatisQiymeti", query = "SELECT d FROM Detal d WHERE d.satisQiymeti = :satisQiymeti"),
    @NamedQuery(name = "Detal.findByAlisYeri", query = "SELECT d FROM Detal d WHERE d.alisYeri = :alisYeri"),
    @NamedQuery(name = "Detal.findByYeniKohne", query = "SELECT d FROM Detal d WHERE d.yeniKohne = :yeniKohne"),
    @NamedQuery(name = "Detal.findByStatus", query = "SELECT d FROM Detal d WHERE d.status = :status"),
    @NamedQuery(name = "Detal.findByIsActive", query = "SELECT d FROM Detal d WHERE d.isActive = :isActive")})
public class Detal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetal")
    private Integer idDetal;
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
    @Column(name = "AlisQiymeti")
    private String alisQiymeti;
    @Basic(optional = false)
    @Column(name = "SatisQiymeti")
    private String satisQiymeti;
    @Basic(optional = false)
    @Column(name = "AlisYeri")
    private String alisYeri;
    @Basic(optional = false)
    @Column(name = "YeniKohne")
    private String yeniKohne;
    @Basic(optional = false)
    @Column(name = "Status")
    private String status;
    @Basic(optional = false)
    @Column(name = "isActive")
    private String isActive;

    public Detal() {
    }

    public Detal(Integer idDetal) {
        this.idDetal = idDetal;
    }

    public Detal(Integer idDetal, String kataloq, String kataloqNovu, String model, String marka, String qeyd, String sayi, String alisQiymeti, String satisQiymeti, String alisYeri, String yeniKohne, String status, String isActive) {
        this.idDetal = idDetal;
        this.kataloq = kataloq;
        this.kataloqNovu = kataloqNovu;
        this.model = model;
        this.marka = marka;
        this.qeyd = qeyd;
        this.sayi = sayi;
        this.alisQiymeti = alisQiymeti;
        this.satisQiymeti = satisQiymeti;
        this.alisYeri = alisYeri;
        this.yeniKohne = yeniKohne;
        this.status = status;
        this.isActive = isActive;
    }

    public Integer getIdDetal() {
        return idDetal;
    }

    public void setIdDetal(Integer idDetal) {
        this.idDetal = idDetal;
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

    public String getAlisQiymeti() {
        return alisQiymeti;
    }

    public void setAlisQiymeti(String alisQiymeti) {
        this.alisQiymeti = alisQiymeti;
    }

    public String getSatisQiymeti() {
        return satisQiymeti;
    }

    public void setSatisQiymeti(String satisQiymeti) {
        this.satisQiymeti = satisQiymeti;
    }

    public String getAlisYeri() {
        return alisYeri;
    }

    public void setAlisYeri(String alisYeri) {
        this.alisYeri = alisYeri;
    }

    public String getYeniKohne() {
        return yeniKohne;
    }

    public void setYeniKohne(String yeniKohne) {
        this.yeniKohne = yeniKohne;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        hash += (idDetal != null ? idDetal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detal)) {
            return false;
        }
        Detal other = (Detal) object;
        if ((this.idDetal == null && other.idDetal != null) || (this.idDetal != null && !this.idDetal.equals(other.idDetal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Detal[ idDetal=" + idDetal + " ]";
    }
    
}
