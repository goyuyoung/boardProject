package com.board.boardProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    private String uuid;

    @Column(name = "id")
    private String userId;

    @Column(name = "password")
    private String userPassword;

    private String name;

    private String phone;
}
