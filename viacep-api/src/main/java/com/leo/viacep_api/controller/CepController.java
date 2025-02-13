package com.leo.viacep_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.viacep_api.model.CepResponse;
import com.leo.viacep_api.service.CepService;

@RestController
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/cep")
    public ResponseEntity<?> searchCep(@RequestParam String cep) {
        try {
            // simple validation for CEP (8 digits)
            if (cep == null || !cep.matches("\\d{8}")) {
                return ResponseEntity.badRequest().body("CEP inválido. O formato deve ser 8 dígitos.");
            }

            CepResponse cepResponse = cepService.searchCep(cep);
            if (cepResponse == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CEP não encontrado.");
            }

            return ResponseEntity.ok(cepResponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação.");
        }
    }
}

// with ResponseEntity we can define the status code like 404 not found, 500 internal server error or 400 bad request
// and this will be returned to the client, being easy to handle the errors and better for the user
// using toString() method we can get the error message