package com.kentoes.caterapi.models.entities.customer;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.caterapi.models.entities.BaseCreatedAbstract;
import com.kentoes.caterapi.models.entities.mStatSmb.MStatSmb;
import com.kentoes.caterapi.models.entities.zone.Zone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = "nosamw")})
@JsonPropertyOrder({
        "noreg", "nosamw", "nama", "alamat", "jlw", "urjlw", "urjlwp", "metL", "zone", "statSmb"
})
public class Customer extends BaseCreatedAbstract implements Serializable {
    @Id
    @Size(max = 10)
    private String noreg;
    @Size(max = 7)
    private String nosamw;
    @Size(max = 50)
    private String nama;
	@Size(max = 100)
    private String alamat;
	@Size(max = 2)
    private String jlw;
    @Size(max = 4)
    private String urjlw;
    @Size(max = 50)
    private String urjlwp;
    private double metL;
    private double rata2;
    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;
    @ManyToOne
    @JoinColumn(name = "stat_smb")
    private MStatSmb statSmb;

    public Customer(String noreg, String nosamw, String nama, String urjlwp, String alamat, String jlw, String urjlw, double metL, double rata2, Zone zone, MStatSmb statSmb) {
        this.noreg = noreg;
        this.nosamw = nosamw;
        this.nama = nama;
        this.urjlwp = urjlwp;
        this.alamat = alamat;
        this.jlw = jlw;
        this.urjlw = urjlw;
        this.metL = metL;
        this.rata2 = rata2;
        this.zone = zone;
        this.statSmb = statSmb;
    }

    public Customer(LocalDateTime createdAt, LocalDateTime updatedAt, String noreg, String nosamw, String nama, String urjlwp, String alamat, String jlw, String urjlw, double metL, double rata2, Zone zone, MStatSmb statSmb) {
        super(createdAt, updatedAt);
        this.noreg = noreg;
        this.nosamw = nosamw;
        this.nama = nama;
        this.urjlwp = urjlwp;
        this.alamat = alamat;
        this.jlw = jlw;
        this.urjlw = urjlw;
        this.metL = metL;
        this.rata2 = rata2;
        this.zone = zone;
        this.statSmb = statSmb;
    }
}
