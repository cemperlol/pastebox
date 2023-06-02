package com.example.demo.services;

import com.example.demo.dto.PasteboxUrl;
import com.example.demo.model.Pastebox;
import com.example.demo.model.PasteboxRestrictions;

import java.time.LocalDateTime;
import java.util.List;

public interface PasteboxService extends CommonEntityService<Pastebox> {

    PasteboxUrl add(LocalDateTime expirationTime, PasteboxRestrictions restriction);

    Pastebox getByHash(int hash);

    List<Pastebox> getLastLimitedAndPublic(int limit);
}
