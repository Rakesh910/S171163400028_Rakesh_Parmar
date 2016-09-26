<script type="text/javascript">
	var app = angular.module('app', []);
	app.controller('FrameTypeController', [ '$scope', '$http', FrameTypeController ]);

	function FrameTypeController($scope, $http) {
		$scope.curPage = 0;
		$scope.pageSize = 5;
		$http.get('http://localhost:8080/Sunglasses/getAllFrameTypes').success( 
				function(data) {
					$scope.FrameTypes = data;
				}).error(function() {
			$scope.error = "An Error has occured while loading posts!";
		});

		$scope.numberOfPages = function() {
			return Math.ceil($scope.FrameTypes.length / $scope.pageSize);
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
AddFrameTypes
<hr>
<div class="alert-danger animated ">${message }</div>
<hr>
<div class="col-sm-6 " >
	<form:form method="POST" commandName="frameTypeAttribute" action="${pageContext.request.contextPath}/saveFrameType" enctype="multipart/form-data">
		
			<c:if test="${frameTypeAttribute.frameType_id != 0 }">
				<div class="form-group">
			<form:input path="frameType_id" class="form-control" type="hidden" value="${frameTypeAttribute.frameType_id}"/>
		</div>
			</c:if>
		<div class="form-group">
			<form:input path="frameType_name" class="form-control"
				placeholder="New FrameType Name" required="true" autofocus="true" />
		</div>
		
	<c:choose>
		<c:when test="${frameTypeAttribute.frameType_id != 0 }">
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
<div class="col-sm-6" data-ng-app="app" data-ng-controller="FrameTypeController">
	<div class="pull-right">
		<input class=" form-control" type="text" size="40"
			data-ng-model="searchTerm" placeholder="Enter Text for Search" />
	</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Frame Type</th>
				<th colspan="2">OPTIONS</th>
			</tr>
		</thead>
		<tbody>
			<tr data-ng-repeat="frmtype in FrameTypes | filter:searchTerm | pagination: curPage * pageSize | limitTo: pageSize">
				<td width="20%">{{frmtype.frameType_id}}</td>
				<td width="50%">{{frmtype.frameType_name}}</td>
				<td>
				<a href="editFrameType?id={{frmtype.frameType_id}}" class="btn btn-primary" aria-label="Left Align">
					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
				</td>
				<td>
					<a href="deleteFrameType?id={{frmtype.frameType_id}}" class="btn btn-danger" aria-label="Right Align" onclick="return confirm('Are You Sure Want to Remove this??');">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
				</td>
			</tr>
		</tbody>

	</table>
	<div class="row">
		<ul class="pagination">
			<li><label><span>Total No. of Records =
						{{FrameTypes.length}}</span></label></li>
		</ul>
		<ul class="pagination pull-right" data-ng-show="FrameTypes.length">
			<li><button class="btn btn-default" data-ng-disabled="curPage == 0"
					data-ng-click="curPage=curPage-1">
					<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
				</button></li>
			<li><button class="btn">
					<span>Page {{curPage + 1}} of {{ numberOfPages()}}</span>
				</button></li>
			<li><button class="btn btn-default"
					data-ng-disabled="curPage >= FrameTypes.length/pageSize - 1"
					data-ng-click="curPage = curPage+1">
					<span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
				</button></li>
		</ul>
	</div>
</div>
</div>