package com.example.demo.controllers;

import com.example.demo.dto.PasteboxUrl;
import com.example.demo.model.Pastebox;
import com.example.demo.model.PasteboxRestrictions;
import com.example.demo.services.PasteboxService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/")
public class PasteboxController extends AbstractController<Pastebox, PasteboxService> {

    public PasteboxController(PasteboxService service) {
        super(service);
    }

    @GetMapping("{hash}")
    public Pastebox getByHash(@PathVariable int hash) {
        return service.getByHash(hash);
    }

    @PostMapping
    public PasteboxUrl add(@RequestParam LocalDateTime expirationTime,
                           @RequestParam PasteboxRestrictions restriction) {

        return service.add(expirationTime, restriction);
    }

    @GetMapping
    public List<Pastebox> get() {
        return service.getLastLimitedAndPublic(10);
    }
}
