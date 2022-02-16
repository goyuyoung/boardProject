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
    var param = {title: title, content: content}
    // $.get(apiUrl + "/saveBoard", param,function (res) {
    //     console.log(res);
    // });
}

var onClickLockYn = function () {
    console.log("sds");
    if (document.getElementsByName("board-lock")[0].checked == true) {
        $("#lockYN").css("display","flex");
    } else {
        $("#lockYN").css("display","none");
    }
}