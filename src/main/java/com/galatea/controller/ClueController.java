package com.galatea.controller;

import com.galatea.service.ManuscriptService;
import com.galatea.web.ManuscriptRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClueController {

    private final ManuscriptService service;

    public ClueController(ManuscriptService service) {
        this.service = service;
    }

    @PostMapping(path = "/clue", consumes = "application/json")
    public ResponseEntity<String> checkClue(@RequestBody ManuscriptRequest request) {
        boolean found = service.processAndStoreIfNew(request.getManuscript());
        if (found) return ResponseEntity.ok("Clue found");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No clue");
    }

    @GetMapping(path = "/stats", produces = "application/json")
    public ResponseEntity<?> stats() {
        return ResponseEntity.ok(service.stats());
    }
}
