package io.github.rafaelaperruci.batch_address_consult_api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.rafaelaperruci.batch_address_consult_api.model.Address;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressDTO (

        @JsonAlias("logradouro")
        String street,

        @JsonAlias("bairro")
        String suburb,

        String cep,

        @JsonAlias("localidade")
        String city,

        @JsonAlias("uf")
        String state
){
        public AddressDTO(Address address) {
                this(address.getStreet(), address.getSuburb(), address.getCep(), address.getCity(), address.getState());
        }
}
