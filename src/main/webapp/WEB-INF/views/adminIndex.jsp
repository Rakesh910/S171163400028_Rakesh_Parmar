<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN HOME</title>
<link type="text/css" rel="stylesheet" href="resources/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="resources/css/main.css" />
<link type="text/css" rel="stylesheet" href="resources/css/admin.css" />
<link type="text/css" rel="stylesheet" href="resources/css/animate.css" />
<script src="resources/js/jquery.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/angular.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="pull-left col-sm-3">
				<h3>ADMINISTRATOR</h3>
			</div>
			<div class="col-sm-6">
				<img alt="LOGO" src="resources/images/logo.png" width="70%"
					height="10%">
			</div>
			<div class="pull-right col-sm-3 text-center">
				<h4>Welcome ${username }</h4>
				<a href="logout" class="btn btn-info btn-block">Logout</a>
			</div>
		</div>
	</div>
	<hr>
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
				<p>
					<a role="button" class="btn btn-default btn-block" href="admin">Admin Home</a>
				</p>
				<p>
					<a role="button" class="btn btn-default btn-block" href="manageBrand">Manage Brand</a>
				</p>
				<p>
					<a role="button" class="btn btn-default btn-block" href="manageCategory">Manage Category</a>
				</p>
				<p>
					<a role="button" class="btn btn-default btn-block" href="manageSupplier">Manage Supplier</a>
				</p>
				<p>
					<a role="button" class="btn btn-default btn-block" href="manageProduct">Manage Product</a>
				</p>
				<p>
					<a role="button" class="btn btn-default btn-block" href="manageProductSize">Manage Size</a>
				</p>
				<p>
					<a role="button" class="btn btn-default btn-block" href="manageFrameType">Frame Type</a>
				</p>
				<p>
					<a role="button" class="btn btn-default btn-block" href="manageFrameColor">Frame Color</a>
				</p>
				<p>
					<a role="button" class="btn btn-default btn-block" href="manageFrameMaterial">Frame Material</a>
				</p>
				<p>
					<a role="button" class="btn btn-default btn-block" href="manageLensColor">Lens Color</a>
				</p>
				<p>
					<a role="button" class="btn btn-default btn-block" href="manageLensMaterial">Lens Material</a>
				</p>
				
			</div>
			  <div class="col-sm-10 text-left" style="overflow-y: scroll; height:100%;">
			  <c:if test="${isAdminClickHome == true }">
			  <img  class="img-thumbnail" src="resources/images/AdminHome.jpg" style="width: 100%;height: 100%" />
			  </c:if>
			  <c:if test="${isAdminClickBrand == true }">
			  	<%@ include file="addBrand.jsp" %>
			  </c:if>
			  <c:if test="${isAdminClickCategory == true }">
			  	<%@ include file="addCategory.jsp" %>
			  </c:if>
			   <c:if test="${isAdminClickSupplier == true }">
			  	<%@ include file="addSupplier.jsp" %>
			  </c:if>
			   <c:if test="${isAdminClickProduct == true }">
			  	<%@ include file="addProduct.jsp" %>
			  </c:if>
			  <c:if test="${isAdminClickProductSize == true }">
			  	<%@ include file="addProductSize.jsp" %>
			  </c:if>
			  <c:if test="${isAdminClickFrameType == true }">
			  	<%@ include file="addFrameType.jsp" %>
			  </c:if>
			  <c:if test="${isAdminClickFrameColor == true }">
			  	<%@ include file="addFrameColor.jsp" %>
			  </c:if>
			  <c:if test="${isAdminClickFrameMaterial == true }">
			  	<%@ include file="addFrameMaterial.jsp" %>
			  </c:if>
			   <c:if test="${isAdminClickFrameMaterial == true }">
			  	<%@ include file="addFrameMaterial.jsp" %>
			  </c:if>
			   <c:if test="${isAdminClickLensColor == true }">
			  	<%@ include file="addLensColor.jsp" %>
			  </c:if>
			   <c:if test="${isAdminClickLensMaterial == true }">
			  	<%@ include file="addLensMaterial.jsp" %>
			  </c:if>
			  </div>
		</div>
	</div>
	<footer class="container-fluid text-center">
		<p>Footer Text</p>
	</footer>

</body>
</html>