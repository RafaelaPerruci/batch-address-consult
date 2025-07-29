package io.github.rafaelaperruci.batch_address_consult_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "address")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String street;

    private String suburb;

    private String cep;

    private String city;

    private String state;

    @CreatedDate
    @Column(name = "date_registry")
    private LocalDateTime dateRegistry;

    public Address() {}

    public Address(String street, String suburb, String cep, String city, String state) {
        this.street = street;
        this.suburb = suburb;
        this.cep = cep;
        this.city = city;
        this.state = state;
    }


}
