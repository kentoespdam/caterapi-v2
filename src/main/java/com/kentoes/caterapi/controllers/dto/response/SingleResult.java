package com.kentoes.caterapi.controllers.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
public class SingleResult<T> {
    private int code;
    private HttpStatus status;
    private T data;

    public SingleResult(int code, HttpStatus status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public ResponseEntity<?> build(T data) {
        HttpStatus httpStatus = HttpStatus.OK;
        if (data == null)
            httpStatus = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(httpStatus).body(
                new SingleResult(
                        httpStatus.value(),
                        httpStatus,
                        data
                )
        );
    }
}
