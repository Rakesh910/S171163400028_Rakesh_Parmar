<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">
						<div class="row">
							<div class="col-xs-7">
								<h5><span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart</h5>
							</div>
							<div class="col-xs-5">
								<button type="button" class="btn btn-primary btn-sm btn-block">
									<span class="glyphicon glyphicon-share-alt"></span> Continue shopping
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-body">
				<c:forEach items="${order.cartDetail}" var="order">
					<form method="POST" action="${pageContext.request.contextPath}/updateCart">
						<div class="row">
						<div class="col-xs-2"><img style="height:10%;width:100%" src="${order.product.product_image}">
						</div>
						<div class="col-xs-4">
							<h4 class="product-name"><strong>${order.product.product_name}</strong></h4><h4><small>Product description</small></h4>
						</div>
						<div class="col-xs-6">
							<div class="col-xs-3 text-right">
								<h6><strong>${order.product.product_price} <span class="text-muted">x</span></strong></h6>
							</div>
							<div class="col-xs-3">
								<input  class="form-control" type="hidden" name="product_id" value="${order.product.product_id}"/>
								<input class="form-control input-sm" name="quantity" value="${order.quantity }" />
							</div>
							<div class="col-xs-3 text-right">
								<h6><strong>${order.total} </strong></h6>
							</div>
							<div class="col-xs-3">
								<button type="submit" class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-edit"></span>
								</button>
								<a href="deleteCart?id=${order.product.product_id}" role="button" class="btn  btn-danger btn-sm"><span class="glyphicon glyphicon-trash"> </span></a>
							</div>
						</div>
					</div>
					</form>
					<hr>
					</c:forEach>
					<div class="row">
						<div class="text-center">
							<div class="col-xs-9">
								<h6 class="text-right">Added items?</h6>
							</div>
							<div class="col-xs-3">
								<button type="button" class="btn btn-default btn-sm btn-block">
									Update cart
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row text-center">
						<div class="col-xs-9">
							<h4 class="text-right">Total <strong>${order.grandTotal }</strong></h4>
						</div>
						<div class="col-xs-3">
							<a href="checkOut" role="button" class="btn btn-success btn-block">Checkout</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
