package com.example.demo.services;

import com.example.demo.model.AbstractEntity;
import com.example.demo.repositories.CommonEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractEntityService<E extends AbstractEntity, R extends CommonEntityRepository<E>>
        implements CommonEntityService<E> {

    private R repository;

    @Autowired
    public AbstractEntityService(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        return repository.save(entity).orElse(null);
    }

    @Override
    public E getById(int id) {
        return repository.findById(id).orElse(null);
    }
}
