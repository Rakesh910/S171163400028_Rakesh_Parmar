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
					<li><a href="home"><span class="glyphicon glyphicon-home"></span>&nbsp;Home</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Brands<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<c:forEach items="${listOfBrands}" var="listOfBrands">
									<li><a href="showProductList?id=${listOfBrands.brand_id}">${listOfBrands.brand_name }</a></li>
							</c:forEach>
						</ul>
					</li>

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
		<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<ol class="breadcrumb">
				<li><a href="home">Home</a></li>
				<c:if test="${isUserClickBrand == true }">
					<li class="active">Brand :<b>${brandList.brand_name }</b></li>
				</c:if>
				<c:if test="${isUserClickByFilterID == true }">
					<c:if test="${indexNumber == 0 }">
						<li>Brand: <strong> <a href="showProductList?id=${brandList.brand_id}">${brandList.brand_name }</a></strong></li>
						<li class="active">Category:<b>${resultList.cat_name }</b></li>
					</c:if>
					<c:if test="${indexNumber == 1 }">
					<li class="active">Product Size : <strong>${resultList.size_name }</strong></li>
					</c:if>
					
					<c:if test="${indexNumber == 2 }">
					<li class="active">FrameColor : <strong>${resultList.frameColor_name }</strong></li>
					</c:if>
					
					<c:if test="${indexNumber == 3 }">
					<li class="active">Frame Material : <strong>${resultList.frameMaterial_name }</strong></li>
					</c:if>
					
					<c:if test="${indexNumber == 4 }">
					<li class="active">Frame Type : <strong>${resultList.frameType_name }</strong></li>
					</c:if>
					
					<c:if test="${indexNumber == 5 }">
					<li class="active">Lens Color : <strong>${resultList.lensColor_name }</strong></li>
					</c:if>
					
					<c:if test="${indexNumber == 6 }">
					<li class="active">Lens Material : <strong>${resultList.lensMaterial_name }</strong></li>
					</c:if>
				</c:if>
				<li class="pull-right">Total <strong>${foundSize}</strong> Products Are Available</li>
			</ol>
		</div>
	</div>
	<div class="row" style="min-height: 338px;">
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
									<li><a href="productListByFilterID?id=${categoryList.cat_id}&bid=${brandList.brand_id}&index=0">${categoryList.cat_name }</a></li>
								</c:forEach>

							</ul>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#collapseTwo" role="button">Product Size</a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav nav-pills nav-stacked">
								<c:forEach items="${productSizeList}" var="productSizeList">
									<li><a href="productListByFilterID?id=${productSizeList.size_id}&bid=${brandList.brand_id}&index=1">${productSizeList.size_name }</a></li>
								</c:forEach>

							</ul>
						</div>
					</div>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#collapseThree" role="button">Frame Color</a>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav nav-pills nav-stacked">
								<c:forEach items="${frameColorList}" var="frameColorList">
									<li><a href="productListByFilterID?id=${frameColorList.frameColor_id}&bid=${brandList.brand_id}&index=2">${frameColorList.frameColor_name }</a></li>
								</c:forEach>

							</ul>
						</div>
					</div>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#collapseFour" role="button">Frame Material</a>
						</h4>
					</div>
					<div id="collapseFour" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav nav-pills nav-stacked">
								<c:forEach items="${frameMaterialList}" var="frameMaterialList">
									<li><a href="productListByFilterID?id=${frameMaterialList.frameMaterial_id}&bid=${brandList.brand_id}&index=3">${frameMaterialList.frameMaterial_name }</a></li>
								</c:forEach>

							</ul>
						</div>
					</div>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#collapseFive" role="button">Frame Type</a>
						</h4>
					</div>
					<div id="collapseFive" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav nav-pills nav-stacked">
								<c:forEach items="${frameTypeList}" var="frameTypeList">
									<li><a href="productListByFilterID?id=${frameTypeList.frameType_id}&bid=${brandList.brand_id}&index=4">${frameTypeList.frameType_name }</a></li>
								</c:forEach>

							</ul>
						</div>
					</div>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#collapseSix" role="button">Lens Color</a>
						</h4>
					</div>
					<div id="collapseSix" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav nav-pills nav-stacked">
								<c:forEach items="${lensColorList}" var="lensColorList">
									<li><a href="productListByFilterID?id=${lensColorList.lensColor_id}&bid=${brandList.brand_id}&index=5">${lensColorList.lensColor_name }</a></li>
								</c:forEach>

							</ul>
						</div>
					</div>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#collapseSeven" role="button">Lens Material</a>
						</h4>
					</div>
					<div id="collapseSeven" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav nav-pills nav-stacked">
								<c:forEach items="${lensMaterialList}" var="lensMaterialList">
									<li><a href="productListByFilterID?id=${lensMaterialList.lensMaterial_id}&bid=${brandList.brand_id}&index=6">${lensMaterialList.lensMaterial_name }</a></li>
								</c:forEach>

							</ul>
						</div>
					</div>
				</div>
				
				
			</div>

		</div>
		<!-- Content Column -->
		<div class="col-md-10" >
			<!-- <h2 class="page-header">Section Heading</h2> -->
			<c:choose>
				<c:when test="${foundSize <= 0}">
				<br><br><br><br>
				<h1 class="text-center">Sorry..No Products Are Available Right Now.</h1>
				
				</c:when>
				<c:otherwise>
				<c:if test="${isUserClickBrand == true }">
			  	<%@ include file="productListByBrand.jsp" %>
			  </c:if>
			  
			  <c:if test="${isUserClickByFilterID == true }">
			  	<%@ include file="productListByFilters.jsp" %>
			  </c:if>
				
				</c:otherwise>
			</c:choose>
			
			  </div>
			  
	</div>

</div>
	
	<hr>
	<%@ include file="footer.jsp" %>
	</body>
</html>
