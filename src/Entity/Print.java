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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pallas
 */
@Entity
@Table(name = "print")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Print.findAll", query = "SELECT p FROM Print p"),
    @NamedQuery(name = "Print.findByIdPrint", query = "SELECT p FROM Print p WHERE p.idPrint = :idPrint")})
public class Print implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPrint")
    private Integer idPrint;
    @Basic(optional = false)
    @Lob
    @Column(name = "Data")
    private String data;

    public Print() {
    }

    public Print(Integer idPrint) {
        this.idPrint = idPrint;
    }

    public Print(Integer idPrint, String data) {
        this.idPrint = idPrint;
        this.data = data;
    }

    public Integer getIdPrint() {
        return idPrint;
    }

    public void setIdPrint(Integer idPrint) {
        this.idPrint = idPrint;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrint != null ? idPrint.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Print)) {
            return false;
        }
        Print other = (Print) object;
        if ((this.idPrint == null && other.idPrint != null) || (this.idPrint != null && !this.idPrint.equals(other.idPrint))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Print[ idPrint=" + idPrint + " ]";
    }
    
}
