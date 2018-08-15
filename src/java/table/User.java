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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId")
    , @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender")
    , @NamedQuery(name = "User.findByTanggalLahir", query = "SELECT u FROM User u WHERE u.tanggalLahir = :tanggalLahir")
    , @NamedQuery(name = "User.findByLevel", query = "SELECT u FROM User u WHERE u.level = :level")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @Lob
    @Size(max = 65535)
    @Column(name = "Nama")
    private String nama;
    @Lob
    @Size(max = 65535)
    @Column(name = "Alamat")
    private String alamat;
    @Column(name = "jenis_kelamin")
    private Integer gender;
    @Column(name = "Tanggal_Lahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    @Lob
    @Size(max = 65535)
    @Column(name = "NO_HP")
    private String hp;
    @Lob
    @Size(max = 65535)
    @Column(name = "NO_KARTU_BEROBAT")
    private String ktp;
    @Lob
    @Size(max = 65535)
    @Column(name = "username")
    private String username;
    @Lob
    @Size(max = 65535)
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private Integer level;

    public User() {
        this.level = 1;
        this.password = "";
        this.username = "" ;
        this.ktp = "";
        this.hp = "";
        this.tanggalLahir = new Date();
        this.gender = 1;
        this.alamat = "";
        this.nama = "";
    }

    public User(Integer userId) {
        this.level = 1;
        this.password = "";
        this.username = "";
        this.ktp = "";
        this.hp = "";
        this.tanggalLahir = new Date();
        this.gender = 1;
        this.alamat = "";
        this.nama = "";
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "table.User[ userId=" + userId + " ]";
    }
    
}
