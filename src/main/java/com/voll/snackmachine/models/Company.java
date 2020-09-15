package com.voll.snackmachine.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@NoArgsConstructor
public class Company {

    public Company(String id, String name, String currency, Float budgetPerUser, List<User> users) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.budgetPerUser = budgetPerUser;
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    @Column
    String name;

    @Column
    Float budgetPerUser;

    @Column
    private String currency;

    @JsonManagedReference
    @OneToMany(mappedBy = "company", targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<User>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getBudgetPerUser() {
        return budgetPerUser;
    }

    public void setBudgetPerUser(Float budgetPerUser) {
        this.budgetPerUser = budgetPerUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
