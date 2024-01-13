package com.webkelompok.webapp.dao;

import com.webkelompok.webapp.Model.Pemesanan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PemesananDao extends JpaRepository<Pemesanan, Integer> {

}
