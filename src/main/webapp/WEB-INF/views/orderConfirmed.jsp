<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sunglass Only</title>
<link type="text/css" rel="stylesheet" href="resources/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="resources/css/main.css" />
<link type="text/css" rel="stylesheet" href="resources/css/admin.css" />
<link type="text/css" rel="stylesheet" href="resources/css/animate.css" />
<script src="resources/js/jquery.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</head>
<body data-spy="scroll" data-target=".navbar" data-offset="50"  id="myPage">
<div class="container-fluid">
		<div class="row">
			<div class="pull-left col-sm-5">
				<img alt="LOGO" src="resources/images/logo.png" width="70%" height="10%">
			</div>
			<div class="col-sm-4">
				<h3>Welcome to Sunglasses </h3>
				<div class="alert-danger animated">${message }</div>
			</div>
			<c:choose>
				<c:when test="${userId == null }">
					<div class="pull-right col-sm-3 text-center">
			
				<a href="#" role="button" class="btn btn-info" data-toggle="modal" data-target="#login-modal">Existing User?? Sign In</a>
				<a href="registration" class="btn btn-info"> New User?? Sign Up</a>
			</div>
				</c:when>
				<c:otherwise>
					<div class="pull-right col-sm-3 text-center">
				<h4>Welcome ${username }</h4>
				<a href="logout" class="btn btn-info btn-block">Logout</a>
			</div>
				</c:otherwise>
			</c:choose>
			
			 
		</div>
</div>
<hr>
<nav class="navbar navbar-inverse" data-spy="affix" data-offset-top="200">
		<div class="container-fluid">
			<div class="navbar-header">
				 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
			</div>
			<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="home">Home</a></li>
				<c:if test="${isUserClickRegister != true }">
				<li><a href="#brands">Brands</a></li>
				<li><a href="#Arrivals">New Arrivals</a></li>
				</c:if>
				
				<li><a href="">Contact Us</a></li>
				<li><a href="">About Us</a></li>
			</ul>
			<c:choose>
				<c:when test="${userId != null }">
						<ul class="nav navbar-nav navbar-right">
							<li ><a href="viewMyCart"  role="button" >My
									Cart &nbsp;<span class="glyphicon glyphicon-shopping-cart"></span>
									(${cartSize}) - Items
							</a></li>
						</ul>
					</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav navbar-right">
							<li ><a href="#"  role="button" >My
									Cart &nbsp;<span class="glyphicon glyphicon-shopping-cart"></span>
									(0) - Items
							</a></li>
						</ul>
				</c:otherwise>
			</c:choose>
			
			</div>
		</div>
	</nav>
	<hr>
			<h1>Thank you...!!!</h1>
			  
	<hr>

	 <script>
$(document).ready(function(){
  // Add smooth scrolling to all links in navbar + footer link
  $(".navbar a, footer a[href='#myPage']").on('click', function(event) {

  // Make sure this.hash has a value before overriding default behavior
  if (this.hash !== "") {

    // Prevent default anchor click behavior
    event.preventDefault();

    // Store hash
    var hash = this.hash;

    // Using jQuery's animate() method to add smooth page scroll
    // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
    $('html, body').animate({
      scrollTop: $(hash).offset().top
    }, 400, function(){

      // Add hash (#) to URL when done scrolling (default click behavior)
      window.location.hash = hash;
      });
    } // End if
  });
})
</script>
<footer class="container-fluid text-center">
		<p> <a class="up-arrow" href="#myPage" data-toggle="tooltip" title="TO TOP">
    <span class="glyphicon glyphicon-chevron-up"></span>
  </a></p>
  <p class="col-md-12">
				<hr class="divider">
				Copyright &COPY; 2016 <a href="http://www.pingpong-labs.com">Additya</a>
			</p>
	</footer>
	</body>
</html>
