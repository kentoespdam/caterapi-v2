package com.kentoes.caterapi.models.entities.hasilBaca;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.caterapi.models.entities.confirmLog.ConfirmLogJoin;
import com.kentoes.caterapi.models.entities.customer.CustomerJoin;
import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.kondisi.KondisiJoin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@JsonPropertyOrder({
        "id", "checkDate", "customer", "periode", "metL", "metK", "pakai", "rata2", "kondisi",
        "ket", "folder", "foto", "posisi", "confirmLogs", "createdAt", "updatedAt"
})
public interface HasilBacaInfo {

    Long getId();

    LocalDate getCheckDate();

    CustomerJoin getCustomer();

    Integer getPeriode();

    double getMetL();

    double getMetK();

    double getPakai();

    double getRata2();

    KondisiJoin getKondisi();

    String getKet();

    String getFolder();

    String getFoto();

    ERole getPosisi();

    Set<ConfirmLogJoin> getConfirmLogs();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
