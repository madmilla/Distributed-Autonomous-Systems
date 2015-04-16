<%@ page language="java" contentType="text/html"%>

<html>
<head>
<title>EL Demo page</title>
<link href="mycss.css" rel="stylesheet" type="text/css"> 
</head>
<body>
		<ul>
			<li>\${300 + 20*4 + 17%3}: ${300 + 20*4 + 17%3}
			<li>\${17/3}: ${17/3} <% 
               pageContext.setAttribute("i1",7);
               pageContext.setAttribute("i2",9);
            %>
			<li>\${i1+i2*100}: ${i1+i2*100} 
			<%-- 
            <li> \${i1 &lt i2}: ${i1<i2}    
            <li> \${i1 &lt i2 && i2&lt11}: ${i1<i2 && i2<11}    
			--%>
			<li>parameters: ${param}
			<li>par1: ${param.par1}
			<li>par2: ${param.par2}
			<li>headers: ${header}
			<li>header-cookie: ${header.cookie} <%        
               pageContext.setAttribute("i3","accept-language");
            %>
			<li>header-accept-language: ${header[i3]}
			<li>cookie: ${cookie}
			<li>sessie-cookie: ${cookie.JSESSIONID}
			<li>session-cookie-value: ${cookie.JSESSIONID.value}
			<li>pageContext: ${pageContext}
			<li>pageContext-session: ${pageContext.session}
			<li>pageContext-session-creationTime:
				${pageContext.session.creationTime}
		</ul>
</body>
</html>