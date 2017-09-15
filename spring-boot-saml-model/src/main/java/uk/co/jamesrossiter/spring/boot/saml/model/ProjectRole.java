package uk.co.jamesrossiter.spring.boot.saml.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectRole {
    private String projectId;
    private String role;
}
