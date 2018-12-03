package com.TYServer.utils;

import com.TYServer.dto.Json;

public class GetJsonObject {
    public static Json returnJson(int i){
        Json json = new Json();
        if(i > 0){
            json.setData("200");
            json.setMsg("success");
            return json;
        }else {
            json.setData("201");
            json.setMsg("failed");
            return json;
        }
    }
}
