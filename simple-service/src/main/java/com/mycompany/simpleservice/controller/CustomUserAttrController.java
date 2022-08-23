package com.mycompany.simpleservice.controller;

import org.springframework.stereotype.Controller;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CustomUserAttrController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void secureMethod(HttpServletRequest request) {
        @SuppressWarnings("rawtypes")
        AccessToken token = ((KeycloakPrincipal) request.getUserPrincipal())
                .getKeycloakSecurityContext().getToken();
        Map<String, Object> otherClaims = token.getOtherClaims();
        System.out.println(otherClaims.size());
        ModelAndView mav = new ModelAndView();
    }
}
//@Controller
//public class CustomUserAttrController {
//
//    @GetMapping(path = "/users")
//    public String getUserInfo(Model model) {
//        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken)
//                SecurityContextHolder.getContext().getAuthentication();
//
//        Principal principal = (Principal) authentication.getPrincipal();
//        String dob="";
//
//        if (principal instanceof KeycloakPrincipal) {
//            KeycloakPrincipal kPrincipal = (KeycloakPrincipal) principal;
//            IDToken token = kPrincipal.getKeycloakSecurityContext().getIdToken();
//
//            Map<String, Object> customClaims = token.getOtherClaims();
//
//            if (customClaims.containsKey("DOB")) {
//                dob = String.valueOf(customClaims.get("DOB"));
//            }
//        }
//
//        model.addAttribute("username", principal.getName());
//        model.addAttribute("dob", dob);
//        return "userInfo";
//    }
//}