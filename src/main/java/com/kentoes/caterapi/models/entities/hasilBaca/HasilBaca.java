package com.kentoes.caterapi.models.entities.hasilBaca;

import com.kentoes.caterapi.models.entities.BaseCreatedAbstract;
import com.kentoes.caterapi.models.entities.confirmLog.ConfirmLog;
import com.kentoes.caterapi.models.entities.customer.Customer;
import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.kondisi.Kondisi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "hasil_baca")
public class HasilBaca extends BaseCreatedAbstract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate checkDate;
    @ManyToOne
    @JoinColumn(name = "nosamw", referencedColumnName = "nosamw")
    private Customer customer;
    private Integer periode;
    private double metL;
    private double metK;
    private double pakai;
    private double rata2;
    @ManyToOne
    @JoinColumn(name = "kondisi_id")
    private Kondisi kondisi;
    private String ket;
    private String folder;
    private String foto;
    @Enumerated(EnumType.STRING)
    private ERole posisi;
    @OneToMany(mappedBy = "hasilBaca", orphanRemoval = true)
    private Set<ConfirmLog> confirmLogs = new HashSet<>();

    public void addConfirmLog(ConfirmLog confirmLog) {
        this.confirmLogs.add(confirmLog);
    }

    public void removeConfirmLog(ConfirmLog confirmLog) {
        this.confirmLogs.remove(confirmLog);
    }
}
