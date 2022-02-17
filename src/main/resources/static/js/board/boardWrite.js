var apiUrl = "/api/board"

$(document).ready( function () {
    initView();
})

var initView = function () {

}

var onClickSaveBtn = function () {
    var title = $("#board-title").val();
    var content = $("#board-content").val();
    var lockYN = "y";
    var lockPw = $("#board-lockPw").val();
    if (document.getElementsByName("board-lock")[0].checked != true) {
        lockYN = "n";
        lockPw = null;
    }
    var param = {title: title, content: content, lockYN: lockYN, lockPw: lockPw}
    $.post(apiUrl + "/saveBoard", param,function (res,status) {
        console.log(res);
        console.log(status)
        if (status == "success") {
            alert("게시글 작성이 완료되었습니다.");
            location.href='boardList';
        }else {
            alert("게시글 작성이 실패하였습니다. 다시 확인해 주세요.");
        }
    });
}

var onClickLockYn = function () {
    if (document.getElementsByName("board-lock")[0].checked == true) {
        $("#lockYN").css("display","flex");
    } else {
        $("#lockYN").css("display","none");
    }
}