<div class="container-fluid">
		<div class="content-wrapper">
			<div class="container">
				<div class="col-md-4">
					<img src="${productDetail.product_image}" alt="Image"
						style="height: 20%;"> <a
						href="addToCart?id=${productDetail.product_id}"
						class="btn btn-success">ADD TO CART</a> <a href="#"
						class="btn btn-info">BUY NOW</a>
				</div>
				<div class="col-md-8">
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
			<td>PRICE</td>
			<td>Rs.<del>${productDetail.product_price}</del> <mark>Rs.${(productDetail.product_price) - (productDetail.product_price * productDetail.product_discount / 100)}</mark></td>
		</tr>
		<tr>
			<td>DISCOUNT</td>
			<td>${productDetail.product_discount}&nbsp;%</td>
		</tr>
		<tr>
			<td>UV PROTECTION</td>
			<td>${productDetail.product_UV}&nbsp;%</td>
		</tr>
		<tr>
			<td>AVAILABLE STOCK</td>
			<td>${productDetail.stock}</td>
		</tr>
		<tr>
			<td>FRAME COLOR</td>
			<td>${productDetail.frameColor.frameColor_name}</td>
		</tr>

		<tr>
			<td>FRAME TYPE</td>
			<td>${productDetail.frameType.frameType_name}</td>
		</tr>
		<tr>
			<td>FRAME MATERIAL</td>
			<td>${productDetail.frameMaterial.frameMaterial_name}</td>
		</tr>
		<tr>
			<td>LENS COLOR</td>
			<td>${productDetail.lensColor.lensColor_name}</td>
		</tr>
		<tr>
			<td>LENS MATERIAL</td>
			<td>${productDetail.lensMaterial.lensMaterial_name}</td>
		</tr>
		<tr>
			<td>PRODUCT SIZE</td>
			<td>${productDetail.productSize.size_name}</td>
		</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<hr>