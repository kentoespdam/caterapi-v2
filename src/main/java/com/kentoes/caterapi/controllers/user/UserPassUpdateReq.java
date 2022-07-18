package com.kentoes.caterapi.controllers.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserPassUpdateReq implements Serializable {
    @NotEmpty(message = "username is required!")
    @Size(max = 30)
    private final String username;
    @NotEmpty(message = "password is required!")
    @Size(min = 6, message = "min password length is 6 character")
    private final String password;
}
