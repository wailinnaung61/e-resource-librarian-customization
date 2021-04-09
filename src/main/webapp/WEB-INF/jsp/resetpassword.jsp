<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Recover Password</title>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/fontawesome-free/css/all.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/dist/css/adminlte.min.css">
</head>
<body class="hold-transition login-page"
	style="background-color: #282828;">
	<div class="login-box">
		<div class="card card-outline card-primary">
			<div class="card-header text-center">
				<a href="#" class="h1"><b>Admin</b></a>
			</div>
			<div class="card-body">
				<p class="login-box-msg">You are only one step a way from your
					new password, recover your password now.</p>
				<form action="${context}/reset/resetpassword" method="post">
					<input type="hidden" name="token" value="${token}" />
					<div class="input-group mb-3">
						<input type="password" class="form-control" name="password"
							id="password" placeholder="Password">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control"
							placeholder="Confirm Password"
							oninput="checkPasswordMatch(this);">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<c:if test="${alert ne null }">
						<div class="alert alert-danger text-center" role="alert">${alert}</div>
					</c:if>
					<c:if test="${message ne null }">
						<div class="alert alert-success" role="alert">${message}</div>
					</c:if>
					<div class="row">
						<div class="col-12">
							<button type="submit" class="btn btn-primary btn-block">Change
								password</button>
						</div>
						<!-- /.col -->
					</div>
				</form>

				<p class="mt-3 mb-1">
					<a href="${context}/login">Login</a>
				</p>
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<!-- /.login-box -->

	<!-- jQuery -->
	<script
		src="${context}/resources/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="${context}/resources/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${context}/resources/AdminLTE/dist/js/adminlte.min.js"></script>
	<script>
		function checkPasswordMatch(fieldConfirmPassword) {
			if (fieldConfirmPassword.value != $("#password").val()) {
				fieldConfirmPassword
						.setCustomValidity("Passwords do not match!");
			} else {
				fieldConfirmPassword.setCustomValidity("");
			}
		}
	</script>
</body>
</html>
