package uk.co.jamesrossiter.spring.boot.saml.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import uk.co.jamesrossiter.spring.boot.saml.api.handler.AuthenticatedUserHandlerMethodArgumentResolver;
import uk.co.jamesrossiter.spring.boot.saml.api.handler.IdpUserHandlerMethodArgumentResolver;

import java.util.List;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
    IdpUserHandlerMethodArgumentResolver idpUserHandlerMethodArgumentResolver;

	@Autowired
    AuthenticatedUserHandlerMethodArgumentResolver authenticatedUserHandlerMethodArgumentResolver;
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
    	argumentResolvers.add(idpUserHandlerMethodArgumentResolver);
    	argumentResolvers.add(authenticatedUserHandlerMethodArgumentResolver);
    }
}
