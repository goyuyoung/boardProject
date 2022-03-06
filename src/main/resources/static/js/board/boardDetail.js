var apiBoardUrl = "/api/board"
var apiReplyUrl = "/api/reply"

$(document).ready( function () {
    initView();
    findBoardDetail();
})

var initView = function () {
    $("#header").load("header");
    $("#footer").load("footer");
}

var findBoardDetail = function () {
    var no = $("#no").val();
    var param = {no :no}
    $.get(apiBoardUrl + "/findBoardDetail", param,function (res) {
        $("#board-title").val(res.title);
        $("#board-writer").val(res.writer);
        $("#board-content").val(res.content);
        $("#lockYn").val(res.lockYN);
        findReply(); //댓글 조회
    });
}

var onClickUpdateBtn = function () {
    if ($("#updateBtn").text() == "수정") {
        $("#board-title").attr("readonly",false);
        $("#board-content").attr("readonly",false);
        $("#updateBtn").text("저장");
    } else {
        //게시글 수정
        var param = { no: $("#no").val()
                  , title: $("#board-title").val()
                  , content: $("#board-content").val() }
        $.post(apiBoardUrl + "/updateBoard", param,function (res,status) {
            if (status == "success") {
                alert("게시글 수정이 완료되었습니다.");
                $("#board-title").attr("readonly",true);
                $("#board-content").attr("readonly",true);
                $("#updateBtn").text("수정");
            }else{
                alert("게시글 수정이 실패하였습니다. 다시 확인해 주세요.");
            }
        });

    }
}

var onClickListBtn = function () {
    var lockYn = $("#lockYn").val();
    if (lockYn != 'y') {
        history.go(-1);
    } else {
        history.go(-2);
    }
}

var findReply = function () {
    $("#reply-list").html("");
    var param = {bno: $("#no").val()};
    $.get(apiReplyUrl + "/findReply", param, function (res,status) {
        if (status == "success") {
            for (var idx in res) {
                $("#reply-list").append(setReplyList(res[idx]));
            };
        }else{
            alert("댓글 조회를 실패하였습니다. 다시 확인해 주세요.");
        }
    });
}

var setReplyList = function (res) {
    var createAt = res.createdAt.substring(0,10);
    var data = '<div class="reply-border reply-wrap">' +
        '           <div class="reply-over m-b-10">' +
        '               <div class="reply-left">' +
        '                   <strong>'+ res.writer +'</strong>' +
        '                   <small>'+ createAt +'</small>' +
        '               </div>' +
        '               <div class="reply-right">' +
        '                   <a href="#">삭제</a>' +
        '                   <a href="#">수정</a>' +
        '               </div>' +
        '           </div>' +
        '           <div class="reply-content">' +
        '               <p>'+ res.content +'</p>' +
        '           </div>' +
        '       </div>';
    return data;
}

var onClickReplySave = function () {
    var sessionUuid = $("#sessionUuid").val();
    if(sessionUuid == "") {
        alert("댓글은 로그인 후 이용이 가능합니다.")
    } else {
        var param = { bno: $("#no").val(), createdBy: sessionUuid, content: $("#reply-content").val()}
        $.post(apiReplyUrl + "/saveReply", param, function (res,status) {
            if (status == "success") {
                $("#reply-content").val("");
                findBoardDetail();
            }else{
                alert("댓글 직성이 실패하였습니다. 다시 확인해 주세요.");
            }
        });
    }

}