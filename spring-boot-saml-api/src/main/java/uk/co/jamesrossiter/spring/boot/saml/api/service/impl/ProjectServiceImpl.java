package uk.co.jamesrossiter.spring.boot.saml.api.service.impl;

import org.springframework.stereotype.Service;
import uk.co.jamesrossiter.spring.boot.saml.api.service.ProjectService;
import uk.co.jamesrossiter.spring.boot.saml.model.CustomField;
import uk.co.jamesrossiter.spring.boot.saml.model.Project;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Override
    public List<Project> findAll() {
        // example from repo
        Project project1 = new Project();
        project1.setCode("project1");
        project1.setDescription("Project One");
        project1.setCustomFields(Collections.singletonList(new CustomField("0", "budget", "1.23")));

        Project project2 = new Project();
        project2.setCode("project2");
        project2.setDescription("Project Two");
        project2.setCustomFields(Collections.singletonList(new CustomField("0", "budget", "5.33")));

        return Arrays.asList(project1, project2);
    }

    @Override
    public Project findByCode(final String code) {
        Project project1 = new Project();
        project1.setCode("project1");
        project1.setDescription("Project One");
        project1.setCustomFields(Collections.singletonList(new CustomField("0", "budget", "1.23")));

        return project1;
    }
}
