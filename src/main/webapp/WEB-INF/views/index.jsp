<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
	<%@ include file="header.jsp" %>
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
					<li><a href="#myPage"><span class="glyphicon glyphicon-home"></span>&nbsp;Home</a></li>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="AdminPages/admin"><span class="glyphicon glyphicon-user"></span>&nbsp;Admin Home</a></li>
					</sec:authorize>
					<li><a href="#brands"><span class="glyphicon glyphicon-th"></span>&nbsp;Brands</a></li>
					<li><a href="#Arrivals"><span class="glyphicon glyphicon-th"></span>&nbsp;New Arrivals</a></li>
					<li><a href="contactUs"><span class="glyphicon glyphicon-phone-alt"></span>&nbsp;Contact Us</a></li>
					<li><a href="aboutUs"><span class="glyphicon glyphicon-globe"></span>&nbsp;About Us</a></li>
				</ul>
				<c:choose>
					<c:when test="${userId != null }">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="UserPages/viewMyCart" role="button">My Cart &nbsp;<span
									class="glyphicon glyphicon-shopping-cart"></span> (${cartSize})
									- Items
							</a></li>
						</ul>
					</c:when>
				</c:choose>

			</div>
		</div>
	</nav>
	<hr>
	
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
	<div class="container" id="Arrivals"  style="padding-top: 30px;background-color: white;">
	<div class="row">
		<div class="col-lg-12 col-md-12">
			<h1 class="page-header">New Arrivals Products </h1>
		</div>
		 <c:forEach items="${newProductsList}" var="newProductsList">
  <div class="col-sm-3 " id="style">
 <!--  <div class="thumbnail"> -->
  		<a href="productDetail?id=${newProductsList.product_id }"><img src="${newProductsList.product_image}" class="img-responsive" style="width:75%;height:200px;" alt="Image">
  		</a>
  	<!-- </div> -->
  	</div>
  </c:forEach>
	</div>
	</div>
	
	<hr>
	<%-- <div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none; padding-top: 15%">
		<div class="modal-dialog">
			<div class="loginmodal-container">
				<h1>Login to Your Account</h1>
				<br>
				<form role="form" action="<c:url value='j_spring_security_check'/>" method="POST">
					<div class="form-group">
						<input type="email" name="j_username"  required class="form-control" placeholder="Example@example.com" autofocus="autofocus" />
					</div>
					<div class="form-group">
						<input type="password" name="j_password" class="form-control" placeholder="XXXXXXXX" required />
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

					<div class="row">
						<div class="col-md-4">
							<button type="submit" class="btn btn-success btn-block">Sign In</button>
						</div>
						<div class="col-md-8 help-block text-right">
							New User?<a class=" btn btn-info" href="registration">Register Here.</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div> --%>
<%@ include file="footer.jsp" %>
</body>
</html>
