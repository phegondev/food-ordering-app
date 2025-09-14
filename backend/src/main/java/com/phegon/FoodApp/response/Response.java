package com.phegon.FoodApp.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private int statusCode; // e.g., "200", "404"
    private String message; // Additional information about the response
    private T data; // The actual data payload
    private Map<String, Serializable> meta;

}
