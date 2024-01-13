package com.webkelompok.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webkelompok.webapp.Model.*;

@Repository
public interface ownerregistdao extends JpaRepository<OwnerRegister, Integer> {
    OwnerRegister findByEmail(String email);
}
