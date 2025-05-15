package com.github.jinn9.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;

    @Embedded
    private Address address;

    public Member(String email, Address address) {
        this.email = email;
        this.address = address;
    }
}
