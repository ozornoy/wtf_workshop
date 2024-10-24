package com.example.wtf_workshop.api.requests;

import com.example.wtf_workshop.api.models.BaseModel;

public interface CrudInterface {
    Object create(BaseModel model);
    Object read(String id);
    Object update(String id, BaseModel model);
    Object delete(String id);
}
