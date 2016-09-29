
<c:forEach items="${resultList.product}" var="productList">

	<div class="col-md-3">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>
					<i class="fa fa-fw fa-check"></i>${productList.product_name}</h5>
			</div>
			<div class="panel-body">
				<div class="thumbnail">
					<img src="${productList.product_image}" alt="Image"
						style="height: 20%;">
					<p class="text-center">
									<strong>Rs.<del>${productList.product_price}</del>
									
									 <mark>Rs.${(productList.product_price) - (productList.product_price * productList.product_discount / 100)}</mark></strong>
								</p>				</div>
				<a href="productDetail?id=${productList.product_id }" role="button"
					class=" btn btn-success btn-block">View Detail</a>
			</div>
		</div>
	</div>
	
</c:forEach>




