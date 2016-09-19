<script type="text/javascript">
	var app = angular.module('app', []);
	app.controller('FrameMaterialController', [ '$scope', '$http', FrameMaterialController ]);

	function FrameMaterialController($scope, $http) {
		$scope.curPage = 0;
		$scope.pageSize = 5;
		$http.get('http://localhost:8080/Sunglasses/getAllFrameMaterials').success( 
				function(data) {
					$scope.FrameMaterials = data;
				}).error(function() {
			$scope.error = "An Error has occured while loading posts!";
		});

		$scope.numberOfPages = function() {
			return Math.ceil($scope.FrameMaterials.length / $scope.pageSize);
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
AddFrameMaterials
<hr>
<div class="alert-danger animated ">${message }</div>
<hr>
<div class="col-sm-6 " >
	<form:form method="POST" commandName="frameMaterialAttribute" action="${pageContext.request.contextPath}/saveFrameMaterial" enctype="multipart/form-data">
		
			<c:if test="${frameMaterialAttribute.frameMaterial_id != 0 }">
				<div class="form-group">
			<form:input path="frameMaterial_id" class="form-control" type="hidden" value="${frameMaterialAttribute.frameMaterial_id}"/>
		</div>
			</c:if>
		<div class="form-group">
			<form:input path="frameMaterial_name" class="form-control"
				placeholder="New FrameMaterial Name" required="true" autofocus="true" />
		</div>
	<c:choose>
		<c:when test="${frameMaterialAttribute.frameMaterial_id != 0 }">
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
<div class="col-sm-6" data-ng-app="app" data-ng-controller="FrameMaterialController">
	<div class="pull-right">
		<input class=" form-control" type="text" size="40"
			data-ng-model="searchTerm" placeholder="Enter Text for Search" />
	</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>BRAND NAME</th>
				<th colspan="2">OPTIONS</th>
			</tr>
		</thead>
		<tbody>
			<tr data-ng-repeat="frmMaterial in FrameMaterials | filter:searchTerm | pagination: curPage * pageSize | limitTo: pageSize">
				<td width="20%">{{frmMaterial.frameMaterial_id}}</td>
				<td width="50%">{{frmMaterial.frameMaterial_name}}</td>
				<td>
				<a href="editFrameMaterial?id={{frmMaterial.frameMaterial_id}}" class="btn btn-primary" aria-label="Left Align">
					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
				</td>
				<td>
					<a href="deleteFrameMaterial?id={{frmMaterial.frameMaterial_id}}" class="btn btn-danger" aria-label="Right Align" onclick="return confirm('Are You Sure Want to Remove this??');">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
				</td>
			</tr>
		</tbody>

	</table>
	<div class="row">
		<ul class="pagination">
			<li><label><span>Total No. of Records =
						{{FrameMaterials.length}}</span></label></li>
		</ul>
		<ul class="pagination pull-right" data-ng-show="FrameMaterials.length">
			<li><button class="btn btn-default" data-ng-disabled="curPage == 0"
					data-ng-click="curPage=curPage-1">
					<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
				</button></li>
			<li><button class="btn">
					<span>Page {{curPage + 1}} of {{ numberOfPages()}}</span>
				</button></li>
			<li><button class="btn btn-default"
					data-ng-disabled="curPage >= FrameMaterials.length/pageSize - 1"
					data-ng-click="curPage = curPage+1">
					<span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
				</button></li>
		</ul>
	</div>
</div>
</div>