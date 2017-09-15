package uk.co.jamesrossiter.spring.boot.saml.api.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.java.Log;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Log
@WebFilter(urlPatterns = { "/api/**" }, filterName = "jwtFilter")
public class JwtFilter extends GenericFilterBean {
    @PostConstruct
    public void init() {
        log.info("JwtFilter initialised");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = getAsHttpRequest(servletRequest);

        if (request.getMethod().equals(HttpMethod.OPTIONS.toString())
                || !request.getServletPath().startsWith("/api/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = request.getHeader("X-Auth-Token");

        if (StringUtils.isEmpty(token) || "undefined".equals(token)) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Forbidden");
            return;
        }

        // end of day is used for signature and expiration
        long endOfDay = ZonedDateTime.now().with(LocalTime.MAX).toInstant().toEpochMilli();
        String endOfDayString = Long.toString(endOfDay);

        Claims claims = Jwts.parser().setSigningKey(endOfDayString).parseClaimsJws(token).getBody();
        servletRequest.setAttribute("claims", claims);
        log.info(new ObjectMapper().writeValueAsString(claims));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private HttpServletRequest getAsHttpRequest(ServletRequest request) {
        if (!(request instanceof HttpServletRequest)) {
            throw new RuntimeException("Expecting an HTTP request");
        }
        return (HttpServletRequest) request;
    }
}
