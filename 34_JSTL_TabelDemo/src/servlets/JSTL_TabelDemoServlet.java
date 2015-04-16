package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.JSTL_TabelDemoBean;

@SuppressWarnings("serial")
public class JSTL_TabelDemoServlet extends HttpServlet
{

   public void init() throws ServletException
   {
      super.init();
	  ServletContext sc = getServletContext();
	  JSTL_TabelDemoBean ldb = new JSTL_TabelDemoBean();
	  int[][] m = new int[3][4];
      for(int i = 0; i<3; i++)
      {
    	  for(int j = 0; j<4; j++)
    	  {
    		  m[i][j] = 100*i+j;
    	  }
      }
	  
	  ldb.setTabel(m);
	  sc.setAttribute("tabelbean", ldb);  
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
	  RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/JSTL_ToonTabel.jsp");
	  dispatcher.forward(request, response);
  }
}

