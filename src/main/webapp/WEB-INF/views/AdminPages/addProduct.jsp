<script type="text/javascript">
	var app = angular.module('app', []);
	app.controller('ProductController',
			[ '$scope', '$http', ProductController ]);

	function ProductController($scope, $http) {
		$scope.curPage = 0;
		$scope.pageSize = 5;
		$http.get('http://localhost:8080/Sunglasses/getAllProducts').success(
				function(data) {
					$scope.Products = data;
				}).error(function() {
			$scope.error = "An Error has occured while loading posts!";
		});

		$scope.numberOfPages = function() {
			return Math.ceil($scope.Products.length / $scope.pageSize);
		};
	}

	angular.module('app').filter('pagination', function() {
		return function(input, start) {
			start = +start;
			return input.slice(start);
		};
	});
	
	

	$(function($scope) { // document and jquery ready
		$("#brandId").on("change",function(e) {
			var bid = $(this).val();
			$.ajax({
					type : "GET",
					url : 'http://localhost:8080/Sunglasses/brandOnchange?id='+ bid,
					datatype : 'JSON',
					contentType : 'application/json; charset=utf-8',
					success : function(data) {
							$("#categoryId").empty();
							for (var i = 0; i < data.length; i++) {
								$("#categoryId").append("<option value='" + data[i].cat_id+ "'>"+ data[i].cat_name+ "</option>");
							}

					},error : function() {
						alert('something bad happened');
						}
					});
		});
	});
