var apiUrl = "/api/board"

$(document).ready( function () {
    initView();
    findBoardDetail();
})

var initView = function () {
    console.log("글 번호: " + $("#no").val());
}

var findBoardDetail = function () {
    var no = $("#no").val();
    var param = {no :no}
    $.get(apiUrl + "/findBoardDetail", param,function (res) {
        console.log(res);
        $("#board-title").val(res.title);
        $("#board-writer").val(res.createdBy);
        $("#board-content").val(res.content);
    });
}

var onClickUpdateBtn = function () {
    if ($("#updateBtn").text() == "수정") {
        $("#board-title").attr("readonly",false);
        $("#board-content").attr("readonly",false);
        $("#updateBtn").text("저장");
    } else {
        //게시글 수정
        param = { no: $("#no").val()
                  , title: $("#board-title").val()
                  , content: $("#board-content").val() }
        $.post(apiUrl + "/updateBoard", param,function (res,status) {
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