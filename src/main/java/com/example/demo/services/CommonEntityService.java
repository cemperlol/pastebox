package com.example.demo.services;

import com.example.demo.model.AbstractEntity;

public interface CommonEntityService<E extends AbstractEntity> {

    E save(E entity);

    E getById(int id);
}