</script>
<div class="container-fluid">
	AddProducts
	<hr>
	<div class="alert-danger animated ">${message }</div>
	<hr>
	<form:form method="POST" commandName="productAttribute"
			action="${pageContext.request.contextPath}/saveProduct"
			enctype="multipart/form-data">
	<div class="col-xs-6 col-md-6">
			<c:if test="${productAttribute.product_id != 0 }">
				<div class="form-group">
					<form:input path="product_id" class="form-control" type="hidden"
						value="${productAttribute.product_id}" />
				</div>
			</c:if>
			<div class="row">
				<div class="col-xs-6 col-md-6 form-group">
					<c:choose>
						<c:when test="${productAttribute.product_id != 0 }">
							<div class="form-group">
								<form:select path="brand.brand_id" class="form-control" required="true" autofocus="true">
									<c:forEach items="${brandList}" var="brandList">
										<c:choose>
											<c:when
												test="${brandList.brand_id == productAttribute.brand.brand_id}">
												<form:option value="${brandList.brand_id}"
													selected="${brandList.brand_id}">${brandList.brand_name}</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${brandList.brand_id}">${brandList.brand_name}</form:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form:select>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group">
								<form:select path="brand.brand_id" id="brandId" class="form-control" required="true" autofocus="true">
									<form:option class="form-control" value="0">Select Brand</form:option>
									<form:options class="form-control" items="${brandList}" itemValue="brand_id" itemLabel="brand_name" />
								</form:select>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<!-- onchange="location.href='http://localhost:8080/Sunglasses/getString'" -->
				<div class="col-xs-6 col-md-6 form-group">
					<c:choose>
						<c:when test="${productAttribute.product_id != 0 }">
							<div class="form-group">
								<form:select path="category.cat_id" id="categoryId" class="form-control" required="true">
									<c:forEach items="${categoryList}" var="categoryList">
										<c:choose>
											<c:when
												test="${categoryList.cat_id == productAttribute.category.cat_id}">
												<form:option value="${categoryList.cat_id}"
													selected="${categoryList.cat_id}">${categoryList.cat_name}</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${categoryList.cat_id}">${categoryList.cat_name}</form:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form:select>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group">
								<form:select path="category.cat_id" class="form-control"  id="categoryId" required="true">
							<form:option class="form-control" value="0">Select Category</form:option>
							<%-- <form:options class="form-control" items="${categoryList}" itemValue="cat_id" itemLabel="cat_name"/> --%>
						</form:select>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			
			<div class="form-group">
				<form:input path="product_name" class="form-control" placeholder="New Product Name" required="true" autofocus="true" />
			</div>
	
			<div class="row">
			<div class="col-xs-6 col-md-6 form-group">
				<c:choose>
						<c:when test="${productAttribute.product_id != 0 }">
							<div class="form-group">
								<form:select path="supplier.supplier_id" class="form-control" required="true">
									<c:forEach items="${supplierList}" var="supplierList">
										<c:choose>
											<c:when
												test="${supplierList.supplier_id == productAttribute.supplier.supplier_id}">
												<form:option value="${supplierList.supplier_id}"
													selected="${supplierList.supplier_id}">${supplierList.supplier_name}</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${supplierList.supplier_id}">${supplierList.supplier_name}</form:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form:select>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group">
								<form:select path="supplier.supplier_id" class="form-control" required="true">
							<form:option class="form-control" value="0">Select Supplier</form:option>
							<form:options class="form-control" items="${supplierList}" itemValue="supplier_id" itemLabel="supplier_name"/>
						</form:select>
							</div>
						</c:otherwise>
					</c:choose>
			</div>
			<div class="col-xs-6 col-md-6  form-group">
				<c:choose>
					<c:when test="${productAttribute.product_id != 0 }">
						<form:input class="btn btn-default btn-block pull-left" path="product_file" type="file" value="${productAttribute.product_image}" />
					</c:when>
					<c:otherwise>
						<form:input class="btn btn-default btn-block pull-left" path="product_file" type="file" required="true" />
					</c:otherwise>
				</c:choose>
			</div>
			
			</div>
			<div class="row">
			<div class="col-xs-6 col-md-6  form-group">
				<form:input path="product_price" class="form-control" placeholder="Enter Price" required="true" />
			</div>
			<div class="col-xs-6 col-md-6  form-group">
				<form:input path="stock" class="form-control" placeholder="Stock of Product" required="true" type="number" max="1000" min="1" />
			</div>
			</div>
			
			<div class="row">
			<div class="col-xs-6 col-md-6  form-group">
				<c:choose>
						<c:when test="${productAttribute.product_id != 0 }">
							<div class="form-group">
								<form:select path="product_UV" class="form-control" required="true">
									<c:forEach begin="10" end="100" step="10" var="uvprotect">
										<c:choose>
											<c:when
												test="${uvprotect == productAttribute.product_UV}">
												<form:option value="${productAttribute.product_UV}" selected="${productAttribute.product_UV}">${productAttribute.product_UV}%</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${uvprotect}">${uvprotect}%</form:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form:select>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group">
							<form:select path="product_UV" class="form-control" required="true">
							<form:option value="0">Select UV Protection</form:option>
							<form:option value="10">10%</form:option>
							<form:option value="20">20%</form:option>
							<form:option value="30">30%</form:option>
							<form:option value="40">40%</form:option>
							<form:option value="50">50%</form:option>
							<form:option value="60">60%</form:option>
							<form:option value="70">70%</form:option>
							<form:option value="80">80%</form:option>
							<form:option value="90">90%</form:option>
							<form:option value="100">100%</form:option>
						</form:select>
							</div>
						</c:otherwise>
					</c:choose>
			</div>
			<div class="col-xs-6 col-md-6  form-group">
			<c:choose>
						<c:when test="${productAttribute.product_id != 0 }">
							<div class="form-group">
								<form:select path="product_discount" class="form-control" required="true">
						<c:forEach begin="10" end="90" step="10" var="discount">
							<c:choose>
								<c:when test="${discount == productAttribute.product_discount }">
									<form:option value="${productAttribute.product_discount}" selected="${productAttribute.product_discount}">${productAttribute.product_discount}%</form:option>
								</c:when>
								<c:otherwise>
									<form:option value="${discount}">${discount}%</form:option>
								</c:otherwise>
							
							</c:choose>
						</c:forEach>
						</form:select>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group">
							<form:select path="product_discount" class="form-control" required="true">
							<form:option value="0">Select Discount</form:option>
							<form:option value="10">10%</form:option>
							<form:option value="20">20%</form:option>
							<form:option value="30">30%</form:option>
							<form:option value="40">40%</form:option>
							<form:option value="50">50%</form:option>
							<form:option value="60">60%</form:option>
							<form:option value="70">70%</form:option>
							<form:option value="80">80%</form:option>
							<form:option value="90">90%</form:option>
						</form:select>
							</div>
						</c:otherwise>
					</c:choose>
			</div>
			</div>
			<c:choose>
				<c:when test="${productAttribute.product_id != 0 }">
					<div class="form-group">
						<button type="submit" class="btn btn-success pull-right  ">Update</button>
					</div>
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<button type="submit" class="btn btn-success pull-right  ">Submit</button>
					</div>
					<div class="form-group">
						<button type="reset" class="btn btn-info ">Reset</button>
					</div>
				</c:otherwise>
			</c:choose>

		
	</div>
	<div class="col-xs-6 col-md-6">
	<c:choose>
		<c:when test="${productAttribute.product_id != 0 }">
			<div class="form-label">Select Size of Product</div>
				<c:forEach items="${productSizeList}" var="productSizeList">
					<c:choose>
								<c:when test="${productSizeList.size_id == productAttribute.productSize.size_id }">
									<form:radiobutton path="productSize.size_id" value="${productSizeList.size_id}" label="${productSizeList.size_name }" checked="true"/>
								</c:when>
								<c:otherwise>
									<form:radiobutton path="productSize.size_id" value="${productSizeList.size_id}" label="${productSizeList.size_name }" />
								</c:otherwise>
							</c:choose>
				
				</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="form-label">Select Size of Product</div>
					<form:radiobuttons items="${productSizeList}" path="productSize.size_id" itemValue="size_id" itemLabel="size_name"/>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${productAttribute.product_id != 0 }">
			<div class="form-label">Select Lens Color</div>
				<c:forEach items="${lensColorList}" var="lensColorList">
					<c:choose>
								<c:when test="${lensColorList.lensColor_id == productAttribute.lensColor.lensColor_id }">
									<form:radiobutton path="lensColor.lensColor_id" value="${lensColorList.lensColor_id}" label="${lensColorList.lensColor_name }" checked="true"/>
								</c:when>
								<c:otherwise>
									<form:radiobutton path="lensColor.lensColor_id" value="${lensColorList.lensColor_id}" label="${lensColorList.lensColor_name }" />
								</c:otherwise>
							</c:choose>
				
				</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="form-label">Select Lens Color</div>
					<form:radiobuttons items="${lensColorList}" path="lensColor.lensColor_id" itemValue="lensColor_id" itemLabel="lensColor_name"/>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${productAttribute.product_id != 0 }">
			<div class="form-label">Select Lens Material</div>
				<c:forEach items="${lensMaterialList}" var="lensMaterialList">
					<c:choose>
								<c:when test="${lensMaterialList.lensMaterial_id == productAttribute.lensMaterial.lensMaterial_id }">
									<form:radiobutton path="lensMaterial.lensMaterial_id" value="${lensMaterialList.lensMaterial_id}" label="${lensMaterialList.lensMaterial_name }" checked="true"/>
								</c:when>
								<c:otherwise>
									<form:radiobutton path="lensMaterial.lensMaterial_id" value="${lensMaterialList.lensMaterial_id}" label="${lensMaterialList.lensMaterial_name }" />
								</c:otherwise>
							</c:choose>
				
				</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="form-label">Select Lens Material</div>
					<form:radiobuttons items="${lensMaterialList}" path="lensMaterial.lensMaterial_id" itemValue="lensMaterial_id" itemLabel="lensMaterial_name"/>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${productAttribute.product_id != 0 }">
			<div class="form-label">Select Frame Color</div>
				<c:forEach items="${frameColorList}" var="frameColorList">
					<c:choose>
								<c:when test="${frameColorList.frameColor_id == productAttribute.frameColor.frameColor_id }">
									<form:radiobutton path="frameColor.frameColor_id" value="${frameColorList.frameColor_id}" label="${frameColorList.frameColor_name }" checked="true"/>
								</c:when>
								<c:otherwise>
									<form:radiobutton path="frameColor.frameColor_id" value="${frameColorList.frameColor_id}" label="${frameColorList.frameColor_name }" />
								</c:otherwise>
							</c:choose>
				
				</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="form-label">Select Frame Color</div>
					<form:radiobuttons items="${frameColorList}" path="frameColor.frameColor_id" itemValue="frameColor_id" itemLabel="frameColor_name"/>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${productAttribute.product_id != 0 }">
			<div class="form-label">Select Frame Material</div>
				<c:forEach items="${frameMaterialList}" var="frameMaterialList">
					<c:choose>
								<c:when test="${frameMaterialList.frameMaterial_id == productAttribute.frameMaterial.frameMaterial_id }">
									<form:radiobutton path="frameMaterial.frameMaterial_id" value="${frameMaterialList.frameMaterial_id}" label="${frameMaterialList.frameMaterial_name }" checked="true"/>
								
								</c:when>
								<c:otherwise>
									<form:radiobutton path="frameMaterial.frameMaterial_id" value="${frameMaterialList.frameMaterial_id}" label="${frameMaterialList.frameMaterial_name }" />
								</c:otherwise>
							</c:choose>
				
				</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="form-label">Select Frame Material</div>
					<form:radiobuttons items="${frameMaterialList}" path="frameMaterial.frameMaterial_id" itemValue="frameMaterial_id" itemLabel="frameMaterial_name"/>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${productAttribute.product_id != 0 }">
			<div class="form-label">Select Frame Type</div>
				<c:forEach items="${frameTypeList}" var="frameTypeList">
					<c:choose>
								<c:when test="${frameTypeList.frameType_id == productAttribute.frameType.frameType_id }">
								<form:radiobutton path="frameType.frameType_id" value="${frameTypeList.frameType_id}" label="${frameTypeList.frameType_name }" checked="true" />
								</c:when>
								<c:otherwise>
									<form:radiobutton path="frameType.frameType_id" value="${frameTypeList.frameType_id}" label="${frameTypeList.frameType_name }" />
								</c:otherwise>
							</c:choose>
				
				</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="form-label">Select Frame Type</div>
					<form:radiobuttons items="${frameTypeList}" path="frameType.frameType_id" itemValue="frameType_id" itemLabel="frameType_name"/>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${productAttribute.product_id != 0 }">
			<c:choose>
				<c:when test="${productAttribute.newArrival != true }">
				<div class="form-label">Product Is New Arrival??</div>
					<form:radiobutton path="newArrival" value="0" label="No" checked="true"></form:radiobutton>
					<form:radiobutton path="newArrival" value="1" label="Yes" ></form:radiobutton>
				</c:when>
				<c:otherwise>
				<div class="form-label">Product Is New Arrival??</div>
					<form:radiobutton path="newArrival" value="0" label="No"></form:radiobutton>
			<form:radiobutton path="newArrival" value="1" label="Yes" checked="true"></form:radiobutton>
				</c:otherwise>			
			</c:choose>
		
		</c:when>
		<c:otherwise>
			<div class="form-label">Product Is New Arrival??</div>
			<form:radiobutton path="newArrival" value="0" label="No"></form:radiobutton>
			<form:radiobutton path="newArrival" value="1" label="Yes" checked="true"></form:radiobutton>
		</c:otherwise>
	</c:choose>
	
	</div>
	</form:form>
