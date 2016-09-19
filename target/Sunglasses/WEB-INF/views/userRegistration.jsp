<div class="alert-danger animated">${message }</div>
<div class="container-fluid" id="wrap">
		<div>
			<div class="col-md-6 col-md-offset-3">
				<form:form commandName="userLoginAttribute"  action="${pageContext.request.contextPath}/saveUserDetail" method="POST" >
				<legend>Sign Up</legend>
				<div class="row">
						<div class="col-xs-6 col-md-6">
							<form:input path="user_fname" class="form-control input-lg" placeholder="Enter First Name" required="true" autofocus="true"/>
						</div>
						<div class="col-xs-6 col-md-6">
							<form:input path="user_lname" class="form-control input-lg" placeholder="Enter Last Name" required="true"/>
						</div>
				</div><br>
				<form:textarea path="user_address" class="form-control input-lg" required="true" placeholder="Enter Your Address"/><br>
				<form:input path="user_contact" pattern="^(\+[\d]{1,5}|0)?[7-9]\d{9}" class="form-control input-lg" required="true" placeholder="Enter Your Contact Number"/><br>
				<form:input path="user_email" class="form-control input-lg" required="true"  type="email" placeholder="Enter Your Email"/><br>
				
				<form:password path="user_password" class="form-control input-lg" required="true"  placeholder="Enter Password"
				title="Password must contain at least 6 characters, including UPPER/lowercase and numbers" name="password"
				pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" /><br>
				<input type="password" class="form-control input-lg" required placeholder="Confirm Password"
				title="Password must contain at least 6 characters, including UPPER/lowercase and numbers" name="ucpassword"
				/><br>
				<form:input path="userRole.user_role_id" value="2" type="hidden"/>
				<button class="btn btn-lg btn-primary btn-block signup-btn" type="submit">Create My Account</button>
				</form:form>
			</div>
		</div>
	</div>