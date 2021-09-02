<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>登録</title>
<h2>登録</h2>
<div class="right_display">
	<%
	  String name = (String)session.getAttribute("name");
	%>
  <th>ユーザー名: <%= name %> </th>
  <br>
  <input type="submit" value="ログアウト">
</div>
<td>
</head>
<body class="background_color">

    <!-- 登録フォーム -->
    <h2>【登録】</h2>
         以下から登録できます。
    <br><br>
    <form action="<%=request.getContextPath()%>/" method="POST">
      　　  タイトル: <input type="text" name="TITLE">
      <br><br>
                  作者: <input type="text" name="AUTHORNAME">
      <br><br>
              読破率: <input type="text" name="PROGRESS">
      <br><br>        
              開始日: <input type="text" name="STARTDATE">
      <br><br>        
              終了日: <input type="text" name="ENDDATE">
      <br><br>        
              　評価: <input type="text" name="EVALUATION">
      <br><br>
        <input type="hidden" name="mode" value="add"> <input type="submit" value="登録">
    </form>


</body>
</html>