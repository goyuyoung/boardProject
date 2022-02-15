package com.board.boardProject.vo;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class BoardVO {

    private String uuid;

    private int no;

    private String title;

    private String content;

    private Timestamp createdAt;

    private String createdBy;

    private String lockPw;

    private String lockYN;

    private int viewCount;

}
