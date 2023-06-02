package com.example.demo.services;

import com.example.demo.model.AbstractEntity;
import com.example.demo.repositories.CommonEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractEntityService<E extends AbstractEntity, R extends CommonEntityRepository<E>>
        implements CommonEntityService<E> {

    protected R repository;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public AbstractEntityService(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public E getById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public int getCount() {
        return repository.count();
    }
}
