<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="info.books.BookBeans"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/check_book.js"></script>
<script type="text/javascript" src="js/check_delete.js"></script>
<script type="text/javascript" src="js/logout.js"></script>
<title>編集画面</title>
<h2>編集</h2>
<div class="right_display">
	<%
	  String name = (String)session.getAttribute("name");
	  BookBeans books = (BookBeans) request.getAttribute("books");
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

    <h3>【一覧】</h3>
    <table border ="1" align="center" class="borderline">
      
      <tr>
        <td class="table_header">タイトル</td>
        <td class="table_header">作者</td>
        <td class="table_header">出版社</td>
        <td class="table_header center_display">読破率（%）</td>
        <td class="table_header center_display">出版日</td>
        <td class="table_header center_display">開始日</td>
        <td class="table_header center_display">終了日</td>
        <td class="table_header center_display">評価</td>
      </tr>

	  <tr>
		<td class="table_body"><%=books.getTitle()%></td>
		<td class="table_body"><%=books.getAuthorname()%></td>
		<td class="table_body"><%=books.getPublisher()%></td>
		<td class="table_body center_display"><%=books.getProgress()%></td>
		<td class="table_body center_display"><%=books.getPublicationdate()%></td>
		<td class="table_body center_display"><%=books.getStartdate()%></td>
		<td class="table_body center_display"><%=books.getEnddate()%></td>
		<td class="table_body center_display"><%=books.getEvaluation()%></td>
			
        <form name="edit_form">
          <input type="hidden" name="TITLE" value="<%=books.getTitle()%>">
          <input type="hidden" name="AUTHORNAME" value="<%=books.getAuthorname()%>">
          <input type="hidden" name="PUBLISHER" value="<%=books.getPublisher()%>">
          <input type="hidden" name="PROGRESS" value="<%=books.getProgress()%>">
          <input type="hidden" name="PUBLICATIONDATE" value="<%=books.getPublicationdate()%>">
          <input type="hidden" name="STARTDATE" value="<%=books.getStartdate()%>">
          <input type="hidden" name="ENDDATE" value="<%=books.getEnddate()%>">
          <input type="hidden" name="EVALUATION" value="<%=books.getEvaluation()%>">
        </form>
      </tr>
      
    </table>
    
    <br>
    <h3>【編集】</h3>
          以下から変更できます。
    <br>
    <div class="editCenter_display">
    <form action="<%=request.getContextPath()%>/check_edit" method="POST" id="book_form">
      <input type="hidden" name="SEQID" value="<%=books.getSeqid()%>">
               タイトル: <input type="text" name="TITLE" maxlength="50" value="<%=books.getTitle()%>">
      <br>
      <br>
               作者：  <input type="text" name="AUTHORNAME" maxlength="50" value="<%=books.getAuthorname()%>">
      <br>         
      <br>        
               出版社：  <input type="text" name="PUBLISHER" maxlength="50" value="<%=books.getPublisher()%>">
      <br>
      <br>
               読破率（%）：  <input type="number" name="PROGRESS" size="2" maxlength="3" value="<%=books.getProgress()%>">
      <br>         
      <br>         
               出版日：  <input type="date" name="PUBLICATIONDATE" value="<%=books.getPublicationdate()%>">
      <br>
      <br>
               開始日：  <input type="date" name="STARTDATE" value="<%=books.getStartdate()%>">
      <br>
      <br>         
               終了日：  <input type="date" name="ENDDATE" value="<%=books.getEnddate()%>">
      <br>
      <br>        
               評価：
        <SELECT name="EVALUATION">
          <OPTION value="<%=books.getEvaluationvalue()%>" selected><%=books.getEvaluation()%></OPTION>
          <OPTION value=""></OPTION>
          <OPTION value="0">高</OPTION>
          <OPTION value="1">低</OPTION>
        </SELECT> 
        
      <div class="editCenter_display">
        <input class="button_line" type="submit" style="width:160px; height:35px" value="編集" onclick="return onBookClick();">
    </form>
    
    <form action="<%=request.getContextPath()%>/book_delete" method="POST" name="bookDelete_form">
      <input type="hidden" name="DELETE_SEQID" value="<%=books.getSeqid()%>">
      <input class="button_line" type="submit" style="width:160px; height:35px" value="削除" onClick="return onDeleteClick();">
    </form>
    </div>
    </div>
    
    <div class="editCenter_display">
      <form action="<%=request.getContextPath()%>/init_bookshelf" method="POST" name="backbookshelf_form">
        <input type="submit" style="width:320px; height:35px" value="戻る">
      </form>
    </div>

</body>
</html>