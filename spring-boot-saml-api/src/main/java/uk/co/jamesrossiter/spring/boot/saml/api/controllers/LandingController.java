package uk.co.jamesrossiter.spring.boot.saml.api.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.co.jamesrossiter.spring.boot.saml.api.stereotypes.IdpUser;

@Controller
public class LandingController {

	@Value("${front.end.url:http://localhost:3000}")
	private String frontEndUrl;

	@RequestMapping("/landing")
	public String landing(@IdpUser uk.co.jamesrossiter.spring.boot.saml.model.IdpUser user) throws Exception {
		String jwt = "";
		if (user != null) {
			jwt = user.getJwt();
		}
		return "redirect:" + frontEndUrl + "/landing?token=" + jwt;
	}

	@RequestMapping("/")
	public String index() throws Exception {
		return "redirect:" + frontEndUrl;
	}
}
