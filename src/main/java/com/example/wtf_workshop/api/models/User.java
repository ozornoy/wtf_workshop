package com.example.wtf_workshop.api.models;

import com.example.wtf_workshop.api.annotations.Parameterizable;
import com.example.wtf_workshop.api.annotations.Random;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseModel{
    private String id;
    @Random
    private String username;
    @Random
    private String password;
    @Parameterizable
    private Roles roles;
}
