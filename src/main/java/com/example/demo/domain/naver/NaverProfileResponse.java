package com.example.demo.domain.naver;

public class NaverProfileResponse {
    private String resultcode;
    private String message;
    private NaverProfile response;

    public NaverProfileResponse() {
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResponse(NaverProfile response) {
        this.response = response;
    }
}