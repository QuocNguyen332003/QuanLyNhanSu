<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập Nhật Chi Nhánh</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/themchinhanh.css" />
    <script>
        window.onload = function() {
            var chinhanh = '${chinhanh}';
            var form = document.getElementById('myForm');
            if (chinhanh != null && chinhanh != '') {
                form.action = 'updateChiNhanh';
            } else {
                form.action = 'insertChiNhanh';
            }
        }
    </script>
</head>

<body>
<form id="myForm" >
    <div class="header-menu-plus">
        <a href="#">Cập Nhật Chi Nhánh</a>
    </div>
    <br>
    <!-- Các trường và nút submit của form -->
    <label for="macn">Mã Chi Nhánh:</label>
    <input type="text" id="macn" name="macn" value="${chinhanh.macn}" required>

    <label for="tencn">Tên Chi Nhánh:</label>
    <input type="text" id="tencn" name="tencn" value="${chinhanh.tencn}" required>


    <label for="diachi">Địa chỉ:</label>
    <select id="diachi" name="diachi">
        <!-- Add an empty option -->
        <option>${chinhanh.diachi}</option>
        <c:forEach items="${listdiachi}" var="x">
            <option>${x.madc}</option>
        </c:forEach>
    </select>

    <label for="magiamdoc">Mã giám đốc:</label>
    <select id="magiamdoc" name="magiamdoc">
        <!-- Add an empty option -->
        <option>${chinhanh.magiamdoc}</option>
        <c:forEach items="${listnhanvien}" var="x">
            <option>${x.matk}</option>
        </c:forEach>
    </select>

    <label for="tinhtrang">Tình Trạng:</label>
    <select id="tinhtrang" name="tinhtrang">
        <option value="Hoạt động"}>Hoạt động</option>
        <option value="Không hoạt động">Không hoạt động</option>
    </select>

    <br>
    <button type="submit">Lưu</button>
</form>
</body>

</html>
