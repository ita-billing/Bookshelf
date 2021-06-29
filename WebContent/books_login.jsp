<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="js/check.js"></script>
<title>ログイン画面</title>

</head>
<body class="background_color">
    <h2>ログイン</h2>

    <div class="center_display">
    <form  method="get" name="login_form">
             ユーザーID：
      <input type="text" name="user_id" value="" size="24" maxlength="5"/>
    <br>
    <br>
             パスワード：
      <input type="text" name="password" value="" size="24" maxlength="10"/>
    <br>
    <br>
      <input type="submit" value="ログイン" onClick="return onButtonClick();">
    </form>
    </div>
</body>
<% if(request.getAttribute("status")== null){ %>
<%    }else{ %>
<%= request.getAttribute("status") %>
<% } %>
</html>
