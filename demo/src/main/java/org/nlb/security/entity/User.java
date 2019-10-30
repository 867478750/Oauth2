package org.nlb.security.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotBlank;

public class User {
    private int id;
    @NotBlank
    private String password;

    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{};
    @JsonView(UserSimpleView.class)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
