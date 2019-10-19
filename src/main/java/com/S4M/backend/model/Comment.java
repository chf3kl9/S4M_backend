package com.S4M.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Comment {
    UUID id;
    User user;
    Movie movie;
    String text;


}
