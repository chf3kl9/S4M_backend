package com.S4M.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    int id;
    String name;
    String password;
    boolean isAdmin;

    public User(@JsonProperty("id") int id,
                @JsonProperty("name") String name,
                @JsonProperty("password") String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
