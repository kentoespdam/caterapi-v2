package com.kentoes.caterapi.controllers;

import com.kentoes.caterapi.controllers.dto.response.ErrorResult;
import com.kentoes.caterapi.controllers.dto.response.PageResult;
import com.kentoes.caterapi.controllers.dto.response.SaveResult;
import com.kentoes.caterapi.controllers.dto.response.SingleResult;

public interface BaseController {
    PageResult pageResult = new PageResult();
    SingleResult singleResult = new SingleResult();
    ErrorResult errorResult = new ErrorResult();
    SaveResult saveResult = new SaveResult();
}
