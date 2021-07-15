<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>本棚</title>
<h2>本棚</h2>
<div class="right_display">
	<%
	  String name = (String)session.getAttribute("name");
	%>

  <th>ID: <%= name %> </th>
  <br>
  <input type="submit" value="ログアウト">
</div>
<td>
</head>
<body class="background_color">
   
    <h3>一覧</h3>
    <table border ="1" align="center" class="borderline">
      <tr>
        <td class="table_header">名称</td>
        <td class="table_header">読破率</td>
        <td class="table_header">開始日</td>
        <td class="table_header">終了日</td>
        <td class="table_header">評価</td>
        <td class="table_header">修正・削除</td>
      </tr>

      <tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td><input type="submit" value="修正・削除"></td>
	    </tr>
    
    </table>

    <br>
    <div class="center_display">
    <tr>
      <input type="submit" style="width:320px; height:35px" value="登録">
    </tr>
    </div>
</body>
</html>