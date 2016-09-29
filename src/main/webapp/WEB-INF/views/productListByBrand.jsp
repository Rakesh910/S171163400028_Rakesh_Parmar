
				<c:forEach items="${brandList.category}" var="catList">
					<c:forEach items="${catList.product}" var="productList">
				
						<div class="col-md-3">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h5>${productList.product_name}<p></h5>
								</div>
								<div class="panel-body">
									<div class="thumbnail">
										<img src="${productList.product_image}" alt="Image"
											style="height: 20%;">
										

									</div>
									<p class="text-center">
									<strong>Rs.<del>${productList.product_price}</del>
									
									 <mark>Rs.${(productList.product_price) - (productList.product_price * productList.product_discount / 100)}</mark></strong>
								</p>
									<a href="productDetail?id=${productList.product_id }" role="button" class=" btn btn-success btn-block">View Detail</a>
									
								</div>
							</div>
						</div>
					
					</c:forEach>
				</c:forEach>



			