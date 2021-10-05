<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" import="com.ibm.csbd.demo.guestbook.model.*,java.util.*" %>

<!DOCTYPE html >
<html lang="en">
  <head>
    <title>Guestbook demo</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <h1>Welcome to Guestbook!</h1>
    <%
    String logo = System.getenv("LOGO");
    
    if(logo == null || !logo.equals("aws")) {
      logo = "IBM_Cloud";
    }

    %>
    Application running in: <img src="<%=logo%>_logo.png" height="100">
    <h2>Create new entry</h2>
    <form method="POST" action="Guestbook">
    Your comment: <br>
    <textarea name="comment" rows="4" cols="50"></textarea> 
    <BR>
    Your name: <input type="text" name="name">
    <BR>
    <input type="submit" value="Add Entry">

    </form>    

      <h2>Current entries:</h2>
      <%
      List<BookEntry> l = (List<BookEntry>)request.getAttribute("allEntries");

      for (BookEntry entry : l) {
        out.println(entry.getDate() + "<BR>");
        out.println(entry.getComment() + "<BR>");
        out.println(entry.getName() + "<BR><HR>");

      }


      %>



      <%
      String error = request.getParameter("error");
      if(error != null) {
        out.println(error + "<BR>");
      }

      %>

  </body>
</html>    