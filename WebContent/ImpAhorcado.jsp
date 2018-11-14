<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
    <%String[]  estado=(String[])request.getAttribute("estado");
    int vidas=(int) request.getAttribute("vidas");
    int vida=vidas-1;
    ArrayList<String> erroneas = new ArrayList<String>();
    erroneas=(ArrayList<String>) request.getAttribute("erroneas");%>
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ahorcado</title>
<style type="text/css">
	tr, td{
	   	border: 1px solid black;
	   	border-radius: 5px;
	   	width: 8%;
		height: 50px;
	}
	*{
		aling: auto;
		text-align: center;
	}
	table{
		margin: auto;
	}
</style>
</head>
<body>
	<div>
		<h1>Ahorcado</h1>
		<table>
			<tr>
				<%for(int i=0;i<estado.length;i++){ 
					if(estado[i]==null){%>
						<td>_</td>
					<%}else{ %>
						<td><%=estado[i] %></td>
				<%}}%>
			</tr>
		</table><br/><br/>
		
		<img id="home" src="./img/<%=vidas%>.jpg"><br><br>
		Letras introducidas qye son erroneas:<%for(int i=0;i<erroneas.size();i++){ 
													if(i==0){%>
														<%=erroneas.get(i)%>
													<%}else{ %>
														,<%=erroneas.get(i)%>
												<%}}%><br/>
		Vidas restantes: <%=vida%>
		<form action="Ahorcado" method="post">
			<input type="text" name="letra"><br/>
		<input type="submit" value="enviar">
		</form>
		<br/>
		<a href="Ahorcado?empezar">Empezar</a>
	</div>
</body>
</html>