package io.github.rafaelaperruci.batch_address_consult_api.service;

import io.github.rafaelaperruci.batch_address_consult_api.configuration.WebClientConfig;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private WebClientConfig webClientConfig;

    public AddressService(WebClientConfig webClientConfig) {
        this.webClientConfig = webClientConfig;
    }
}
