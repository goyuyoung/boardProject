package com.board.boardProject.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "board")
public  class Board {

    @Id
    private Integer no;

    private String title;

    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "lock_pw")
    private String lockPw;

    @Column(name = "lock_yn")
    private String lockYN;

    @Column(name = "view_count")
    private int viewCount;
}
