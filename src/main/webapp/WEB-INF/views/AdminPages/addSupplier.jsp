<script type="text/javascript">
	var app = angular.module('app', []);
	app.controller('SupplierController', [ '$scope', '$http', SupplierController ]);

	function SupplierController($scope, $http) {
		$scope.curPage = 0;
		$scope.pageSize = 5;
		$http.get('http://localhost:8080/Sunglasses/getAllSuppliers').success( 
				function(data) {
					$scope.Suppliers = data;
				}).error(function() {
			$scope.error = "An Error has occured while loading posts!";
		});

		$scope.numberOfPages = function() {
			return Math.ceil($scope.Suppliers.length / $scope.pageSize);
		};
	}

	angular.module('app').filter('pagination', function() {
		return function(input, start) {
			start = +start;
			return input.slice(start);
		};
	});
</script>
<div class="container-fluid">
<h3 class="text-center"><strong>MANAGE SUPPLIER</strong></h3>
<hr>
<div class="alert-danger animated ">${message }</div>
<hr>
<div class="modal-dialog" >
	<form:form method="POST" commandName="supplierAttribute" action="${pageContext.request.contextPath}/AdminPages/saveSupplier" enctype="multipart/form-data">
		
			<c:if test="${supplierAttribute.supplier_id != 0 }">
				<div class="form-group">
			<form:input path="supplier_id" class="form-control" type="hidden" value="${supplierAttribute.supplier_id}"/>
		</div>
			</c:if>
		<div class="form-group">
			<form:input path="supplier_name" class="form-control" placeholder="New Supplier Name" title="Enter 3 to 50 Characters"
			required="true" autofocus="true" />
			<form:errors path="supplier_name" class="alert-danger"/>
		</div>
		
		<div class="form-group">
					<form:textarea path="supplier_address" class="form-control" placeholder="Supplier Address" title="Enter 3 to 50 Characters"
					required="true"/>
					<form:errors path="supplier_address" class="alert-danger"/>
				</div>
				
				<div class="form-group">
					<form:input path="supplier_contact" class="form-control" placeholder="Supplier Mobile Number" title="Enter Numbers Only" pattern="^(\+[\d]{1,5}|0)?[7-9]\d{9}"
					 required="true" />
					<form:errors path="supplier_contact" class="alert-danger"/>
				</div>
				<div class="form-group">
					<form:input path="supplier_email" class="form-control" placeholder="Supplier Email Address" required="true" type="email" title="Enter Valid Email Id" />
					<form:errors path="supplier_email" class="alert-danger"/>
				</div>
	<c:choose>
		<c:when test="${supplierAttribute.supplier_id != 0 }">
			<div class="form-group">
			<button type="submit" class="btn btn-success btn-block  ">Update</button>
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
		
	</form:form>
</div>
<hr>
<div class="" data-ng-app="app" data-ng-controller="SupplierController">
	<div class="pull-right">
		<input class=" form-control" type="text" size="40"
			data-ng-model="searchTerm" placeholder="Enter Text for Search" />
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Address</th>
				<th>Contact No.</th>
				<th>Email</th>
				<th colspan="2">OPTIONS</th>
			</tr>
		</thead>
		<tbody>
			<tr data-ng-repeat="brnd in Suppliers | filter:searchTerm | pagination: curPage * pageSize | limitTo: pageSize">
				<td >{{brnd.supplier_id}}</td>
				<td >{{brnd.supplier_name}}</td>
				<td>{{brnd.supplier_address}}</td>
				<td>{{brnd.supplier_contact}}</td>
				<td>{{brnd.supplier_email}}</td>
				<td>
				<a href="editSupplier?id={{brnd.supplier_id}}" class="btn btn-primary" aria-label="Left Align">
					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
				</td>
				<td>
					<a href="deleteSupplier?id={{brnd.supplier_id}}" class="btn btn-danger" aria-label="Right Align" onclick="return confirm('Are You Sure Want to Remove this??');">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
				</td>
			</tr>
		</tbody>

	</table>
	<div class="row">
		<ul class="pagination">
			<li><label><span>Total No. of Records =
						{{Suppliers.length}}</span></label></li>
		</ul>
		<ul class="pagination pull-right" data-ng-show="Suppliers.length">
			<li><button class="btn btn-default" data-ng-disabled="curPage == 0"
					data-ng-click="curPage=curPage-1">
					<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
				</button></li>
			<li><button class="btn">
					<span>Page {{curPage + 1}} of {{ numberOfPages()}}</span>
				</button></li>
			<li><button class="btn btn-default"
					data-ng-disabled="curPage >= Suppliers.length/pageSize - 1"
					data-ng-click="curPage = curPage+1">
					<span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
				</button></li>
		</ul>
	</div>
</div>
</div>