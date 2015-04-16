package servlets;

import beans.*;

//AantalBezoekers.java

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class MVC_DemoServlet extends HttpServlet
{ 
	
   CounterBean acb;	
	
   public void init() throws ServletException
   {
      super.init();
	  ServletContext sc = getServletContext();
	  acb = new CounterBean();
	  sc.setAttribute("application_counter", acb);  
      log("servlet init");
   }
   
   public void destroy()
   {
      log("servlet destroy");
      super.destroy();
   }
   
   public void doGet(HttpServletRequest request,HttpServletResponse response)
                                         throws ServletException, IOException
   {
	  HttpSession session = request.getSession();
	  CounterBean scb = (CounterBean)session.getAttribute("session_counter");
	  if (scb!= null && scb.getCurrentValue() > 2)
	  { 
		  session.invalidate();
		  response.sendRedirect("/24_MVC_Demo");
	  }	  
	  else 
	  {	  if (scb == null)
	      {
		      scb = new CounterBean();
	      }
	      session.setAttribute("session_counter", scb);
	  	  RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/MVC_Demo.jsp");
	      dispatcher.forward(request, response);
	  }    
   }	  
}

