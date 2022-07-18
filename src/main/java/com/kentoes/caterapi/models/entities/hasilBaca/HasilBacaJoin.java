package com.kentoes.caterapi.models.entities.hasilBaca;

import com.kentoes.caterapi.models.entities.customer.CustomerJoin;
import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.kondisi.KondisiJoin;

import java.time.LocalDate;

public interface HasilBacaJoin {
    Long getId();

    LocalDate getCheckDate();

    Integer getPeriode();

    double getMetL();

    double getMetK();

    double getPakai();

    double getRata2();

    String getKet();

    String getFolder();

    String getFoto();

    ERole getPosisi();

    CustomerJoin getCustomer();

    KondisiJoin getKondisi();
}
