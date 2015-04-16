<%@ page language="java" contentType="text/html" %>
<jsp:useBean id    = "listbean" 
             class = "beans.ListDemoBean" 
             scope = "application" />

<%! 
	public void jspInit()
	{
	    log("toonlijst init");
	}   
%>

<html>
   <head>
      <title>toonlijst page</title>
      <link href="mycss.css" rel="stylesheet" type="text/css">
   </head>
   <body>
   <div id = "centeredcontent">
         toonlijst page
   </div>
         <br>
         <hr>
         <ol>  
 
<%
      int len = listbean.getLen();
      for (int i=0 ; i<len; i++)
      {
          pageContext.setAttribute("i",i);
%>

             <li> ${listbean.list[i]} </li>
<%
      }
%>
         </ol>
         <br>
         <hr>
   </body>
</html>