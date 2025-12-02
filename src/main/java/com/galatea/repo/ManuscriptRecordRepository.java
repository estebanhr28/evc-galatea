package com.galatea.repo;

import com.galatea.model.ManuscriptRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ManuscriptRecordRepository extends JpaRepository<ManuscriptRecord, Long> {
    Optional<ManuscriptRecord> findByManuscriptHash(String manuscriptHash);
    long countByClueFoundTrue();
    long countByClueFoundFalse();
}
