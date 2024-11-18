package com.ondc.ulip.Service;

import com.ondc.ulip.DTO.AuthRequest;
import com.ondc.ulip.DTO.SarathiRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;

@Service
public class UlipIntegrationService {
    @Value("$sarathi.auth.url")
    private String authUrl;

    @Value("$sarathi.auth.token")
    private String authToken;

    @Value("$sarathi.auth.username")
    private String username;

    @Value("$sarathi.auth.password")
    private String password;

    @Value("$sarathi.api.url")
    private String apiUrl;

    @Value("$sarathi.api.token")
    private String apiToken;

    private final RestTemplate restTemplate;
    private String bearerToken;

    public UlipIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void authenticate() {
        AuthRequest authRequest = new AuthRequest(username, password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthRequest> entity = new HttpEntity<>(authRequest, headers);

        try {
            String tokenResponse = restTemplate.postForObject(authUrl, entity, String.class);
            this.bearerToken = tokenResponse;  // Extract token from response

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }

    private String getBearerToken() {
        if (bearerToken == null) {
            authenticate();
        }
        return bearerToken;
    }

    public String postSarathiDetails(SarathiRequest sarathiRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(getBearerToken());

        HttpEntity<SarathiRequest> entity = new HttpEntity<>(sarathiRequest, headers);

        try {
            return restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class).getBody();
        } catch (HttpClientErrorException.Unauthorized e) {
            // If unauthorized, re-authenticate and retry for 401
            authenticate();
            headers.setBearerAuth(getBearerToken());
            entity = new HttpEntity<>(sarathiRequest, headers);
            return restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class).getBody();
        } catch (Exception e) {
            // TODO change according to the exceptions given
            authenticate();
            headers.setBearerAuth(getBearerToken());
            entity = new HttpEntity<>(sarathiRequest, headers);
            return restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class).getBody();
        }
    }
}