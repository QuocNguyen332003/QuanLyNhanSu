<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông tin cơ bản</title>
    <!-- Linking Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/thongtincoban.css" />
    <style>
        .body-infor {
            height: 100px;
        }
    </style>
</head>
<body>
<div class="body-infor">
    <div class="section">
        <i class="fas fa-user"></i>
        <h2>${tencapbac_menu}</h2>
    </div>
    <div class="section">
        <i class="fas fa-users"></i>
        <h2>${thongtinnv.mapb}</h2>
    </div>
    <div class="section">
        <i class="fas fa-building"></i>
        <h2>${chinhanh_header.tencn}</h2>
    </div>
</div>
</body>
</html>
