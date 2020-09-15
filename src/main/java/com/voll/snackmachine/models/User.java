package com.voll.snackmachine.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {

    public User(String id, String name, String email, Company company) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.company = company;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    @Column
    String name;

    @Column
    @NotNull
    String email;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
