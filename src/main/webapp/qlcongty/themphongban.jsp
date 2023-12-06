<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<%@ page isErrorPage="true" %>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập Nhật Phòng Ban</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/themphongban.css" />
    <script>
        window.onload = function() {
            var phongban = '${phongban}';
            var form = document.getElementById('myForm');
            alert(phongban);
            if (phongban != null && phongban != '' ) {
                form.action = 'updatePhongBan';
            } else {
                form.action = 'insertPhongBan';
            }
            // Get the select element
        }

        function updateNhanVienList() {
            var mainComboValue = document.getElementById("macnSelect").value;
            var matrphongSelect = document.getElementById('matrphongSelect');

            // Use AJAX to send mainComboValue to the server and update dependent combo box
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "editPhongBan?mainComboValue=" + encodeURIComponent(mainComboValue), true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var options = JSON.parse(xhr.responseText);

                    // Add the new options to matrphongSelect
                    options.forEach(function(option) {
                        var newOption = document.createElement('option');
                        newOption.text = option;
                        matrphongSelect.add(newOption);
                    });
                }
                location.reload();
            };
            xhr.send();
        }
    </script>
</head>
<body>
<form id="myForm">
    <div class="header-menu-plus">
        <a href="#">Cập Nhật Phòng Ban</a>
    </div>
    <br>
    <!-- Các trường và nút submit của form -->
    <label for="mapb">Mã Phòng Ban:</label>
    <input type="text" id="mapb" name="mapb" value="${phongban.mapb}" required>

    <label for="tenpb">Tên Phòng Ban:</label>
    <input type="text" id="tenpb" name="tenpb" value="${phongban.tenpb}" required>

    <label for="macnSelect" class="mr-2">Mã chi nhánh:</label>
    <select id="macnSelect" name = "macnSelect" onchange="updateNhanVienList()">
        <c:forEach items="${listchinhanh}" var="x">
            <option>${x.macn}</option>
        </c:forEach>
    </select>

    <label for="matrphongSelect">Mã Trưởng Phòng:</label>
    <select id="matrphongSelect" name="matrphongSelect" >
        <!-- Add an empty option -->
        <c:forEach items="${listnhanvien}" var="x">
            <option>${x.matk}</option>
        </c:forEach>
    </select>

    <label for="mapbtrSelect">Mã Trưởng Phòng:</label>
    <select id="mapbtrSelect" name="mapbtrSelect" >
        <!-- Add an empty option -->
        <option>${phongban.matrphong}</option>
    </select>
    <br>

    <button type="submit">Cập Nhật</button>
</form>
</body>
</html>
