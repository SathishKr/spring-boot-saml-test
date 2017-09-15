package uk.co.jamesrossiter.spring.boot.saml.api.service;

import lombok.extern.java.Log;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Service;
import uk.co.jamesrossiter.spring.boot.saml.builder.JwtBuilder;
import uk.co.jamesrossiter.spring.boot.saml.model.IdpUser;
import uk.co.jamesrossiter.spring.boot.saml.model.ProjectRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log
@Service
public class SAMLUserDetailsServiceImpl implements SAMLUserDetailsService {

	@Override
	public Object loadUserBySAML(SAMLCredential credential)
			throws UsernameNotFoundException {
		
		// The method is supposed to identify local account of user referenced by
		// data in the SAML assertion and return UserDetails object describing the user.
		String userID = credential.getNameID().getValue();
		
		log.info(userID + " is logged in");
		List<GrantedAuthority> authorities = new ArrayList<>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		authorities.add(authority);

		// domain specific user
        IdpUser user = new IdpUser(userID, "<abc123>", true, true, true, true, authorities);

        // example permission set from db
        Map<String, Object> userData = new HashMap<>();
        userData.put("permissions", Arrays.asList(
                new ProjectRole("project1", "admin"),
                new ProjectRole("project2", "user")
        ));

        // create JWT
        String jwt = new JwtBuilder()
                .withSubject(userID)
                .withClaims(userData)
                .build();

        // append JWT to returned user
        user.setJwt(jwt);

        return user;
	}
}
