package com.webkelompok.webapp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "tbl_pemesanan")
public class Pemesanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpemesanan;

    @ManyToOne
    @JoinColumn(name = "id")
    private Register user;

    private String namakos;

    private Date tanggalmulai;

    private Date tanggalselesai;

    public Integer getIdpemesanan() {
        return idpemesanan;
    }

    public void setIdpemesanan(Integer idpemesanan) {
        this.idpemesanan = idpemesanan;
    }

    public Register getUser() {
        return user;
    }

    public void setUser(Register user) {
        this.user = user;
    }

    public String getNamakos() {
        return namakos;
    }

    public void setNamakos(String namakos) {
        this.namakos = namakos;
    }

    public Date getTanggalmulai() {
        return tanggalmulai;
    }

    public void setTanggalmulai(Date tanggalmulai) {
        this.tanggalmulai = tanggalmulai;
    }

    public Date getTanggalselesai() {
        return tanggalselesai;
    }

    public void setTanggalselesai(Date tanggalselesai) {
        this.tanggalselesai = tanggalselesai;
    }

}
