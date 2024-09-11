package com.Cloud.Cloud.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        // Check if the user logged in with OAuth2
        if (authentication instanceof OAuth2AuthenticationToken) {

            var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User) oauth2AuthenticationToken.getPrincipal();
            String username = "";

            if (clientId.equalsIgnoreCase("google")) {
                // If signed in with Google
                System.out.println("Getting email with Google");
                username = oauth2User.getAttribute("email").toString();
            } else if (clientId.equalsIgnoreCase("github")) {
                // If signed in with GitHub
                System.out.println("Getting email with GitHub");
                username = oauth2User.getAttribute("email") != null 
                            ? oauth2User.getAttribute("email").toString()
                            : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }

            return username;

        } else { 
            // If not OAuth, assume simple email login
            System.out.println("Getting data from local database");
            return authentication.getName();
        }
    }

    public static String  getLinkForEmailVerification(String emailToken){
        String link = "http://localhost:8081/auth/verify-email?token="+emailToken;
        return link;
    }
}
