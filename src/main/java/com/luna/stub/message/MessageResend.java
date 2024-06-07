package com.luna.stub.message;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class MessageResend {
    private final RestTemplate restTemplate;

    public void sendResponce(String body, String authorization, String event, String confirmationKey, String initialEventId,String eventSwitchTime) throws ParseException, JSONException {
        String url = "https://lt-app.loonabrowser.ru/event-service/confirmation/events/"+event+"/state";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        String message = "{\"state\":\"opened\",\"initialEventId\":\""+initialEventId+"\",\"eventSwitchTime\":\""+eventSwitchTime+"\",\"confirmationKey\":\""+confirmationKey+"\"}";
        HttpEntity<String> request = new HttpEntity<>(message, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
    }
}

