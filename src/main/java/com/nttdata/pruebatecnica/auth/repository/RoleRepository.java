package com.nttdata.pruebatecnica.auth.repository;

import com.nttdata.pruebatecnica.auth.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel,Long> {
    Optional<RoleModel> findByNombre(String nombre);
}
