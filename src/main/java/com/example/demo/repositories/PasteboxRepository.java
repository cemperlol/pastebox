package com.example.demo.repositories;

import com.example.demo.model.Pastebox;

import java.util.Optional;

public interface PasteboxRepository extends CommonEntityRepository<Pastebox> {
    Optional<Pastebox> findByHash(String hash);
}
