package servlets;

//SessionDemo.java

import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class SessionDemo extends HttpServlet
{
   private int aantal = 0;
   
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
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      HttpSession session = request.getSession();
      Integer count = (Integer)session.getAttribute("session_count");
      if (count== null)
    	  count = 1;
      else 
    	  count = count +1;
      session.setAttribute("session_count", count);
      
      synchronized(this)
      {
          String s =
             "<html>\n" +
             "   <head>\n" +
             "       <title>\n" +
             "          Session demo page\n" +
             "       </title>\n" +
             "   </head>\n" +
             "   <body bgcolor=\"#8AAFED\">\n" +
             "      <center>\n" +
             "         <h1>\n" +
             "            applicatie-count: " + ++aantal + "<br>\n" +
             "            session-count: " + count + "<br>\n" +
             "            <a href=" + response.encodeURL("/15_SessionDemo/") + "> herhaal </a><br>\n" +
             "         </h1>\n" +
             "      </center>\n" +
             "   </body>\n" +
             "</html>\n";
          out.print(s);
      }
      if (count >=3)
    		  session.invalidate();
   }
}

