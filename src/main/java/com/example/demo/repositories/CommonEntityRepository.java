package com.example.demo.repositories;

import com.example.demo.model.AbstractEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface CommonEntityRepository<E extends AbstractEntity> {

    E save(E entity);

    Optional<E> findById(int id);

    List<E> findAll();
}
