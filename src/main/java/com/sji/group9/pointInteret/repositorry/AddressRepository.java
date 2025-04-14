package com.sji.group9.pointInteret.repositorry;

import com.sji.group9.pointInteret.entity.Address;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Observed
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findAddressByVilleIgnoreCaseAndQuartierIgnoreCaseAndPointRepereIgnoreCase(String ville, String quartier, String ptRepere);
}
