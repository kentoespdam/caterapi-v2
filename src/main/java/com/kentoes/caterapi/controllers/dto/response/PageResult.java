package com.kentoes.caterapi.controllers.dto.response;

import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class PageResult {
    private Integer code;
    private HttpStatus status;
    private List data;
    private Integer page;
    private Integer limit;
    private Integer totalPages;
    private Long totalData;
    private String firstPage;
    private String prevPage;
    private String nextPage;
    private String lastPage;

    public PageResult(HttpServletRequest request, HttpStatus status, Page result, PaggingReq paggingReq) {
        this.code = status.value();
        this.status = status;
        if (result.getContent().size() == 0) return;
        this.data = result.getContent();
        this.page = result.getNumber();
        this.limit = result.getSize();
        this.totalPages = result.getTotalPages();
        this.totalData = result.getTotalElements();
        this.uriPageBuilder(request, result, paggingReq);
    }

    private void uriPageBuilder(HttpServletRequest request, Page result, PaggingReq paggingReq) {
        String sortString = "";
        String searchString = "";
        if (Objects.nonNull(paggingReq.getSortBy())) {
            sortString = "&sortBy=" + paggingReq.getSortBy() + "&sortDir=" + paggingReq.getSortDir();
        }
        if (Objects.nonNull(paggingReq.getSearch()))
            searchString = "&search=" + paggingReq.getSearch();
        String url = request.getRequestURL().toString();
        String posString = "?pos=";
        String limitString = "&limit=" + result.getSize() + sortString + searchString;

        String nextString, prevString = null;
        if (!result.isFirst() && !result.isLast()) {
            nextString = String.valueOf(result.getNumber() + 1);
            prevString = String.valueOf(result.getNumber() - 1);
            this.firstPage = url + posString + "0" + limitString;
            this.nextPage = url + posString + nextString + limitString;
            this.prevPage = url + posString + prevString + limitString;
            this.lastPage = url + posString + String.valueOf(result.getTotalPages() - 1) + limitString;
        }
        if (result.isFirst() && !result.isLast()) {
            nextString = String.valueOf(result.getNumber() + 1);
            this.nextPage = url + posString + nextString + limitString;
            this.lastPage = url + posString + String.valueOf(result.getTotalPages() - 1) + limitString;
        }
        if (!result.isFirst() && result.isLast()) {
            prevString = String.valueOf(result.getNumber() - 1);
            this.firstPage = url + posString + "0" + limitString;
            this.prevPage = url + posString + prevString + limitString;
        }
    }

    public ResponseEntity<?> build(HttpServletRequest request, Page result, PaggingReq paggingReq) {
        HttpStatus status = HttpStatus.OK;
        if (result.getContent().size() == 0) status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new PageResult(request, status, result, paggingReq));
    }
}
