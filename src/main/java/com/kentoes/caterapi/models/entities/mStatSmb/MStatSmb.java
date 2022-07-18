package com.kentoes.caterapi.models.entities.mStatSmb;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.caterapi.models.entities.BaseCreatedAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mstat_smb")
@JsonPropertyOrder({"statSmb", "urstatSmb", "statAkhir", "statSkt", "aktivasi", "jns"})
public class MStatSmb extends BaseCreatedAbstract implements Serializable {
    @Id
    @Column(name = "stat_smb")
    @Size(max = 2)
    private String statSmb;
    @Size(max = 100)
    private String urstatSmb;
    @Size(max = 100)
    private String statAkhir;
    @Size(max = 100)
    private String statSkt;
    private boolean aktivasi = false;
    private boolean jns = false;

    public MStatSmb(String statSmb, String urstatSmb, String statAkhir, String statSkt, boolean aktivasi, boolean jns) {
        this.statSmb = statSmb;
        this.urstatSmb = urstatSmb;
        this.statAkhir = statAkhir;
        this.statSkt = statSkt;
        this.aktivasi = aktivasi;
        this.jns = jns;
    }

    public MStatSmb(LocalDateTime createdAt, LocalDateTime updatedAt, String statSmb, String urstatSmb, String statAkhir, String statSkt, boolean aktivasi, boolean jns) {
        super(createdAt, updatedAt);
        this.statSmb = statSmb;
        this.urstatSmb = urstatSmb;
        this.statAkhir = statAkhir;
        this.statSkt = statSkt;
        this.aktivasi = aktivasi;
        this.jns = jns;
    }
}
