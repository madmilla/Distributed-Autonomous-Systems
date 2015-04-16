package servlets;

//AantalBezoekers.java

import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AantalHits extends HttpServlet
{
  private int aantal = 0;

  public void init() throws ServletException{
    super.init();
    log("servlet init");
    String s = getInitParameter("aantal");
    try{
    aantal   = Integer.parseInt(s);
    }catch (Exception e){}
  }
   
  public void destroy(){
    log("servlet destroy");
    super.destroy();
  }
   
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String s1 =
    "<html>\n" +
    "   <head>\n" +
    "       <title>\n" +
    "          Aantal hits page\n" +
    "       </title>\n" +
    "   </head>\n" +
    "   <body bgcolor=\"#8AAFED\">\n" +
    "      <center>\n" +
    "         <h1>\n";

    String s2 = "";
    synchronized(this){
      s2 ="            Aantal hits: " + ++aantal + "\n";
    }
    String s3 =
    "         </h1>\n" +
    "      </center>\n" +
    "   </body>\n" +
    "</html>\n";
    out.print(s1+s2+s3);
  }
}

