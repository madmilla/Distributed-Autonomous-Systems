package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class EL_DemoServlet extends HttpServlet
{
   public void init() throws ServletException
   {
      super.init();
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
	  RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/EL_Demo.jsp?par1=55&par2=99");
	  dispatcher.forward(request, response);
   }
}

