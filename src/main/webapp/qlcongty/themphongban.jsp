<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Phòng Ban</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/themphongban.css" />
</head>

<body>

<c:if test="${phongban != null}" >
<form action="updatePhongBan" >
    </c:if>

    <c:if test="${phongban == null}">
    <form action="insertPhongBan" >
        </c:if>

        <div class="header-menu-plus">
            <a href="#">Cập nhật Phòng Ban</a>
        </div>
        <br>

        <!-- Các trường và nút submit của form -->
            <c:choose>
                <c:when test="${phongban == null}">
                    <label for="mapb">Mã Phòng Ban:</label>
                    <input type="input" id="mapb" name="mapb" value='${phongban.mapb}' required >
                </c:when>
                <c:when test="${phongban != null}">
                    <input type="hidden" id="mapb" name="mapb" value='${phongban.mapb}' readonly >
                </c:when>
            </c:choose>


            <label for="tenpb">Tên Phòng Ban:</label>
        <input type="text" id="tenpb" name="tenpb" value='${phongban.tenpb}' required>

        <label for="macn">Mã Chi Nhánh:</label>
        <input type="text" id="macn" name="macn" value='${phongban.macn}' required>

        <label for="matrphong">Mã Trưởng Phòng:</label>
        <input type="text" id="matrphong" name="matrphong"  value='${phongban.matrphong}' required>

        <label for="mapbtr">Mã Phòng Ban Cha:</label>
        <input type="text" id="mapbtr" name="mapbtr" value='${phongban.mapbtr}' >

        <br>
        <button type="submit">Lưu</button>
    </form>
</body>

</html>
