package com.example.wtf_workshop.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.example.wtf_workshop.api.annotations.Parameterizable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildTypeId extends BaseModel {
    @Parameterizable
    private String id;
}
