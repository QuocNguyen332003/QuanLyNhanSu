<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<form id="myForm" method="post">
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
    <input type="text" id="diachi" name="diachi" value="${chinhanh.diachi}" required>

    <label for="magiamdoc">Mã Giám Đốc:</label>
    <input type="text" id="magiamdoc" name="magiamdoc" value="${chinhanh.magiamdoc}" required>

    <label for="tinhtrang">Tình Trạng:</label>
    <input type="text" id="tinhtrang" name="tinhtrang" value="${chinhanh.tinhtrang}">

    <br>
    <button type="submit">Lưu</button>
</form>
</body>

</html>
