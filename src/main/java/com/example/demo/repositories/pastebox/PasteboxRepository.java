package com.example.demo.repositories.pastebox;

import com.example.demo.model.pastebox.Pastebox;
import com.example.demo.repositories.CommonEntityRepository;

import java.util.Optional;

public interface PasteboxRepository extends CommonEntityRepository<Pastebox> {
    Optional<Pastebox> findByHash(String hash);
}
