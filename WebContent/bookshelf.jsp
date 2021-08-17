<%@ page language="java" contentType="text/html; chars=utf-8" %>
<%@ page import="java.sql.*"%>
<html>
<head>
<meta chars="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>本棚</title>
<h2>本棚</h2>
<div class="right_display">
	<%
	  ResultSet rs = (ResultSet) request.getAttribute("result"); 
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
        <td class="table_header">タイトル</td>
        <td class="table_header">作者</td>
        <td class="table_header center_display">読破率</td>
        <td class="table_header center_display">開始日</td>
        <td class="table_header center_display">終了日</td>
        <td class="table_header center_display">評価</td>
        <td class="table_header center_display">修正・削除</td>
      </tr>
      <%
		while (rs.next()) {
      %>
	  <tr>
		<td class="table_body"><%=rs.getString(1)%></td>
		<td class="table_body"><%=rs.getString(2)%></td>
		<td class="table_body"><%=rs.getString(3)%></td>
		<td class="table_body"><%=rs.getString(4)%></td>
		<td class="table_body"><%=rs.getString(5)%></td>
		<td class="table_body"><%=rs.getString(6)%></td>
			
			<td>
                <form action="/jsp_servlet_test/Edit" method="POST">
                    <input type="hidden" name="title" value="<%=rs.getString(1)%>">
                    <input type="hidden" name="authorname" value="<%=rs.getString(2)%>">
                    <input type="hidden" name="progress" value="<%=rs.getString(3)%>">
                    <input type="hidden" name="startdate" value="<%=rs.getString(4)%>">
                    <input type="hidden" name="enddate" value="<%=rs.getString(5)%>">
                    <input type="hidden" name="evaluation" value="<%=rs.getString(6)%>">
                    <input type="submit" value="修正・削除">
                </form>
            </td>
		</tr>
		<%
			}
		%>
    </table>

    <br>
    <div class="center_display">
    <tr>
      <input type="submit" style="width:320px; height:35px" value="登録">
    </tr>
    </div>
</body>
</html>