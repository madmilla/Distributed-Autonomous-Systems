<%@ page language="java" contentType="text/html"%>

<%!public void jspInit() {
		log("calculate init");
	}%>

<html>
<head>
<title>calculate page</title>
<link href="mycss.css" rel="stylesheet" type="text/css"> 
</head>
<body>
		calculate page <br>
		<hr>

		<%
			String sx = request.getParameter("xvalue");
			String sy = request.getParameter("yvalue");
			int x = Integer.parseInt(sx);
			int y = Integer.parseInt(sy);
			out.println("" + x + " + " + y + " = " + (x + y));
		%>
		<br>
		<hr>
		<a href="formdemo.html"> terug </a>
</body>
</html>