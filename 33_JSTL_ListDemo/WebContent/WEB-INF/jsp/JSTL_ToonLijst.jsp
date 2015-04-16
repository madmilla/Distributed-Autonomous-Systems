<%@ page language="java" contentType="text/html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%! 
	public void jspInit()
	{
	    log("jstl_toonlijst init");
	}   
%>

<html>
   <head>
      <title>jstl_toonlijst page</title>
      <link href="mycss.css" rel="stylesheet" type="text/css">
   </head>
   <body>
      <div id = "centeredcontent">
         toonlijst page
      </div>
      <br>
      <hr>
          <ol>
             <c:forEach var="item" items= "${listbean.list}">
                 <li>
                     ${item} 
                 </li>
             </c:forEach>
          </ol>
          <br>
      <hr>
          <ol>
             <c:forEach var="item" items= "${listbean.list2}">
                 <li>
                     ${item} 
                 </li>
             </c:forEach>
          </ol>
          <br>
      <hr>

   </body>
</html>