var apiUserUrl = "/api/user"
var apiBoardUrl = "/api/board"

$(document).ready( function () {
    initView();
})

var initView = function () {
    $("#header").load("header");
    $("#footer").load("footer");
}

setTimeout(function () {
    param = {name: $("#sessionName").val()}
    $.get(apiUserUrl + "/myBoardList", param,function (res) {
        console.log(res);
        for (var idx in res) {
            $("#myBoardList-table").append(setMyBoardList(res[idx]));
        };
    });
},300);

var setMyBoardList = function (vo) {
    var createAt = vo.createdAt.substring(0,10);
    if (vo.lockYN != "y") {
        lock = ""
    }
    var data = "<tr>"
        + "   <input type='hidden' id='lockYN-"+ vo.no+"' value='"+ vo.lockYN +"'/>"
        + "   <input type='hidden' id='viewCount-"+ vo.no+"' value='"+ vo.viewCount +"'/>"
        + "   <th class='text-center' scope='row'>"+ vo.no +"</th>"
        + "   <td>"
        + "       <a href='javascript:void(0)' onclick='onclickTitle("+vo.no+");' >"+ vo.title +"</a>"
        + "   </td>"
        + "   <td>"+ vo.createdBy +"</td>"
        + "   <td>"+ createAt +"</td>"
        + "   <td class='text-center'>"+ vo.viewCount +"</td>"
        + "</tr>";
    return data;

}

var onclickTitle = function (no) {
    //조회수 카운트 로직 추가
    var viewCount= $("#viewCount-"+no).val();
    var param = {no: no, viewCount: viewCount};
    $.post(apiBoardUrl + "/updateViewCount", param,function (res,status) {
        var lockYN = $("#lockYN-"+no).val();
        if (lockYN == "y") {
            // 비밀글인 경우
            location.href='boardLock?no='+no;
        } else {
            // 비밀글 아닌 경우
            location.href='boardDetail?no='+no;
        }
    });

}