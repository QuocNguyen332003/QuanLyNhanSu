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
                       <h2> Quản lý khen thưởng và kỷ luật </h2>
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
                                <div class="form-group mx-2">
                                    <label for="" class="mr-2"> Mã chi nhánh:</label>
                                    <select class="form-control form-control-sm box_search">
                                        <!-- Option 1 -->
                                    </select>
                                </div>
                                <div class="form-group mx-2">
                                    <label for="" class="mr-2"> Mã phòng ban:</label>
                                    <select class="form-control form-control-sm box_search">
                                        <!-- Option 1 -->
                                    </select>
                                </div>
                                <div class="form-group mx-2">
                                    <label for="" class="mr-2"> Mã nhân viên:</label>
                                    <select class="form-control form-control-sm box_search">
                                        <!-- Option 1 -->
                                    </select>
                                </div>
                                <div class="form-group mx-2">
                                    <button class = "button_add">Thêm nhân viên</button>
                                </div>
                            </form>
                        </div>
                        <br>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Mã nhân viên</th>
				                    <th>Mã phòng ban</th>
				                    <th>Mã Chi Nhánh</th>
				                    <th>Mã chi nhánh</th>
				                    <th>Ngày bắt đầu</th>
				                    <th>Tình trạng </th>
				                    <th>SA THẢI</th>
				                    <th>XEM THÊM</th>
                                </tr>
                            </thead>
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
                    
                    <div class="form_chidinh" id="chidinh">
                        <form action="chidinh" class="form-container">
                            <h3 class = "form_title">Chỉ định công việc</h3>
                            <div class="form-group form-inline">
                                <label for="matk" class = "label_form_control"><b>Mã nhân viên:</b></label>
                                <input type="text" class="form-control" id="matk" placeholder="Mã nhân viên" name="matk" required>
                            </div>
                            <div class="form-group form-inline">
                                <label for="congviec" class = "label_form_control"><b>Công việc:</b></label>
                                <input type="text" class="form-control" id="congviec" placeholder="Công việc" name="congviec" required>
                            </div>
                            <div class="form-group form-inline">
                                <label for="phongban" class = "label_form_control"><b>Phòng Ban:</b></label>
                                <input type="text" class="form-control" id="phongban" placeholder="Phòng Ban" name="phongban" required>
                            </div>
                            <div class="form_button">
                                <button type="submit">Xác nhận</button>
                                <button type="button" onclick="closeFormAdd()">Hủy</button>
                            </div>
                        </form>
                    </div>
                    
                    <div class="form_thongbao" id="thongbao">
                        <ul>
                        	<li>
                        		<a href="#" >Trưởng phòng ban Tài chính yêu cầu thêm nhân viên cho phòng ban</a>
                        	</li>
                        </ul>
                        <div class="form_button">
                                <button type="button" onclick="closeFormNotify()">Hủy</button>
                            </div>
                    </div>
                    
                    <script>
                        function openFormRequest() {
                          	document.getElementById("yeucau").style.display = "block";
                        }

                        function closeFormRequest() {
                          	document.getElementById("yeucau").style.display = "none";
                        }
                        function openFormAdd() {
                          	document.getElementById("chidinh").style.display = "block";
                        }

                        function closeFormAdd() {
                          	document.getElementById("chidinh").style.display = "none";
                        }
                        function openFormNotify() {
                          	document.getElementById("thongbao").style.display = "block";
                        }

                        function closeFormNotify() {
                          	document.getElementById("thongbao").style.display = "none";
                        }
                   	</script>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
