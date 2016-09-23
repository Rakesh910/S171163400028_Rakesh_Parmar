<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sunglass Only</title>
<link type="text/css" rel="stylesheet"
	href="resources/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="resources/css/main.css" />
<!-- <link type="text/css" rel="stylesheet" href="resources/css/admin.css" /> -->
<link type="text/css" rel="stylesheet" href="resources/css/animate.css" />
<script src="resources/js/jquery.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</head>
<body data-spy="scroll" data-target=".navbar" data-offset="50"
	id="myPage">
	<div class="container-fluid">
		<div class="row">
			<div class="pull-left col-sm-4">
				<img alt="LOGO" src="resources/images/logo.png" width="100%"
					height="10%">
			</div>
			<div class="col-sm-4 text-center">
				<h3>Welcome to Sunglasses</h3>
				<div class="alert-danger animated">${message }</div>
			</div>
			<c:choose>
				<c:when test="${userId == null }">
					<div class="pull-right col-sm-4 text-center">
						<br> <a href="#" role="button" class="btn btn-success "
							data-toggle="modal" data-target="#login-modal">Existing
							User?? Sign In</a> <a href="registration" class="btn btn-info ">
							New User?? Sign Up</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="pull-right col-sm-4 text-center">
						<h4>Welcome ${username }</h4>
						<a href="logout" class="btn btn-info btn-block">Logout</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<hr>
	<nav class="navbar navbar-inverse" data-spy="affix"
		data-offset-top="200">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="#myPage">Home</a></li>
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
							<li><a href="viewMyCart" role="button">My Cart &nbsp;<span
									class="glyphicon glyphicon-shopping-cart"></span> (${cartSize})
									- Items
							</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<!-- <ul class="nav navbar-nav navbar-right">
							<li><a href="#" role="button">My Cart &nbsp;<span
									class="glyphicon glyphicon-shopping-cart"></span> (0) - Items
							</a></li>
						</ul> -->
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</nav>
	<hr>
	<%-- <c:if test="${isUserClickHome == true }">
		<%@ include file="home.jsp"%>
	</c:if>
	<c:if test="${isUserClickRegister == true }">
		<%@ include file="userRegistration.jsp"%>
	</c:if>
	<c:if test="${isUserClickProductList == true }">
		<%@ include file="productList.jsp"%>
	</c:if>
	<c:if test="${isUserClickProductDetail == true }">
		<%@ include file="productDetail.jsp"%>
	</c:if>
	<c:if test="${isUserClickViewCart == true }">
		<%@ include file="viewCart.jsp"%>
	</c:if> --%>
	
	 	<div id="myCarousel" class="carousel slide" data-ride="carousel" style="margin-top: -20px;">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<li data-target="#myCarousel" data-slide-to="3"></li>
			<li data-target="#myCarousel" data-slide-to="4"></li>
			<li data-target="#myCarousel" data-slide-to="5"></li>
			<li data-target="#myCarousel" data-slide-to="6"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img src="resources/images/1.jpg" alt="Sunglasses">
			</div>

			<div class="item">
				<img src="resources/images/2.jpg" alt="Sunglasses">
			</div>

			<div class="item">
				<img src="resources/images/3.jpg" alt="Sunglasses">
			</div>

			<div class="item">
				<img src="resources/images/4.jpg" alt="Sunglasses">
			</div>

			<div class="item">
				<img src="resources/images/5.jpg" alt="Sunglasses">
			</div>

			<div class="item">
				<img src="resources/images/6.jpg" alt="Sunglasses">
			</div>
		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<hr>
	<div class="container" id="brands"  style="padding-top: 30px;">
	<div class="row">
		<div class="col-lg-12 col-md-12">
			<h1 class="page-header">Our Top New Brands </h1>
		</div>
		 <c:forEach items="${brandList}" var="brandList">
  <div class="col-sm-4 " id="style">
  <div class="thumbnail">
  		<a href="showProductList?id=${brandList.brand_id}"><img src="${brandList.brand_image}" class="img-responsive" style="width:75%;" alt="Image">
  		</a>
  	</div>
  	</div>
  </c:forEach>
	</div>
	</div>
	<hr>
	<div class="container" id="Arrivals"  style="padding-top: 30px;">
	<div class="row">
		<div class="col-lg-12 col-md-12">
			<h1 class="page-header">New Arrivals Products </h1>
		</div>
		 <c:forEach items="${newProductsList}" var="newProductsList">
  <div class="col-sm-3 " id="style">
  <div class="thumbnail">
  		<a href="productDetail?id=${newProductsList.product_id }"><img src="${newProductsList.product_image}" class="img-responsive" style="width:75%;height:200px;" alt="Image">
  		</a>
  	</div>
  	</div>
  </c:forEach>
	</div>
	</div>
	
	<hr>
	<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none; padding-top: 15%">
		<div class="modal-dialog">
			<div class="loginmodal-container">
				<h1>Login to Your Account</h1>
				<br>
				<form:form action="userLogin" commandName="userLoginAttribute"
					method="POST">
					<div class="form-group">
						<form:input path="user_email" class="form-control"
							placeholder="Example@example.com" type="email" required="true" />
					</div>
					<div class="form-group">
						<form:password path="user_password" class="form-control"
							placeholder="XXXXXXXX" required="true" />
					</div>
					<div class="row">
						<div class="col-md-4">
							<button type="submit" class="btn btn-success btn-block">Sign
								In</button>
						</div>
						<div class="col-md-8 help-block text-right">
							New User?<a class=" btn btn-info" href="registration">Register
								Here.</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
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
		<p>
			<a class="up-arrow" href="#myPage" data-toggle="tooltip"
				title="TO TOP"> <span class="glyphicon glyphicon-chevron-up"></span>
			</a>
		</p>
		<p class="col-md-12">
		<hr class="divider">
		Copyright &COPY; 2016 <a href="http://www.pingpong-labs.com">Additya</a>
		</p>
	</footer>
</body>
</html>
