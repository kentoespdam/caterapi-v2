package com.kentoes.caterapi.models.entities.user;

import com.kentoes.caterapi.models.entities.BaseCreatedAbstract;
import com.kentoes.caterapi.models.entities.cabang.Cabang;
import com.kentoes.caterapi.models.entities.role.Role;
import com.kentoes.caterapi.models.entities.zone.Zone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User extends BaseCreatedAbstract implements Serializable {
    @Id
    @Size(max = 30)
    private String username;

    @Size(max = 100)
    private String fullName;
    @Email
    @Size(max = 50)
    @Column(columnDefinition = "varchar(50) NOT NULL")
    private String email;
    private String address;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "satker")
    private Cabang cabang;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    private boolean enabled;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Zone> zones = new HashSet<>();
}
