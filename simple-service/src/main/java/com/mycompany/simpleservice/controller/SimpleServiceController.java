package com.mycompany.simpleservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Map;

import static com.mycompany.simpleservice.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@RequestMapping("/api")
public class SimpleServiceController {

    @Operation(summary = "Get string from public endpoint")
    @GetMapping("/public")
    public String getPublicString() {
        return "It is public.\n";
    }

    @Operation(
            summary = "Get string from private/secured endpoint",
            security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("/private")
    public String getPrivateString(Principal principal) {
        return String.format("%s, it is private.%n", principal.getName());
    }

    @GetMapping("/customHeader")
    ResponseEntity<String> customHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");

        return new ResponseEntity<>(
                "Custom header set", headers, HttpStatus.OK);
    }


    HttpServletRequest request;

        @GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
        public UserData handleUserInfoRequest(Principal principal) {
            System.out.println("principal "+principal);

            UserData user = new UserData();

            if (principal instanceof KeycloakPrincipal) {

                KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
                AccessToken token = kp.getKeycloakSecurityContext().getToken();
                user.setId(token.getId());
                user.setUserName(token.getName());
                Map<String, Object> otherClaims = token.getOtherClaims();
                user.setCustomAttributes(otherClaims);
            }
            return user;
        }

    @RequestMapping("/logout")
    public ResponseEntity<Object> redirectToExternalUrl() throws URISyntaxException {
        URI logoutURI= new URI("http://localhost:8080/auth/realms/company-services/protocol/openid-connect/logout");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(logoutURI);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
//    public void logout(String refreshToken) {
//            try {
//                MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//                requestParams.add("client_id", "simple-service");
//                requestParams.add("client_secret", "3ea3319b-8cfe-4235-b445-e63ccb9c0fdf");
//                requestParams.add("refresh_token", refreshToken);
//
//                logoutUserSession(requestParams);
//
//            } catch (Exception e) {
////            log.info(e.getMessage(), e);
//                throw e;
//            }
//        }
//
//        private void logoutUserSession(MultiValueMap<String, String> requestParams) {
//            HttpHeaders headers = new HttpHeaders();
//            RestTemplate restTemplate = new RestTemplate();
//
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestParams, headers);
//            String realmName = "company-services";
//            String url = "/auth/realms/company-services/protocol/openid-connect/logout";
//
//        restTemplate.postForEntity(url, request, Object.class);
//            // got response 204, no content
//        }
}