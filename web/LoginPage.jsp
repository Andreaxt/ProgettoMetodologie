<%--
  Created by IntelliJ IDEA.
  User: Andrea
  Date: 28/04/2017
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<style>
  input[type=text], select {
    width: 50%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }

  input[type=password], select {
    width: 50%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }

  input[type=submit] {
    width: 50%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  input[type=submit]:hover {
    background-color: #45a049;
  }

  form {
    height: 50%;
    border-radius: 5px;
    background: gray;
    padding: 20px;
  }
</style>
<head>

  <title>$LoginPage$</title>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.css">
  <link href="style.css" rel="stylesheet" type="text/css">

</head>

<body>


<form action="login.do" method="post"  >
  Username <input type="text" name="user"><br>
  Password <input type="password" name="psw"/><br>
  <input type="submit" id="invio" value="Login">
</form>

</body>
</html>
