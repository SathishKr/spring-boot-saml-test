package uk.co.jamesrossiter.spring.boot.saml.api.handler;

import lombok.extern.java.Log;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import uk.co.jamesrossiter.spring.boot.saml.api.stereotypes.IdpUser;

import java.security.Principal;

@Log
@Component
public class IdpUserHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterAnnotation(IdpUser.class) != null
				&& methodParameter.getParameterType().equals(uk.co.jamesrossiter.spring.boot.saml.model.IdpUser.class);
	}

	public Object resolveArgument(MethodParameter methodParameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		if (this.supportsParameter(methodParameter)) {
			Principal principal = webRequest.getUserPrincipal();
			if (principal == null) {
				return null;
			}
			return ((Authentication) principal).getPrincipal();
		} else {
			return WebArgumentResolver.UNRESOLVED;
		}
	}
}
