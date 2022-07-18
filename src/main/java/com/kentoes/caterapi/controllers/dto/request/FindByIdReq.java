package com.kentoes.caterapi.controllers.dto.request;

import lombok.Data;

@Data
public class FindByIdReq {
    private String id;

    public Long getLongId() {
        return Long.valueOf(id);
    }

    public Integer getIntegerId() {
        return Integer.valueOf(id);
    }
}
