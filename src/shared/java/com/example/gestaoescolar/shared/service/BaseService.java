package com.example.gestaoescolar.shared.service;

import com.example.gestaoescolar.shared.dto.BaseDTO;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseDTO> {

    T create(T dto);

    Optional<T> findById(Long id);

    List<T> findAll();

    T update(Long id, T dto);

    void delete(Long id);
}
