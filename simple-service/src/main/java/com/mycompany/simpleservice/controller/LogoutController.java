//package com.mycompany.simpleservice.controller;
//
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@RequestMapping("/logout")
//public class LogoutController {
//    public static void logout(String refreshToken) {
//        try {
//            MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//            requestParams.add("client_id", "simple-service");
//            requestParams.add("client_secret", "3ea3319b-8cfe-4235-b445-e63ccb9c0fdf");
//            requestParams.add("refresh_token", refreshToken);
//
//            logoutUserSession(requestParams);
//
//        } catch (Exception e) {
////            log.info(e.getMessage(), e);
//            throw e;
//        }
//    }
//
//    private static void logoutUserSession(MultiValueMap<String, String> requestParams) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestParams, headers);
//        String realmName = "company-services";
//        String url = "/auth/realms/company-services/protocol/openid-connect/logout";
//
////        RestTemplate.postForEntity(url, request, Object.class);
//        // got response 204, no content
//    }
////    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
////    public ModelAndView method() {
////        return new ModelAndView("redirect:" + "http://localhost:8080/auth/realms/company-services/protocol/openid-connect/logout");
////    }
//
////    @GetMapping
////    ResponseEntity<Void> redirect() {
////        return ResponseEntity.status(HttpStatus.FOUND)
////                .location(URI.create("http://localhost:8080/auth/realms/company-services/protocol/openid-connect/logout"))
////                .build();
////    }
////    @RequestMapping("/to-be-redirected")
////    public ResponseEntity<Object> redirectToExternalUrl() throws URISyntaxException {
////        URI yahoo = new URI("http://localhost:8080/auth/realms/company-services/protocol/openid-connect/logout");
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setLocation(yahoo);
////        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
////    }
//}
