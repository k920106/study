package com.spring.www.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString(exclude = {"userRoles"})
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
//public class Account {
public class Account implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    //private String age;
    private int age;

    //private String role;
    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(name = "account_roles"
             , joinColumns = {@JoinColumn(name = "account_id")}
             , inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> userRoles = new HashSet<>();
}
