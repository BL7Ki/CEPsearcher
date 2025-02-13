package com.leo.viacep_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.leo.viacep_api.model.CepResponse;
import com.leo.viacep_api.util.JsonFileWriter;

@Service
public class CepService {

    @Autowired
    private RestTemplate restTemplate; // Injects RestTemplate to make HTTP requests

    public CepResponse searchCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/"; // Builds the API URL using the provided CEP
        
        // Makes the API request and stores the response in a variable
        CepResponse cepResponse = restTemplate.getForObject(url, CepResponse.class);

        // Saves the response to a JSON file if it is valid
        if (cepResponse != null && cepResponse.getCep() != null) {
            JsonFileWriter.saveToFile(cepResponse);
        }

        return cepResponse; // Returns the CEP data to the caller
    }
}
