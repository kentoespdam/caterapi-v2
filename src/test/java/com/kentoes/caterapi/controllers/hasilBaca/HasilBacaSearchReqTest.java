package com.kentoes.caterapi.controllers.hasilBaca;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HasilBacaSearchReqTest {
    @Test
    void skrng() {
        LocalDate skrng = LocalDate.now();
        String tahun = String.valueOf(skrng.getYear());
        String bulan = (skrng.getMonthValue() < 10)
                ? "0" + String.valueOf(skrng.getMonthValue())
                : String.valueOf(skrng.getMonthValue());
        String periode = tahun + bulan;
        System.out.println("tahun : " + tahun);
        System.out.println("bulan : " + bulan);
        System.out.println("periode : " + periode);
        System.out.print("periode integer: ");
        System.out.println(Integer.valueOf(periode));
    }
}