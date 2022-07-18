package com.kentoes.caterapi.models.entities.confirmLog;

import com.kentoes.caterapi.models.entities.BaseCreatedAbstract;
import com.kentoes.caterapi.models.entities.enums.EConfirm;
import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.hasilBaca.HasilBaca;
import com.kentoes.caterapi.models.entities.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "confirm_logs")
public class ConfirmLog extends BaseCreatedAbstract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "hasil_baca_id")
    private HasilBaca hasilBaca;
    @ManyToOne
    @JoinColumn(name = "username")
    private User petugas;
    @Enumerated(EnumType.STRING)
    private ERole role;
    @Enumerated(EnumType.STRING)
    private EConfirm confirm;
    private String keterangan;

    public ConfirmLog(Long id, HasilBaca hasilBaca, User petugas, ERole role, EConfirm confirm, String keterangan) {
        this.id = id;
        this.hasilBaca = hasilBaca;
        this.petugas = petugas;
        this.role = role;
        this.confirm = confirm;
        this.keterangan = keterangan;
    }

    public ConfirmLog(LocalDateTime createdAt, LocalDateTime updatedAt, Long id, HasilBaca hasilBaca, User petugas, ERole role, EConfirm confirm, String keterangan) {
        super(createdAt, updatedAt);
        this.id = id;
        this.hasilBaca = hasilBaca;
        this.petugas = petugas;
        this.role = role;
        this.confirm = confirm;
        this.keterangan = keterangan;
    }
}
