package com.kentoes.caterapi.controllers.hasilBaca;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kentoes.caterapi.models.entities.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HasilBacaUpdateReq implements Serializable {
    @NotNull(message = "id is required!")
    private Long id;
    @NotNull(message = "checkDate is required!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String tgl;
    @NotNull(message = "periode is required!")
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
    private MultipartFile foto;
    private ERole posisi;
}
