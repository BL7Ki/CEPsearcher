package com.leo.viacep_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.leo.viacep_api.model.CepResponse;

@Service
public class CepService {

    @Autowired
    private RestTemplate restTemplate; // inject restTemplate used to send requests

    public CepResponse searchCep(String cep) { // receive cep and send to api and return cepResponse
        String url = "https://viacep.com.br/ws/" + cep + "/json/"; // viacep.com.br/ws/01001000/json/
        return restTemplate.getForObject("https://viacep.com.br/ws/" + cep + "/json/", CepResponse.class);
    }
}
