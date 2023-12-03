<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Menu Form</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/themchinhanh.css" />
</head>

<body>
<form>
  <div class="header-menu-plus">
    <a href="#">Cập nhật Chi Nhánh</a>
  </div>
  <br>
  <!-- Các trường và nút submit của form -->
  <label for="macn">Mã Chi Nhánh:</label>
  <input type="text" id="macn" name="macn">

  <label for="tencn">Tên Chi Nhánh:</label>
  <input type="text" id="tencn" name="tencn">

  <label for="magiamdoc">Mã Giám Đốc:</label>
  <select id="magiamdoc" name="magiamdoc">
    <option value="option1">Option 1</option>
    <option value="option2">Option 2</option>
    <!-- Thêm các option khác tùy ý -->
  </select>

  <br>
  <button type="submit">Lưu</button>
</form>
</body>
</html>
