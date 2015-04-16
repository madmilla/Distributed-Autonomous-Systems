package servlets;

//Forward.java

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ForwardDemo extends HttpServlet
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
      RequestDispatcher dispatcher = request.getRequestDispatcher("forward.html");
      dispatcher.forward(request, response);
   }
}

