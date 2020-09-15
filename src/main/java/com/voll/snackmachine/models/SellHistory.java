package com.voll.snackmachine.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sellHistory")
@NoArgsConstructor
public class SellHistory {
    public SellHistory(String id, User user, LocalDate dateWhen, Float value) {
        this.id = id;
        this.user = user;
        this.dateWhen = dateWhen;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    @ManyToOne
    @JoinColumn(name="user_id")
    public User user;

    @Column
    LocalDate dateWhen;

    @Column
    Float value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateWhen() {
        return dateWhen;
    }

    public void setDateWhen(LocalDate dateWhen) {
        this.dateWhen = dateWhen;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
