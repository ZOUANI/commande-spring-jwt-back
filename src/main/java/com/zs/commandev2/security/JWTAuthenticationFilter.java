package com.zs.commandev2.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.zs.commandev2.Commandev2Application;
import com.zs.commandev2.bean.Role;
import com.zs.commandev2.service.facade.RoleService;
import com.zs.commandev2.service.facade.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            com.zs.commandev2.bean.User appUser= new ObjectMapper().readValue(request.getInputStream(), com.zs.commandev2.bean.User.class);
            System.out.println(appUser.getUsername());
            System.out.println(appUser.getPassword());
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(),appUser.getPassword()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        com.zs.commandev2.bean.User user=(com.zs.commandev2.bean.User)authResult.getPrincipal();

        UserService userService = Commandev2Application.getCtx().getBean(UserService.class);
        com.zs.commandev2.bean.User myUser = userService.findByUsernameWithRoles(user.getUsername());
        Collection<String> roles = new ArrayList<>();
        if (myUser.getAuthorities() != null) {
            myUser.getAuthorities().forEach(a->roles.add(a.getAuthority()));
        }
        Boolean passwordChanged = myUser.isPasswordChanged();
        if (passwordChanged == null) {
            passwordChanged=Boolean.FALSE;
        }

        String jwt= JWT.create()
                .withIssuer(request.getRequestURI())
                .withSubject(user.getUsername())
                .withArrayClaim("roles",roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis()+ SecurityParams.EXPIRATION))
                .withClaim("passwordChanged",passwordChanged)
                .sign(Algorithm.HMAC256(SecurityParams.SECRET));
        response.addHeader(SecurityParams.JWT_HEADER_NAME,SecurityParams.HEADER_PREFIX+jwt);
        System.out.println(jwt);
    }

}
