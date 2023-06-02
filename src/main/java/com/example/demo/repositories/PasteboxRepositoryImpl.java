package com.example.demo.repositories;

import com.example.demo.model.Pastebox;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PasteboxRepositoryImpl implements PasteboxRepository {

    private final Map<Integer, Pastebox> vault = new HashMap<>();

    @Override
    public Pastebox save(Pastebox entity) {
        return vault.put(entity.getId(), entity);
    }

    @Override
    public Optional<Pastebox> findById(int id) {
        return Optional.ofNullable(vault.get(id));
    }

    @Override
    public Optional<Pastebox> findByHash(String hash) {
        return vault.values().stream()
                .filter(p -> p.getHash().equals(hash))
                .findFirst();
    }

    @Override
    public List<Pastebox> findAll() {
        return vault.values().stream().toList();
    }

    @Override
    public int count() {
        return vault.size();
    }
}
