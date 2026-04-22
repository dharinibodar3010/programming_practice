<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up - Foodie</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
      
    <style>
        .input-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
        }
        @media(max-width: 500px) {
            .input-grid {
                grid-template-columns: 1fr;
                gap: 0;
            }
        }
    </style>
    
    <script>
    function validateForm()
{
	 var name = document.forms["registrationForm"]["name"].value;
	 var email = document.forms["registrationForm"]["email"].value;
	 var phone= document.forms["registrationForm"]["phone"].value;
	 var password = document.forms["registrationForm"]["password"].value;
	 var confirm_password = document.forms["registrationForm"]["confirm_password"].value;
	 
	 if(name =="" || email =="" || phone =="" || password =="" || confirm_password =="")
		 {
		    alert('All Fields are mandatory!!');
		    return false;
		 }
	 if(password!=confirm_password)
		 {
		    alert('Password and Confirm Password mismatched!!');
		    return false;
		 }
	 return true;
}

// Validating phone number
function isNumeric(elem, helperMsg)
{
	var numericExpression = /^[0-9]+$/;
	if(elem.value.match(numericExpression))
    {
		return true;
	}
	else
	{
		alert(helperMsg);
		elem.focus();
		return false;
	}
}

// Validating password and confirmPassword
function isEquals(password, confirm_password, helperMsg)
{
	 if(confirm_password === password)
		 {
		    return true;
		 }
	 else
		 {
		    alert(helperMsg);
		    return false;
		 }
}

// Validating Name Field
function isAlphabet(elem, helperMsg)
{
	 var alphaExp = /^[a-zA-Z]+$/;
	  if(elem.value.match(alphaExp))
		  {
		     return true;
		  }
	  else 
		  {
		     alert(helperMsg);
		     elem.focus();
		     return false;
		  }
}

// Length Restriction
function lengthRestriction(elem, min, max)
{
	 var uInput = elem.value;
	 if(uInput.length >= min && uInput.length <= max)
		 {
		    return true;
		 }
	 else
		 {
		    alert("Please enter between " + min+ " and " +max+ "characters");
		    elem.focus();
		    return false;
		 }
}
// Email validator
 function emailValidator(elem, helperMsg)
{
 var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
  if(elem.value.match(emailExp)){
 	return true;
   }
   else
   {
      alert(helperMsg);
      elem.focus();
       return false;
   }
 }

 function verifyEmail()
 {
	 var email = document.forms["registrationForm"]["email"].value;
	 $.ajax({
		 type: 'POST', url: 'validatorAjax.jsp', data: {email: email},
		 success: function (response) 
		 {
			 if(response === "available")
				 {
				    alert("Email is available.");
				 }
			 else if(response === "unavailable")
				 {
				    alert("Email already exists, try entering another Email.")
				 }
		 }
	 });
 }	
</script>

</head>
<body>

    <div class="auth-container">
        <!-- Left Side: Banner -->
        <div class="auth-banner" style="background: url('https://images.unsplash.com/photo-1493770348161-369560ae357d?q=80&w=2070&auto=format&fit=crop') center/cover no-repeat;">
            <div class="auth-banner-overlay">
                <h2>Join the Foodie Family</h2>
                <p>Taste the best food around you, delivered blazing fast.</p>
            </div>
        </div>

        <!-- Right Side: Form -->
        <div class="auth-form-wrapper">
            <div class="auth-form glass-panel" style="max-width: 550px;">
                <div style="text-align: center; margin-bottom: 2rem;">
                    <a href="index.jsp" class="logo" style="justify-content: center; font-size: 2rem;">
                        <i class="fa-solid fa-utensils"></i> Foodie
                    </a>
                </div>
                
                <h3>Create Account</h3>
                <p style="margin-bottom: 20px;">Please fill in your details to sign up.</p>

                <form name="registrationForm" action="register.jsp" method="post" onsubmit="return validateForm()">
                    
                    <div class="input-grid">
                        <div class="form-group">
                            <input type="text" class="form-control" id="fname" name="fname" placeholder=" " required>
                            <label for="fname" class="form-label">First Name</label>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="lname" name="lname" placeholder=" " required>
                            <label for="lname" class="form-label">Last Name</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <input type="email" class="form-control" id="email" name="email" placeholder=" " required>
                        <label for="email" class="form-label">Email Address</label>
                    </div>

                    <div class="form-group">
                        <input type="tel" class="form-control" id="phone" name="phone" placeholder=" " required>
                        <label for="phone" class="form-label">Phone Number</label>
                    </div>

                    <div class="input-grid">
                        <div class="form-group">
                            <input type="password" class="form-control" id="password" name="password" placeholder=" " required>
                            <label for="password" class="form-label">Password</label>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="confirm_password" name="confirm_password" placeholder=" " required>
                            <label for="confirm_password" class="form-label">Confirm Password</label>
                        </div>
                    </div>



                    <button type="submit" class="btn btn-primary auth-btn">Sign Up</button>
                </form>



                <p style="text-align: center; margin-top: 25px; margin-bottom: 0;">
                    Already have an account? <a href="login.jsp" style="color: var(--primary); font-weight: 600;">Sign in</a>
                </p>
            </div>
        </div>
    </div>

</body>
</html>
