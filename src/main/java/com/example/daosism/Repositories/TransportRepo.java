package com.example.daosism.Repositories;

import com.example.daosism.Models.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepo extends JpaRepository<Transport, Integer> {
    Transport findTransportByNumber(String number);
}
