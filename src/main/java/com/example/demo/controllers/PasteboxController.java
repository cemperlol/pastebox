package com.example.demo.controllers;

import com.example.demo.dto.PasteboxRequest;
import com.example.demo.dto.PasteboxResponse;
import com.example.demo.dto.PasteboxUrl;
import com.example.demo.model.pastebox.Pastebox;
import com.example.demo.services.pastebox.PasteboxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Api("pastebox data")
public class PasteboxController extends AbstractController<Pastebox, PasteboxService> {

    @Autowired
    public PasteboxController(PasteboxService service) {
        super(service);
    }

    @GetMapping("{hash}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PasteboxResponse.class),
            @ApiResponse(code = 404, message = "Entity not found")
    })
    public PasteboxResponse getByHash(@PathVariable String hash) {
        return service.getByHash(hash);
    }

    @PostMapping
    @ApiOperation(value = "adds entity, getting data from request body",
            response = PasteboxUrl.class,
            notes = "returns url of host name and hash, e.g.: https://localhost:8080/1a")
    public PasteboxUrl add(@RequestBody PasteboxRequest request) {
        return service.add(request);
    }

    @GetMapping
    @ApiOperation(value = "returns 10 or less last added and public data",
            response = List.class,
            notes = "list is parametrized with PasteboxResponse")
    public List<PasteboxResponse> get() {
        return service.getLastLimitedAndPublic();
    }
}
