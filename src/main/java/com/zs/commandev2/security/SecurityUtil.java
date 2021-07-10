package com.zs.commandev2.security;


import com.zs.commandev2.Commandev2Application;

import com.zs.commandev2.bean.User;
import com.zs.commandev2.service.facade.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SecurityUtil {


    public static User getCurrentUser() {
        UserService userService= Commandev2Application.getCtx().getBean(UserService.class);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object user = securityContext.getAuthentication().getPrincipal();
        System.out.println(user);
        if (user instanceof String) {
            return userService.findByUsername((String) user);
        } else if (user instanceof User) {
            return (User) user;
        } else {
            return null;
        }
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
                getAuthorities(authentication).noneMatch(AuthoritiesConstants.ANONYMOUS::equals);
    }

    /**
     * If the current user has a specific authority (security role).
     * <p>
     * The name of this method comes from the {@code isUserInRole()} method in the Servlet API.
     *
     * @param authority the authority to check.
     * @return true if the current user has the authority, false otherwise.
     */
    public static boolean isCurrentUserInRole(String authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
                getAuthorities(authentication).anyMatch(authority::equals);
    }

    private static Stream<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority);
    }

}
