<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
            rel="stylesheet">
    <link rel="stylesheet"
          href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
          crossorigin="anonymous">
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sodo.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css" />
    <title>Sơ đồ</title>
</head>

<body>
<jsp:include page="../header_menu/header.jsp" />
<div class="container">
    <div class="row">
        <div class="col-md-1">
            <jsp:include page="../header_menu/menu.jsp" />
        </div>
        <div class="col-md-11 container_css">
            <jsp:include page="../header_menu/thongtincoban.jsp" />
            <nav class="navbar navbar-expand-md navbar-dark navbar_css">
                <div class = "col-11">
                    <h2> Cấu trúc công ty </h2>
                </div>
            </nav>
            <br>
            <div class="row">
                <div class="col-md-5 mb-4 box_tree">
                    <ul id="tree1">
                        <br>
                        <li><a href="#">Quản lý</a>
                            <ul>
                                <li>Chi nhánh Hồ Chí Minh</li>
                                <li>Chi nhánh Đồng Tháp
                                    <ul>
                                        <li>Reports
                                            <ul>
                                                <li>Report1</li>
                                                <li>Report2</li>
                                                <li>Report3</li>
                                            </ul>
                                        </li>
                                        <li>Employee Maint.</li>
                                    </ul>
                                </li>
                                <li>Chi nhánh Bến Tre</li>
                                <li>Chi nhánh Đồng Nai</li>
                            </ul></li>
                    </ul>
                </div>

                <div class="col-md-5 mb-4 box_tree">
                    <ul id="tree2">
                        <br>
                        <li><a href="#">Quản Lý</a>
                            <ul>
                                <li>Phan Minh Quân</li>
                                <li>0765058830</li>
                                <li>21110740@student.chmute.edu.vn</li>
                            </ul></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $.fn
        .extend({
            treed : function(o) {
                let openedClass = "fa-circle fa-xs";
                let closedClass = "fa-plus fa-xs";

                if (typeof o != "undefined") {
                    if (typeof o.openedClass != "undefined") {
                        openedClass = o.openedClass;
                    }
                    if (typeof o.closedClass != "undefined") {
                        closedClass = o.closedClass;
                    }
                }
                //initialize each of the top levels
                let tree = $(this);
                tree.addClass("tree");
                tree
                    .find('li')
                    .has("ul")
                    .each(
                        function() {
                            let branch = $(this); //li with children ul
                            branch.prepend("<i class='fa-solid " + closedClass + "'></i>");
                            branch.addClass('branch');
                            branch
                                .on(
                                    'click',
                                    function(e) {
                                        if (this === e.target) {
                                            var icon = $(
                                                this)
                                                .children(
                                                    'i:first');
                                            icon
                                                .toggleClass(openedClass
                                                    + " "
                                                    + closedClass);
                                            $(this)
                                                .children()
                                                .children()
                                                .toggle();
                                        }
                                    })
                            branch.children().children()
                                .toggle();
                        });
                //fire event from the dynamically added icon
                tree.find('.branch .indicator').each(function() {
                    $(this).on('click', function() {
                        $(this).closest('li').click();
                    });
                });
                //fire event to open branch if the li contains an anchor instead of text
                tree.find('.branch>a').each(function() {
                    $(this).on('click', function(e) {
                        $(this).closest('li').click();
                        e.preventDefault();
                    });
                });
                //fire event to open branch if the li contains a button instead of text
                tree.find('.branch>button').each(function() {
                    $(this).on('click', function(e) {
                        $(this).closest('li').click();
                        e.preventDefault();
                    });
                });
            }
        });
    $('#tree1').treed();
    $('#tree2').treed();
</script>
</body>

</html>