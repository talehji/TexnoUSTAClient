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
@Table(name = "kassa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kassa.findAll", query = "SELECT k FROM Kassa k"),
    @NamedQuery(name = "Kassa.findByIdKassa", query = "SELECT k FROM Kassa k WHERE k.idKassa = :idKassa"),
    @NamedQuery(name = "Kassa.findByMedaxil", query = "SELECT k FROM Kassa k WHERE k.medaxil = :medaxil"),
    @NamedQuery(name = "Kassa.findByMexaric", query = "SELECT k FROM Kassa k WHERE k.mexaric = :mexaric"),
    @NamedQuery(name = "Kassa.findByDate", query = "SELECT k FROM Kassa k WHERE k.date = :date"),
    @NamedQuery(name = "Kassa.findByIdDaxilOlan", query = "SELECT k FROM Kassa k WHERE k.idDaxilOlan = :idDaxilOlan"),
    @NamedQuery(name = "Kassa.findByTerefinden", query = "SELECT k FROM Kassa k WHERE k.terefinden = :terefinden"),
    @NamedQuery(name = "Kassa.findByAciqlama", query = "SELECT k FROM Kassa k WHERE k.aciqlama = :aciqlama"),
    @NamedQuery(name = "Kassa.findByQeyd", query = "SELECT k FROM Kassa k WHERE k.qeyd = :qeyd")})
public class Kassa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKassa")
    private Integer idKassa;
    @Basic(optional = false)
    @Column(name = "Medaxil")
    private double medaxil;
    @Basic(optional = false)
    @Column(name = "Mexaric")
    private double mexaric;
    @Basic(optional = false)
    @Column(name = "Date")
    private String date;
    @Basic(optional = false)
    @Column(name = "idDaxilOlan")
    private String idDaxilOlan;
    @Basic(optional = false)
    @Column(name = "Terefinden")
    private String terefinden;
    @Basic(optional = false)
    @Column(name = "Aciqlama")
    private String aciqlama;
    @Basic(optional = false)
    @Column(name = "Qeyd")
    private String qeyd;

    public Kassa() {
    }

    public Kassa(Integer idKassa) {
        this.idKassa = idKassa;
    }

    public Kassa(Integer idKassa, double medaxil, double mexaric, String date, String idDaxilOlan, String terefinden, String aciqlama, String qeyd) {
        this.idKassa = idKassa;
        this.medaxil = medaxil;
        this.mexaric = mexaric;
        this.date = date;
        this.idDaxilOlan = idDaxilOlan;
        this.terefinden = terefinden;
        this.aciqlama = aciqlama;
        this.qeyd = qeyd;
    }

    public Integer getIdKassa() {
        return idKassa;
    }

    public void setIdKassa(Integer idKassa) {
        this.idKassa = idKassa;
    }

    public double getMedaxil() {
        return medaxil;
    }

    public void setMedaxil(double medaxil) {
        this.medaxil = medaxil;
    }

    public double getMexaric() {
        return mexaric;
    }

    public void setMexaric(double mexaric) {
        this.mexaric = mexaric;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdDaxilOlan() {
        return idDaxilOlan;
    }

    public void setIdDaxilOlan(String idDaxilOlan) {
        this.idDaxilOlan = idDaxilOlan;
    }

    public String getTerefinden() {
        return terefinden;
    }

    public void setTerefinden(String terefinden) {
        this.terefinden = terefinden;
    }

    public String getAciqlama() {
        return aciqlama;
    }

    public void setAciqlama(String aciqlama) {
        this.aciqlama = aciqlama;
    }

    public String getQeyd() {
        return qeyd;
    }

    public void setQeyd(String qeyd) {
        this.qeyd = qeyd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKassa != null ? idKassa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kassa)) {
            return false;
        }
        Kassa other = (Kassa) object;
        if ((this.idKassa == null && other.idKassa != null) || (this.idKassa != null && !this.idKassa.equals(other.idKassa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kassa[ idKassa=" + idKassa + " ]";
    }
    
}
