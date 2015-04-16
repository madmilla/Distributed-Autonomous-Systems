package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.QueryBean;

@SuppressWarnings("serial")
public class DabaQueryServlet extends HttpServlet
{

   public void init() throws ServletException
   {
      super.init();
	  ServletContext sc = getServletContext();
	  QueryBean qb = new QueryBean();
	  
	  qb.setQuery("select * from sporten");
	  sc.setAttribute("querybean", qb);  
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
	  RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ToonQueryResult.jsp");
	  dispatcher.forward(request, response);
  }
}

