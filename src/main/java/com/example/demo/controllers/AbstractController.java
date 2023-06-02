package com.example.demo.controllers;

import com.example.demo.model.AbstractEntity;
import com.example.demo.services.CommonEntityService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractController<E extends AbstractEntity, S extends CommonEntityService<E>> {

    protected S service;

    @Autowired
    public AbstractController(S service) {
        this.service = service;
    }
}
