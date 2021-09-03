<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>編集画面</title>
<h2>編集</h2>
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

    <h3>一覧</h3>
    <table border ="1" align="center" class="borderline">
      
      <tr>
        <td class="table_hidden">ID</td>
        <td class="table_header">タイトル</td>
        <td class="table_header">作者</td>
        <td class="table_header center_display">読破率</td>
        <td class="table_header center_display">開始日</td>
        <td class="table_header center_display">終了日</td>
        <td class="table_header center_display">評価</td>
        <td class="table_header center_display">修正・削除</td>
      </tr>
    
    </table>
    
    <br>
    <div class="editCenter_display">
    <form  method="POST" name="bookEdit_form">
      <input type="submit" style="width:160px; height:35px" value="編集">
    </form>
        <form  method="POST" name="bookDelete_form">
      <input type="submit" style="width:160px; height:35px;" value="削除">
    </form>
    </div>
</body>
</html>