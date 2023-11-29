<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Thông tin cá nhân</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
        integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/thongtincanhan.css" />
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
                       <h2> Thông tin cá nhân </h2>
                    </div>
                    <ul class="navbar-nav ml-auto col-2">
                        <li class="nav-item" >
                            <button class="button_icon">
                               <i class="fa-solid fa-pen-to-square fa-2xl"></i>
                            </button>
                        </li>
                        <li class="nav-item" >
                            <button class="button_icon">
                               <i class="fa-solid fa-floppy-disk fa-2xl"></i>
                            </button>
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
									<label for=""><b>Mã NV:</b></label> 
									<input type="text" class=" control" id="manhanvien" placeholder="Mã NV" name="manhanvien" required>
								</div>
								<div class="form-group form-inline">
									<label for=""><b>Họ và tên:</b></label> 
									<input type="text" class=" control" id="hovaten" placeholder="Họ và tên" name="hovaten" required>
								</div>
								<div class="form-group form-inline">
									<label for=""><b>Ngày sinh:</b></label> 
									<input type="date" class=" control" id="ngaysinh" placeholder="Ngày sinh" name="ngaysinh" required>
								</div>
								<div class="form-group form-inline">
									<label for=""><b>Giới tính:</b></label> 
									<input type="text" class=" control" id="gioitinh" placeholder="Giới tính"name="gioitinh" required>
								</div>
								<div class="form-group form-inline">
									<label for="" ><b>Số CCCD:</b></label> 
									<input type="text" class=" control" id="cccd" placeholder="Số CCCD" name="cccd" required>
								</div>
								<div class="form-group form-inline">
									<label for="" ><b>Địa chỉ:</b></label> 
									<input type="text" class=" control" id="diachi" placeholder="Địa chỉ" name="diachi" required>							
								</div>
							</div>
						</div>
					
						<div class = "col">
							<div class="box_info">
								<p class="style_text"><b>Thông tin liên lạc</b></p>
							</div>
					
							<div class="box_info">
								<div class="form-group form-inline">
									<label for=""><b>Số điện thoại:</b></label> 
									<input type="text" class="control" id="sdt" placeholder="Số điện thoại" name="manhanvien" required>
								</div>
								<div class="form-group form-inline">
									<label for=""><b>Email:</b></label> 
									<input type="email" class="control" id="hovaten" placeholder="Email" name="hovaten" required>
								</div>
								<br>
								<div class="form-group form-inline">
									<label for=""><b>Tên tài khoản:</b></label> 
									<input type="text" class="control" id="taikhoan" placeholder="Tên tài khoản" name="taikhoan" required>
								</div>
								<div class="form-group form-inline">
									<label for=""><b>Mật khẩu:</b></label> 
									<input type="password" class="control_more" id="password" placeholder="Mật khẩu" name="password" required>							
									<button class = "button_icon_small"><i class="fa-solid fa-eye fa-sm"></i></button>					
								</div>
								<div>
									<button type="submit" class="btn_mk"><b>Đổi mật khẩu</b></button>
								</div>
							</div>
						</div>
				
						<div class = "col">
							<div class="box_info">
								<p class="style_text"><b>Thông tin chức vụ</b></p>
							</div>
					
							<div class="box_info">
								<div class="form-group form-inline">
									<label for=""><b>Mã NV:</b></label> 
									<input type="text" class="control" id="manhanvien" placeholder="Mã NV" name="manhanvien" required>
								</div>
								<div class="form-group form-inline">
									<label for=""><b>Chức vụ:</b></label> 
									<input type="text" class="control" id="chucvu" placeholder="Chức vụ" name="chucvu" required>
								</div>
								<div class="form-group form-inline">
									<label for=""><b>Phòng ban:</b></label> 
									<input type="text" class="control" id="phongban" placeholder="Phòng ban" name="phongban" required>
								</div>
								<div class="form-group form-inline">
									<label for=""><b>Chi nhánh:</b></label> 
									<input type="text" class="control" id="chinhanh" placeholder="Chi nhánh" name="chinhanh" required>
								</div>
								<div class="form-group form-inline">
									<label for=""><b>Cấp bậc:</b></label> 
									<input type="text" class="control" id="capbac" placeholder="Cấp bậc" name="capbac" required>
								</div>
								<div class="form-group form-inline">
									<label for=""><b>Ngày bắt đầu:</b></label> 
									<input type="date" class="control" id="ngaybatdau" placeholder="Ngày bắt đầu" name="ngaybatdau" required>
								</div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>