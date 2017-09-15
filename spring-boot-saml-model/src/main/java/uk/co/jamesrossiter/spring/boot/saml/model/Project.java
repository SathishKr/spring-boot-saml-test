package uk.co.jamesrossiter.spring.boot.saml.model;

import lombok.Data;

import java.util.List;

@Data
public class Project {
    private String code;
    private String description;
    private List<CustomField> customFields;
}
