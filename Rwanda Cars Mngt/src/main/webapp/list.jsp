<%@page import="java.util.*"%>
<%@page import="Dao.GariDao"%>
<%@page import="Models.Gari"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rwanda Cars Management</title>
</head>
<body>
<table>
		<tr>
			<th>Plate No</th>
			<th>Cost</th>
			<th>Manufacturing Date</th>
			<th>institution</th>
			<th>action</th>
		</tr>	
		<% 
		
		Gari gari = new Gari();
		
		GariDao dao = new GariDao(gari);
		
		List<Gari> listGari = dao.findAll();
		
		for(Gari g : listGari){
			 pageContext.setAttribute("gari", g);
		
		
		%>
		<tr>
			<td>${gari.plate}</td>
			<td>${gari.cost}</td>
			<td>${gari.date}</td>
			<td>${gari.institute}</td>
            <td>
             <a href="Gariforms.jsp?id=${gari.plate}">Edit</a>
             <a href="AllFunctions/delete?id=${gari.plate}">Delete</a>
            
		</tr>
		<%
		} 
		%>
	</table>
</body>
</html>