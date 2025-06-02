package com.nttdata.pruebatecnica.oficina.service;

import com.nttdata.pruebatecnica.oficina.dto.OficinaDTO;

import java.util.List;
import java.util.Optional;

public interface OficinaService {


    List<OficinaDTO> listarOficinas();
    OficinaDTO crearOficina(OficinaDTO oficinaDTO);
    Optional<OficinaDTO> obtenerOficinaPorId(Long id);

    OficinaDTO actualizarOficina(Long id, OficinaDTO oficinaDTO);

    void eliminarOficina(Long id);
}
