package com.example.demo.model.pastebox;

import com.example.demo.model.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class Pastebox extends AbstractEntity {

    private String data;

    private String hash;

    private LocalDateTime expirationTime;

    private PasteboxRestrictions restriction;
}
