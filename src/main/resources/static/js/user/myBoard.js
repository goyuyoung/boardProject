var apiUrl = "/api/user"

$(document).ready( function () {
    initView();
    findMyBordList();
})

var initView = function () {
    $("#header").load("header");
    $("#footer").load("footer");
}

setTimeout(function () {
    param = {name: $("#sessionName").val()}
    $.get(apiUrl + "/myBoardList", param,function (res) {
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