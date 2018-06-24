/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "rubrik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rubrik.findAll", query = "SELECT r FROM Rubrik r")
    , @NamedQuery(name = "Rubrik.findByRubrikId", query = "SELECT r FROM Rubrik r WHERE r.rubrikId = :rubrikId")
    , @NamedQuery(name = "Rubrik.findByTanggal", query = "SELECT r FROM Rubrik r WHERE r.tanggal = :tanggal")})
public class Rubrik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RUBRIK_ID")
    private Integer rubrikId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "INFO")
    private String info;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;

    public Rubrik() {
        tanggal = new Date();
    }

    public Rubrik(Integer rubrikId) {
        tanggal = new Date();
        this.rubrikId = rubrikId;
    }

    public Rubrik(Integer rubrikId, String info, Date tanggal) {
        this.rubrikId = rubrikId;
        this.info = info;
        tanggal = new Date();
    }

    public Integer getRubrikId() {
        return rubrikId;
    }

    public void setRubrikId(Integer rubrikId) {
        this.rubrikId = rubrikId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rubrikId != null ? rubrikId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubrik)) {
            return false;
        }
        Rubrik other = (Rubrik) object;
        if ((this.rubrikId == null && other.rubrikId != null) || (this.rubrikId != null && !this.rubrikId.equals(other.rubrikId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String[] split = info.split("::");
        String temp = "Judul :" + split[0];
        try {
            temp+= "\nGejala :";
            temp+= split[1];
        } catch (Exception e) {
        }
        try {
            temp+= "\nPenaganan :";
            temp+= split[2];
        } catch (Exception e) {
        }
//        return info.replace("||", "\n");
        return temp;
    }
    
}
