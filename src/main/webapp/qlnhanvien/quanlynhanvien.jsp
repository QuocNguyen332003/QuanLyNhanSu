<%@ page import="DAO.yeucauDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>Employee Management</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
        integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/quanlynhanvien.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css" />
    <style>
        .button_add{
            width: 16rem;
        }
        .form_tacvu{
            display: none;
            position: fixed;
            background-color: #F4F5F7;
            border: 3px solid var(--maincolor);
            box-shadow: 0 0 1rem 0.3rem var(--maincolor);
            z-index: 9;
        }
        .form_sathai{
            width: 40rem;
            height: 25rem;
            top: 30rem;
            left:60rem;
        }
        .form_chidinhcongviec{
            width: 40rem;
            height: 25rem;
            top: 30rem;
            left:60rem;
        }
        .form_button button{
            width: 10rem; height: 3rem;
            background-color: var(--maincolor);
            color: white;
        }
        .form_xoa h5{
            width: 100%;
            height: 5rem;
        }
        .form_thongbao{
            width: 40rem;
            height: 40rem;
            display: none;
            position: fixed;
            top: 20rem;
            left:82rem;
            background-color: #F4F5F7;
            border: 3px solid var(--maincolor);
            box-shadow: 0 0 1rem 0.3rem var(--maincolor);
            z-index: 9;
        }
        .form_thongbao li{
            width: 40rem; height: 6rem;
            border: 1px solid var(--maincolor);
            display: flex; flex-direction: row; justify-content: space-around;
        }
        .box_button_thongbao{
            position: fixed;
            top: 55rem;
            left:95rem;
        }
        .box_noidung_thongbao{
            width: 30rem;
        }
        .box_tinhtrang_thongbao{
            width: 5rem;
            text-align: center;
        }
        .box_check_thongbao{
            width: 5rem;
            display: flex; flex-direction: column; justify-content: center;
        }
        .box_check_thongbao a{
            width: 2.5rem; height: 2.5rem;
            background-color: transparent;
        }
        .greencolor{
            color: green;
        }
        .redcolor{
            color: red;
        }
    </style>
</head>

