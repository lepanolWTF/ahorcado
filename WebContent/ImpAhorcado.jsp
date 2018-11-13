<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%@ page session="false" %>
    <%String[]  estado=(String[])request.getAttribute("estado"); %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ahorcado</title>
<style type="text/css">
table, tr, td {
   border: 1px solid black;
}
</style>
</head>
<body>
	<h1>Ahorcado</h1>
	<table>
		<tr>
			<%for(int i=0;i<estado.length;i++){ 
				if(estado[i]==null){%>
					<td>_</td>
				<%}else{ %>
					<td><%=estado[i] %></td>
			<%} }%>
		</tr>
	</table>
	<form action="Ahorcado" method="post">
	
	<input type="submit" value="enviar">
	</form>
</body>
</html>