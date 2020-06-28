package ar.com.ada.aprende.component.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("restAuthEntryPoint")
public class RestAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex) throws IOException, ServletException {
        Integer scStatus = null;
        String errorType = ex.getLocalizedMessage().toUpperCase().replace(" ", "_");
        switch (errorType) {
            case "BAD_CREDENTIALS":
                scStatus = res.SC_BAD_REQUEST;
                break;
            default:
                scStatus = res.SC_UNAUTHORIZED;
        }
        res.sendError(scStatus,"invalid credentials, please check");
    }
}
