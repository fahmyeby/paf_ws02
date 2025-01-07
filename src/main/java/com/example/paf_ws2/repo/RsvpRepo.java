package com.example.paf_ws2.repo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.example.paf_ws2.model.Rsvp;

@Repository
public class RsvpRepo {
    @Autowired JdbcTemplate template;

    // sql queries
    private static final String getAllRsvp = "select * from rsvp";
    private static final String getRsvpById = "select * from rsvp where name like ?";
    private static final String addRsvp = "insert into rsvp (name, email, phone, confirmDate, comments) values (?, ?, ?, ?, ?)";
    private static final String updateRsvp = "update rsvp set name = ?, phone = ?, confirmDate = ?, comments = ? where email = ?";
    private static final String countRsvp = "select count(*) as total from rsvp";

    // get all rsvp
    public List<Rsvp> getAllRsvp(){
        SqlRowSet rs = template.queryForRowSet(getAllRsvp);
        List<Rsvp> rsvps = new LinkedList<>();
        while(rs.next()){
            rsvps.add(Rsvp.toRsvp(rs));
        } return rsvps;
    }

    // get by name
    public Optional<Rsvp> getByName(String name){
        SqlRowSet rs = template.queryForRowSet(getRsvpById, "%%%s%%".formatted(name));
        if(rs.next()){
            return Optional.of(Rsvp.toRsvp(rs));
        } return Optional.empty();
    }

    // add rsvp
    public Boolean addRsvp(Rsvp rsvp){
        int added = template.update(
            addRsvp,
            rsvp.getName(),
            rsvp.getEmail(),
            rsvp.getPhone(),
            rsvp.getConfirmDate(),
            rsvp.getComments());
            return added > 0;
    }

    // update rsvp
    public Boolean updateRsvp(String email, Rsvp rsvp){
        int updated = template.update(
            updateRsvp, 
            rsvp.getName(),
            rsvp.getPhone(),
            rsvp.getConfirmDate(),
            rsvp.getComments(),
            email);
            return updated > 0;
    }

    // count rsvps
    public int countRsvp(){
        SqlRowSet rs = template.queryForRowSet(countRsvp);
        if(rs.next()){
            return rs.getInt("total");
        } return 0;
    }
}
