<div class="container-fluid">
		<div class="row">
			<div class="pull-left col-sm-4">
				<a href="home"><img alt="LOGO" src="resources/images/logo.png" width="100%"
					height="10%"></a>
			</div>
			<div class="col-sm-4 text-center">
				<h3>Welcome to Sunglasses</h3>
				<div class="alert-danger animated"><strong>${message }</strong></div>
			</div>
			<c:if test="${isLoginPage != true }">
			
			<c:choose>
				<c:when test="${empty userId}">
					<div class="pull-right col-sm-4 text-center">
						<br> 
						<!-- <a href="#" role="button" class="btn btn-success "
							data-toggle="modal" data-target="#login-modal">Existing
							User?? Sign In&nbsp;<span class="glyphicon glyphicon-log-in"></span></a> -->
							<a href="loginPage" role="button" class="btn btn-success ">ExistingUser?? Sign In&nbsp;<span class="glyphicon glyphicon-log-in"></span></a>
							
						<a href="registration" class="btn btn-info "> New User?? Sign Up</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="pull-right col-sm-4 text-center">
						<h4>Welcome ${username }</h4>
						<a href="logout" class="btn btn-info btn-block">Logout</a>
					</div>
				</c:otherwise>
			</c:choose>
				
			</c:if>
			
		</div>
	</div>
	