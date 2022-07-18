package com.kentoes.caterapi.models.entities.zone;

import com.kentoes.caterapi.models.entities.BaseCreatedAbstract;
import com.kentoes.caterapi.models.entities.cabang.Cabang;
import com.kentoes.caterapi.models.entities.user.User;
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
@Table(name = "zones",
        uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Zone extends BaseCreatedAbstract implements Serializable {
    @Id
    @Size(max = 3)
    private String id;
    @Size(max = 50)
    private String name;
    @Size(max = 100)
    private String address;
    @Column(columnDefinition = "int(11)")
    private Integer target;
    @Column(columnDefinition = "int(11)")
    private Integer terbaca;
    @Column(name = "progress", columnDefinition = "Decimal(3,2)")
    private Float progress;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "satker")
    private Cabang cabang;

    public Zone(String address, Integer target, Integer terbaca, Float progress, User user, Cabang cabang) {
        this.address = address;
        this.target = target;
        this.terbaca = terbaca;
        this.progress = progress;
        this.user = user;
        this.cabang = cabang;
    }

    public Zone(String id, String name, String address, Integer target, Integer terbaca, Float progress, User user, Cabang cabang) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.target = target;
        this.terbaca = terbaca;
        this.progress = progress;
        this.user = user;
        this.cabang = cabang;
    }

    public Zone(LocalDateTime createdAt, LocalDateTime updatedAt, String id, String name, String address, Integer target, Integer terbaca, Float progress, User user, Cabang cabang) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.address = address;
        this.target = target;
        this.terbaca = terbaca;
        this.progress = progress;
        this.user = user;
        this.cabang = cabang;
    }
}
