package uk.co.jamesrossiter.spring.boot.saml.api.filter;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log
@WebFilter(urlPatterns = { "/api/**" })
public class CorsFilter implements Filter {
    @PostConstruct
    public void init() {
        log.info("Cors Filter initialised");
    }

    /**
     * Add CORS headers to response
     * @param req ServletRequest request
     * @param res ServletResponse response
     * @param chain FilterChain filter chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, "
                + "X-Requested-With, X-Auth-Token");
        response.setHeader("Cache-Control", "no-store, must-revalidate, no-cache, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "Fri, 01 Jan 1990 00:00:00 GMT");
        chain.doFilter(req, res);
    }

    public void destroy() { }

    public void init(FilterConfig filterConfig) { }
}
