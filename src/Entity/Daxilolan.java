/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pallas
 */
@Entity
@Table(name = "daxilolan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Daxilolan.findAll", query = "SELECT d FROM Daxilolan d"),
    @NamedQuery(name = "Daxilolan.findByIdDaxilOlan", query = "SELECT d FROM Daxilolan d WHERE d.idDaxilOlan = :idDaxilOlan"),
    @NamedQuery(name = "Daxilolan.findByAd", query = "SELECT d FROM Daxilolan d WHERE d.ad = :ad"),
    @NamedQuery(name = "Daxilolan.findBySoyad", query = "SELECT d FROM Daxilolan d WHERE d.soyad = :soyad"),
    @NamedQuery(name = "Daxilolan.findByTelefon", query = "SELECT d FROM Daxilolan d WHERE d.telefon = :telefon"),
    @NamedQuery(name = "Daxilolan.findByIdDaxilOlanNov", query = "SELECT d FROM Daxilolan d WHERE d.idDaxilOlanNov = :idDaxilOlanNov"),
    @NamedQuery(name = "Daxilolan.findByModel", query = "SELECT d FROM Daxilolan d WHERE d.model = :model"),
    @NamedQuery(name = "Daxilolan.findByMarka", query = "SELECT d FROM Daxilolan d WHERE d.marka = :marka"),
    @NamedQuery(name = "Daxilolan.findByAksesuar", query = "SELECT d FROM Daxilolan d WHERE d.aksesuar = :aksesuar"),
    @NamedQuery(name = "Daxilolan.findByProblem", query = "SELECT d FROM Daxilolan d WHERE d.problem = :problem"),
    @NamedQuery(name = "Daxilolan.findByNetice", query = "SELECT d FROM Daxilolan d WHERE d.netice = :netice"),
    @NamedQuery(name = "Daxilolan.findByQeyd", query = "SELECT d FROM Daxilolan d WHERE d.qeyd = :qeyd"),
    @NamedQuery(name = "Daxilolan.findByDate", query = "SELECT d FROM Daxilolan d WHERE d.date = :date"),
    @NamedQuery(name = "Daxilolan.findByIsActive", query = "SELECT d FROM Daxilolan d WHERE d.isActive = :isActive"),
    @NamedQuery(name = "Daxilolan.findByDatePlan", query = "SELECT d FROM Daxilolan d WHERE d.datePlan = :datePlan"),
    @NamedQuery(name = "Daxilolan.findByDateTemir", query = "SELECT d FROM Daxilolan d WHERE d.dateTemir = :dateTemir"),
    @NamedQuery(name = "Daxilolan.findByDateTehvil", query = "SELECT d FROM Daxilolan d WHERE d.dateTehvil = :dateTehvil"),
    @NamedQuery(name = "Daxilolan.findByGy", query = "SELECT d FROM Daxilolan d WHERE d.gy = :gy")})
public class Daxilolan implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDaxilOlan")
    private Collection<Tehvil> tehvilCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDaxilOlan")
    private Collection<Temir> temirCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDaxilOlan")
    private Integer idDaxilOlan;
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
    @Column(name = "idDaxilOlanNov")
    private String idDaxilOlanNov;
    @Basic(optional = false)
    @Column(name = "Model")
    private String model;
    @Basic(optional = false)
    @Column(name = "Marka")
    private String marka;
    @Basic(optional = false)
    @Column(name = "Aksesuar")
    private String aksesuar;
    @Basic(optional = false)
    @Column(name = "Problem")
    private String problem;
    @Basic(optional = false)
    @Column(name = "Netice")
    private String netice;
    @Basic(optional = false)
    @Column(name = "Qeyd")
    private String qeyd;
    @Basic(optional = false)
    @Column(name = "Date")
    private String date;
    @Basic(optional = false)
    @Column(name = "isActive")
    private String isActive;
    @Basic(optional = false)
    @Column(name = "DatePlan")
    private String datePlan;
    @Basic(optional = false)
    @Column(name = "DateTemir")
    private String dateTemir;
    @Basic(optional = false)
    @Column(name = "DateTehvil")
    private String dateTehvil;
    @Basic(optional = false)
    @Column(name = "GY")
    private String gy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDaxilOlan")
    private Collection<Mutexesiswork> mutexesisworkCollection;

    public Daxilolan() {
    }

    public Daxilolan(Integer idDaxilOlan) {
        this.idDaxilOlan = idDaxilOlan;
    }

    public Daxilolan(Integer idDaxilOlan, String ad, String soyad, String telefon, String idDaxilOlanNov, String model, String marka, String aksesuar, String problem, String netice, String qeyd, String date, String isActive, String datePlan, String dateTemir, String dateTehvil, String gy) {
        this.idDaxilOlan = idDaxilOlan;
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
        this.idDaxilOlanNov = idDaxilOlanNov;
        this.model = model;
        this.marka = marka;
        this.aksesuar = aksesuar;
        this.problem = problem;
        this.netice = netice;
        this.qeyd = qeyd;
        this.date = date;
        this.isActive = isActive;
        this.datePlan = datePlan;
        this.dateTemir = dateTemir;
        this.dateTehvil = dateTehvil;
        this.gy = gy;
    }

    public Integer getIdDaxilOlan() {
        return idDaxilOlan;
    }

    public void setIdDaxilOlan(Integer idDaxilOlan) {
        this.idDaxilOlan = idDaxilOlan;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getIdDaxilOlanNov() {
        return idDaxilOlanNov;
    }

    public void setIdDaxilOlanNov(String idDaxilOlanNov) {
        this.idDaxilOlanNov = idDaxilOlanNov;
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

    public String getAksesuar() {
        return aksesuar;
    }

    public void setAksesuar(String aksesuar) {
        this.aksesuar = aksesuar;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getNetice() {
        return netice;
    }

    public void setNetice(String netice) {
        this.netice = netice;
    }

    public String getQeyd() {
        return qeyd;
    }

    public void setQeyd(String qeyd) {
        this.qeyd = qeyd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getDatePlan() {
        return datePlan;
    }

    public void setDatePlan(String datePlan) {
        this.datePlan = datePlan;
    }

    public String getDateTemir() {
        return dateTemir;
    }

    public void setDateTemir(String dateTemir) {
        this.dateTemir = dateTemir;
    }

    public String getDateTehvil() {
        return dateTehvil;
    }

    public void setDateTehvil(String dateTehvil) {
        this.dateTehvil = dateTehvil;
    }

    public String getGy() {
        return gy;
    }

    public void setGy(String gy) {
        this.gy = gy;
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
        hash += (idDaxilOlan != null ? idDaxilOlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Daxilolan)) {
            return false;
        }
        Daxilolan other = (Daxilolan) object;
        if ((this.idDaxilOlan == null && other.idDaxilOlan != null) || (this.idDaxilOlan != null && !this.idDaxilOlan.equals(other.idDaxilOlan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Daxilolan[ idDaxilOlan=" + idDaxilOlan + " ]";
    }

    @XmlTransient
    public Collection<Tehvil> getTehvilCollection() {
        return tehvilCollection;
    }

    public void setTehvilCollection(Collection<Tehvil> tehvilCollection) {
        this.tehvilCollection = tehvilCollection;
    }

    @XmlTransient
    public Collection<Temir> getTemirCollection() {
        return temirCollection;
    }

    public void setTemirCollection(Collection<Temir> temirCollection) {
        this.temirCollection = temirCollection;
    }
    
}
