package com.example.wtf_workshop.api.models;

import com.example.wtf_workshop.api.annotations.Optional;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties extends BaseModel {
    private Integer count;
    @Optional
    private List<Property> property;
}