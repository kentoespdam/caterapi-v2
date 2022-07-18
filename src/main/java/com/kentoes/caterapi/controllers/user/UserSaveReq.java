package com.kentoes.caterapi.controllers.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveReq implements Serializable {
    @NotEmpty(message = "username is required!")
    @Size(max = 30)
    private String username;
    @NotEmpty(message = "fullName is required!")
    @Size(max = 100)
    private String fullName;
    @Email(message = "email not valid!")
    @NotEmpty(message = "email is required!")
    @Size(max = 50)
    private String email;
    private String address;
    private String phone;
    @NotEmpty(message = "password is required!")
    @Size(min = 6, message = "min password length is 6 character")
    private String password;
    @NotEmpty(message = "satker is required!")
    private String satker;
    @NotEmpty(message = "role is required!")
    private String role;
    private boolean enabled;
}
