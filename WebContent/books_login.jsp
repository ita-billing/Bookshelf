<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>ログイン画面</title>
</head>
<body class="background_color">
    <h2>ログイン</h2>
    <div class="center_display">
    <tr>
      <th>ユーザーID：</th>
      <td>
        <input type = "text" name = "user_id" value = "" size ="24" maxlength ="5"/>
      </td>
    </tr>
    <br>
    <br>
    <tr>
      <th>パスワード： </th>
      <td>
        <input type = "text" name = "user_id" value = "" size ="24" maxlength ="10"/>
      </td>  
    </tr>
    <br>
    <br>
    <tr>
      <input type="submit" value="ログイン">
    </tr>
    </div>
</body>
</html>
