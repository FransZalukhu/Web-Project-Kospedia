package com.webkelompok.webapp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_kos")
public class RegisterKos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idkos;

    private String namakos;

    private String alamatkos;

    private Integer hargasewa;

    @Lob
    private byte[] gambar;

    @ManyToOne
    @JoinColumn(name = "idpemilik")
    private OwnerRegister owner;

    public Integer getIdkos() {
        return idkos;
    }

    public void setIdkos(Integer idkos) {
        this.idkos = idkos;
    }

    public String getNamakos() {
        return namakos;
    }

    public void setNamakos(String namakos) {
        this.namakos = namakos;
    }

    public String getAlamatkos() {
        return alamatkos;
    }

    public void setAlamatkos(String alamatkos) {
        this.alamatkos = alamatkos;
    }

    public Integer getHargasewa() {
        return hargasewa;
    }

    public void setHargasewa(Integer hargasewa) {
        this.hargasewa = hargasewa;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }

    public OwnerRegister getOwner() {
        return owner;
    }

    public void setOwner(OwnerRegister owner) {
        this.owner = owner;
    }

}