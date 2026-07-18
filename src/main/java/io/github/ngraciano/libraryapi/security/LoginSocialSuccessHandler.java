package io.github.ngraciano.libraryapi.security;



import io.github.ngraciano.libraryapi.model.User;
import io.github.ngraciano.libraryapi.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final String PASSWORD_DEFAULT="123";

    private final UserService service;
    private final PasswordEncoder encoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken auth2AuthenticationToken=(OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();
        String email=oAuth2User.getAttribute("email");
        User user=service.getByEmail(email);

        if(user == null){
            user = registerUserDB(email);
        }

        authentication= new CustomAuthentication(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        super.onAuthenticationSuccess(request,response,authentication);

    }

    private User registerUserDB(String email) {
        User user;
        user = new User();
        user.setEmail(email);
        user.setLogin(getLoginbyEmail(email));
        user.setPassword(PASSWORD_DEFAULT);
        user.setRoles(List.of("OPERADOR"));
        service.save(user);
        return user;
    }

    private String getLoginbyEmail(String email) {
        return email.substring(0,email.indexOf("@"));
    }
}
