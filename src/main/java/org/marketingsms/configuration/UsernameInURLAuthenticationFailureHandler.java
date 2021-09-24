package org.marketingsms.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsernameInURLAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private String urlPrefix;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private String formUsernameKey = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

    public UsernameInURLAuthenticationFailureHandler() {
        this.urlPrefix = "login?username=";
    }

    //Failure logic:
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //We inherited that method:
        saveException(request, exception);

        //Prepare URL:
        String username = request.getParameter(formUsernameKey);
        String redirectUrl = urlPrefix + username;

        //Redirect:
        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

    //Getters and setters:
    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getFormUsernameKey() {
        return formUsernameKey;
    }

    public void setFormUsernameKey(String formUsernameKey) {
        this.formUsernameKey = formUsernameKey;
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
