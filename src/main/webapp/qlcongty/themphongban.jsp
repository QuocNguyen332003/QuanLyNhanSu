<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập Nhật Phòng Ban</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/themphongban.css" />
    <script>
        window.onload = function() {
            var phongban = '${phongban}';
            var form = document.getElementById('myForm');
            if (phongban != null && phongban != '') {
                form.action = 'updatePhongBan';
            } else {
                form.action = 'insertPhongBan';
            }
        }
    </script>
</head>

<body>
<form id="myForm" method="post">
    <div class="header-menu-plus">
        <a href="#">Cập Nhật Phòng Ban</a>
    </div>
    <br>
    <!-- Các trường và nút submit của form -->
    <label for="mapb">Mã Phòng Ban:</label>
    <input type="text" id="mapb" name="mapb" value="${phongban.mapb}" required>

    <label for="tenpb">Tên Phòng Ban:</label>
    <input type="text" id="tenpb" name="tenpb" value="${phongban.tenpb}" required>

    <label for="macn">Mã Chi Nhánh:</label>
    <select class="form-control form-control-sm box_search" id="macn"
            onchange="searchTable()">
        <!-- Add an empty option -->
        <option value="">- Select -</option>
        <c:forEach items="${listchinhanh}" var="x">
            <option>${x.macn}</option>
        </c:forEach>
    </select>

    <label for="matrphong">Mã Trưởng Phòng:</label>
    <input type="text" id="matrphong" name="matrphong" value="${phongban.matrphong}" required>

    <label for="mapbtr">Mã Phòng Ban Cha:</label>
    <input type="text" id="mapbtr" name="mapbtr" value="${phongban.mapbtr}">

    <br>
    <button type="submit">Cập Nhật</button>
</form>
</body>

</html>
