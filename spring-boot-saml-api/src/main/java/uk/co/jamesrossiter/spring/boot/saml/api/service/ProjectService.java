package uk.co.jamesrossiter.spring.boot.saml.api.service;

import uk.co.jamesrossiter.spring.boot.saml.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();

    Project findByCode(final String code);
}
