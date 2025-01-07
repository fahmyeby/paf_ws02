package com.example.paf_ws2.model;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Rsvp {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private Date confirmDate;
    private String comments;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getConfirmDate() {
        return confirmDate;
    }
    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    // helper method for parsing
    public static Rsvp toRsvp(SqlRowSet rs){
        Rsvp r = new Rsvp();
        r.setId(rs.getInt("id"));
        r.setName(rs.getString("name"));
        r.setEmail(rs.getString("email"));
        r.setPhone(rs.getString("phone"));
        r.setConfirmDate(rs.getDate("confirmDate"));
        r.setComments(rs.getString("comments"));
        return r;
    }
}
