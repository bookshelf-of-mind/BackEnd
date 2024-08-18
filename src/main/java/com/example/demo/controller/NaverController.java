package com.example.demo.controller;

import com.example.demo.domain.NaverOAuthToken;
import com.example.demo.domain.NaverProfileResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames.CLIENT_ID;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.CLIENT_SECRET;

@RestController
public class NaverController {
    @RequestMapping("/naverlogin")
        public String Naver() {
            return "naverlogin";
    }

    @RequestMapping("/callback")
    public ResponseEntity<?> CallBack() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("loginafter.html"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    public void NaverCallback(String code, String state) throws JsonProcessingException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", CLIENT_SECRET);
        params.add("code", code);
        params.add("state", state);
        // Parameter로 전달할 속성들 추가

        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = makeTokenRequest(params);
        // Http 메시지 생성

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> tokenResponse = rt.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                naverTokenRequest,
                String.class
        );
        // TOKEN_REQUEST_URL로 Http 요청 전송

        ObjectMapper objectMapper = new ObjectMapper();
        NaverOAuthToken naverToken = objectMapper.readValue(tokenResponse.getBody(), NaverOAuthToken.class);
        // ObjectMapper를 통해 NaverOAuthToken 객체로 매핑

        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = makeProfileRequest(naverToken);

        ResponseEntity<String> profileResponse = rt.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.POST,
                naverProfileRequest,
                String.class
        );

        NaverProfileResponse naverProfileResponse = objectMapper.readValue(profileResponse.getBody(), NaverProfileResponse.class);

    }

    private HttpEntity<MultiValueMap<String, String>> makeTokenRequest(MultiValueMap<String, String> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);
        return naverTokenRequest;
    }

    private HttpEntity<MultiValueMap<String, String>> makeProfileRequest(NaverOAuthToken naverToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+ naverToken.getAccess_token());
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = new HttpEntity<>(headers);
        return naverProfileRequest;
    }
}