<body>
    <jsp:include page="../header_menu/header.jsp" />
    <div class="container">
        <div class="row">
            <div class="col-md-1">
            	<jsp:include page="../header_menu/menu.jsp" />
            </div>
            <div class="col-md-11">
            	<jsp:include page="../header_menu/thongtincoban.jsp" />
                <nav class="navbar navbar-expand-md navbar-dark navbar_css">
                    <div class = "col-10">
                       <h2> Quản lý nhân viên </h2>
                    </div>
                    <ul class="navbar-nav ml-auto col-2">
                    	<li class="nav-item" >
                            <button class="button_icon">
                              	<i class="fa-solid fa-file-excel fa-2x"></i>
                            </button>
                        </li>
                        <li class="nav-item" >
                            <button class="button_icon" onclick="openFormNotify()">
                               <i class="fa-solid fa-bell fa-2xl"></i>
                            </button>
                        </li>
                    </ul>
                </nav>
                <br>
                <div class="row">
                    <div class="container body">
                    	<div class="container text-left">
                            <form class="form-inline">
                                <div class="form-group mx-3">
                                    <label for="search" class="mr-2"> Tìm kiếm:</label>
                                    <input class = "form-control box_search" id = "search" onkeyup="Search_textbox()" placeholder="Search">
                                </div>
                                <div class="form-group mx-2">
                                    <label for="select_macn" class="mr-2"> Mã chi nhánh:</label>
                                    <select class="form-control form-control-sm box_search" id = "select_macn" onchange="search_Input('select_macn')">
                                        <option value="ALL">Tất cả</option>
                                        <c:forEach var="item" items="${setmacn_nv}">
                                            <option value="<c:out value="${item}" />">${item}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group mx-2">
                                    <label for="select_mapb" class="mr-2"> Mã phòng ban:</label>
                                    <select class="form-control form-control-sm box_search" id = "select_mapb" onchange="search_Input('select_mapb')">
                                        <option value="ALL">Tất cả</option>
                                        <c:forEach var="item" items="${setmapb_nv}">
                                            <option value="<c:out value="${item}" />">${item}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group mx-2">
                                    <label for="select_matk" class="mr-2"> Mã nhân viên:</label>
                                    <select class="form-control form-control-sm box_search" id = "select_matk" onchange="search_Input('select_matk')">
                                        <option value="ALL">Tất cả</option>
                                        <c:forEach var="item" items="${setmatk_nv}">
                                            <option value="<c:out value="${item}" />">${item}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <c:set var="capbac" value="${capbac}" />
                                <c:if test="${capbac > 1}">
                                    <div class="form-group mx-2">
                                        <a class = "button_add" href="<%=request.getContextPath()%>/themnhanvien">Thêm nhân viên</a>
                                    </div>
                                </c:if>
                                <c:if test="${capbac == 1}">
                                    <div class="form-group mx-2">
                                        <button type="button" class = "button_add" onclick="openFormRequest()">Yêu cầu thêm nhân viên</button>
                                    </div>
                                </c:if>
                            </form>
                        </div>
                        <br>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Mã nhân viên</th>
				                    <th>Mã phòng ban</th>
				                    <th>Mã Chi Nhánh</th>
				                    <th>Ngày bắt đầu</th>
				                    <th>Tình trạng </th>
                                    <th>Công Việc </th>
				                    <th>SA THẢI</th>
				                    <th>XEM THÊM</th>
                                    <c:if test="${capbac > 1}">
                                        <th>CHỈ ĐỊNH</th>
                                    </c:if>
                                </tr>
                            </thead>
                            <tbody id = "row_table">
                            <c:set var="count" value="0" />
                            <c:forEach var="x" items="${listnv}">
                                <tr>
                                    <td>${x.matk}</td>
                                    <td>${x.mapb}</td>
                                    <td>${x.macn}</td>
                                    <td>${x.ngaybatdau}</td>
                                    <td>${x.tinhtrang}</td>
                                    <td>${x.congviec}</td>
                                    <td><button onclick="openFormXoa${count}()">Sa thải</button></td>
                                    <td><a href="xemthongtinnhanvien?matk=<c:out value='${x.matk}' />">Xem thêm</a></td>
                                    <c:if test="${capbac > 1}">
                                        <td><button onclick="openFormChiDinh${count}()">Chỉ định</button></td>
                                    </c:if>
                                </tr>
                                <div class = "form_sathai form_tacvu" id = "form_sathai${count}">
                                    <h3 class = "form_title">Xác nhận sa thải </h3>
                                    <div class="form-group form-inline">
                                        <h5 class = "label_form_control">Bạn có chắc chắn sa thải nhân viên ${x.matk} không?</h5>
                                        <h5>Bấm Xác nhận để xóa.</h5>
                                    </div>
                                    <div class="form_button">
                                        <a href="<%=request.getContextPath()%>/sathainhanvien?matk=<c:out value='${x.matk}' />" >Xác nhận</a>
                                        <button type="button" onclick="closeFormXoa${count}()">Hủy</button>
                                    </div>
                                </div>
                                <div class = "form_chidinhcongviec form_tacvu" id = "form_chidinh${count}" >
                                    <form action="<%=request.getContextPath()%>/chidinhnhanvien">
                                        <h3 class = "form_title">Chỉ định nhân viên </h3>
                                        <div class="form-group form-inline" style="display: none;">
                                            <label for="matk${count}" class = "label_form_control"><b>Mã tài khoản:</b></label>
                                            <input type="text" value="<c:out value='${x.matk}' />" class="form-control" id="matk${count}" placeholder="Công việc" name="matk" required>
                                        </div>
                                        <div class="form-group form-inline">
                                            <label for="macn${count}" class = "label_form_control"><b>Mã chi nhánh:</b></label>
                                            <select id="macn${count}" name="macn" class="form-control box_form_control form-select">
                                                <option value="ALL">Tất cả</option>
                                                <c:forEach var="item" items="${setmacn_nv}">
                                                    <option value="<c:out value="${item}" />">${item}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group form-inline">
                                            <label for="mapb${count}" class = "label_form_control"><b>Mã phòng ban:</b></label>
                                            <select id="mapb${count}" name="mapb" class="form-control box_form_control form-select">
                                                <option value="ALL">Tất cả</option>
                                                <c:forEach var="item" items="${chitietphongban}">
                                                    <option value="<c:out value="${item.mapb}" />">${item.mapb}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group form-inline">
                                            <label for="congviec${count}" class = "label_form_control"><b>Công việc:</b></label>
                                            <input type="text" class="form-control" id="congviec${count}" placeholder="Công việc" name="congviec" required>
                                        </div>
                                        <div class="form_button">
                                            <button type="submit">Xác nhận</button>
                                            <button type="button" onclick="closeFormChiDinh${count}()">Hủy</button>
                                        </div>
                                    </form>
                                </div>
                                <script>
                                    function openFormXoa${count}() {
                                        document.getElementById("form_sathai${count}").style.display = "block";
                                    }
                                    function closeFormXoa${count}(){
                                        document.getElementById("form_sathai${count}").style.display = "none";
                                    }
                                    function openFormChiDinh${count}() {
                                        document.getElementById("form_chidinh${count}").style.display = "block";
                                    }
                                    function closeFormChiDinh${count}(){
                                        document.getElementById("form_chidinh${count}").style.display = "none";
                                    }
                                </script>
                            <c:set var="count" value="${count + 1}"/>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    
                    <div class="form_yeucau" id="yeucau">
                        <form action="/yeucau" class="form-container">
                            <h3 class = "form_title">Yêu cầu nhân viên</h3>
                            <div class="form-group form-inline">
                                <label for="tencongviec" class = "label_form_control"><b>Công Việc:</b></label>
                                <input type="text" class="form-control" id="tencongviec" placeholder="Tên công việc" name="congviec" required>
                            </div>
                            <div class="form_button">
                                <button type="submit">Xác nhận</button>
                                <button type="button" onclick="closeFormRequest()">Hủy</button>
                            </div>
                        </form>
                    </div>

                    <div class="form_thongbao" id="thongbao">
                        <ul>
                            <c:set var="count" value="0" />
                            <c:forEach var="x" items="${listyeucau}">
                        	<li>
                        		<p class = "box_noidung_thongbao">Trưởng phòng ban ${x.matk} yêu cầu thêm nhân viên ${x.congviec}</p>
                                <div class = "box_tinhtrang_thongbao">${x.tinhtrang}</div>
                                <div class = "box_check_thongbao" style="display:${sessionScope.capbac == 2 && x.tinhtrang == "chưa duyệt" ? 'inline' : 'none'}">
                                    <a href="<%=request.getContextPath()%>/duyetyeucau?mayeucau=<c:out value='${x.mayeucau}' />"><i class="fa-solid fa-check fa-xs greencolor"></i></a>
                                    <a href="<%=request.getContextPath()%>/tuchoiyeucau?mayeucau=<c:out value='${x.mayeucau}' />"><i class="fa-solid fa-x fa-xs redcolor"></i></a>
                                </div>
                        	</li>
                            <c:set var="count" value="${count + 1}"/>
                            </c:forEach>
                        </ul>
                        <div class="form_button box_button_thongbao">
                           <button type="button" onclick="closeFormNotify()">Đóng</button>
                        </div>
                    </div>
                    
                    <script>
                        function openFormRequest() {
                          	document.getElementById("yeucau").style.display = "block";
                        }

                        function closeFormRequest() {
                          	document.getElementById("yeucau").style.display = "none";
                        }
                        function openFormNotify() {
                          	document.getElementById("thongbao").style.display = "block";
                        }

                        function closeFormNotify() {
                          	document.getElementById("thongbao").style.display = "none";
                        }
                        function Search_textbox() {
                            var input, filter, table, tr, td, i, txtValue;
                            input = document.getElementById("search");
                            filter = input.value.toUpperCase();
                            table = document.getElementById("row_table");
                            tr = table.getElementsByTagName("tr");
                            for (i = 0; i < tr.length; i++) {
                                td = tr[i].getElementsByTagName("td");
                                let j = 0;
                                for (j = 0; j < td.length; j++){
                                    let x = tr[i].getElementsByTagName("td")[j];
                                    if (x) {
                                        txtValue = x.textContent || x.innerText;
                                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                            tr[i].style.display = "";
                                            break;
                                        } else {
                                            tr[i].style.display = "none";
                                        }
                                    }
                                }
                            }
                        }
                        function search_Input(box_search){
                            var input, filter, table, tr, td, i, txtValue;
                            input = document.getElementById(box_search);
                            filter = input.value.toUpperCase();
                            table = document.getElementById("row_table");
                            tr = table.getElementsByTagName("tr");
                            for (i = 0; i < tr.length; i++) {
                                td = tr[i].getElementsByTagName("td");
                                let j = box_search === "select_matk"? 0: box_search === "select_mapb"? 1: 2;
                                let x = tr[i].getElementsByTagName("td")[j];
                                if (x) {
                                    txtValue = x.textContent || x.innerText;
                                    if (txtValue.toUpperCase().indexOf(filter) > -1 || filter.toUpperCase().indexOf("ALL") > -1) {
                                        tr[i].style.display = "";
                                    } else {
                                        tr[i].style.display = "none";
                                    }
                                }
                            }
                        }
                   	</script>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
