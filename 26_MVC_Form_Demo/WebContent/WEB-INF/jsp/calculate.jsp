<%@ page language="java" contentType="text/html"%>
<!-- 
<jsp:useBean id="calculator" class= "beans.CalculatorBean" />
<jsp:setProperty name = "calculator" property = "*" />
-->

<%! 
	public void jspInit()
	{
	    log("doTelop init");
	}   
%>

<html>
<head>
<title>calculate result page</title>
<link href="mycss.css" rel="stylesheet" type="text/css">
</head>
<body>
	calculate result page
	<br>
	<hr>
	<!--          
		<jsp:getProperty name = "calculator" property = "xvalue" /> 
		+
		<jsp:getProperty name = "calculator" property = "yvalue" />
		=
		<jsp:getProperty name = "calculator" property = "sum" />
		-->
	${calc["xvalue"]} + ${calc["yvalue"]} = ${calc["sum"]}
	<br>
	<hr>
	<a href="formdemo.html"> terug </a>
</body>
</html>