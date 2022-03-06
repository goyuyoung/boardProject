var apiUrl = "/api/user"

$(document).ready( function () {
    initView();
})

var initView = function () {
    $("#header").load("header");
    $("#footer").load("footer");
    document.getElementById("footer").style.position = "fixed";
}

var onClickLoginBtn = function () {
    var writeId = $("#writeId").val();
    var writePassword = $("#writePassword").val();
    if (writeId == "") {
        alert("아이디를 입력해 주세요.");
    } else if(writePassword == "") {
        alert("비밀번호를 입력해 주세요.");
    } else {
        //로그인 진행
        var param = { userId: writeId, userPassword: writePassword }
        $.post(apiUrl + "/loginUser", param,function (res,status) {
            if (status == "success") {
                if (res > 0) {
                    location.href="boardList";
                } else {
                    alert("아이디와 비밀번호를 확인해 주세요.");
                    $("#writePassword").val("");
                }
            }else{
                alert("로그인이 실패하였습니다. 다시 확인해 주세요.");
            }
        });
    }
}

var enterClick = function () {
    if (window.event.keyCode === 13) {
        onClickLoginBtn();
    }
}