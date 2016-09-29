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
<body data-spy="scroll" data-target=".navbar" data-offset="50" id="myPage">
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
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="AdminPages/admin"><span class="glyphicon glyphicon-user"></span>&nbsp;Admin Home</a></li>
					</sec:authorize>
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
	<div class="container">
     
    
     <!--  <div class='col-xs-9'>
          <div class="center">        
        <h1 >About Us</h1>
        <p class="lead " ><u>Website Design, Development, SEO and Digital Marketing Company</u></p>        
     </div>
          <p  >SkyStep INFOS is a an IT Training and Development Company, which deals with Website Development and Designing , Software Development, Mobile Application Development, Game Development, Graphics Designing, Search Engine Optimization, Social Media and Digital Marketing, Cyber Security Services, Training and Research.</p>
            <br>
            <p  > We are well organized startup founded by Mr.Saket Goyal and Mr. Seshasai J. Pandravada on 11th December 2014 with a vision of developing quality products that would not only satisfy our customer`s need but also our own standard.</p>
            <br>
            <p  >With the latest technologies changing every minute clients require an enormous change in the way they present their website, which we do. Ranging from a simple design of the website, to its extensive maintenance SkyStep INFOS provides an exquisite solution to any design required by the customer.</p>
            <br>
            <p  >Constantly developing and reiterating professionalism in every endeavor of ours is our key. Providing support being the basic need of the customer, we at SkyStep INFOS believe in a higher role.We at SkyStep INFOS believe that a healthy relationship with the customer can give a company a huge advantage among others and we follow this until the best possible solution is delivered.</p>
            <br>
            <p  >SkyStep INFOS is an IT solutions company, providing innovative products and reliable professional services to business enterprises across the globe. We, at SkyStep INFOS help organizations assess how to maximize the clients? performance and work with them to achieve their vision. We develop and implement technology to improve our clients? productivity and efficiency. Ultimately, we enable our clients to become high-performance businesses and governments.</p>
            
            </div> -->
        <div class="col-xs-12">
        <div class="center"> 
        	<h1 class="text-center">About Us</h1>
       		 <hr>
       		<h2>Services we offer</h2><br>
        </div>
            
            <p  ><i class="glyphicon glyphicon-check"></i> Website Development and Designing</p>
            <p  ><i class="glyphicon glyphicon-check"></i> E-Commerce Application Development</p>
            <p  ><i class="glyphicon glyphicon-check"></i> Software Development</p>
            <p  ><i class="glyphicon glyphicon-check"></i> Graphics Designing and Branding Solutions</p>
            <p  ><i class="glyphicon glyphicon-check"></i> Mobile Application Developments</p>
            <p  ><i class="glyphicon glyphicon-check"></i> Search Engine Optimization (SEO)</p>
            <p  ><i class="glyphicon glyphicon-check"></i> Social Media and Digital Marketing</p>
            <p  ><i class="glyphicon glyphicon-check"></i> SSL and Payment Gateway</p>
            <p  ><i class="glyphicon glyphicon-check"></i>Cyber Security Services</p>
            <p  ><i class="glyphicon glyphicon-check"></i> Training</p>
            
            <hr>
            
         </div>
           
</div>
	<hr>
<%@ include file="footer.jsp" %>
</body>
</html>	