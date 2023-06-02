package com.example.demo.controllers;

import com.example.demo.model.AbstractEntity;
import com.example.demo.services.CommonEntityService;

public class AbstractController<E extends AbstractEntity, S extends CommonEntityService<E>>
        implements CommonEntityController<E>{

}
