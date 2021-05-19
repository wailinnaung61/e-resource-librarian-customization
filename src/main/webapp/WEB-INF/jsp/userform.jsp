<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Add User</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/plugins/fontawesome-free/css/all.min.css">
<!-- IonIcons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/dist/css/adminlte.min.css">

</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">



		<%@ include file="navbar.jsp"%>


		<aside class="main-sidebar elevation-4">
			<%@ include file="sidebar.jsp"%>
		</aside>


		<div class="content-wrapper">


			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Create Users</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Create Users</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->


			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-10" style="margin: auto;">
							<div class="card card-info">
								<div class="card-header">
									<h3 class="card-title">Create Users</h3>
								</div>
								<form:form method="post" action="${pageContext.request.contextPath}/users/save" role="form">
									<div class="card-body">

										<div class="row">
											<div class="col-md-6">

												<div class="form-group">
													<label>Name</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter Full Name" path="name"
														required="required" />

												</div>
												<div class="form-group">
													<label>User Name</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter User Name" path="username"
														required="required" />
												</div>
												<div class="form-group">
													<label>Email</label>
													<form:input type="email" class="form-control" id=""
														placeholder="Enter Email" path="email" required="required" />
												</div>

											</div>
											<!-- column -->
											<!-- column -->
											<div class="col-md-6">

												<div class="form-group">
													<label>Role</label>
													<form:select class="form-control combo" path="rolename">
														<c:forEach var="user" items="${list}">
															<option value="${user.rolename}">${user.rolename}</option>
														</c:forEach>
													</form:select>
												</div>
												<div class="form-group">
													<label>Password</label>
													<div class="old">
														<form:input type="password" id="password"
															placeholder="Enter Password" path="password"
															class="form-control" required="required" />
													</div>
												</div>
												<div class="form-group">
													<label>Confirm Password</label>
													<div class="old">
														<input type="password" class="form-control"
															id="txtConfirmPassword"
															oninput="checkPasswordMatch(this);"
															placeholder="Confirm Password" required>
													</div>
												</div>

											</div>
											<!-- column -->
										</div>
										<!-- row -->

										<div class="row">
											<div class="col-md-12">
												<p align="center" class="text-danger">${alert}</p>
												<p align="center" class="text-success">${successful}</p>
											</div>
										</div>
									</div>
									<!-- card body -->

									<div class="card-footer">
										<button type="submit" class="btn btn-info"
											onclick="return Validate()">Submit</button>
									</div>
								</form:form>
							</div>
						</div>

					</div>
					<!-- row -->
				</div>
				<!-- container -->
			</section>

		</div>
		<!-- wrapper -->

		<%@ include file="footer.jsp"%>

	</div>

	<script src="${pageContext.request.contextPath}/resources/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/AdminLTE/dist/js/adminlte.js"></script>
	<script src="${pageContext.request.contextPath}/resources/AdminLTE/dist/js/demo.js"></script>
	<script type="text/javascript">
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

