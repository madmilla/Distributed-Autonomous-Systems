package servlets;

//SecurityDemo.java

import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SecurityDemo extends HttpServlet
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
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String s =
         "<html>\n" +
         "   <head>\n" +
         "       <title>\n" +
         "          Security demo page\n" +
         "       </title>\n" +
         "   </head>\n" +
         "   <body bgcolor=\"#8AAFED\">\n" +
         "      <center>\n" +
         "         <h1>\n" +
         "            welkom " + request.getRemoteUser() + "\n" +
         "         </h1>\n" +
         "      </center>\n" +
         "   </body>\n" +
         "</html>\n";
      out.print(s);
   }
}

