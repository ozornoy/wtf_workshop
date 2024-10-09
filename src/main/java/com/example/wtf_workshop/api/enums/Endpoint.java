package com.example.wtf_workshop.api.enums;

import com.example.wtf_workshop.api.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Endpoint {
    BUILDS("/app/rest/builds", Builds.class),
    BUILD_TYPES("/app/rest/buildTypes", BuildType.class),
    BUILD_QUEUE("/app/rest/buildQueue", BuildQueue.class),
    PROJECTS("/app/rest/projects", Project .class),
    USERS("/app/rest/users", User.class);

    private final String url;
    private final Class<? extends BaseModel> modelClass;
}
