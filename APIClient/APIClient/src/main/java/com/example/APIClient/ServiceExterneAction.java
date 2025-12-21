package com.example.APIClient;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ServiceExterneAction {

    private final RestClient restClient;

    public ServiceExterneAction(RestClient restClient) {
        this.restClient = restClient;
    }

    public String obtenirDataDepuisAilleurs() {
        return restClient.get()
                .uri("/infos-complementaires")
                .retrieve()
                .body(String.class);
    }
}
