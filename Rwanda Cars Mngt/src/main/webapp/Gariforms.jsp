
<%@page import="java.util.Map"%>
<%@page import="Dao.GariDao"%>
<%@page import="Models.Gari"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    String id = request.getParameter("id"); 
    
    Gari gari = null;
    
    GariDao dao = new GariDao(gari);
    if (id != null) {
    	gari = dao.findById(id);
    } else {
    	gari = (Gari)session.getAttribute("edit");
        session.removeAttribute("edit");

        Map<String, String> errors = (Map)session.getAttribute("errors");
        pageContext.setAttribute("stuckerror", errors);
        session.removeAttribute("errors");
    }
    pageContext.setAttribute("gari", gari);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rwanda Cars Management</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>
	<form method="post">
		Plate: <input type="text" name="plate" value="${gari.plate}"><label class="error">${stuckerror["plate"]}</label><br><br>
		Cost : <input type="text" name="cost" value="${gari.cost}"><label class="error">${stuckerror["cost"]}</label><br><br>
		Date : <input type="date" name="date" value="${gari.date}"><label class="error">${stuckerror["date"]}</label><br><br>
		inst : <select name="institution">
			<option>AUCA</option>
			<option>UoK</option>
			<option>MountK</option>
			
		</select><br><br>
		
        <% if (id == null) { %>
                   <button formaction="AllFunctions">save</button>
        <% } else if (gari != null) { %>
                   <button formaction="AllFunctions/update">Update</button>
        <% } %>
		
	</form>
</body>
</html>