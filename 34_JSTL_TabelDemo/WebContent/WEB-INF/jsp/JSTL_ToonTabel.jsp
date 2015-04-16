<%@ page language="java" contentType="text/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%!public void jspInit() {
		log("toonlijst init");
	}%>

<html>

<head>
<title>toontabel page</title>
<link href="mycss.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div id="centeredcontent">toontabel page</div>
	<br>
	<hr>
	<c:forEach var="item" items="${tabelbean.tabel}">
		<c:forEach var="item2" items="${item}">
                         ${item2} 
                 </c:forEach>
		<br />
	</c:forEach>
	<br>
	<hr>
	<div id="centeredcontent">
	<table class="center">
			<tr class="trcolor1">
				<th>kolom 1</th>
				<th>kolom 2</th>
				<th>kolom 3</th>
				<th>kolom 4</th>
			</tr>
			<c:forEach var="item" items="${tabelbean.tabel}">
				<tr class="trcolor2">
					<c:forEach var="item2" items="${item}">
						<td>${item2}
					</c:forEach>
				</tr>
			</c:forEach>
	</table>
	</div>
</body>
</html>