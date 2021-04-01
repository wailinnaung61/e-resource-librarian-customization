<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Add Member</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/fontawesome-free/css/all.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/daterangepicker/daterangepicker.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- Select2 -->
<link rel="stylesheet" href="resources/AdminLTE/plugins/select2/css/select2.min.css">
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
<!-- BS Stepper -->
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/bs-stepper/css/bs-stepper.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="resources/AdminLTE/dist/css/adminlte.min.css">
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
							<h1 class="m-0">Create Members</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Create Members</li>
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

								<div class="card-header header">
									<h3 class="card-title textheader">Create Member</h3>
								</div>

								<form:form method="post" action="savemember"
									modelAttribute="member">
									<div class="row">
										<!-- card row -->
										<div class="col-md-6">
											<!-- card left column -->
											<div class="card-body">
												<div class="form-group">
													<label>SurName</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter SurName" path="surname"
														required="required" />
												</div>
												<div class="form-group">
													<label>Username</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter User Name" path="username"
														required="required" />
												</div>
												<div class="form-group">
													<label>Password</label>
													<form:input type="password" class="form-control" id="txtPassword"
														placeholder="Enter Password" path="password"
														required="required" />
												</div>
												<div class="form-group">
													<label>Confirm Password</label> <input type="password"
														class="form-control" id="txtConfirmPassword"
														placeholder="Enter Confirm Password" required />
												</div>
												<div class="form-group">
													<label>Email</label>
													<form:input type="email" class="form-control" id=""
														placeholder="Enter Email" path="email" required="required" />
												</div>
											</div>
										</div>
										<!-- card left column -->
										<div class="col-md-6">
											<!-- card right column -->
											<div class="card-body">
												<div class="form-group">
													<label>Title</label>
													<form:select path="title" class="form-control mr-1 w-100">
														<form:option value="Mrs">Mrs</form:option>
														<form:option value="Miss">Miss</form:option>
													</form:select>
												</div>
												<div class="form-group">
													<label>Date of Birth:</label>
													<div class="input-group date" id="reservationdate"
														data-target-input="nearest">
														<form:input type="text"
															class="form-control datetimepicker-input"
															data-target="#reservationdate" path="dateOfBirth"
															required="required" />
														<div class="input-group-append"
															data-target="#reservationdate"
															data-toggle="datetimepicker">
															<div class="input-group-text">
																<i class="fa fa-calendar"></i>
															</div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label>Card Number</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter Card Name" path="cardNumber" />
												</div>
												<div class="form-group">
													<label>City</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter City" path="city" />
												</div>
												<div class="form-group">
													<label>Category</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter Category" path="category" />
												</div>

											</div>
											<!-- card body -->
										</div>
										<!-- card right column -->
									</div>
									<!-- card row column -->

									<div class="row">
										<div class="col-md-12">
											<p align="center" class="text-danger">${alert}</p>
											<p align="center" class="text-success">${successful}</p>
										</div>
									</div>
									<div class="card-footer">
										<button type="submit" class="btn btn-info" onclick="return Validate()">Submit</button>
									</div>

								</form:form>

							</div>
							<!-- card -->
						</div>
						<!-- column -->

					</div>
					<!-- row -->
				</div>
				<!-- container -->
			</section>

		</div>
		<!-- wrapper -->

		<%@include file="footer.jsp"%>

	</div>

	<!-- jQuery -->
	<script src="resources/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="resources/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Select2 -->
	<script src="resources/AdminLTE/plugins/select2/js/select2.full.min.js"></script>
	<!-- Bootstrap4 Duallistbox -->
	<script
		src="resources/AdminLTE/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
	<!-- InputMask -->
	<script src="resources/AdminLTE/plugins/moment/moment.min.js"></script>
	<script
		src="resources/AdminLTE/plugins/inputmask/jquery.inputmask.min.js"></script>
	<!-- date-range-picker -->
	<script
		src="resources/AdminLTE/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap color picker -->
	<script
		src="resources/AdminLTE/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="resources/AdminLTE/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Bootstrap Switch -->
	<script
		src="resources/AdminLTE/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<!-- BS-Stepper -->
	<script
		src="resources/AdminLTE/plugins/bs-stepper/js/bs-stepper.min.js"></script>
	<!-- dropzonejs -->
	<script src="resources/AdminLTE/plugins/dropzone/min/dropzone.min.js"></script>
	<!-- AdminLTE App -->
	<script src="resources/AdminLTE/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="resources/AdminLTE/dist/js/demo.js"></script>

	<script>
		$(function() {

			//Date picker
			$('#reservationdate').datetimepicker({
				format : 'L'
			});

		})
		
		function Validate() {
			var password = document.getElementById("txtPassword").value;
			var confirmPassword = document.getElementById("txtConfirmPassword").value;
			if (password != confirmPassword) {
				alert("Password and ConfirmPassword do not match.");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>