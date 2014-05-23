<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Login</title>
<link rel="shortcut icon" href="aero2.ico" >
<script type="text/javascript" 
                  src="Scripts/common.js"></script>
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<link rel="stylesheet" type="text/css" href="Scripts/common.css" media="screen" />
</head>
<body onload="validatelogin()" background="2.jpg" style="background-color:#B2B2B2">



<div class="signin-box" style="float:left;width:63.5%;margin-top:0px;margin-left:230px;border:1px">
<font size=100 color="black" style="margin-left:221px" face="verdana">Fly High Airlines
</font>
</div>
<form action="loginpattern" method="post" onsubmit="return onLoginClick()">
<div id="signin" class="signin-box">

<label>
<strong class="uname-label">
Sign in
</strong>
</label>

<div class="divunamepwd">
<label>
<strong class="uname-label">
Username
</strong>
</label>

<input type="text" id="uname" name="uname"  class="logininput"></input>
</div>
<div class="divunamepwd">
<label>
<strong class="uname-label">
Password
</strong>
</label>
<input type="password" id="password" name="password" class="logininput"/>
</div>

<div id="errormsg1" style="color:#FF0000;display:hide"></div><br>
<input type="submit" name="Signin" value="Sign in"/>    New users

register <a id="register" href="Register.jsp"> here</a>
</div>

</form>
</body>
</html>