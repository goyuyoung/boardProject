var apiUrl = "/api/board"
var boardListSorts = [{key: 'createdAt', orderBy: 'desc'}];
var boardListParams = {page: {pageSize: 2, selectPage: 0}, sorts: boardListSorts};
var boardListPage = {page: 0, size: 10, sort: [{}]}


$(document).ready( function() {
    initView();
    findBordList();
});

var initView = function () {
    $("#header").load("header");
    $("#footer").load("footer");
}

var findBordList = function () {
    $.get(apiUrl + "/boardList", {},function (res) {
        for (var idx in res) {
            $("#boardList-table").append(setBoardList(res[idx]));
        };
    });
}

var setBoardList = function (vo) {
    var createAt = vo.createdAt.substring(0,10);
    var lock = "<i class='bi bi-key'></i>";
    if (vo.lockYN != "y") {
        lock = ""
    }
    var data = "<tr>"
        + "   <input type='hidden' id='uuid-"+ vo.no+"' value='"+ vo.uuid +"'/>"
        + "   <input type='hidden' id='lockYN-"+ vo.no+"' value='"+ vo.lockYN +"'/>"
        + "   <th class='text-center' scope='row'>"+ vo.no +"</th>"
        + "   <td>"
        + "       <a href='javascript:void(0)' onclick='onclickTitle("+vo.no+");' >"+ vo.title +"</a>"
        + "   </td>"
        + "   <td>"+ vo.createdBy +"</td>"
        + "   <td>"+ createAt +"</td>"
        + "   <td class='text-center'>"+ vo.viewCount +"</td>"
        + "   <td class='text-center'>"+ lock +"</td>"
        + "   <input type='hidden' value='"+ vo.lock +"'>"
        + "</tr>";
    return data;

}
var onclickTitle = function (no) {
    // href='boardLock'
    console.log(no);
    var uuid = $("#uuid-"+no).val();
    var lockYN = $("#lockYN-"+no).val();
    if (lockYN == "y") {
        // 비밀글인 경우
        location.href='boardLock?no='+no;
    } else {
        // 비밀글 아닌 경우
        location.href='boardDetail?no='+no;
    }
}