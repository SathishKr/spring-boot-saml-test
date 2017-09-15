package uk.co.jamesrossiter.spring.boot.saml.api.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    @ResponseBody
    public ErrorJson error(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> err = errorAttributes.getErrorAttributes(requestAttributes, false);
        return new ErrorJson(
                response.getStatus(), (String) err.get("error"), (String) err.get("message"),
                err.get("timestamp").toString());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @Data
    @AllArgsConstructor
    private class ErrorJson {
        Integer status;
        String error;
        String message;
        String timestamp;
    }
}
