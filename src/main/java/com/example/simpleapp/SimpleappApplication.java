package com.example.simpleapp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

import java.util.Arrays;

@SpringBootApplication
public class SimpleappApplication {

//    @Value("${security.oauth2.client.accessTokenUri}")
//    private String accessTokenUri;
//
//    @Value("${security.oauth2.client.userAuthorizationUri}")
//    private String userAuthorizationUri;
//
//    @Value("${security.oauth2.client.clientId}")
//    private String clientId;
//
//    @Value("${security.oauth2.client.clientSecret}")
//    private String clientSecret;

    public static void main(String[] args) {
        SpringApplication.run(SimpleappApplication.class, args);
    }

//    @Bean
//    public OAuth2RestTemplate oauth2RestTemplate(@Qualifier("OAuth2ClientContext") OAuth2ClientContext oauth2ClientContext,
//                                                 OAuth2ProtectedResourceDetails details) {
//        return new OAuth2RestTemplate(details, oauth2ClientContext);
//    }

//    private OAuth2ProtectedResourceDetails resource() {
//        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
//        resource.setClientId(clientId);
//        resource.setClientSecret(clientSecret);
//        resource.setAccessTokenUri(accessTokenUri);
//        resource.setUserAuthorizationUri(userAuthorizationUri);
//        resource.setScope(Arrays.asList("full"));
//        resource.setPreEstablishedRedirectUri();
//
//        return resource;
//    }
}
