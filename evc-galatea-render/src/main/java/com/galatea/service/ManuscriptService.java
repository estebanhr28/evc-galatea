package com.galatea.service;

import com.galatea.model.ManuscriptRecord;
import com.galatea.repo.ManuscriptRecordRepository;
import com.galatea.util.ContainsArtifactClueUtil;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Service
public class ManuscriptService {

    private final ManuscriptRecordRepository repository;

    public ManuscriptService(ManuscriptRecordRepository repository) {
        this.repository = repository;
    }

    public boolean processAndStoreIfNew(String[] manuscript) {
        if (manuscript == null) return false;
        String key = ManuscriptRecord.hashManuscript(manuscript);

        Optional<ManuscriptRecord> existing = repository.findByManuscriptHash(key);
        boolean found = ContainsArtifactClueUtil.containsArtifactClue(manuscript);

        if (existing.isEmpty()) {
            ManuscriptRecord r = new ManuscriptRecord();
            r.setManuscriptHash(key);
            r.setClueFound(found);
            r.setCreatedAt(Instant.now());
            r.setLineCount(manuscript.length);
            repository.save(r);
        }
        return found;
    }

    public Map<String, Object> stats() {
        long yes = repository.countByClueFoundTrue();
        long no = repository.countByClueFoundFalse();
        double ratio = (yes + no == 0) ? 0 : (double) yes / (yes + no);
        return Map.of("count_clue_found", yes, "count_no_clue", no, "ratio", ratio);
    }
}
