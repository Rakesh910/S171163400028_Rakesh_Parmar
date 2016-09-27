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
					<li><a href="home"><span class="glyphicon glyphicon-home"></span>&nbsp;Home</a></li>
					<li><a href="contactUs"><span class="glyphicon glyphicon-phone-alt"></span>&nbsp;Contact Us</a></li>
					<li><a href="aboutUs"><span class="glyphicon glyphicon-globe"></span>&nbsp;About Us</a></li>
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
	
	<div class="container">
	 <div id="login-overlay" class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <h2 class="modal-title" id="myModalLabel">Sign In</h2>
          </div>
          <div class="modal-body">
              <div class="row">
                  <div class="col-xs-12">
                      <div class="well">
                         <!--  <form id="loginForm" method="POST" action="/login/" novalidate="novalidate">
                              <div class="form-group">
                                  <label for="username" class="control-label">Username</label>
                                  <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you username" placeholder="example@gmail.com">
                                  <span class="help-block"></span>
                              </div>
                              <div class="form-group">
                                  <label for="password" class="control-label">Password</label>
                                  <input type="password" class="form-control" id="password" name="password" value="" required="" title="Please enter your password">
                                  <span class="help-block"></span>
                              </div>
                              <div id="loginErrorMsg" class="alert alert-error hide">Wrong username og password</div>
                             
                              <button type="submit" class="btn btn-success btn-block">Login</button>
                          </form> -->
                          
                          <form class="login-form" action="<c:url value='j_spring_security_check'/>" method="POST">
					<div class="form-group">
						<input type="email" name="j_username"  required class="form-control" placeholder="Example@example.com" autofocus="autofocus" />
					</div>
					<div class="form-group">
						<input type="password" name="j_password" class="form-control" placeholder="XXXXXXXX" required />
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

					<div class="row">
						<div class="col-md-6">
							<button type="submit" class="btn btn-success btn-block">Sign In</button>
						</div>
						<div class="col-md-6">
							<a class=" btn btn-info btn-block" href="registration">New User?? Register Here.</a>
						</div>
					</div>
				</form>
                      </div>
                  </div>
                 
              </div>
          </div>
      </div>
  </div>
	</div>
	<br><br>
	<%@ include file="footer.jsp" %>
</body>
</html>