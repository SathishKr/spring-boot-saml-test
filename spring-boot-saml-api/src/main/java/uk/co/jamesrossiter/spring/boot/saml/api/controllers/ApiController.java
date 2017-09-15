package uk.co.jamesrossiter.spring.boot.saml.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.jamesrossiter.spring.boot.saml.api.service.ProjectService;
import uk.co.jamesrossiter.spring.boot.saml.api.stereotypes.AuthenticatedUser;
import uk.co.jamesrossiter.spring.boot.saml.model.Project;
import uk.co.jamesrossiter.spring.boot.saml.model.User;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log
public class ApiController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ProjectService projectService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User user(@AuthenticatedUser User user) throws Exception {
        log.info(objectMapper.writeValueAsString(user));
		return user;
	}

	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public List<Project> projects() throws Exception {
        return projectService.findAll();
	}

	@RequestMapping(value = "/project/{code}", method = RequestMethod.GET)
	public Project project(@PathVariable("code") String code) throws Exception {
        return projectService.findByCode(code);
	}
}
