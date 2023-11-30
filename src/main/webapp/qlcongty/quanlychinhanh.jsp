<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Employee Management</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
	integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.19.0/font/bootstrap-icons.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
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
					<div>
						<h2>Quản lý chi nhánh</h2></a>
					</div>
					<ul class="navbar-nav ml-auto">
						<li class="nav-item">
							<button class="button_icon">
								<i class="fa-solid fa-file-excel fa-2x"></i>
							</button>
						</li>
						<li class="nav-item">
							<button class="button_icon">
								<i class="fa-sharp fa-solid fa-plus fa-2x"></i>
							</button>
						</li>
						<li class="nav-item">
							<button class="button_icon">
								<i class="fa-solid fa-pen-to-square fa-2x"></i>
							</button>
						</li>
						<li class="nav-item">
							<button class="button_icon">
								<i class="fa-solid fa-trash fa-2x"></i>
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
									<label for="yearSelect" class="mr-2"> Năm:</label> <select
										class="form-control form-control-sm box_search">
										<!-- Option 1 -->
									</select>
								</div>
								<div class="form-group mx-2">
									<label for="monthSelect" class="mr-2">Tháng:</label> <select
										class="form-control form-control-sm box_search">
										<!-- Option 2 -->
									</select>
								</div>
								<div class="form-group mx-2">
									<label for="daySelect" class="mr-2">Ngày:</label> <select
										class="form-control form-congàytrol-sm box_search">
										<!-- Option 3 -->
									</select>
								</div>
								<div class="form-group mx-2">
									<label for="creDaySelect" class="mr-2">Ngày tạo phòng:</label>
									<select class="form-control form-control-sm box_search">
										<!-- Option 4 -->
									</select>
								</div>
							</form>
						</div>
						<br>
						<div class="table-container">
							<table class="table table-bordered">
								<thead>
								<tr>
									<th>Mã Chi Nhánh</th>
									<th>Tên Chi Nhánh</th>
									<th>Địa Chỉ</th>
									<th>Mã Giám Đốc</th>
									<th>Tình Trạng</th>
									<th>Ngày Tạo</th>
									<th></th>
								</tr>
								</thead>
								<tbody>
								<c:forEach var="x" items="${listchinhanh}">
									<tr>
										<td>${x.macn}</td>
										<td>${x.tencn}</td>
										<td>${x.diachi}</td>
										<td>${x.magiamdoc}</td>
										<td>${x.tinhtrang}</td>
										<td>${x.ngaytao}</td>
										<td><a href="edit?macn=<c:out value='${x.macn}' />">Edit</a>
											&nbsp;&nbsp;&nbsp;&nbsp; <a
													href="delete?macn=<c:out value='${x.macn}' />">Delete</a></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
