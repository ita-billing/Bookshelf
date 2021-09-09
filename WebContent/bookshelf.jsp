<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="js/logout.js"></script>
<title>本棚</title>
<h2>本棚</h2>
<div class="right_display">
	<%
	  ResultSet rs = (ResultSet) request.getAttribute("result"); 
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
      <%
		while (rs.next()) {
      %>
	  <tr>
		<td class="table_hidden"><%=rs.getString(1)%></td>
		<td class="table_body"><%=rs.getString(2)%></td>
		<td class="table_body"><%=rs.getString(3)%></td>
		<td class="table_body center_display"><%=rs.getString(4)%></td>
		<td class="table_body center_display"><%=rs.getString(5)%></td>
		<td class="table_body center_display"><%=rs.getString(6)%></td>
		<td class="table_body center_display"><%=rs.getString(7)%></td>
			
			<td>
                <form action="<%=request.getContextPath()%>/change_edit" method="POST" name="edit_form">
                    <input type="hidden" name="SEQID" value="<%=rs.getString(1)%>">
                    <input type="hidden" name="TITLE" value="<%=rs.getString(2)%>">
                    <input type="hidden" name="AUTHORNAME" value="<%=rs.getString(3)%>">
                    <input type="hidden" name="PROGRESS" value="<%=rs.getString(4)%>">
                    <input type="hidden" name="STARTDATE" value="<%=rs.getString(5)%>">
                    <input type="hidden" name="ENDDATE" value="<%=rs.getString(6)%>">
                    <input type="hidden" name="EVALUATION" value="<%=rs.getString(7)%>">
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
    <form action="<%=request.getContextPath()%>/change_register" method="POST" name="register_form">
    <tr>
      <input type="submit" style="width:320px; height:35px" value="登録">
    </tr>
    </form>
    </div>
</body>
</html>