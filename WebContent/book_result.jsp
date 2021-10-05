<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="js/logout.js"></script>
<title>処理結果</title>
<div class="right_display">
	<%
	  String name = (String)session.getAttribute("name");
	%>

	<form action="<%=request.getContextPath()%>/logout" method="POST" name="logout_form">
      <th>ユーザー名: <%= name %> </th>
      <br>
      <input type="submit" value="ログアウト" onClick="return onLogoutButton();">
    </form>
</div>

</head>
<body>
    <h2>処理結果</h2>

	<%
	  String message = (String) request.getAttribute("message");
	%>

	<%=message%>
	<br>
    <div class="editCenter_display">
      <form action="<%=request.getContextPath()%>/init_bookshelf" method="POST">
        <input type="submit" style="width:160px; height:35px" value="本棚に戻る">
      </form>
    </div>

</body>
</html>