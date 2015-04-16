package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.JSTL_ListDemoBean;

import java.util.*;

@SuppressWarnings("serial")
public class JSTL_ListDemoServlet extends HttpServlet
{

public void init() throws ServletException
   {
      super.init();
	  ServletContext sc = getServletContext();
	  JSTL_ListDemoBean ldb = new JSTL_ListDemoBean();
	  ArrayList<String> al = new ArrayList<String>();
	  al.add("piet");
	  al.add("jan");
	  al.add("kees");
	  ldb.setList(al);
	  sc.setAttribute("listbean", ldb);  
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
	  RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/JSTL_ToonLijst.jsp");
	  dispatcher.forward(request, response);
  }
}

