package org.example.model;

import lombok.Data;

@Data
public class CommonResponse<T> {

    private String code = "0";
    private String message = "";
    private T data = null;

    public CommonResponse() {
    }

    public CommonResponse(T data) {
        this.data = data;
    }


}
