package com.sji.group9.pointInteret.repositorry;

import com.sji.group9.pointInteret.entity.Categorie;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Observed
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findByLibelleIgnoreCase(String libelle);
}
