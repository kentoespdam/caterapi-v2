package com.kentoes.caterapi.models.entities.munit;

import com.kentoes.caterapi.models.entities.BaseCreatedAbstract;
import com.kentoes.caterapi.models.entities.cabang.Cabang;
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
@Table(name = "munits",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class MUnit extends BaseCreatedAbstract implements Serializable {
    @Id
    @Size(max = 2)
    private String unit;
    @Size(max = 50)
    private String name;
    @Size(max = 100)
    private String address;
    @ManyToOne
    @JoinColumn(name = "satker", columnDefinition = "varchar(4)")
    private Cabang cabang;

    public MUnit(String unit, String name, String address, Cabang cabang) {
        this.unit = unit;
        this.name = name;
        this.address = address;
        this.cabang = cabang;
    }

    public MUnit(LocalDateTime createdAt, LocalDateTime updatedAt, String unit, String name, String address, Cabang cabang) {
        super(createdAt, updatedAt);
        this.unit = unit;
        this.name = name;
        this.address = address;
        this.cabang = cabang;
    }
}
