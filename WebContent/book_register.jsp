<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/check_book.js"></script>
<script type="text/javascript" src="js/logout.js"></script>
<title>登録</title>
<h2>登録</h2>
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
<td>
</head>
<body class="background_color">

         以下から登録できます。
    <div class="editCenter_display">
    <form action="<%=request.getContextPath()%>/check_register" method="POST" id="book_form">
               タイトル: <input type="text" name="TITLE" maxlength="50"　value="">
      <br>
      <br>
               作者：  <input type="text" name="AUTHORNAME" maxlength="50"　value="">
      <br>
      <br>
               出版社：  <input type="text" name="PUBLISHER" maxlength="50"　value="">
      <br>
      <br>
               読破率（%）：  <input type="number" name="PROGRESS" size="2" maxlength="3" min="0" max="100"　value="">
      <br>
      <br>
               出版日：  <input type="date" name="PUBLICATIONDATE"　value="">
      <br>
      <br>
               開始日：  <input type="date" name="STARTDATE"　value="">
      <br>
      <br>
               終了日：  <input type="date" name="ENDDATE"　value="">
      <br>
      <br>
               評価：
       <SELECT name="EVALUATION">
         <OPTION value=""></OPTION>
         <OPTION value="0">高</OPTION>
         <OPTION value="1">低</OPTION>
       </SELECT>
    
      <div class="editCenter_display">
          <input type="submit" class="button_line" style="width:160px; height:35px" value="登録" onClick="return onBookClick();">
      </div>
    </form>
    </div>
    
    <div class="editCenter_display">
      <form action="<%=request.getContextPath()%>/init_bookshelf" method="POST" name="bookRegister_form">
        <input type="submit" style="width:160px; height:35px" value="戻る">
      </form>
    </div>

</body>
</html>