</div>
<div class="container-fluid" data-ng-app="app" data-ng-controller="ProductController">
		<div class="pull-right">
			<input class=" form-control" type="text" size="40"
				data-ng-model="searchTerm" placeholder="Enter Text for Search" />
		</div>
		<table class="table table-striped col-md-12">
			<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Price</th>
							<th>Discount</th>
							<th>Stock</th>
							<th>Brand</th>
							<th>Category</th>
							<th>Supplier</th>
							<th>UV </th>
							<th>Size</th>
							<th>Lens Color</th>
							<th>Lens Material</th>
							<th>Frame Color</th>
							<th>Frame Material</th>
							<th>FrameType</th>
							<th >OPTIONS</th>
						</tr>
					</thead>
			<tbody>
				<tr
					data-ng-repeat="prd in Products | filter:searchTerm | pagination: curPage * pageSize | limitTo: pageSize">
					<td width="2%">{{prd.product_id}}</td>
					<td width="10%">{{prd.product_name}}</td>
					<td width="3%">{{prd.product_price}}</td>
					<td width="5%">{{prd.product_discount}}</td>
					<td width="2%">{{prd.stock}}</td>
					<td width="10%">{{prd.brand_name}}</td>
					<td width="10%">{{prd.cat_name}}</td>
					<td width="10%">{{prd.supplier_name}}</td>
					<td width="2%">{{prd.product_UV}}</td>
					<td width="5%">{{prd.size_name}}</td>
					<td width="5%">{{prd.lensColor_name}}</td>
					<td width="10%">{{prd.lensMaterial_name}}</td>
					<td width="5%">{{prd.frameColor_name}}</td>
					<td width="5%">{{prd.frameMaterial_name}}</td>
					<td width="10%">{{prd.frameType_name}}</td>
					
					<td><a href="editProduct?id={{prd.product_id}}"
						class="btn btn-primary" aria-label="Left Align"> <span
							class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
					<a href="deleteProduct?id={{prd.product_id}}"
						class="btn btn-danger" aria-label="Right Align"
						onclick="return confirm('Are You Sure Want to Remove this??');">
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					</a></td>
				</tr>
			</tbody>

		</table>
		<div class="row">
			<ul class="pagination">
				<li><label><span>Total No. of Records =
							{{Products.length}}</span></label></li>
			</ul>
			<ul class="pagination pull-right" data-ng-show="Products.length">
				<li><button class="btn btn-default"
						data-ng-disabled="curPage == 0" data-ng-click="curPage=curPage-1">
						<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
					</button></li>
				<li><button class="btn">
						<span>Page {{curPage + 1}} of {{ numberOfPages()}}</span>
					</button></li>
				<li><button class="btn btn-default"
						data-ng-disabled="curPage >= Products.length/pageSize - 1"
						data-ng-click="curPage = curPage+1">
						<span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
					</button></li>
			</ul>
		</div>
</div>