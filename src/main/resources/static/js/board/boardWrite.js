var apiUrl = "/api/board"

$(document).ready( function () {
    initView();
})

var initView = function () {
    $("#header").load("header");
    $("#footer").load("footer");
}

setTimeout(function () {
    var writer = $("#sessionName").val();
    if (writer == undefined || writer == null || writer == "") {
        writer = "비회원";
    }
    $("#board-writer").val(writer);
},300);

var onClickSaveBtn = function () {
    var title = $("#board-title").val();
    var content = $("#board-content").val();
    var lockYN = "y";
    var lockPw = $("#board-lockPw").val();
    var createdBy = $("#board-writer").val();

    if (title == "" || content == "") {
        alert("항목을 모두 입력해주세요.");
        return;
    }
    if (document.getElementsByName("board-lock")[0].checked != true) {
        lockYN = "n";
        lockPw = null;
    } else {
        if(lockPw == "") {
            alert("비밀번호를 입력해 주세요.");
            return;
        }
    }
    var param = {title: title, content: content, lockYN: lockYN, lockPw: lockPw, createdBy: createdBy}
    $.post(apiUrl + "/saveBoard", param,function (res,status) {
        if (status == "success") {
            alert("게시글 작성이 완료되었습니다.");
            var sessionId = $("#sessionId").val();
            if (sessionId != ""){
                location.href='myBoard';
            } else {
                location.href='boardList';
            }

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

var onClickListBtn = function () {
    var sessionId = $("#sessionId").val();
    if (sessionId != ""){
        location.href='myBoard';
    } else {
        location.href='boardList';
    }
}