package com.nttdata.pruebatecnica.auth.service.impl;

import com.nttdata.pruebatecnica.auth.dto.RegisterRequestDTO;
import com.nttdata.pruebatecnica.auth.dto.UserDTO;
import com.nttdata.pruebatecnica.auth.mapper.UserMapper;
import com.nttdata.pruebatecnica.auth.model.RoleModel;
import com.nttdata.pruebatecnica.auth.model.UserModel;
import com.nttdata.pruebatecnica.auth.repository.RoleRepository;
import com.nttdata.pruebatecnica.auth.repository.UserRepository;
import com.nttdata.pruebatecnica.auth.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void initUsers() {

        // Crear roles si no existen
        RoleModel roleUser = roleRepository.findByNombre("USER")
                .orElseGet(() -> roleRepository.save(new RoleModel(null, "USER")));

        RoleModel roleAdmin = roleRepository.findByNombre("ADMIN")
                .orElseGet(() -> roleRepository.save(new RoleModel(null, "ADMIN")));

        // Crear usuario con rol USER
        if (userRepository.findByUsername("usuario").isEmpty()) {
            UserModel user = new UserModel();
            user.setUsername("usuario");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRol(roleUser);
            userRepository.save(user);
            System.out.println("Usuario 'usuario' creado con rol USER.");
        }

        // Crear usuario con rol ADMIN
        if (userRepository.findByUsername("admin").isEmpty()) {
            UserModel admin = new UserModel();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRol(roleAdmin);
            userRepository.save(admin);
            System.out.println("Usuario 'admin' creado con rol ADMIN.");
        }
    }



    @Transactional
    @Override
    public Optional<UserDTO> crearUsuario(RegisterRequestDTO request) {
        RoleModel role = roleRepository.findByNombre(request.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        UserModel usuario = new UserModel();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(role);

        UserModel guardado = userRepository.save(usuario);
        return Optional.ofNullable(UserMapper.toDTO(guardado));
    }
    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> listarUsuarios() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<UserDTO> obtenerUsuarioPorId(Long id) {
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return Optional.ofNullable(UserMapper.toDTO(userModel));
    }
    @Transactional
    @Override
    public void eliminarUsuario(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("No existe usuario con ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
