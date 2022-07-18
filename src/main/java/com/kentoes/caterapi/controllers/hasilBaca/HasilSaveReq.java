package com.kentoes.caterapi.controllers.hasilBaca;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HasilSaveReq {
    @NotNull(message = "checkDate is required!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String tgl;
    @NotEmpty(message = "nosamw is required!")
    @Size(max = 7)
    private String nosamw;
    @NotNull(message = "periode is required")
    private Integer periode;
    @NotNull(message = "metL is required")
    private double metL;
    @NotNull(message = "metK is required")
    private double metK;
    @NotNull(message = "pakai is required")
    private double pakai;
    @NotNull(message = "rata2 is required")
    private double rata2;
    @NotEmpty(message = "kondisiKode is required!")
    @Size(max = 4)
    private String kondisiKode;
    private String ket;
    @JsonIgnore
    private MultipartFile img;
}
