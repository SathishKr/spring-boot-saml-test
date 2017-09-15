package uk.co.jamesrossiter.spring.boot.saml.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    String email;
    List<ProjectRole> permissions;
}
