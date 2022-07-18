package com.kentoes.caterapi.models.entities.kondisi;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.caterapi.models.entities.BaseCreatedAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mkondisi", uniqueConstraints = {@UniqueConstraint(columnNames = "kode")})
@JsonPropertyOrder({"id", "kode", "kondisi", "createdAt", "updatedAt"})
public class Kondisi extends BaseCreatedAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 4)
    private String kode;
    private String kondisi;

    public Kondisi(Long id, String kode, String kondisi) {
        this.id = id;
        this.kode = kode;
        this.kondisi = kondisi;
    }

    public Kondisi(LocalDateTime createdAt, LocalDateTime updatedAt, Long id, String kode, String kondisi) {
        super(createdAt, updatedAt);
        this.id = id;
        this.kode = kode;
        this.kondisi = kondisi;
    }
}
