<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sunglasses Only</title>
<link type="text/css" rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="../resources/css/main.css" />
<link type="text/css" rel="stylesheet" href="../resources/css/admin.css" />
<link type="text/css" rel="stylesheet" href="../resources/css/animate.css" />
<script src="../resources/js/jquery.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
<script src="../resources/js/angular.min.js"></script>
</head>
<body data-spy="scroll" data-target=".navbar" data-offset="50" id="myPage">
<div class="container-fluid">
		<div class="row">
			<div class="pull-left col-sm-5">
				<img alt="LOGO" src="../resources/images/logo.png" width="70%" height="10%">
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
				<a href="../j_spring_security_logout" class="btn btn-info btn-block">Logout</a>
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
					<li><a href="../home"><span class="glyphicon glyphicon-home"></span>&nbsp;Home</a></li>
					<li><a href="../contactUs"><span class="glyphicon glyphicon-phone-alt"></span>&nbsp;Contact Us</a></li>
					<li><a href="../aboutUs"><span class="glyphicon glyphicon-globe"></span>&nbsp;About Us</a></li>
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
	<div class="container-fluid">
	<form:form commandName="orderDetailAttribute">
		
		 <section class="container" style="min-height: 360px;">
		 <div class="container-page">
		 <h3>Confirm Your Detail</h3>
		 <hr>
		 	<div class="col-md-6">
		 		
			<div class="form-group col-lg-12">
				<label>User Name</label>
				<input class="form-control" readonly="readonly" value="<%=session.getAttribute("username") %> "/>
			</div>
				<div class="form-group col-lg-6">
					<label>Email Address</label>
					<form:input readonly="true" path="user_detail.user_email" class="form-control" value="${orderDetailAttribute.user_detail.user_email }" />
				</div>
				<div class="form-group col-lg-6">
					<label>Contact Number</label>
					<form:input readonly="true" path="user_detail.user_contact" class="form-control" value="${orderDetailAttribute.user_detail.user_contact }" />
				</div>
				<div class="form-group col-lg-12">
		 	<label>Permanent Address</label>
		 	<form:textarea  readonly="true" path="user_detail.user_address" class="form-control"/>
		 	</div>
			
		 	</div>
		 	<div class="col-md-6">
		 	<div class="form-group col-lg-12">
		 	<label>Shipping Address</label>
		 	<form:textarea path="shipping_address" class="form-control" required="true" autofocus="true"/>
		 	</div>
		 	
		 	<div class="form-group col-lg-12">
		 	<label>Billing Address</label>
		 	<form:textarea path="billing_address" class="form-control" required="true"/>
		 	</div>
		 	
		 	<div class="col-lg-6">
		 		<a href="viewMyCart" role="button" class="btn btn-block btn-info">View Cart</a>
		 	</div>
		 	<div class="col-lg-6">
		 		<input name="_eventId_submit" type="submit" value="Next" class="btn btn-primary btn-block" />
		 	</div>
		 	</div>
		 </div>
		</section>
	</form:form>
	</div>
	<hr>
<%@ include file="../footer.jsp" %>
	</body>
</html>
