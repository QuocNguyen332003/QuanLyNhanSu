<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>Đổi mật khẩu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
        integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css" />
    
</head>
<body>
	<div class="container">
		<div class="image">
			<img src="https://www.evn.com.vn/userfile/VH/User/huyent_tcdl/images/2021/6/hrmscuatapdoan24621(1).jpeg" style="width:700px;height:460px;">
		</div>
		<div class="form">
        	<form action="/submit-login" method="post">
            <h1>ĐỔI MẬT KHẨU</h1>
            <div class="input-box">
				<div class = "box_icon_login"><i class="fa-solid fa-user fa-2xl"></i></div>
            	<input type="text" name="username" placeholder="Tài khoản" required>
            </div>
            
            <div class="input-box">
				<div class = "box_icon_login"><i class="fa-solid fa-lock fa-2xl"></i></div>
            	<input type="password" name="oldpassword" placeholder="Mật khẩu cũ" required>
            </div>
            
            <div class="input-box">
				<div class = "box_icon_login"><i class="fa-solid fa-lock-open fa-2xl"></i></div>
            	<input type="password" name="newpassword" placeholder="Mật khẩu mới" required>
            </div>
            
            <div class="input-box">
				<div class = "box_icon_login"><i class="fa-solid fa-lock-open fa-2xl"></i></div>
            	<input type="password" name="newpassword" placeholder="Nhập lại mật khẩu" required>
            </div>
            
            <div class="box_show">
            	<input type="checkbox" name="showpass" id = "showpass">
            	<label for="showpass"><i>Show pass</i></label>
            </div>
            
            <div class = "box_button_login"><button type="submit" class="btn"><b>Xác nhận</b></button></div>

        </form>
    	</div>
	</div>
</body>
</html>