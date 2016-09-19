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
<body>
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
	<div class="alert-danger animated">${message }</div>
<div class="container-fluid" id="wrap">
		<div>
			<div class="col-md-6 col-md-offset-3">
				<form:form commandName="userDetailAttribute"   >
				<h2>Confirm Your Details</h2>
				<table class="table table-hover">
					<tbody>
						<tr>
							<td>
							<form:label path="user_fname">First Name:-</form:label>
							</td>
							<td class="h3">
							${userDetailAttribute.user_fname}
							</td>
						</tr>
						<tr>
							<td>
							<form:label path="user_lname">Last Name:-</form:label>
							</td>
							<td class="h3">
							${userDetailAttribute.user_lname }
							</td>
						</tr>
						<tr>
							<td>
							<form:label path="user_address">Permanent Address:- </form:label>
							</td>
							<td class="h3">
							${userDetailAttribute.user_address }
							</td>
						</tr>
						<tr>
							<td>
							<form:label path="user_contact">Contact Number:-</form:label>
							</td>
							<td class="h3">
							${userDetailAttribute.user_contact }
							</td>
						</tr>
						<tr>
							<td>
							<form:label path="user_email">Email Address:-</form:label>
							</td>
							<td class="h3">
							${userDetailAttribute.user_email }
							</td>
						</tr>
						<tr>
							<td><input name="_eventId_edit" type="submit" class="btn btn-info btn-block btn-lg" value="Change Details"></td>
							<td><input name="_eventId_submit" type="submit" class="btn btn-success btn-block btn-lg" value="Confrim Details"></td>
						</tr>
					</tbody>
				</table>
		</form:form>
		
			</div>
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
						<form:form action="userLogin" commandName="userDetailAttribute" method="POST">
							<div class="form-group">
								<form:input path="user_email" class="form-control" placeholder="Example@example.com" type="email" required="true"/>
							</div>
							<div class="form-group">
								<form:password path="user_password" class="form-control" placeholder="XXXXXXXX" required="true"/>
							</div>
							<div class="row">
								<div class="col-md-4">
									<button type="submit" class="btn btn-success btn-block">Sign
										In</button>
								</div>
								<div class="col-md-8 help-block text-right">
									New User?<a class=" btn btn-info" href="registration">Register Here.</a>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<footer class="container-fluid text-center" style="margin-top: 10px;">
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
