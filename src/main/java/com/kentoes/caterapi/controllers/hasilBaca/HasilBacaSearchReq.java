package com.kentoes.caterapi.controllers.hasilBaca;

import com.kentoes.caterapi.models.entities.enums.EConfirm;
import com.kentoes.caterapi.models.entities.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HasilBacaSearchReq implements Serializable {
    private LocalDate fromDate = defaultFromDate();
    private LocalDate toDate = LocalDate.now();
    private Integer periode = periodeKini();
    private String nosamw;
    private String nama;
    private String kondisiKode;
    private String kondisi;
    private ERole posisi;
    private String username;
    private ERole role;
    private EConfirm confirm;

    protected LocalDate defaultFromDate() {
        LocalDate skrng = LocalDate.now();
        int tahun = skrng.getYear();
        int bulan = skrng.getMonthValue();
        int tanggal = 1;
        return LocalDate.of(tahun, bulan, tanggal);
    }

    protected Integer periodeKini() {
        LocalDate skrng = LocalDate.now();
        String tahun = String.valueOf(skrng.getYear());
        String bulan = (skrng.getMonthValue() < 10)
                ? "0" + String.valueOf(skrng.getMonthValue())
                : String.valueOf(skrng.getMonthValue());
        String periode = tahun + bulan;
        return Integer.valueOf(periode);
    }
}
