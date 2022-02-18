package com.board.boardProject.vo;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class BoardVO {

    private Integer no;

    private String title;

    private String content;

    private Timestamp createdAt;

    private String createdByName;

    private String createdByUuid;

    private String lockPw;

    private String lockYN;

    private int viewCount;

}
