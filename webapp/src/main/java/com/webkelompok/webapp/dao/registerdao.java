package com.webkelompok.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webkelompok.webapp.Model.*;

@Repository
public interface registerdao extends JpaRepository<Register, Integer> {
    Register findByEmail(String email);

    Register findByUsername(String username);
}
