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
@Table(name = "booking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b")
    , @NamedQuery(name = "Booking.findByIdBooking", query = "SELECT b FROM Booking b WHERE b.idBooking = :idBooking")
    , @NamedQuery(name = "Booking.findByTanggalBooking", query = "SELECT b FROM Booking b WHERE b.tanggalBooking = :tanggalBooking")
    , @NamedQuery(name = "Booking.findByWaktuBooking", query = "SELECT b FROM Booking b WHERE b.waktuBooking = :waktuBooking")
    , @NamedQuery(name = "Booking.findByIdUser", query = "SELECT b FROM Booking b WHERE b.idUser = :idUser")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_booking")
    private Integer idBooking;
    @Basic(optional = false)
    @Column(name = "tanggal_booking")
    @Temporal(TemporalType.DATE)
    private Date tanggalBooking;
    @Basic(optional = false)
    @Column(name = "waktu_booking")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuBooking;
    @Basic(optional = false)
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "id_user")
    private int idUser;
    @Basic(optional = false)
    @Column(name = "status")
    private int status = 0;
    @Basic(optional = false)
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "token")
    private String token;

    public Booking() {
    }

    public Booking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public Booking(Integer idBooking, Date tanggalBooking, Date waktuBooking, String nama, int idUser, String token) {
        this.idBooking = idBooking;
        this.tanggalBooking = tanggalBooking;
        this.waktuBooking = waktuBooking;
        this.nama = nama;
        this.idUser = idUser;
        this.token = token;
    }

    public Integer getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public Date getTanggalBooking() {
        return tanggalBooking;
    }

    public void setTanggalBooking(Date tanggalBooking) {
        this.tanggalBooking = tanggalBooking;
    }

    public Date getWaktuBooking() {
        return waktuBooking;
    }

    public void setWaktuBooking(Date waktuBooking) {
        this.waktuBooking = waktuBooking;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBooking != null ? idBooking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.idBooking == null && other.idBooking != null) || (this.idBooking != null && !this.idBooking.equals(other.idBooking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "table.Booking[ idBooking=" + idBooking + " ]";
    }
    
}
