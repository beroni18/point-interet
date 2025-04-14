package com.sji.group9.pointInteret.repositorry;

import com.sji.group9.pointInteret.entity.Contact;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Observed
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUrl(String url);
    Contact findByEmail(String email);
}
