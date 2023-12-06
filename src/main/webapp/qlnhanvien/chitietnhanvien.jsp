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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/thongtincanhan.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css" />
	<style>
		.box_button_add{
			margin: 2rem;
			width: 101.5rem;
			height: 3rem;
			background-color: var(--maincolor);
		}
		.button_text{
			width: 100%; height: 100%;
			color: white;
			text-align: center;
			background-color: transparent;
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
                    <div class = "col-11">
                       <h2> Quản lý khen thưởng và kỷ luật </h2>
                    </div>
                    <ul class="navbar-nav ml-auto col-1">
                    	<li class="nav-item" >
                            <a class="button_icon" href="<%=request.getContextPath()%>/quanlynhanvien">
                               <i class="fa-solid fa-angles-left fa-2xl"></i>
                            </a>
                        </li>
                    </ul>
                </nav>
                <br>
                <div class="row">
					<div class="container body container_css">
						<div class = "col">
							<div class="box_info">
								<p class="style_text"><b>Thông tin nhân viên</b></p>
							</div>

							<div class="box_info">
								<div class="form-group form-inline">
									<label for="matk"><b>Mã NV:</b></label>
									<input type="text" class=" control" id="matk" placeholder="Mã NV" name="matk" value="${thongtincanhan.matk}" readonly required>
								</div>
								<div class="form-group form-inline">
									<label for="hoten"><b>Họ và tên:</b></label>
									<input type="text" class=" control" id="hoten" placeholder="Họ và tên" name="hoten" value="${thongtincanhan.hoten}" readonly required>
								</div>
								<div class="form-group form-inline">
									<label for="ngaysinh"><b>Ngày sinh:</b></label>
									<input type="date" class=" control" id="ngaysinh" placeholder="Ngày sinh" name="ngaysinh" value="${thongtincanhan.ngaysinh}" readonly required>
								</div>
								<div class="form-group form-inline">
									<label for="gioitinh"><b>Giới tính:</b></label>
									<input type="text" class=" control" id="gioitinh" placeholder="Giới tính"name="gioitinh" value="${thongtincanhan.gioitinh}" readonly required>
								</div>
								<div class="form-group form-inline">
									<label for="cccd" ><b>Số CCCD:</b></label>
									<input type="text" class=" control" id="cccd" placeholder="Số CCCD" name="cccd" value="${cancuoc}" readonly required>
								</div>
								<div class="form-group form-inline">
									<label for="diachi" ><b>Địa chỉ:</b></label>
									<input type="text" class=" control" id="diachi" placeholder="Địa chỉ" name="diachi" value="${thongtincanhan.diachi}" readonly required>
								</div>
							</div>
						</div>

						<div class = "col">
							<div class="box_info">
								<p class="style_text"><b>Thông tin liên lạc</b></p>
							</div>

							<div class="box_info">
								<div class="form-group form-inline">
									<label for="sdt"><b>Số điện thoại:</b></label>
									<input type="text" class="control" id="sdt" placeholder="Số điện thoại" name="sdt" value="${thongtincanhan.sdt}" readonly required>
								</div>
								<div class="form-group form-inline">
									<label for="email"><b>Email:</b></label>
									<input type="email" class="control" id="email" placeholder="Email" name="email" value="${thongtincanhan.email}" readonly required>
								</div>
								<br>
								<div class="form-group form-inline">
									<label for="username"><b>Tên tài khoản:</b></label>
									<input type="text" class="control" id="username" placeholder="Tên tài khoản" name="username" value="${taikhoan.username}" readonly required>
								</div>
								<div class="form-group form-inline">
									<label for="pass"><b>Mật khẩu:</b></label>
									<input type="password" class="control_more" id="pass" placeholder="Mật khẩu" name="pass" value="${taikhoan.pass}" readonly required>
									<button class="button_icon_small" onclick="showPass();"><i class="fa-solid fa-eye fa-sm"></i></button>
								</div>
							</div>
						</div>

						<div class = "col">
							<div class="box_info">
								<p class="style_text"><b>Thông tin chức vụ</b></p>
							</div>

							<div class="box_info">
								<div class="form-group form-inline">
									<label for="congviec"><b>Công việc:</b></label>
									<input type="text" class="control" id="congviec" placeholder="Công việc" name="congviec" value="${congviec}" readonly required>
								</div>
								<div class="form-group form-inline">
									<label for="chucvu"><b>Chức vụ:</b></label>
									<input type="text" class="control" id="chucvu" placeholder="Chức vụ" name="chucvu" value="${chucvu}" readonly required>
								</div>
								<div class="form-group form-inline">
									<label for="phongban"><b>Phòng ban:</b></label>
									<input type="text" class="control" id="phongban" placeholder="Phòng ban" name="phongban" value="${tenpb}" readonly required>
								</div>
								<div class="form-group form-inline">
									<label for="chinhanh"><b>Chi nhánh:</b></label>
									<input type="text" class="control" id="chinhanh" placeholder="Chi nhánh" name="chinhanh" value="${tencn}" readonly required>
								</div>
								<div class="form-group form-inline">
									<label for="capbac"><b>Cấp bậc:</b></label>
									<input type="text" class="control" id="capbac" placeholder="Cấp bậc" name="bangcap" value="${thongtincanhan.bangcap}"  readonly required>
								</div>
								<div class="form-group form-inline">
									<label for="ngaybatdau"><b>Ngày bắt đầu:</b></label>
									<input type="date" class="control" id="ngaybatdau" placeholder="Ngày bắt đầu" name="ngaybatdau" value="${ngaybatdau}" readonly required>
								</div>
							</div>
						</div>
					</div>
                    <div class = "box_button_add">
							<button class = "button_text">
								Lưu thay đổi
							</button>
						</div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
