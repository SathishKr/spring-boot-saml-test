package uk.co.jamesrossiter.spring.boot.saml.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public Map<String, String> test() throws Exception {
		Map<String, String> response = new HashMap<>();
		response.put("status", "ok");
		return response;
	}
}
