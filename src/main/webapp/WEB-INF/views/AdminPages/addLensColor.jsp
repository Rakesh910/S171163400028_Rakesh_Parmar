<script type="text/javascript">
	var app = angular.module('app', []);
	app.controller('LensColorController', [ '$scope', '$http', LensColorController ]);

	function LensColorController($scope, $http) {
		$scope.curPage = 0;
		$scope.pageSize = 5;
		$http.get('http://localhost:8080/Sunglasses/getAllLensColors').success( 
				function(data) {
					$scope.LensColors = data;
				}).error(function() {
			$scope.error = "An Error has occured while loading posts!";
		});

		$scope.numberOfPages = function() {
			return Math.ceil($scope.LensColors.length / $scope.pageSize);
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

<h3 class="text-center"><strong>MANAGE LENSCOLOR</strong></h3>
<hr>
<div class="alert-danger animated ">${message }</div>
<hr>
<div class="col-sm-6 " >
	<form:form method="POST" commandName="lensColorAttribute" action="${pageContext.request.contextPath}/AdminPages/saveLensColor" enctype="multipart/form-data">
		
			<c:if test="${lensColorAttribute.lensColor_id != 0 }">
				<div class="form-group">
			<form:input path="lensColor_id" class="form-control" type="hidden" value="${lensColorAttribute.lensColor_id}"/>
		</div>
			</c:if>
		<div class="form-group">
			<form:input path="lensColor_name" class="form-control" title="Enter 3 to 20 Characters"
				placeholder="New LensColor Name" required="true" autofocus="true" />
				<form:errors path="lensColor_name" class="alert-danger"/>
		</div>
	<c:choose>
		<c:when test="${lensColorAttribute.lensColor_id != 0 }">
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
<div class="col-sm-6" data-ng-app="app" data-ng-controller="LensColorController">
	<div class="pull-right">
		<input class=" form-control" type="text" size="40"
			data-ng-model="searchTerm" placeholder="Enter Text for Search" />
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>LensColor NAME</th>
				<th>Lens Color</th>
				<th colspan="2">OPTIONS</th>
			</tr>
		</thead>
		<tbody>
			<tr data-ng-repeat="lnsclr in LensColors | filter:searchTerm | pagination: curPage * pageSize | limitTo: pageSize">
				<td width="10%">{{lnsclr.lensColor_id}}</td>
				<td width="40%">{{lnsclr.lensColor_name}}</td>
				<td width="20%" style="background-color:{{lnsclr.lensColor_name}} ">{{lnsclr.lensColor_name}}</td>
				<td>
				<a href="editLensColor?id={{lnsclr.lensColor_id}}" class="btn btn-primary" aria-label="Left Align">
					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
				</td>
				<td>
					<a href="deleteLensColor?id={{lnsclr.lensColor_id}}" class="btn btn-danger" aria-label="Right Align" onclick="return confirm('Are You Sure Want to Remove this??');">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
				</td>
			</tr>
		</tbody>

	</table>
	<div class="row">
		<ul class="pagination">
			<li><label><span>Total No. of Records =
						{{LensColors.length}}</span></label></li>
		</ul>
		<ul class="pagination pull-right" data-ng-show="LensColors.length">
			<li><button class="btn btn-default" data-ng-disabled="curPage == 0"
					data-ng-click="curPage=curPage-1">
					<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
				</button></li>
			<li><button class="btn">
					<span>Page {{curPage + 1}} of {{ numberOfPages()}}</span>
				</button></li>
			<li><button class="btn btn-default"
					data-ng-disabled="curPage >= LensColors.length/pageSize - 1"
					data-ng-click="curPage = curPage+1">
					<span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
				</button></li>
		</ul>
	</div>
</div>
</div>