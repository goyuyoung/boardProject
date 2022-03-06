package com.board.boardProject.vo;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class ReplyVO {

    private String uuid;

    private int bno;

    private String content;

    private Timestamp createdAt;

    private String createdBy;

    private String writer;

}
