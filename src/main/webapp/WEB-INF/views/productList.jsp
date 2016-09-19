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
		<div class="col-md-9 ">
			<h2 class="page-header">Section Heading</h2>
			
			<c:forEach items="${brandList.category}" var="catList">
					<c:forEach items="${catList.product}" var="productList">
						<div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h5><i class="fa fa-fw fa-check"></i>${productList.product_name}</h5>
                    </div>
                    <div class="panel-body">
                        <div class="thumbnail">
								<img src="${productList.product_image}" alt="Image"
									style="height: 20%;">
								<p>
									<strong>${productList.product_name}</strong>
								</p>
								<a href="productDetail?id=${productList.product_id }"
									role="button" class=" btn btn-success btn-block">View
									Detail</a>
							</div>
                    </div>
                </div>
            </div>
					</c:forEach>

				</c:forEach>
			
           
            
		</div>
	</div>

</div>