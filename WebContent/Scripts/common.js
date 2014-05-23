function validateregistration()
{
	var name;
	name=$('#Name').val();
	var regexpforusername=/^[0-9a-zA-Z]+$/;
	var regexpforpassword=/^[0-9a-zA-Z*@%]+$/;
	var username=$('#unamereg').val();	
	password1 = $('#passwordreg').val();  
	password2=$('#repasswordreg').val();
	if(name=="")
	{ 
		$('#errormsg')[0].innerHTML="";
		$('#errormsg')[0].innerHTML="Error: Name field cannot be empty";
		$('#errormsg').show();
		$('#Name').focus();
		return false;
	}
	else if(name.length<3){
		
		 $('#errormsg')[0].innerHTML="";
			$('#errormsg')[0].innerHTML="Error: Name should be atleast 3 characters";
			$('#errormsg').show();
			$('#Name').focus();	
		return false;
	}
	else if(username=="")
	{   $('#errormsg')[0].innerHTML="";
		$('#errormsg')[0].innerHTML="Error: Username field cannot be empty";
		$('#errormsg').show();
		$('#unamereg').focus();
	return false;
	}
	else if(username.length<3 )
	{
		
		 $('#errormsg')[0].innerHTML="";
		$('#errormsg')[0].innerHTML="Error: Username should be atleast 3 characters";
			$('#errormsg').show();
			$('#unamereg').focus();	
		return false;
	}
	else if(!regexpforusername.test(username)){
		$('#errormsg')[0].innerHTML="";
		$('#errormsg')[0].innerHTML="Error: Username must be Alphanumeric with no spaces";
		$('#errormsg').show();
		$('#unamereg').focus();
		return false;
		}

	else if(password1.length>0 && password1.length<3 ){
		
		 $('#errormsg')[0].innerHTML="";
			$('#errormsg')[0].innerHTML="Error: Password should be atleast 3 characters";
			$('#errormsg').show();
			$('#password1').focus();	
		return false;
	}
	else if(password1!=""&&password1.length>3&&!regexpforpassword.test(password1)){
		$('#errormsg')[0].innerHTML="";
		$('#errormsg')[0].innerHTML="Error: Password accepts alpahnumeric,* and @";
		$('#errormsg').show();
		$('#password1').focus();
		return false;
	}
	else if(password1==""|| password2=="")
	{		$('#errormsg')[0].innerHTML="";
			$('#errormsg')[0].innerHTML="Error: Password field cannot be empty";
			$('#errormsg').show();
			$('#password1').focus();
		return false;
	}

	
	else if(password1.trim()!=password2.trim())
		{
		$('#errormsg')[0].innerHTML="";
		$('#errormsg')[0].innerHTML="Error: Two password should match";
		$('#errormsg').show();
		$('#password1').focus();
		return false;
		}
	
		return true;
	
	}


	$('.button1').click(function(){			
		 var id = this.id;
	     var flightid=id.substring('flight_'.length);
	     idflight.setText(flightid);
	
	});


function getQueryString()
{
	var vars=[];
	var hash;
	var hashes=window.location.href.slice(window.location.href.indexOf('?')+1).split('&');
	for(var i =0;i<hashes.length;i++)
		{
		hash=hashes[i].split('=');
		vars.push(hash[0]);
		vars[hash[0]]=hash[1];
		}
	return vars;
	
	
}

function print1()
{
	
	$('#btn_print').printPreview();
	}


function checkuserexists(){
$('#Name').focus();
var queryparam=getQueryString();
if(queryparam!=null && queryparam!=undefined)
	{
var Errormessage=queryparam["Error"];
if(Errormessage=="UserExists")
	{
	$('#errormsg')[0].innerHTML="";
	$('#errormsg')[0].innerHTML="User with same Username already exists";
	}
	}
}

function validatelogin()
{
	$('#uname').focus();	
	var queryparam=getQueryString();
	if(queryparam!=null && queryparam!=undefined)
		{
	var Errormessage=queryparam["Error"];
	if(Errormessage=="usernamedoesnotexist")
		{
		$('#errormsg1')[0].innerHTML="";
		$('#errormsg1')[0].innerHTML="Username does not exist";
		$('#uname').focus();
		}
	else if(Errormessage=="wrongpassword")
		{
		$('#errormsg1')[0].innerHTML="";
		$('#errormsg1')[0].innerHTML="Username and Password does not match";
		$('#uname').focus();
		}
		}
	}

	function onLoginClick()
	{
	
		username = $('#uname').val();
		password = $('#password').val();
		 if(username=="")
		{   $('#errormsg1')[0].innerHTML="";
			$('#errormsg1')[0].innerHTML="Error: Username field cannot be empty";
			$('#errormsg1').show();
			$('#uname').focus();
		return false;
		}
		 if(password=="")
			{		$('#errormsg1')[0].innerHTML="";
					$('#errormsg1')[0].innerHTML="Error: password field cannot be empty";
					$('#errormsg1').show();
					 $('#password').focus();
					 return false;
			}
	
	}

	function resetregistration()
	{
		 $('#errormsg')[0].innerHTML="";
	    $("#register1 :input").each(function(){
	       if(this.type=='password'||this.type=='text')
	    	$(this).val("");
	       
	    });	
	    $('#uname').focus();
	    
	}
	
	
	
	$("#Cancel").click(function(){
		   document.location.href='QueryPage.jsp';
		});
	
	
	