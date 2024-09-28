package com.example.wtf_workshop.api.requests;

import com.example.wtf_workshop.api.enums.Endpoint;
import io.restassured.specification.RequestSpecification;

public class Request {
    /**
     * Request - это класс, описывающий меняющиеся параметры запроса, такие как
     *  спецификация, ендпоинт(relative URL, model)
     */
    private final RequestSpecification specification;
    private final Endpoint endpoint;

    public Request(RequestSpecification specification, Endpoint endpoint) {
        this.specification = specification;
        this.endpoint = endpoint;
    }
}
