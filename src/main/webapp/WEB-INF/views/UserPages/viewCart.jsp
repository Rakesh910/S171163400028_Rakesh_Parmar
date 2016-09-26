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
			<div class="pull-left col-sm-4">
				<a href="../home"><img alt="LOGO" src="../resources/images/logo.png" width="100%"
					height="10%"></a>
			</div>
			<div class="col-sm-4 text-center">
				<h3>Welcome to Sunglasses</h3>
				<div class="alert-danger animated">${message }</div>
			</div>
			<c:choose>
				<c:when test="${empty userId}">
					<div class="pull-right col-sm-4 text-center">
						<br> 
						<!-- <a href="#" role="button" class="btn btn-success "
							data-toggle="modal" data-target="#login-modal">Existing
							User?? Sign In&nbsp;<span class="glyphicon glyphicon-log-in"></span></a> -->
							<a href="loginPage" role="button" class="btn btn-success ">ExistingUser?? Sign In&nbsp;<span class="glyphicon glyphicon-log-in"></span></a>
							
						<a href="registration" class="btn btn-info "> New User?? Sign Up</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="pull-right col-sm-4 text-center">
						<h4>Welcome ${username }</h4>
						<a href="../logout" class="btn btn-info btn-block">Logout</a>
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
					<li><a href=""><span class="glyphicon glyphicon-phone-alt"></span>&nbsp;Contact Us</a></li>
					<li><a href=""><span class="glyphicon glyphicon-globe"></span>&nbsp;About Us</a></li>
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
		<div class="container" style="min-height: 400px;">
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">
						<div class="row">
							<div class="col-xs-7">
								<h5><span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart</h5>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-body">
				<c:choose>
					<c:when test="${cartList > 0 }">
						<c:forEach items="${order.cartDetail}" var="order">
					<form method="POST" action="${pageContext.request.contextPath}/UserPages/updateCart">
						<div class="row">
						<div class="col-xs-2"><img style="height:10%;width:100%" src="${order.product.product_image}">
						</div>
						<div class="col-xs-4">
							<h4 class="product-name"><strong>${order.product.product_name}</strong></h4><h4><small>Product description</small></h4>
						</div>
						<div class="col-xs-6">
							<div class="col-xs-3 text-right">
								<h6><strong>${order.product.product_price} <span class="text-muted">x</span></strong></h6>
							</div>
							<div class="col-xs-3">
								<input  class="form-control" type="hidden" name="product_id" value="${order.product.product_id}"/>
								<input class="form-control input-sm" name="quantity" value="${order.quantity }" />
							</div>
							<div class="col-xs-3 text-right">
								<h6><strong>${order.total} </strong></h6>
							</div>
							<div class="col-xs-3">
								<button type="submit" class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-edit"></span>
								</button>
								<a href="deleteCart?id=${order.product.product_id}" role="button" class="btn  btn-danger btn-sm"><span class="glyphicon glyphicon-trash"> </span></a>
							</div>
						</div>
					</div>
					</form>
					<hr>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="alert alert-danger"><strong>Sorry..Your Cart Is Empty</strong></div>
					</c:otherwise>
				</c:choose>
				
					
				</div>
				<div class="panel-footer">
					<div class="row text-center">
						<div class="col-xs-9">
							<h4 class="text-right">Total <strong>${order.grandTotal }</strong></h4>
						</div>
						<div class="col-xs-3">
							<a href="checkOut" role="button" class="btn btn-success btn-block">Checkout</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
		
	<hr>
	
<%@ include file="../footer.jsp" %>
</body>
</html>
