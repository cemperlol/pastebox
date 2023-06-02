package com.example.demo.services;

import com.example.demo.config.ConfigProperties;
import com.example.demo.dto.PasteboxRequest;
import com.example.demo.dto.PasteboxResponse;
import com.example.demo.dto.PasteboxUrl;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.model.Pastebox;
import com.example.demo.model.PasteboxRestrictions;
import com.example.demo.repositories.PasteboxRepository;
import com.example.demo.utils.generators.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PasteboxServiceImpl extends AbstractEntityService<Pastebox, PasteboxRepository>
        implements PasteboxService {

    private final String hostName;

    private final int limit;

    @Autowired
    public PasteboxServiceImpl(PasteboxRepository repository, ConfigProperties configProperties) {
        super(repository);
        this.hostName = configProperties.getHost();
        this.limit = configProperties.getLimit();
    }

    @Override
    public PasteboxUrl add(PasteboxRequest request) {
        Pastebox pastebox = new Pastebox();
        pastebox.setId(IdGenerator.generateId(repository.count()));
        pastebox.setHash(Integer.toHexString(pastebox.getId()));
        pastebox.setData(request.getData());
        pastebox.setExpirationTime(LocalDateTime.now().plusSeconds(request.getExpirationTime()));
        pastebox.setRestriction(request.getRestriction());

        super.save(pastebox);

        return new PasteboxUrl(hostName + "/" + pastebox.getHash());
    }

    @Override
    public PasteboxResponse getByHash(String hash) {
        Pastebox pastebox = repository.findByHash(hash).orElse(null);

        if (pastebox == null) throw new EntityNotFoundException("Nothing was found by hash: " + hash);
        return new PasteboxResponse(pastebox.getData());
    }

    @Override
    public List<PasteboxResponse> getLastLimitedAndPublic() {
        LocalDateTime timeNow = LocalDateTime.now();

        return repository.findAll().stream()
                .filter(p -> p.getExpirationTime().isAfter(timeNow)
                        && p.getRestriction().equals(PasteboxRestrictions.PUBLIC))
                .sorted(Comparator.comparingInt(Pastebox::getId).reversed())
                .limit(limit)
                .map(p -> new PasteboxResponse(p.getData()))
                .toList();
    }
}
