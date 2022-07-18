package com.kentoes.caterapi.controllers.statSmb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MStatSmbSaveReq implements Serializable {
    @NotEmpty(message = "statSmb is required!")
    @Size(max = 2)
    private String statSmb;
    @NotEmpty(message = "urstatSmb is required!")
    @Size(max = 100)
    private String urstatSmb;
    @NotEmpty(message = "statAkhir is required!")
    @Size(max = 100)
    private String statAkhir;
    @Size(max = 100)
    private String statSkt = "-";
    private boolean aktivasi = false;
    private boolean jns = false;
}
