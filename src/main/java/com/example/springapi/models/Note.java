package com.example.springapi.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

@Entity(name="notes")
@EntityListeners(AuditingEntityListener.class)
public class Note {
    @Id
    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private String txt;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

}
