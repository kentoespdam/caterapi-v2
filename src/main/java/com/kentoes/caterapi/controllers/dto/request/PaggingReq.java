package com.kentoes.caterapi.controllers.dto.request;

import lombok.Data;

@Data
public class PaggingReq {
    private int pos = 0;
    private int limit = 10;
    private String sortBy;
    private String sortDir = "asc";
    private String search;
}
