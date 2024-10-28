package com.example.wtf_workshop.api.requests.unchecked;

import com.example.wtf_workshop.api.enums.Endpoint;
import com.example.wtf_workshop.api.models.BaseModel;
import com.example.wtf_workshop.api.requests.CrudInterface;
import com.example.wtf_workshop.api.requests.ManageBuildsInterface;
import com.example.wtf_workshop.api.requests.Request;
import com.example.wtf_workshop.api.requests.SearchInterface;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UncheckedBase extends Request implements CrudInterface, ManageBuildsInterface, SearchInterface {
    public UncheckedBase(RequestSpecification spec, Endpoint endpoint) {
        super(spec, endpoint);
    }

    @Override
    public Response create(BaseModel model) {
        return RestAssured.given().spec(spec).body(model).post(endpoint.getUrl());
    }

    @Override
    public Response read(String locator) {
        return RestAssured.given().spec(spec).get(endpoint.getUrl() + "/" + locator);
    }

    @Override
    public Response update(String locator, BaseModel model) {
        return RestAssured.given().spec(spec).body(model).put(endpoint.getUrl() + "/" + locator);
    }

    @Override
    public Response delete(String locator) {
        return RestAssured.given().spec(spec).delete(endpoint.getUrl() + "/" + locator);
    }

    @Override
    public Response startBuild(BaseModel model) {
        return RestAssured.given().spec(spec).post(endpoint.getUrl());
    }

    @Override
    public Response search(String locator) {
        return RestAssured.given().spec(spec).get(endpoint.getUrl() + "?locator=" + locator);
    }
}
