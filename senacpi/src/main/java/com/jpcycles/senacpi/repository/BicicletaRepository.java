package com.jpcycles.senacpi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jpcycles.senacpi.model.Bicicleta;

@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta, Integer> {
    
}
