package com.webkelompok.webapp.Model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_pemilik_kos")
public class OwnerRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpemilik;

    private String usernamepemilik;

    private String notelepon;

    private String email;

    private String alamatpemilik;

    private String password;

    @OneToMany(mappedBy = "owner")
    private List<RegisterKos> kosList;

    public String getUsernamepemilik() {
        return usernamepemilik;
    }

    public void setUsernamepemilik(String usernamepemilik) {
        this.usernamepemilik = usernamepemilik;
    }

    public String getNotelepon() {
        return notelepon;
    }

    public void setNotelepon(String notelepon) {
        this.notelepon = notelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamatpemilik() {
        return alamatpemilik;
    }

    public void setAlamatpemilik(String alamatpemilik) {
        this.alamatpemilik = alamatpemilik;
    }

    public Integer getIdPemilik() {
        return idpemilik;
    }

    public void setIdPemilik(Integer idpemilik) {
        this.idpemilik = idpemilik;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RegisterKos> getKosList() {
        return kosList;
    }

    public void setKosList(List<RegisterKos> kosList) {
        this.kosList = kosList;
    }

}