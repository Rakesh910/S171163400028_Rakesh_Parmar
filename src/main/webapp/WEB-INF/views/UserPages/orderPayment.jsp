<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<!--CREDIT CART PAYMENT-->
	<div class="modal-dialog">
		<div class="panel panel-success">
			<div class="panel-heading">
				<span><i class="glyphicon glyphicon-lock"></i></span> Secure Payment
			</div>
			<div class="panel-body">
				<form:form role="form" commandName="orderDetailAttribute">
					<div class="form-group">
						<div class="col-md-12">
						<c:forEach
					items="${flowRequestContext.messageContext.getMessagesBySource('cardType')}"
					var="err">
					<div class="alert-danger">
						<span>${err.text}</span>
					</div>
				</c:forEach>
						<form:label class="control-label" path="cardDetail.cardType"><strong>Card Type:</strong></form:label>
							<form:select path="cardDetail.cardType" class="form-control" autofocus="true" >
								<option value="" disabled selected>Select CardType</option>	
								<form:option value="1">Visa</form:option>
								<form:option value="2">MasterCard</form:option>
								<form:option value="3">American Express</form:option>
								<form:option value="4">Discover</form:option>
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-8">
						<c:forEach
					items="${flowRequestContext.messageContext.getMessagesBySource('cardNumber')}"
					var="err">
					<div class="alert-danger">
						<span>${err.text}</span>
					</div>
				</c:forEach>
						<form:errors path="cardDetail.cardNumber" class="error"></form:errors>
					<form:label class="control-label" path="cardDetail.cardNumber"><strong>Card Number</strong></form:label>
						<form:input path="cardDetail.cardNumber"  type="text" pattern="^[0-9]+$" title="Enter a valid card no"  class="form-control" />		 
						</div>
						
						<div class="col-md-4">
						<c:forEach
					items="${flowRequestContext.messageContext.getMessagesBySource('cvvNumber')}"
					var="err">
					<div class="alert-danger">
						<span>${err.text}</span>
					</div>
				</c:forEach>
						<form:errors path="cardDetail.cvNumber" class="error"></form:errors>
						<form:label class="control-label" path="cardDetail.cvNumber"><strong>Card CVV:</strong></form:label>
							<form:input path="cardDetail.cvNumber"  type="text" pattern="^[0-9]{4}$" tilte="Enter a 4 digit ccv" class="form-control"  />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
						<c:forEach
					items="${flowRequestContext.messageContext.getMessagesBySource('expiryMonth')}"
					var="err">
					<div class="alert-danger">
						<span>${err.text}</span>
					</div>
				</c:forEach>
							<form:errors path="cardDetail.expiryMonth" class="error"></form:errors>
							<form:label  path="cardDetail.expiryMonth"><strong>Expiry Month:</strong></form:label>
							<form:select path="cardDetail.expiryMonth" class="form-control" >
								<option value="" disabled selected>MM</option>
									<form:option value="1">Jan</form:option>
									<form:option value="2">Feb</form:option>
									<form:option value="3">Mar</form:option>
									<form:option value="4">Apr</form:option>
									<form:option value="5">May</form:option>
									<form:option value="6">Jun</form:option>
									<form:option value="7">July</form:option>
									<form:option value="8">Aug</form:option>
									<form:option value="9">Sep</form:option>
									<form:option value="10">Oct</form:option>
									<form:option value="11">Nov</form:option>
									<form:option value="12">Dec</form:option>	
							</form:select>
						</div>
						<div class="col-md-6">
						<c:forEach
					items="${flowRequestContext.messageContext.getMessagesBySource('expiryYear')}"
					var="err">
					<div class="alert-danger">
						<span>${err.text}</span>
					</div>
				</c:forEach>
							<form:errors path="cardDetail.expiryYear" class="error"></form:errors>
							<form:label  path="cardDetail.expiryYear"><strong>Expiry Year:</strong></form:label>
							<form:select path="cardDetail.expiryYear" class="form-control" >
								<option value="" disabled selected>Year</option>
									<form:option value="2016">2016</form:option>
									<form:option value="2017">2017</form:option>
									<form:option value="2018">2018</form:option>
									<form:option value="2019">2019</form:option>
									<form:option value="2020">2020</form:option>
									<form:option value="2021">2021</form:option>
									<form:option value="2022">2022</form:option>
									<form:option value="2023">2023</form:option>
									<form:option value="2024">2024</form:option>
									<form:option value="2025">2025</form:option>
									<form:option value="2026">2026</form:option>
									<form:option value="2027">2027</form:option>
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
						<c:forEach
					items="${flowRequestContext.messageContext.getMessagesBySource('NmOnCard')}"
					var="err">
					<div class="alert-danger">
						<span>${err.text}</span>
					</div>
				</c:forEach>
							<form:errors path="cardDetail.nameOnCard" class="error"></form:errors>
							<form:label  path="cardDetail.nameOnCard"><strong>Name On Card:</strong></form:label>
							<form:input path="cardDetail.nameOnCard" type="text" class="form-control" />
						</div>
					</div>
					<div class="col-md-12">
                                    <ul class="cards">
                                        <li class="visa hand">Visa</li>
                                        <li class="mastercard hand">MasterCard</li>
                                        <li class="amex hand">Amex</li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
					
					<div class="form-group">
						<div class="col-md-6">
						<input name="_eventId_edit" type="submit" class="btn btn-info btn-block form-control " value="Change Details">
						</div>
						<div class="col-md-6">
						<input name="_eventId_submit" type="submit" class="btn btn-danger btn-block" value="Place Your Order">
						</div>
					</div>
					
				</form:form>

			</div>
		</div>
	</div>


	<!--CREDIT CART PAYMENT END-->
	<hr>
	
	
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
