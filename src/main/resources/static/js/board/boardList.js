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
        console.log(res);
        for (var idx in res) {
            $("#boardList-table").append(setBoardList(res[idx]));
        };

    }).done(function () {

    });

    var setBoardList = function (vo) {
        var createAt = vo.createdAt.substring(0,10);
        var lock = "<i class='bi bi-key'></i>";
        if (vo.lockYN != "y") {
            lock = ""
        }
        var data = "<tr>"
            + "   <th class='text-center' scope='row'>"+ vo.no +"</th>"
            + "   <td>"
            + "       <a href='boardLock'>"+ vo.title +"</a>"
            + "   </td>"
            + "   <td>"+ vo.createdBy +"</td>"
            + "   <td>"+ createAt +"</td>"
            + "   <td class='text-center'>"+ vo.viewCount +"</td>"
            + "   <td class='text-center'>"+ lock +"</td>"
            + "</tr>";
        return data;

    }
}