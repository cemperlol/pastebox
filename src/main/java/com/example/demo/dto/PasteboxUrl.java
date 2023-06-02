package com.example.demo.dto;

import lombok.Data;

@Data
public class PasteboxUrl {

    private String host;

    private final String hash;

    @Override
    public String toString() {
        return host + "/" + hash;
    }
}
