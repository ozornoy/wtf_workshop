package com.example.wtf_workshop.api.enums;

import com.example.wtf_workshop.api.models.BaseModel;
import com.example.wtf_workshop.api.models.BuildType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Endpoint {
    BUILD_TYPES("/app/rest/buildTypes", BuildType.class);

    private final String url;
    private final Class<? extends BaseModel> modelClass;
}
