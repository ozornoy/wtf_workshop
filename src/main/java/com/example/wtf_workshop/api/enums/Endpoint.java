package com.example.wtf_workshop.api.enums;

import com.example.wtf_workshop.api.models.BaseModel;
import com.example.wtf_workshop.api.models.BuildType;
import com.example.wtf_workshop.api.models.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Endpoint {
    BUILD_TYPES("/app/rest/buildTypes", BuildType.class),
    PROJECT("/app/rest/projects",Project .class);

    private final String url;
    private final Class<? extends BaseModel> modelClass;
}
