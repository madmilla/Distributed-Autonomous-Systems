<%@ page language="java" contentType="text/html"%>

<%--  
<jsp:useBean id="application_counter" class= "beans.CounterBean" scope="application" />
<jsp:useBean id="session_counter" class= "beans.CounterBean" scope="session" />
--%>

<%! 
    private int aantal = 0;	

    public void jspInit()
	{
//	    super.jspInit();
	    log("jsp init");
	}   
	
	public void jspDestroy()
	{
	    log("jsp destroy");
//	    super.jspDestroy();
	}  
%>

<html>
<head>
<title>MVC demo page</title>
<link href="mycss.css" rel="stylesheet" type="text/css"> 
</head>
<body>
	<h1>
		<%--              
            applicatie-count: <jsp:getProperty name = "application_counter" property = "nextValue" /> <br>
            session-count: <jsp:getProperty name = "session_counter" property = "nextValue" />  <br>
        --%>
		application-count: ${application_counter["nextValue"]} <br>
		session-count: ${session_counter["nextValue"]} <br> <a
			href=<%= response.encodeURL("/24_MVC_Demo") %>> herhaal </a><br>
	</h1>
</body>
</html>


