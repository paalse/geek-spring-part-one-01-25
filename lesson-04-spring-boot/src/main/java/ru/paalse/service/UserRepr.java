package ru.paalse.service;

import ru.paalse.persist.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserRepr {

    private Long id;

    @NotEmpty
    private String username;

    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String matchingPassword;

    public UserRepr() { }

    public UserRepr(String username) {
        this.username = username;
    }

    public UserRepr(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
