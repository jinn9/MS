package com.github.jinn9.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member extends BaseEntity {

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;

    // TODO: hash password
    private String password;

}
