package com.example.demo.dto;

import com.example.demo.model.pastebox.PasteboxRestrictions;
import lombok.Data;

@Data
public class PasteboxRequest {

    private String data;

    private long expirationTime;

    private PasteboxRestrictions restriction;
}
