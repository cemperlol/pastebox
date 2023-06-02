package com.example.demo.services;

import com.example.demo.dto.PasteboxRequest;
import com.example.demo.dto.PasteboxResponse;
import com.example.demo.dto.PasteboxUrl;
import com.example.demo.model.Pastebox;

import java.util.List;

public interface PasteboxService extends CommonEntityService<Pastebox> {

    PasteboxUrl add(PasteboxRequest request);

    PasteboxResponse getByHash(String hash);

    List<PasteboxResponse> getLastLimitedAndPublic();
}
