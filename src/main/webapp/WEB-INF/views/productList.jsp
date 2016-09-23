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
					<li><a href="home">Home</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Brands<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<c:forEach items="${listOfBrands}" var="listOfBrands">
									<li><a href="showProductList?id=${listOfBrands.brand_id}">${listOfBrands.brand_name }</a></li>
							</c:forEach>
						</ul>
					</li>

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
		<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<ol class="breadcrumb">
				<li><a href="home">Home</a></li>
				<li class="active"><b>${brandList.brand_name }</b></li>
			</ol>
		</div>
	</div>
	<div class="row">
		<!-- Sidebar Column -->
		<div class="col-md-2">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#collapseOne" role="button">Categories</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav nav-pills nav-stacked">
								<c:forEach items="${brandList.category}" var="categoryList">
									<li><a href="${categoryList.cat_id}">${categoryList.cat_name }</a></li>
								</c:forEach>

							</ul>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- Content Column -->
		<div class="col-md-10 ">
			<!-- <h2 class="page-header">Section Heading</h2> -->
			
			<c:forEach items="${brandList.category}" var="catList">
					<c:forEach items="${catList.product}" var="productList">
						<div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h5><i class="fa fa-fw fa-check"></i>${productList.product_name}</h5>
                    </div>
                    <div class="panel-body">
                        <div class="thumbnail">
								<img src="${productList.product_image}" alt="Image"
									style="height: 20%;">
								<%-- <p>
									<strong>${productList.product_name}</strong>
								</p> --%>
								
							</div>
							<a href="productDetail?id=${productList.product_id }"
									role="button" class=" btn btn-success btn-block">View
									Detail</a>
                    </div>
                </div>
            </div>
					</c:forEach>

				</c:forEach>
			
           
            
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
