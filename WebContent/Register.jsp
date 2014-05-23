<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" 
                  src="Scripts/common.js"></script>
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js">

 </script>   
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
<link rel="shortcut icon" href="aero2.ico" >
<link rel="stylesheet" type="text/css" href="Scripts/common.css" media="screen" />
</head>
<body  onload="checkuserexists()" background="2.jpg" style="background-color:#B2B2B2">
<div class="signin-box" style="float:left;width:96.5%;margin-top:0px;margin-left:0px;border:1px">
<font size=100 color="black" style="margin-left:442px" face="verdana">Fly High Airlines
</font>

</div>
<form action="CustomerRegistration" method="post" onsubmit="validateregistration()">
<div id="register1" class="signin-box">
<label>
<strong class="uname-label">
Sign up
</strong>
</label>
<div class="divunamepwd">
<label>
<strong class="uname-label">
Name<sup>*</sup>
</strong>
</label>

<input type="text" id="Name" class="logininput" name="Name"/>
</div>
<div class="divunamepwd">
<label>
<strong class="uname-label">
Username<sup>*</sup>
</strong>
</label>

<input type="text" id="unamereg" name="unamereg" class="logininput"/>
</div>
<div class="divunamepwd">
<label>
<strong class="uname-label">
Password <sup>*</sup>
</strong>
</label>

<input type="password" id="passwordreg" name="passwordreg" class="logininput"/>
</div>
<div class="divunamepwd">
<label>
<strong class="uname-label">
Confirm Password <sup>*</sup>
</strong>
</label>

<input type="password" id="repasswordreg" class="logininput"/><br><br>
<div id="errormsg" style="color:#FF0000;display:hide"></div>
</div>
<input type="submit" id="btn_submitregistration" value="Register"/>
<input type="button" id="btn_resetregistration" onclick="resetregistration()" value="Reset"/>
<a id="Login" href="LoginPage.jsp" class="a">Go to Login Page</a>


</div>
</form>

</body>
</html>