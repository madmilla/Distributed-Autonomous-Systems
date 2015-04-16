<%@ page language="java" contentType="text/html" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%! 
	public void jspInit()
	{
	    log("JSTLDemo init");
	}   
%>

<html>
   <head>
      <title>jstldemo page</title>
      <link href="mycss.css" rel="stylesheet" type="text/css">
   </head>
   <body>
          <c:set var="j" value="4" scope = "page"/>
          <c:set var="k" value="3" scope = "page"/>
		  <c:out value="${j} + ${k} = ${j+k}" />
		  <br>
          =========================
          <br/> 
          <c:forEach var="i" begin="1" end="10">
               ${i*i-1} <br>
          </c:forEach>
          =========================
          <br/>
          <c:forTokens var="token" items="rood,wit,blauw" delims = "," >
              ${token} <br>
          </c:forTokens>
          =========================
          <br>
		  <c:if test="${j+k==7}">
                ${j}+${k} == 7
          </c:if>
          <br>
          =========================
          <br/>
          <c:choose> 
              <c:when test="${j>k}"> 
                 ${j} &gt ${k}
              </c:when> 
              <c:when test="${j==k}"> 
                 ${j} == ${k}
              </c:when> 
              <c:otherwise> 
                 ${j} &lt ${k} 
              </c:otherwise> 
          </c:choose> 
          <br>
          =========================
          <br>
          <c:set var="n" value="twee"/>
          <c:catch var="MyException">
              ${k/n} <br>
          </c:catch>
          <c:choose>
              <c:when test="${MyException != null}">
                  Foutje.
              </c:when>
              <c:otherwise>
                  Alles gaat goed.
              </c:otherwise>
          </c:choose>
          <br>
          =========================
          <br>
  
          <h3>Remove Example</h3>
           j: ${j}<br>           
           <c:remove var="j" />
           After remove: j: ${j} <br> 
           j == null: ${j == null} 
   </body>
</html>