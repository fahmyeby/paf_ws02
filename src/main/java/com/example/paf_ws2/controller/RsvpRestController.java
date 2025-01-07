package com.example.paf_ws2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.paf_ws2.model.Rsvp;
import com.example.paf_ws2.service.RsvpService;

@RestController
@RequestMapping("/api")
public class RsvpRestController {
    @Autowired
    RsvpService rsvpService;

    // get all
    @GetMapping("/rsvps")
    public ResponseEntity<List<Rsvp>> getAllRsvp() {
        List<Rsvp> rsvps = rsvpService.getAllRsvp();
        if (rsvps.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok().body(rsvps);
    }

    // get by name
    @GetMapping("/rsvp")
    public ResponseEntity<?> getRsvpByName(@RequestParam("q") String name) {
        Optional<Rsvp> rsvp = rsvpService.getRsvpByName(name);
        if (rsvp.isPresent()) {
            return ResponseEntity.ok(rsvp.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No RSVP found with matching name: " + name);
        }
    }

    @PostMapping("/rsvp")
    public ResponseEntity<?> addRsvp(@RequestBody Rsvp rsvp){
        Boolean added = rsvpService.addRsvp(rsvp);
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).body("RSVP added successfully");
        } return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add RSVP");
    }

    @PutMapping("/rsvp/{email}")
    public ResponseEntity<?> updateRsvp(@PathVariable String email, @RequestBody Rsvp rsvp){
        Boolean updated = rsvpService.updateRsvp(email, rsvp);
        if (updated){
            return ResponseEntity.status(HttpStatus.OK).body("RSVP updated successfully");
        } return ResponseEntity.status(HttpStatus.NOT_FOUND).body("RSVP not found with email: " + email);
    }

    @GetMapping("/rsvps/count")
    public ResponseEntity<?> countRsvps(){
        int count = rsvpService.countRsvps();
        if (count > 0){
            return ResponseEntity.ok().body("Total RSVP count: " + count);
        } return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No RSVPs found");
    }
}
