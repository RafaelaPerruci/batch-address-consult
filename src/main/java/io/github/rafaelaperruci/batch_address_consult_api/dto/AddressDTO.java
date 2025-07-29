package io.github.rafaelaperruci.batch_address_consult_api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressDTO (

        @JsonAlias("logradouro")
        String street,

        @JsonAlias("bairro")
        String suburb,

        String cep,

        @JsonAlias("cidade")
        String city,

        @JsonAlias("uf")
        String state
){
}
