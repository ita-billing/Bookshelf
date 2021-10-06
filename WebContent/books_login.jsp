<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="js/check.js"></script>
<title>ログイン画面</title>

</head>
<body class="background_color">
    <%
	  // セッション終了
	  session.invalidate();
	%>
    
    <h2>ログイン</h2>

    <div class="center_display">
    <form action="<%=request.getContextPath()%>/login" method="POST" name="login_form">
             ユーザーID：
      <input type="text" name="user_id" value="" size="24" maxlength="5" value = '00001'/>
    <br>
    <br>
             パスワード：
      <input type="password" name="password" value="" size="24" maxlength="10" value = 'testpass'/>
    <br>
    <br>
      <input type="submit" value="ログイン" onClick="return onButtonClick();">
    </form>

    <% if(request.getAttribute("message")== null){ %>
    <%    }else{ %>
    <h3><%= request.getAttribute("message") %></h3>
    <% } %>

    </div>
</body>
</html>
