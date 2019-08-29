package burak.springsecurity.logout;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Component
public class MyLogoutHandler implements LogoutHandler {

    private static Logger logger = LoggerFactory.getLogger(LogoutHandler.class);

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        logger.info("User '" + auth.getName() + "' has logged out!");

        try {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/?logout");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
