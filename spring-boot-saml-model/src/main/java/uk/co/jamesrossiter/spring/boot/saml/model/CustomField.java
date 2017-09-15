package uk.co.jamesrossiter.spring.boot.saml.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomField {
    private String key;
    private String name;
    private String value;
}
