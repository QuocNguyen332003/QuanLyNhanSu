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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/congtac.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css" />
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
                    <div class = "navbar_item_css">
                       <h2> Quản lý công tác </h2>
                       <div class="nav-item" >
                           <button class="button_icon">
                               <i class="fa-solid fa-eye fa-2xl"></i>
                           </button>
                       </div>
                    </div> 
                </nav>
                <br>
                <div class="row">
                    <div class="container body">
					<c:forEach var="item_ct" items="${listcongtac}">
						<form class = "box_form">
							<div class = "col-md-11 box_content">
								<div class="form-group form-inline">
									<label for="ngaybatdau" class = "label_form_control">Ngày bắt đầu:</label>
									<input type="date" value="<c:out value="${item_ct.ngaybatdau}" />" class="form-control box_form_control" id="ngaybatdau" placeholder="Ngày bắt đầu" name="ngaybatdau" required>
								</div>
								<div class="form-group form-inline">
									<label for="tentochuc" class = "label_form_control">Tên tổ chức:</label>
									<input type="text" value="<c:out value="${item_ct.tentochuc}" />" class="form-control box_form_control" id="tentochuc" placeholder="Tên tổ chức" name="tentochuc" required>
								</div>
								<div class="form-group form-inline">
									<label for="diachi" class = "label_form_control">Địa chỉ:</label>
									<input type="text" value="<c:out value="${item_ct.diachi}" />" class="form-control box_form_control" id="diachi" placeholder="Địa chỉ" name="diachi" required>
								</div>
								<div class="form-group form-inline">
									<label for="chucvu" class = "label_form_control">Chức vụ:</label>
									<input type="text" value="<c:out value="${item_ct.chucvu}" />" class="form-control box_form_control" id="chucvu" placeholder="Chức vụ" name="chucvu" required>
								</div>
								<div class="form-group form-inline">
									<label for="lydo" class = "label_form_control">Lý do:</label>
									<input type="text" value="<c:out value="${item_ct.lydo}" />" class="form-control box_form_control"id="lydo" placeholder="Lý do nghỉ" name="lydo" required>
								</div>
							</div>
							<div class = "col-md-1 box_button">
								<button class = "button_icon">
									<i class="fa-solid fa-trash fa-2xl"></i>
								</button>
								<button class = "button_icon">
									<i class="fa-solid fa-pen-to-square fa-2xl"></i>
								</button>
								<button class = "button_icon">
									<i class="fa-solid fa-floppy-disk fa-2xl"></i>
								</button>
							</div>
						</form>
					</c:forEach>
						<div class = "col-md-12 box_button_add">
							<button class = "button_icon">
								<i class="fa-solid fa-plus fa-2xl"></i>
							</button>
						</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
