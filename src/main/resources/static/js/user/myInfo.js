var apiUrl = "/api/user"

$(document).ready( function () {
    initView();
})

setTimeout(function () {
    findMyInfo();
},300);

var initView = function () {
    $("#header").load("header");
    $("#footer").load("footer");
}

var findMyInfo = function () {
    var userUuid = $("#sessionUuid").val();
    var param = {uuid :userUuid}
    $.get(apiUrl + "/findMyInfo", param,function (res) {
        $("#userId").val(res.userId);
        $("#userName").val(res.name);
        $("#phone").val(res.phone);
    });
}

var checkPassword = function () {
    var userPassword = $("#userPassword").val();
    var checkUserPassword = $("#checkUserPassword").val();
    if( userPassword == checkUserPassword) {
        $("#checkUserPassword").css("border","2px solid blue");
    } else {
        $("#checkUserPassword").css("border","2px solid red");
    }
}

var onClickMyInfoSaveBtn = function () {
    var userUuid = $("#sessionUuid").val()
    var userId = $("#userId").val();
    var name = $("#userName").val();
    var phone = $("#phone").val();
    var userPassword = $("#userPassword").val();
    if (name == "") {
        alert("이름 입력란을 다시 확인해주세요.");
    } else if(phone == "") {
        alert("휴대전화번호 입력란을 다시 확인해주세요.");
    } else if(userPassword == "") {
        alert("비밀번호 입력란을 다시 확인해주세요.");
    } else if ($("#checkUserPassword").css("border-color") != "rgb(0, 0, 255)") {
        alert("비밀번호 확인란을 다시 확인해주세요.")
    } else {
        var param = {uuid: userUuid, userId: userId, name: name, phone: phone, userPassword: userPassword};
        updateMyInfo(param);
    }
}
var updateMyInfo = function (param) {
    $.post(apiUrl + "/updateMyInfo", param,function (res) {
        alert("정보 수정이 완료되었습니다.");
        location.href='myInfo';
    });
}