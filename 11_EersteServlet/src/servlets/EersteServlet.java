package servlets;

//EersteServlet.java

import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class EersteServlet extends HttpServlet
{
   public void doGet(HttpServletRequest request,HttpServletResponse response)
                                         throws ServletException, IOException
   {
      response.setContentType("text/html");
      
      PrintWriter out = response.getWriter();
      String s =
      "<!doctype html public \"-//w3c//dtd html 4.0 Transitional//EN\">\n" +
      "<html>\n" +
      "   <head>\n" +
      "       <title>\n" +
      "          Eerste Servlet page\n" +
      "       </title>\n" +
      "   </head>\n" +
      "   <body bgcolor=\"#8AAFED\">\n" +
      "      <center>\n" +
      "         <h1>\n" +
      "            Eerste Servlet\n" +
      "         </h1>\n" +
      "      </center>\n" +
      "   </body>\n" +
      "</html>\n";

      out.print(s); 
   }
}
