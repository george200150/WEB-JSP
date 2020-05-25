<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Problema2</title>
  <link rel="stylesheet" type="text/css" href="mystyle.css">
</head>

<body>
<br/>
<div class = "form">
<form action="${pageContext.request.contextPath}/verifiy" method="post">
  <br/>
  <img alt="Click to generate an image!" height="200" width="300" src="data:image;base64, ${captcha}"><br/>
  <br/>
  <input type="text" placeholder="Please fill the input" name="captchaInput">
  <br/>
  <br/>
  <input type="submit" name="verify" value="Check"><br/>
</form>
<p>${message}</p>
</div>
</body>
</html>
