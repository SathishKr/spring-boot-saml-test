package uk.co.jamesrossiter.spring.boot.saml.api.handler;

import io.jsonwebtoken.Claims;
import lombok.extern.java.Log;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import uk.co.jamesrossiter.spring.boot.saml.api.stereotypes.AuthenticatedUser;
import uk.co.jamesrossiter.spring.boot.saml.model.User;

import java.util.List;

@Log
@Component
public class AuthenticatedUserHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterAnnotation(AuthenticatedUser.class) != null
				&& methodParameter.getParameterType().equals(uk.co.jamesrossiter.spring.boot.saml.model.User.class);
	}

	public Object resolveArgument(MethodParameter methodParameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		if (this.supportsParameter(methodParameter)) {
			Claims userClaims = (Claims) webRequest.getAttribute("claims", 0);
			if (userClaims == null) {
				return null;
			}
			User authenticatedUser = new User();
			authenticatedUser.setEmail(userClaims.getSubject());
			authenticatedUser.setPermissions(userClaims.get("permissions", List.class));
			return authenticatedUser;
		} else {
			return WebArgumentResolver.UNRESOLVED;
		}
	}
}