<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Problema3</title>
  </head>
  <body>
  <br/><br/>
  <a href="login.jsp">Login</a><br/><br/>

  <label for="comentariu">Adauga un comentariu:</label><br/>
  <textarea name = "comentariu" id="comentariu" form="usrform"></textarea>
  <br/>
  <form action="/addComment" id="usrform" method="post">
      <input type="submit" value="Comment">
  </form>
  </body>
</html>
