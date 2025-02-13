package com.leo.viacep_api.dto;

public record CepDTO(String cep, String logradouro, String complemento, String bairro, String localidade, String uf) {}
// that record is like a model class but already have the getters, setters and constructors
// and it is more simple because we define the fields that return