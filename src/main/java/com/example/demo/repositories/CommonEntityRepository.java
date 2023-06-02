package com.example.demo.repositories;

import com.example.demo.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface CommonEntityRepository<E extends AbstractEntity> extends JpaRepository<E, Integer> {

    Optional<E> save(E entity);

    Optional<E> findById(int id);
}
