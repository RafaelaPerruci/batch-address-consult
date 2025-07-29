package io.github.rafaelaperruci.batch_address_consult_api.repository;

import io.github.rafaelaperruci.batch_address_consult_api.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}
