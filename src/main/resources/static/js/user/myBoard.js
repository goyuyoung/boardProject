var apiUserUrl = "/api/user"
var apiBoardUrl = "/api/board"
var myBoardListPage = {page : 0, size: 10};

$(document).ready( function () {
    initView();
})

var initView = function () {
    $("#header").load("header");
    $("#footer").load("footer");
}

setTimeout(function () {
    findMyBoardList(0);
},300);

var findMyBoardList = function (idx) {
    $("#myBoardList-table").html('');
    $("#pageDiv").html('');
    myBoardListPage = {page : idx, size: 10};
    var param = {uuid: $("#sessionUuid").val()}
    $.get(apiUserUrl + "/myBoardList", $.extend(myBoardListPage,param), function (res) {
        for (var idx in res.list) {
            $("#myBoardList-table").append(setMyBoardList(res.list[idx]));
        };
        setPaging(res.page);
    });
}

var setMyBoardList = function (vo) {
    var createAt = vo.createdAt.substring(0,10);
    var lock = "<i class='bi bi-key'></i>";
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
        + "   <td>"+ vo.writer +"</td>"
        + "   <td>"+ createAt +"</td>"
        + "   <td class='text-center'>"+ vo.viewCount +"</td>"
        + "   <td class='text-center'>"+ lock +"</td>"
        + "</tr>";
    return data;

}

var setPaging = function (param) {
    var data ='<nav aria-label="Page navigation example">'
        + '     <ul class="pagination justify-content-center">';
    if (param.prev) {
        data += '         <li class="page-item">'
            + '             <a class="page-link" aria-label="Previous" onclick="findMyBoardList('+ (param.startPage - 2) +')">'
            + '                 <span aria-hidden="true">&laquo;</span>'
            + '             </a>'
            + '         </li>';
    }
    for (var i = param.startPage; i <= param.endPage; i++) {
        data += '         <li class="page-item" id="li-'+ i +'"><a class="page-link" onclick="findMyBoardList('+ (i - 1) +')">'+ i +'</a></li>'
    }
    if (param.next) {
        data += '         <li class="page-item">'
            + '             <a class="page-link" aria-label="Next" onclick="findMyBoardList('+ param.endPage +')">'
            + '                 <span aria-hidden="true">&raquo;</span>'
            + '             </a>'
            + '         </li>';
    }
    data += '     </ul>'
        + '</nav>';
    $("#pageDiv").append(data);

    $("#li-"+param.currentPage).addClass("active");
    $("#li-"+(param.currentPage-1)).removeClass("active");
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