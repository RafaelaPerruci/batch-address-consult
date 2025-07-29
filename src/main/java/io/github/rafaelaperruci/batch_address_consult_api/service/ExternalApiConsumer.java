package io.github.rafaelaperruci.batch_address_consult_api.service;

import io.github.rafaelaperruci.batch_address_consult_api.configuration.WebClientConfig;
import io.github.rafaelaperruci.batch_address_consult_api.dto.AddressDTO;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalApiConsumer {

    private WebClient webClient;

    private final String baseUrl = "https://viacep.com.br/ws/";

    public ExternalApiConsumer(WebClient webClient) {
        this.webClient = webClient;
    }

    public AddressDTO getAddress(String cep) {

        if (cep == null) {
            throw new RuntimeException("CEP vazio.");
        }

        AddressDTO addressDTO = webClient.get()
                .uri(baseUrl + cep + "/json")
                .retrieve()
                .bodyToMono(AddressDTO.class)
                .block();


        if (addressDTO == null) {
            throw new EntityNotFoundException("CEP inválido ou não encontrado: " + cep);
        }

        return addressDTO;
    }
}
