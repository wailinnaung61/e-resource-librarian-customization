<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<!DOCTYPE html>
<html>
    
<head>
	<title>Reset Password</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <style type="text/css">
	body,
		html {
			margin: 0;
			padding: 0;
			height: 100%;
			background: #475B9E !important;
		}
		.card {
			height: 350px;
			width: 400px;
			margin-top: auto;
			margin-bottom: auto;
			background: #FFFFFF;
			padding: 25px;
			box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
			-webkit-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
			-moz-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
			border-radius: 5px;

		}
		.brand_logo_container {
			height: 60px;
			width: 60px;
			background-color:#FFFF;
			border-radius: 50%;
			margin-right:5px;			
		}
		.brand_logo {
			height: 60px;
			width: 60px;
			border-radius: 50%;
			border: 2px solid white;
		}
		.logoheader
		{
		 margin-top:13px;
		 font-weight:500;
		 font-size:18px;
		 font-family: Arial, Helvetica, sans-serif;
		 color:gray;
		}
		.identity
		{
          font-family: Arial, Helvetica, sans-serif;
          font-size:21px;
		 font-weight:500;
		 margin-bottom:30px;
		}
		.second
		{
		margin-left:18px;
		font-size:13px;
		color:gray;
		}
		.btn
		{
		 margin-left:5px;
		 float:right;
		 margin-top:7px;
		}
		.codebtn
		{
		color:white;
		background-color:#475B9E;
		}
		.codebtn:hover
		{
		background-color:#667cc4;
		}
		.input-group-text
		{
		background-color:#475B9E;
		color:white;
		}
		.input-group i {
	    margin-left: -30px;
	    margin-top:13px;
	    cursor: pointer;
		}
		.oldpassword
		{
		width:99%;
		height:40px;
		border-radius:5px;
		border:1px solid #CED4DA;
		padding:10px;
		}
		
	</style>
    
</head>

<body>
	<div class="container h-100">
		<div class="d-flex justify-content-center h-100">		
			<div class="card">
			
			<div class="card-title">
			 <img src="resources/WhatsApp Image 2020-07-22 at 11.03.44 AM.jpeg" alt="Library of Nay Pyi Taw" class="brand-image img-circle elevation-3"
              style="width:50px;height:50px;">
             <span class="brand-text font-weight-white logoheader title">National Library Nay Pyi Taw</span>
             </div>
             
				  <div class="card-body">			 				    
				    
				    <form:form action="resetchange"  method="post" class="card-text">
				    <div class="input-group mb-3">
					<form:input type="password" id="txtPassword" placeholder="Change Password" path="changepassword" class="oldpassword" required="required"/>
					<i class="far fa-eye" id="togglePassword1"></i>
					</div>
					<br>
					 <div class="input-group mb-3">
					<input type="password" name="" id="password-field" class="oldpassword" placeholder="Confirm Password" required="required"/>	
					<i class="far fa-eye" id="togglePassword"></i>	
					</div>
					<br>
						<input type="submit" name="button" class="btn codebtn" onclick="return Validate()" value="Reset Password"/>&nbsp;&nbsp;											
					</form:form>
									  
				  </div>
           </div>
		</div><!-- center -->
	</div><!-- container -->
   <script type="text/javascript">
   
   function Validate() {
	    var password = document.getElementById("txtPassword").value;
	    var confirmPassword = document.getElementById("password-field").value;
	    if (password != confirmPassword) {
	        alert("Password and Confirm Password do not match!");
	        return false;
	    }
	    else
	    	{
	    return true;
	    	}
	}
   
	const togglePassword1 = document.querySelector('#togglePassword1');
	const password1 = document.querySelector('#txtPassword');

	togglePassword1.addEventListener('click', function (e) {
	    // toggle the type attribute
	    const type1 = password1.getAttribute('type') === 'password' ? 'text' : 'password';
	    password1.setAttribute('type', type1);
	    // toggle the eye slash icon
	    this.classList.toggle('fa-eye-slash');
	});
	
	const togglePassword = document.querySelector('#togglePassword');
	const password = document.querySelector('#password-field');

	togglePassword.addEventListener('click', function (e) {
	    // toggle the type attribute
	    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
	    password.setAttribute('type', type);
	    // toggle the eye slash icon
	    this.classList.toggle('fa-eye-slash');
	});
	</script>
</body>
</html>