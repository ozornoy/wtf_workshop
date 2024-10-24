package com.example.wtf_workshop.api.models;

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
public class Step extends BaseModel {
    private String id;
    @Random
    private String name;
    @Builder.Default
    private String type = "simpleRunner";
    private Properties properties;
}
