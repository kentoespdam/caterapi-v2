package com.kentoes.caterapi.controllers.zone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZoneSaveReq implements Serializable {
    @NotEmpty(message = "zone ID is required!")
    @Size(max = 3)
    private String id;
    @NotEmpty(message = "zone name is required!")
    @Size(max = 50)
    private String name;
    @Size(max = 100)
    private String address;
    private Integer target = 0;
    private Integer terbaca = 0;
    private Float progress = 0F;
    @NotEmpty(message = "userId is required!")
    private String userId;
    @NotEmpty(message = "satker is required!")
    private String satker;
}
