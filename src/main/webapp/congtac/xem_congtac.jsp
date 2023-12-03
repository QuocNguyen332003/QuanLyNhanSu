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
    <div class="col-md-11">
      <jsp:include page="../header_menu/thongtincoban.jsp" />
      <nav class="navbar navbar-expand-md navbar-dark navbar_css">
        <div class = "navbar_item_css">
          <h2> Quản lý công tác </h2>
          <div class="nav-item">
            <a class="button_icon" href="<%=request.getContextPath()%>/congtac">
              <i class="fa-solid fa-angles-left fa-2xl"></i>
            </a>
          </div>
        </div>
      </nav>
      <br>
      <div class="row">
        <div class="container body">
          <div class="container text-left">
            <form class="form-inline">
              <div class="form-group mx-2">
                <label for="chinhanh" class="mr-2"> Mã chi nhánh:</label>
                <select id = "chinhanh" class="form-control form-control-sm box_search">
                  <!-- Option 1 -->
                </select>
              </div>
              <div class="form-group mx-2">
                <label for="phongban" class="mr-2"> Mã phòng ban:</label>
                <select id = "phongban" class="form-control form-control-sm box_search">
                  <!-- Option 1 -->
                </select>
              </div>
              <div class="form-group mx-2">
                <label for="nhanvien" class="mr-2"> Mã nhân viên:</label>
                <select id = "nhanvien" class="form-control form-control-sm box_search">
                  <!-- Option 1 -->
                </select>
              </div>
            </form>
          </div>
          <br>
          <c:set var="count" value="0" />
          <c:forEach var="item_ct" items="${listcongtac_nv}">
          <form class = "box_form">
            <div class = "col-md-12 box_content">
              <div class="form-group form-inline">
                <label for="ngaybatdau${count}" class = "label_form_control">Ngày bắt đầu:</label>
                <input type="date" class="form-control box_form_control" id="ngaybatdau${count}" placeholder="Ngày bắt đầu" name="ngaybatdau" value="<c:out value="${item_ct.ngaybatdau}" />" readonly required>
              </div>
              <div class="form-group form-inline">
                <label for="tentochuc${count}" class = "label_form_control">Tên tổ chức:</label>
                <input type="text" class="form-control box_form_control" id="tentochuc${count}" placeholder="Tên tổ chức" name="tentochuc" value="<c:out value="${item_ct.tentochuc}" />" readonly required>
              </div>
              <div class="form-group form-inline">
                <label for="diachi${count}" class = "label_form_control">Địa chỉ:</label>
                <input type="text" class="form-control box_form_control" id="diachi${count}" placeholder="Địa chỉ" name="diachi" value="<c:out value="${item_ct.diachi}" />" readonly required>
              </div>
              <div class="form-group form-inline">
                <label for="chucvu${count}" class = "label_form_control">Chức vụ:</label>
                <input type="text" class="form-control box_form_control" id="chucvu${count}" placeholder="Chức vụ" name="chucvu" value="<c:out value="${item_ct.chucvu}" />" readonly required>
              </div>
              <div class="form-group form-inline">
                <label for="lydo${count}" class = "label_form_control">Lý do:</label>
                <input type="text" class="form-control box_form_control" id="lydo${count}" placeholder="Lý do nghỉ" name="lydo" value="<c:out value="${item_ct.lydo}" />" readonly required>
              </div>
            </div>
          </form>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</div>
</body>

</html>
