package com.example.demo.services;

import com.example.demo.dto.PasteboxUrl;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Pastebox;
import com.example.demo.model.PasteboxRestrictions;
import com.example.demo.repositories.PasteboxRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PasteboxServiceImpl extends AbstractEntityService<Pastebox, PasteboxRepository>
        implements PasteboxService {


    public PasteboxServiceImpl(PasteboxRepository repository) {
        super(repository);
    }

    @Override
    public PasteboxUrl add(LocalDateTime expirationTime, PasteboxRestrictions restriction) {
        Pastebox pastebox = new Pastebox();
        pastebox.setHash(Integer.toHexString(pastebox.getId()));
        pastebox.setExpirationTime(expirationTime);
        pastebox.setRestriction(restriction);

        super.save(pastebox);

        return new PasteboxUrl(pastebox.getHash());
    }

    @Override
    public Pastebox getByHash(int hash) {
        String hexHash = Integer.toHexString(hash);
        Pastebox pastebox = repository.findByHash(hexHash).orElse(null);

        if (pastebox == null) throw new NotFoundException("Nothing was found by hash: " + hexHash);
        else return pastebox;
    }

    @Override
    public List<Pastebox> getLastLimitedAndPublic(int limit) {
        LocalDateTime timeNow = LocalDateTime.now();

        return repository.findAll().stream()
                .filter(p -> p.getExpirationTime().isAfter(timeNow)
                        && p.getRestriction().equals(PasteboxRestrictions.PUBLIC))
                .sorted(Comparator.comparingInt(Pastebox::getId).reversed())
                .toList();
    }
}
