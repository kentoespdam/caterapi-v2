package com.kentoes.caterapi.models.entities.customer;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.caterapi.models.entities.mStatSmb.MStatSmbJoin;
import com.kentoes.caterapi.models.entities.zone.ZoneInfo;
import com.kentoes.caterapi.models.entities.zone.ZoneJoin;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "noreg", "nosamw", "nama", "alamat", "jlw", "urjlw", "urjlwp",
        "metL", "zone", "statSmb", "createdAt", "updatedAt"
})
public interface CustomerInfo {

    String getNoreg();

    String getNosamw();

    String getNama();

    String getUrjlwp();

    String getAlamat();

    String getJlw();

    String getUrjlw();

    double getMetL();

    double getRata2();

    ZoneJoin getZone();

    MStatSmbJoin getStatSmb();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
