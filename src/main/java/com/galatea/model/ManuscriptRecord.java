package com.galatea.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "manuscripts", indexes = {
        @Index(name = "idx_hash", columnList = "manuscriptHash", unique = true)
})
public class ManuscriptRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manuscriptHash;
    private boolean clueFound;
    private Instant createdAt;
    private int lineCount;

    public static String hashManuscript(String[] manuscript) {
        return String.join("\n", manuscript);
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getManuscriptHash() { return manuscriptHash; }
    public void setManuscriptHash(String manuscriptHash) { this.manuscriptHash = manuscriptHash; }
    public boolean isClueFound() { return clueFound; }
    public void setClueFound(boolean clueFound) { this.clueFound = clueFound; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public int getLineCount() { return lineCount; }
    public void setLineCount(int lineCount) { this.lineCount = lineCount; }
}
