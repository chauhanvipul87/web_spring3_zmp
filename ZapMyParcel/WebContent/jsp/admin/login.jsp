<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zap My Parcel</title>
<style type="text/css">
#login_body {padding:0; background:url(images/bg_login.gif);}
.login_container {margin:0 auto; margin-top:12%; width:428px; background:url(images/shadow.png) center bottom no-repeat;}
.login_box {
	background:#fff url(images/bg_login-box.gif) left bottom repeat-x;
	width:327px;
	border-radius:7px;
	padding:5px 0;
	border:1px solid #e4e4e4;
}
.login_box input[type=text], .login_box input[type=password] {
	border:1px solid #b4b4b4;
	outline: none;
	width:97%;
	padding:4px 0;
	border-radius:5px;
}
.login_box span {
	font-family:Arial, Helvetica, sans-serif;
	font-size:13px;
	font-weight:bold;
	color:#181818;
}
.login_box a img {
	border:0;
}
.error {
	color: #D8000C;
	background-color: #FFBABA;
	background-image:url(images/error.png);
	border: 1px solid;
	margin: 10px 0px;
	padding:8px 8px 8px 50px;
	background-repeat: no-repeat;
	background-position: 10px center;
}

</style>
</head>
<body id="login_body">
<div class="login_container">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" style="padding-bottom: 10px;">
				<img src="images/zmp_logo.png" width="233" />
			</td>
		</tr>
		<tr>
			<td align="center" valign="middle" style="padding: 0 0 35px 0;">
				<div class="login_box">
					<form name="loginForm" action="adminlogin.html" method="post">
					<table width="100%" border="0" cellspacing="10" cellpadding="0" onKeyPress="return submitenter(event)">
						<tr id="errorId" style="display: none;">
							<td colspan="2" align="left">
								<div id="errosMsg" class="error">${errorMsg}</div>
							</td>
						</tr>
						<tr>
							<td align="left" valign="middle"><span>Username :</span></td>
							<td align="left" valign="middle">
								<label for="username"></label>
								<input type="text" name="username" id="username" />
							</td>
						</tr>
						<tr align="left" valign="middle">
							<td><span>Password :</span></td>
							<td><input type="password" name="password" id="password" /></td>
						</tr>
						<tr align="left" valign="middle">
							<td>&nbsp;</td>
							<td>
								<a style="display: block; line-height: 32px;" href="javascript:void(0);" onclick="return validateLogin()">
									<img src="images/go_btn.png" alt="login" />
								</a>
							</td>
						</tr>
					</table>
					</form>
				</div>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward(); 
	}
	$(document).ready(function() {
		$("#username").focus();
		var text = $("#errosMsg").html();
		if(text==""){
			$("#errorId").hide();
		}else{
			$("#errorId").show('fast').delay(5000).hide('fast');
		}
	});
	function clearMsg(){
		$("#errorId").hide();
	}
	function validateLogin(){
		var t=setTimeout("clearMsg()",5000);
		var username = $("#username").val();
		var password = $("#password").val();
		if(username=="" && password==""){
			$("#errosMsg").html("Please enter username and password.");
			$("#errorId").show();
			return false;
		}
		if(username==""){
			$("#errosMsg").html("Please enter username.");
			$("#errorId").show();
			return false;
		}
		if(password==""){
			$("#errosMsg").html("Please enter password.");
			$("#errorId").show();
			return false;
		}
		if(username != "" && password != ""){
			$("#errorId").hide();
			document.loginForm.submit();
			return true;
		}
	}
	
	function submitenter(e){
		var keycode;
		if (window.event) 
			keycode = window.event.keyCode;
		else if (e) 
			keycode = e.which;
		else 
			return false;
		if (keycode == 13){
			validateLogin();
		}
	} 
</script>
</body>
</html>