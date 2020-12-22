package com.t09.TrackYourFridge.models;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_data")
public class User {

    @Id
    @Column(name = "username", unique = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private String username;

    @Column(name = "pass")
    @NotFound(action = NotFoundAction.IGNORE)
    private String password;

    @Column(name = "email", unique = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private String email;

    @OneToMany(mappedBy = "fridgeUser")
    private Set<Fridge> usersFridges;

    public User() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Fridge> getUsersFridges() {
        return usersFridges;
    }

    public void setUsersFridges(Set<Fridge> usersFridges) {
        this.usersFridges = usersFridges;
    }

}
