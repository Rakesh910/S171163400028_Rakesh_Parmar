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
	<div class="content-wrapper">
		<div class="container">
			<div class="col-md-5">
				<img src="${productDetail.product_image}" class="img-thumbnail" alt="Image"
					style="height: 70%; width: 100%;">
					 <a href="addToCart?id=${productDetail.product_id}" class="btn btn-success btn-lg">ADD TO CART</a>
					 <a href="#" class="btn btn-info btn-lg">BUY NOW</a>
			</div>
			<div class="col-md-6">
				<h2>${productDetail.product_name}</h2>
				<table class="table table-hover">
					<tbody>
						<tr>
							<th>BRAND NAME</th>
							<td>${productDetail.brand.brand_name}</td>
						</tr>
						<tr>
							<th>CATEGORY NAME</th>
							<td>${productDetail.category.cat_name}</td>
						</tr>
						<tr>
							<th>SUPPLIER</th>
							<td>${productDetail.supplier.supplier_name}</td>
						</tr>
						<tr>
							<th>PRICE</th>
							<td>Rs.<del>${productDetail.product_price}</del> <mark>Rs.${(productDetail.product_price) - (productDetail.product_price * productDetail.product_discount / 100)}</mark></td>
						</tr>
						<tr>
							<th>DISCOUNT</th>
							<td>${productDetail.product_discount}&nbsp;%</td>
						</tr>
						<tr>
							<th>UV PROTECTION</th>
							<td>${productDetail.product_UV}&nbsp;%</td>
						</tr>
						<tr>
							<th>AVAILABLE STOCK</th>
							<td>${productDetail.stock}</td>
						</tr>
						<tr>
							<th>FRAME COLOR</th>
							<td>${productDetail.frameColor.frameColor_name}</td>
						</tr>
						<tr>
							<th>FRAME TYPE</th>
							<td>${productDetail.frameType.frameType_name}</td>
						</tr>
						<tr>
							<th>FRAME MATERIAL</th>
							<td>${productDetail.frameMaterial.frameMaterial_name}</td>
						</tr>
						<tr>
							<th>LENS COLOR</th>
							<td>${productDetail.lensColor.lensColor_name}</td>
						</tr>
						<tr>

							<th>LENS MATERIAL</th>
							<td>${productDetail.lensMaterial.lensMaterial_name}</td>
						</tr>
						<tr>
							<th>PRODUCT SIZE</th>
							<td>${productDetail.productSize.size_name}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<hr>

	<div class="container">
		<div class="col-xs-12">
			<div class="carousel slide" id="myCarousel">
				<div class="carousel-inner">
					<div class="item active">
						<ul class="thumbnails">
							<li class="col-sm-3">
								<div class="fff">
									<div class="thumbnail">
										<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
									</div>
									<div class="caption">
										<h4>Praesent commodo</h4>
										<p>Nullam Condimentum Nibh Etiam Sem</p>
									</div>
								</div>
							</li>
							<li class="col-sm-3">
								<div class="fff">
									<div class="thumbnail">
										<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
									</div>
									<div class="caption">
										<h4>Praesent commodo</h4>
										<p>Nullam Condimentum Nibh Etiam Sem</p>
										<a class="btn btn-mini" href="#">» Read More</a>
									</div>
								</div>
							</li>
							<li class="col-sm-3">
								<div class="fff">
									<div class="thumbnail">
										<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
									</div>
									<div class="caption">
										<h4>Praesent commodo</h4>
										<p>Nullam Condimentum Nibh Etiam Sem</p>
										<a class="btn btn-mini" href="#">» Read More</a>
									</div>
								</div>
							</li>
							<li class="col-sm-3">
								<div class="fff">
									<div class="thumbnail">
										<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
									</div>
									<div class="caption">
										<h4>Praesent commodo</h4>
										<p>Nullam Condimentum Nibh Etiam Sem</p>
										<a class="btn btn-mini" href="#">» Read More</a>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<!-- /Slide1 -->
					<div class="item">
						<ul class="thumbnails">
							<li class="col-sm-3">
								<div class="fff">
									<div class="thumbnail">
										<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
									</div>
									<div class="caption">
										<h4>Praesent commodo</h4>
										<p>Nullam Condimentum Nibh Etiam Sem</p>
										<a class="btn btn-mini" href="#">» Read More</a>
									</div>
								</div>
							</li>
							<li class="col-sm-3">
								<div class="fff">
									<div class="thumbnail">
										<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
									</div>
									<div class="caption">
										<h4>Praesent commodo</h4>
										<p>Nullam Condimentum Nibh Etiam Sem</p>
										<a class="btn btn-mini" href="#">» Read More</a>
									</div>
								</div>
							</li>
							<li class="col-sm-3">
								<div class="fff">
									<div class="thumbnail">
										<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
									</div>
									<div class="caption">
										<h4>Praesent commodo</h4>
										<p>Nullam Condimentum Nibh Etiam Sem</p>
										<a class="btn btn-mini" href="#">» Read More</a>
									</div>
								</div>
							</li>
							<li class="col-sm-3">
								<div class="fff">
									<div class="thumbnail">
										<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
									</div>
									<div class="caption">
										<h4>Praesent commodo</h4>
										<p>Nullam Condimentum Nibh Etiam Sem</p>
										<a class="btn btn-mini" href="#">» Read More</a>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<!-- /Slide2 -->
					<div class="item">
						<ul class="thumbnails">
							<li class="col-sm-3">
								<div class="fff">
									<div class="thumbnail">
										<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
									</div>
									<div class="caption">
										<h4>Praesent commodo</h4>
										<p>Nullam Condimentum Nibh Etiam Sem</p>
										<a class="btn btn-mini" href="#">» Read More</a>
									</div>
								</div>
							</li>
							<li class="col-sm-3">
								<div class="fff">
									<div class="thumbnail">
										<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
									</div>
									<div class="caption">
										<h4>Praesent commodo</h4>
										<p>Nullam Condimentum Nibh Etiam Sem</p>
										<a class="btn btn-mini" href="#">» Read More</a>
									</div>
								</div>
							</li>
							<li class="col-sm-3">
								<div class="fff">
									<div class="thumbnail">
										<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
									</div>
									<div class="caption">
										<h4>Praesent commodo</h4>
										<p>Nullam Condimentum Nibh Etiam Sem</p>
										<a class="btn btn-mini" href="#">» Read More</a>
									</div>
								</div>
							</li>
							<li class="col-sm-3">
								<div class="fff">
									<div class="thumbnail">
										<a href="#"><img src="http://placehold.it/360x240" alt=""></a>
									</div>
									<div class="caption">
										<h4>Praesent commodo</h4>
										<p>Nullam Condimentum Nibh Etiam Sem</p>
										<a class="btn btn-mini" href="#">» Read More</a>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<!-- /Slide3 -->
				</div>


				<nav>
					<ul class="control-box pager">
						<li><a data-slide="prev" href="#myCarousel" class=""><i
								class="glyphicon glyphicon-chevron-left"></i></a></li>
						<li><a data-slide="next" href="#myCarousel" class=""><i
								class="glyphicon glyphicon-chevron-right"></i></li>
					</ul>
				</nav>
				<!-- /.control-box -->

			</div>
			<!-- /#myCarousel -->
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
