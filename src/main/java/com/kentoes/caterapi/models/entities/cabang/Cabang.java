package com.kentoes.caterapi.models.entities.cabang;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.caterapi.models.entities.BaseCreatedAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cabangs", uniqueConstraints = {
        @UniqueConstraint(columnNames = "satker"),
        @UniqueConstraint(columnNames = "name")})
@JsonPropertyOrder({"satker", "name", "address", "createdAt", "updatedAt"})
public class Cabang extends BaseCreatedAbstract implements Serializable {
    @Id
    @Size(max = 4)
    private String satker;
    @Size(max = 50)
    private String name;
    @Size(max = 100)
    private String address;

    public Cabang(String satker, String name, String address) {
        this.satker = satker;
        this.name = name;
        this.address = address;
    }

    public Cabang(LocalDateTime createdAt, LocalDateTime updatedAt, String satker, String name, String address) {
        super(createdAt, updatedAt);
        this.satker = satker;
        this.name = name;
        this.address = address;
    }
}
