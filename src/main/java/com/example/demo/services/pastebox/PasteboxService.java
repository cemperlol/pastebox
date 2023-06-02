package com.example.demo.services.pastebox;

import com.example.demo.dto.PasteboxRequest;
import com.example.demo.dto.PasteboxResponse;
import com.example.demo.dto.PasteboxUrl;
import com.example.demo.model.pastebox.Pastebox;
import com.example.demo.services.CommonEntityService;

import java.util.List;

public interface PasteboxService extends CommonEntityService<Pastebox> {

    PasteboxUrl add(PasteboxRequest request);

    PasteboxResponse getByHash(String hash);

    List<PasteboxResponse> getLastLimitedAndPublic();
}
