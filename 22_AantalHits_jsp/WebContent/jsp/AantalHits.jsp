<%@ page language="java" contentType="text/html"%>

<%!private int aantal = 0;

	public void jspInit() {
		//super.jspInit();
		log("jsp init");
		ServletConfig sc = getServletConfig(); //config is nog niet bekend
		try {
			String s = sc.getInitParameter("aantal");
			aantal = Integer.parseInt(s);
			log("aantal: " + aantal);
		} catch (Exception e2) {
		}
	}

	public void jspDestroy() {
		log("jsp destroy");
		//super.jspDestroy();
	}%>

<%
	synchronized (this) {
		aantal++;
	}
%>

<html>
<head>
<title>Aantal hits page</title>
<link href="mycss.css" rel="stylesheet" type="text/css"> 
</head>
<body>
	<h1>
		Aantal hits:
		<%=aantal%><br>
	</h1>
</body>
</html>

