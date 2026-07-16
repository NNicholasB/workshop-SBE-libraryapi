package io.github.ngraciano.libraryapi.security;

import io.github.ngraciano.libraryapi.model.User;
import io.github.ngraciano.libraryapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

   private final UserService userService;
   private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login=authentication.getName();
        String passwordDigit=authentication.getCredentials().toString();

        User userFound = userService.getByLogin(login);

        if (userFound==null){
            throw getUsernameNotFoundException();
        }

        String passwordCrypt=userFound.getPassword();
        boolean passwordMatch=passwordEncoder.matches(passwordDigit,passwordCrypt);


        if (passwordMatch){
            return new CustomAuthentication(userFound);
        }
        throw getUsernameNotFoundException();
    }

    private  UsernameNotFoundException getUsernameNotFoundException() {
        return new UsernameNotFoundException("User/Password not found");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
