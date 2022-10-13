<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Registration</title>
</head>
<body>
<form action="/registration" method="POST">
  Имя пользователя: <input type="text" name="name"><br>
  Введите email: <input type="email" name="email"><br>
  Введите пароль: <input type="password" name="password"><br>
  <input type="submit" value="Зарегистрироваться"/>
</form>
<#if errorMessage??>
  <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>