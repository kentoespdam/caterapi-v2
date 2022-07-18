package com.kentoes.caterapi.controllers.munit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MUnitReq implements Serializable {
    @NotEmpty(message = "unit cannot be empty")
    @Size(max = 2)
    private String unit;
    @NotEmpty(message = "name cannot be empty")
    @Size(max = 50)
    private String name;
    @Size(max = 100)
    private String address;
    @NotEmpty(message = "satker cannot be empty")
    private String satker;
}
