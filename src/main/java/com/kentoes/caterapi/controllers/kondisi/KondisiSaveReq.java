package com.kentoes.caterapi.controllers.kondisi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KondisiSaveReq implements Serializable {
    private Long id;
    @NotEmpty(message = "kode is required!")
    @Size(max = 4)
    private String kode;
    @NotEmpty(message = "kondisi is required!")
    private String kondisi;
}
