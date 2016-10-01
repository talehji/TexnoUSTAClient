/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pallas
 */
@Entity
@Table(name = "mutexesiswork")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mutexesiswork.findAll", query = "SELECT m FROM Mutexesiswork m"),
    @NamedQuery(name = "Mutexesiswork.findByIdMutexesisWork", query = "SELECT m FROM Mutexesiswork m WHERE m.idMutexesisWork = :idMutexesisWork"),
    @NamedQuery(name = "Mutexesiswork.findByIdMutexesis", query = "SELECT m FROM Mutexesiswork m WHERE m.idMutexesisler = :idMutexesisler"),
    @NamedQuery(name = "Mutexesiswork.findByDate", query = "SELECT m FROM Mutexesiswork m WHERE m.date = :date"),
    @NamedQuery(name = "Mutexesiswork.findByStatus", query = "SELECT m FROM Mutexesiswork m WHERE m.status = :status")})
public class Mutexesiswork implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMutexesisWork")
    private Integer idMutexesisWork;
    @Basic(optional = false)
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "Status")
    private String status;
    @JoinColumn(name = "idMutexesisler", referencedColumnName = "idMutexesisler")
    @ManyToOne(optional = false)
    private Mutexesisler idMutexesisler;
    @JoinColumn(name = "idDaxilOlan", referencedColumnName = "idDaxilOlan")
    @ManyToOne(optional = false)
    private Daxilolan idDaxilOlan;

    public Mutexesiswork() {
    }

    public Mutexesiswork(Integer idMutexesisWork) {
        this.idMutexesisWork = idMutexesisWork;
    }

    public Mutexesiswork(Integer idMutexesisWork, Date date, String status) {
        this.idMutexesisWork = idMutexesisWork;
        this.date = date;
        this.status = status;
    }

    public Integer getIdMutexesisWork() {
        return idMutexesisWork;
    }

    public void setIdMutexesisWork(Integer idMutexesisWork) {
        this.idMutexesisWork = idMutexesisWork;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Mutexesisler getIdMutexesisler() {
        return idMutexesisler;
    }

    public void setIdMutexesisler(Mutexesisler idMutexesisler) {
        this.idMutexesisler = idMutexesisler;
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
        hash += (idMutexesisWork != null ? idMutexesisWork.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mutexesiswork)) {
            return false;
        }
        Mutexesiswork other = (Mutexesiswork) object;
        if ((this.idMutexesisWork == null && other.idMutexesisWork != null) || (this.idMutexesisWork != null && !this.idMutexesisWork.equals(other.idMutexesisWork))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Mutexesiswork[ idMutexesisWork=" + idMutexesisWork + " ]";
    }
    
}
