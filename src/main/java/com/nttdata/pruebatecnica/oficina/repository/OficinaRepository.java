package com.nttdata.pruebatecnica.oficina.repository;

import com.nttdata.pruebatecnica.oficina.model.OficinaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficinaRepository extends JpaRepository<OficinaModel,Long> {
}
