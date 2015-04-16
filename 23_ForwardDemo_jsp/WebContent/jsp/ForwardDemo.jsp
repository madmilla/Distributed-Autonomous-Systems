<%@ page language="java" contentType="text/html"%>

<%! 
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

<jsp:forward page="forward.html"></jsp:forward>


