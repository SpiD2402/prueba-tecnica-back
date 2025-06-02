package com.nttdata.pruebatecnica.auth.service;

import com.nttdata.pruebatecnica.auth.dto.AuthRequestDTO;
import com.nttdata.pruebatecnica.auth.dto.AuthResponseDTO;
import com.nttdata.pruebatecnica.auth.dto.RegisterRequestDTO;
import com.nttdata.pruebatecnica.auth.dto.RegisterResponseDTO;


public interface AuthService {

    AuthResponseDTO login(AuthRequestDTO authRequestDTO);
    RegisterResponseDTO register (RegisterRequestDTO registerRequestDTO);


}
