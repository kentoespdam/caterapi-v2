package com.kentoes.caterapi.config;

import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class PageableBuilder {
    public PageRequest build(PaggingReq req) {
        if (Objects.nonNull(req.getSortBy())) {
            if (req.getSortDir().equals("desc"))
                return PageRequest.of(req.getPos(), req.getLimit(), Sort.by(Sort.Direction.DESC, req.getSortBy()));
            return PageRequest.of(req.getPos(), req.getLimit(), Sort.by(Sort.Direction.ASC, req.getSortBy()));
        }
        return PageRequest.of(req.getPos(), req.getLimit());
    }
}
