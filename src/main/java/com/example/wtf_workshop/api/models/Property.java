package com.example.wtf_workshop.api.models;

import com.example.wtf_workshop.api.annotations.Parameterizable;
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
public class Property extends BaseModel {
    @Parameterizable
    private String name;
    @Parameterizable
    private String value;
}