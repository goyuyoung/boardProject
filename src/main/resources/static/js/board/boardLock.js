var apiUrl = "/api/board"

$(document).ready( function () {
    initView();
})

var initView = function () {
    $("#header").load("header");
    $("#footer").load("footer");
    document.getElementById("footer").style.position = "fixed";
}

var enterClick = function () {
    if (window.event.keyCode === 13) {
        onClickOkBtn();
    }
}

var onClickOkBtn = function () {
    var boardLockPw = $("#boardLockPw").val();
    var no = $("#no").val();
    var param = {no :no, lockPw: boardLockPw}
    $.get(apiUrl + "/checkLockPw", param,function (res) {
        if(res > 0) {
            // 비밀번호 맞음
            location.href='boardDetail?no='+no;
        } else {
            // 비밀번호 틀림
            alert("비밀번호가 틀렸습니다. 다시 확인해 주세요.")
            $("#boardLockPw").val("");
        }
    });
}