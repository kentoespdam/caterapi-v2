package com.kentoes.caterapi.controllers.customer;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ApiIgnore
@JsonPropertyOrder({"noreg", "nosamw", "nama", "alamat", "jlw", "urjlw", "urjlwp", "metL", "rata2", "statSmbKode", "zoneKode"})
@Data
public class CustomerSaveReq implements Serializable {
    @NotEmpty(message = "noreg is required!")
    @Size(max = 10)
    private String noreg;
    @Size(max = 7)
    @NotEmpty(message = "nosamw is required!")
    private String nosamw;
    @Size(max = 50)
    @NotEmpty(message = "nama is required!")
    private String nama;
    @Size(max = 100)
    private String alamat;
    @Size(max = 2)
    @NotEmpty(message = "jlw is required!")
    private String jlw;
    @Size(max = 4)
    @NotEmpty(message = "urjlw is required!")
    private String urjlw;
    @Size(max = 50)
    @NotEmpty(message = "urjlwp is required!")
    private String urjlwp;
    private double metL;
    private double rata2;
    @NotEmpty(message = "zoneId is required!")
    private String zoneId;
    @NotEmpty(message = "statSmbId is required!")
    private String statSmbId;
}
