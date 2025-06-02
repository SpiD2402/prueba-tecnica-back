package com.nttdata.pruebatecnica.auth.service.impl;

import com.nttdata.pruebatecnica.auth.dto.AuthRequestDTO;
import com.nttdata.pruebatecnica.auth.dto.AuthResponseDTO;
import com.nttdata.pruebatecnica.auth.dto.RegisterRequestDTO;
import com.nttdata.pruebatecnica.auth.dto.RegisterResponseDTO;
import com.nttdata.pruebatecnica.auth.model.RoleModel;
import com.nttdata.pruebatecnica.auth.model.UserModel;
import com.nttdata.pruebatecnica.auth.repository.RoleRepository;
import com.nttdata.pruebatecnica.auth.repository.UserRepository;
import com.nttdata.pruebatecnica.auth.security.JwtService;
import com.nttdata.pruebatecnica.auth.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional
    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        UserDetails userDetails;

        try {
            userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("El usuario no existe.");
        }

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDTO.getUsername(),
                            authRequestDTO.getPassword()
                    )
            );
            String token = jwtService.generateToken(userDetails);
            UserModel userModel = userRepository.findByUsername(authRequestDTO.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            return new AuthResponseDTO(token, userModel.getUsername(), userModel.getRol().getNombre());

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciales incorrectas. Por favor, verifica tu contraseña.");
        } catch (DisabledException e) {
            throw new DisabledException("La cuenta está deshabilitada. Por favor, contacta al administrador.");
        }
    }
    @Transactional
    @Override
    public RegisterResponseDTO register(RegisterRequestDTO request) {
        RoleModel role = roleRepository.findByNombre(request.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        UserModel usuario = new UserModel();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(role);

        userRepository.save(usuario);
        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setUsername(usuario.getUsername());
        response.setRol(role.getNombre());
        return response;
    }



}
