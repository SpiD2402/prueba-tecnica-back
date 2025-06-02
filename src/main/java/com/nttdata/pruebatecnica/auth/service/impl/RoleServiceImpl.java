package com.nttdata.pruebatecnica.auth.service.impl;

import com.nttdata.pruebatecnica.auth.dto.RoleDTO;
import com.nttdata.pruebatecnica.auth.mapper.RoleMapper;
import com.nttdata.pruebatecnica.auth.model.RoleModel;
import com.nttdata.pruebatecnica.auth.repository.RoleRepository;
import com.nttdata.pruebatecnica.auth.service.RoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initRoles() {
        if (roleRepository.count() == 0) {
            RoleModel roleUser = new RoleModel();
            roleUser.setNombre("USER");
            roleRepository.save(roleUser);
            RoleModel roleAdmin = new RoleModel();
            roleAdmin.setNombre("ADMIN");
            roleRepository.save(roleAdmin);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoleDTO> obtenerTodasLosRoles() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<RoleDTO> obtenerRolePorId(Long id) {
        RoleModel role = roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado con id: " + id));
        return Optional.of(RoleMapper.toDTO(role));
    }
    @Transactional
    @Override
    public Optional<RoleDTO> crearRole(RoleDTO roleDTO) {
        RoleModel role = RoleMapper.toEntity(roleDTO);
        RoleModel roleSaved = roleRepository.save(role);
        return Optional.of(RoleMapper.toDTO(roleSaved));
    }
    @Transactional
    @Override
    public Optional<RoleDTO> actualizarRole(Long id, RoleDTO roleDTO) {
        RoleModel existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado con id: " + id));
        existingRole.setNombre(roleDTO.getNombre());
        return Optional.of(RoleMapper.toDTO(existingRole));
    }

    @Transactional
    @Override
    public void eliminarRole(Long id) {
        RoleModel existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rol no encontrado con id: " + id));
        roleRepository.delete(existingRole);
    }
}
