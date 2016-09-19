	<div id="myCarousel" class="carousel slide" data-ride="carousel" style="margin-top: -20px;">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<li data-target="#myCarousel" data-slide-to="3"></li>
			<li data-target="#myCarousel" data-slide-to="4"></li>
			<li data-target="#myCarousel" data-slide-to="5"></li>
			<li data-target="#myCarousel" data-slide-to="6"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img src="resources/images/1.jpg" alt="Sunglasses">
			</div>

			<div class="item">
				<img src="resources/images/2.jpg" alt="Sunglasses">
				<!--       <div class="carousel-caption"> -->
				<!--         <h3>Chania</h3> -->
				<!--         <p>The atmosphere in Chania has a touch of Florence and Venice.</p> -->
				<!--       </div> -->
			</div>

			<div class="item">
				<img src="resources/images/3.jpg" alt="Sunglasses">
			</div>

			<div class="item">
				<img src="resources/images/4.jpg" alt="Sunglasses">
			</div>

			<div class="item">
				<img src="resources/images/5.jpg" alt="Sunglasses">
			</div>

			<div class="item">
				<img src="resources/images/6.jpg" alt="Sunglasses">
			</div>
		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<hr>
	<div class="container" id="brands"  style="padding-top: 30px;">
	<div class="row">
		<div class="col-lg-12 col-md-12">
			<h1 class="page-header">Our Top New Brands </h1>
		</div>
		 <c:forEach items="${brandList}" var="brandList">
  <div class="col-sm-4 " id="style">
  <div class="thumbnail">
  		<a href="showProductList?id=${brandList.brand_id}"><img src="${brandList.brand_image}" class="img-responsive" style="width:75%;" alt="Image">
  		</a>
  	</div>
  	</div>
  </c:forEach>
	</div>
	</div>

	<hr>
	