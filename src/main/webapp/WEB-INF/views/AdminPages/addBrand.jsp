<script type="text/javascript">
	var app = angular.module('app', []);
	app.controller('BrandController', [ '$scope', '$http', BrandController ]);

	function BrandController($scope, $http) {
		$scope.curPage = 0;
		$scope.pageSize = 5;
		$http.get('http://localhost:8080/Sunglasses/getAllBrands').success( 
				function(data) {
					$scope.Brands = data;
				}).error(function() {
			$scope.error = "An Error has occured while loading posts!";
		});

		$scope.numberOfPages = function() {
			return Math.ceil($scope.Brands.length / $scope.pageSize);
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
<h3 class="text-center"><strong>MANAGE BRAND</strong></h3>
<hr>
<div class="alert-danger animated">${message }</div>
<hr>
<div class="col-sm-6 " >
	<form:form method="POST" commandName="brandAttribute" action="${pageContext.request.contextPath}/AdminPages/saveBrand" enctype="multipart/form-data">
		
			<c:if test="${brandAttribute.brand_id != 0 }">
				<div class="form-group">
			<form:input path="brand_id" class="form-control" type="hidden" value="${brandAttribute.brand_id}"/>
		</div>
			</c:if>
		<div class="form-group">
			<form:input path="brand_name" class="form-control" title="Enter 3 to 20 Characters"
			placeholder="New Brand Name" required="true" autofocus="true" />
			<form:errors path="brand_name" class="alert-danger"/>
		</div>
		<div class="form-group">
		<c:choose>
			<c:when test="${brandAttribute.brand_id != 0 }">
				<form:input class="btn btn-default btn-block pull-left" path="brand_file" type="file" value="${brandAttribute.brand_image}" />
			</c:when>
			<c:otherwise>
				<form:input class="btn btn-default btn-block pull-left" path="brand_file" type="file" required="true" />
			</c:otherwise>
		</c:choose>
		</div>
	<c:choose>
		<c:when test="${brandAttribute.brand_id != 0 }">
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
		
	</form:form>
</div>
<div class="col-sm-6" data-ng-app="app" data-ng-controller="BrandController">
	<div class="pull-right">
		<input class=" form-control" type="text" size="40" data-ng-model="searchTerm" placeholder="Enter Text for Search" />
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>BRAND NAME</th>
				<th colspan="2">OPTIONS</th>
			</tr>
		</thead>
		<tbody>
			<tr data-ng-repeat="brnd in Brands | filter:searchTerm | pagination: curPage * pageSize | limitTo: pageSize">
				<td width="20%">{{brnd.brand_id}}</td>
				<td width="50%">{{brnd.brand_name}}</td>
				<td>
				<a href="editBrand?id={{brnd.brand_id}}" class="btn btn-primary" aria-label="Left Align">
					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
				</td>
				<td>
					<a href="deleteBrand?id={{brnd.brand_id}}" class="btn btn-danger" aria-label="Right Align" onclick="return confirm('Are You Sure Want to Remove this??');">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
				</td>
			</tr>
		</tbody>

	</table>
	<div class="row">
		<ul class="pagination">
			<li><label><span>Total No. of Records =
						{{Brands.length}}</span></label></li>
		</ul>
		<ul class="pagination pull-right" data-ng-show="Brands.length">
			<li><button class="btn btn-default" data-ng-disabled="curPage == 0"
					data-ng-click="curPage=curPage-1">
					<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
				</button></li>
			<li><button class="btn">
					<span>Page {{curPage + 1}} of {{ numberOfPages()}}</span>
				</button></li>
			<li><button class="btn btn-default"
					data-ng-disabled="curPage >= Brands.length/pageSize - 1"
					data-ng-click="curPage = curPage+1">
					<span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
				</button></li>
		</ul>
	</div>
</div>
</div>