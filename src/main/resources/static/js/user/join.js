var apiUrl = "/api/user"

$(document).ready( function () {
    initView();
})

var initView = function () {
    $("#header").load("header");
    $("#footer").load("footer");
}

var onClickId = function () {
    $("#searchIdModal").modal("show");
    $("#resultId").css("display","none");
    $("#searchId").val("");
}

var onClickIdBtn = function () {
    var userId = $("#searchId").val();
    var IdLength = userId.length;
    if(IdLength > 3 && IdLength < 17) {
        param = { userId: userId }
        $.get(apiUrl + "/checkId", param,function (res,status) {
            if (status == "success") {
                $("#inputId").text(userId);
                $("#resultId").css("display","block");
                if (res < 1) {
                    $("#okId").css("display","inline");
                    $("#noId").css("display","none");
                } else {
                    $("#okId").css("display","none");
                    $("#noId").css("display","inline");
                }
            }else{
                $("#resultId").css("display","none");
            }
        });
    }
}

var onClickUseBtn = function () {
    if ($("#resultId").css("display") != "none") {
        if ($("#okId").css("display") == "inline") {
            $("#userId").val($("#inputId").text());
            $("#userId").attr("readonly",true)
            $("#searchIdModal").modal("hide");

        } else {
            alert("입력하신 아이디를 확인해 주세요.");
        }
    } else {
        alert("아이디 중복확인을 진행해 주세요.");
    }
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

var onClickJoinBtn = function () {
    var userId = $("#userId").val();
    var name = $("#name").val();
    var phone = $("#phone").val();
    var userPassword = $("#userPassword").val();

    if(userId == "") {
        alert("아이디 입력란을 다시 확인해주세요.")
    } else if(name == "") {
        alert("이름 입력란을 다시 확인해주세요.")
    } else if(phone == "") {
        alert("휴대전화번호 입력란을 다시 확인해주세요.")
    } else if(userPassword == "") {
        alert("비밀번호 입력란을 다시 확인해주세요.")
    } else if ($("#checkUserPassword").css("border-color") != "rgb(0, 0, 255)") {
        alert("비밀번호 확인란을 다시 확인해주세요.")
    } else {
        //회원가입 진행
        param = { userId: userId, name: name, phone: phone, userPassword: userPassword }
        $.post(apiUrl + "/joinUser", param,function (res,status) {
            if (status == "success") {
                alert("축하합니다. 회원가입이 완료되었습니다.")
                location.href="login";
            }else{
                alert("회원가입이 실패하였습니다. 다시 확인해 주세요.");
            }
        });
    }
}
