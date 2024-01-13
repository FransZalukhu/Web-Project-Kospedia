package com.webkelompok.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webkelompok.webapp.Model.RegisterKos;

@Repository
public interface RegistKosdao extends JpaRepository<RegisterKos, Integer> {
    RegisterKos findByNamakos(String namakos);

    RegisterKos findByIdkos(Integer idkos);
}
