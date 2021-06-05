package com.cojo.passwordmanager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BioDataRepository extends JpaRepository<BioData, Long> {
    BioData findByBiomail(String email);
}
