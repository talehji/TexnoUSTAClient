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
@Table(name = "kataloqnovu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kataloqnovu.findAll", query = "SELECT k FROM Kataloqnovu k"),
    @NamedQuery(name = "Kataloqnovu.findByIdKataloqNovu", query = "SELECT k FROM Kataloqnovu k WHERE k.idKataloqNovu = :idKataloqNovu"),
    @NamedQuery(name = "Kataloqnovu.findByName", query = "SELECT k FROM Kataloqnovu k WHERE k.name = :name"),
    @NamedQuery(name = "Kataloqnovu.findByKataloqName", query = "SELECT k FROM Kataloqnovu k WHERE k.kataloqName = :kataloqName")})
public class Kataloqnovu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKataloqNovu")
    private Integer idKataloqNovu;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "KataloqName")
    private String kataloqName;

    public Kataloqnovu() {
    }

    public Kataloqnovu(Integer idKataloqNovu) {
        this.idKataloqNovu = idKataloqNovu;
    }

    public Kataloqnovu(Integer idKataloqNovu, String name, String kataloqName) {
        this.idKataloqNovu = idKataloqNovu;
        this.name = name;
        this.kataloqName = kataloqName;
    }

    public Integer getIdKataloqNovu() {
        return idKataloqNovu;
    }

    public void setIdKataloqNovu(Integer idKataloqNovu) {
        this.idKataloqNovu = idKataloqNovu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKataloqName() {
        return kataloqName;
    }

    public void setKataloqName(String kataloqName) {
        this.kataloqName = kataloqName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKataloqNovu != null ? idKataloqNovu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kataloqnovu)) {
            return false;
        }
        Kataloqnovu other = (Kataloqnovu) object;
        if ((this.idKataloqNovu == null && other.idKataloqNovu != null) || (this.idKataloqNovu != null && !this.idKataloqNovu.equals(other.idKataloqNovu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kataloqnovu[ idKataloqNovu=" + idKataloqNovu + " ]";
    }
    
}
