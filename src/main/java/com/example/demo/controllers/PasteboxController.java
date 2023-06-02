package com.example.demo.controllers;

import com.example.demo.dto.PasteboxRequest;
import com.example.demo.dto.PasteboxResponse;
import com.example.demo.dto.PasteboxUrl;
import com.example.demo.model.Pastebox;
import com.example.demo.services.PasteboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PasteboxController extends AbstractController<Pastebox, PasteboxService> {

    @Autowired
    public PasteboxController(PasteboxService service) {
        super(service);
    }

    @GetMapping("{hash}")
    public PasteboxResponse getByHash(@PathVariable String hash) {
        return service.getByHash(hash);
    }

    @PostMapping
    public PasteboxUrl add(@RequestBody PasteboxRequest request) {
        return service.add(request);
    }

    @GetMapping
    public List<PasteboxResponse> get() {
        return service.getLastLimitedAndPublic();
    }
}
