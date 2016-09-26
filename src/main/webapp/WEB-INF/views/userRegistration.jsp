<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
					<li><a href="home">Home</a></li>
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
	<div class="alert-danger animated">${message }</div>
<div class="container-fluid" id="wrap">
		<div>
			<div class="col-md-6 col-md-offset-3">
				<form:form commandName="userDetailAttribute"   >
				<legend>Sign Up</legend>
				<c:forEach
						items="${flowRequestContext.messageContext.getMessagesBySource('registrationFail')}"
						var="err">
						<div class="alert-danger">
							<span><h3>${err.text}</h3></span>
						</div>
					</c:forEach>
				<div class="row">
						<div class="col-xs-6 col-md-6">
							<form:input path="user_fname" class="form-control input-lg" placeholder="Enter First Name" required="true" autofocus="true"/>
						</div>
						<div class="col-xs-6 col-md-6">
							<form:input path="user_lname" class="form-control input-lg" placeholder="Enter Last Name" required="true"/>
						</div>
				</div><br>
				<form:textarea path="user_address" class="form-control input-lg" required="true" placeholder="Enter Your Address"/><br>
				<form:input path="user_contact" pattern="^(\+[\d]{1,5}|0)?[7-9]\d{9}" class="form-control input-lg" required="true" placeholder="Enter Your Contact Number"/><br>
				<form:input path="user_email" class="form-control input-lg" required="true"  type="email" placeholder="Enter Your Email"/><br>
					<c:forEach
						items="${flowRequestContext.messageContext.getMessagesBySource('user_email')}"
						var="err">
						<div class="alert-danger">
							<span><h3>${err.text}</h3></span>
						</div>
					</c:forEach>
					<form:password path="user_password" class="form-control input-lg" required="true"  placeholder="Enter Password"
				title="Password must contain at least 6 characters, including UPPER/lowercase and numbers" name="password"
				pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" /><br>
				<input type="password" class="form-control input-lg" required placeholder="Confirm Password"
				title="Password must contain at least 6 characters, including UPPER/lowercase and numbers" name="ucpassword"
				/><br>
				<%-- <form:input path="userRole.user_role_id" value="2" type="hidden"/> --%>
				<input name="_eventId_submit" type="submit" value="Create My Account" class="btn btn-lg btn-primary btn-block signup-btn" />
				</form:form>
			</div>
		</div>
	</div>
	<hr>
	<%@ include file="footer.jsp" %>
</body>
</html>
