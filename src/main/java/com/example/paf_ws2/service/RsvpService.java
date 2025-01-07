package com.example.paf_ws2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paf_ws2.model.Rsvp;
import com.example.paf_ws2.repo.RsvpRepo;

@Service
public class RsvpService {
    @Autowired
    RsvpRepo rsvpRepo;

    // get all
    public List<Rsvp> getAllRsvp() {
        return rsvpRepo.getAllRsvp();
    }

    // get by name
    public Optional<Rsvp> getRsvpByName(String name) {
        return rsvpRepo.getByName(name);
    }

    // add rsvp
    public Boolean addRsvp(Rsvp rsvp) {
        return rsvpRepo.addRsvp(rsvp);
    }

    // update rsvp
    public Boolean updateRsvp(String email, Rsvp rsvp) {
        return rsvpRepo.updateRsvp(email, rsvp);
    }

    // count rsvp
    public int countRsvps() {
        return rsvpRepo.countRsvp();
    }
}
