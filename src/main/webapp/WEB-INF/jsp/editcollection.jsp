<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Add Collections</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/fontawesome-free/css/all.min.css">
<!-- IonIcons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="resources/AdminLTE/dist/css/adminlte.min.css">
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
							<h1 class="m-0">Edit Collections</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Edit Collections</li>
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
									<h3 class="card-title textheader">Edit Collection</h3>
								</div>

								<form:form method="post" action="editsavecollection"
									modelAttribute="collection">
									<div class="row">
										<!-- card row -->
										<div class="col-md-6">
											<!-- card left column -->



											<div class="card-body">
												<div class="form-group">
													<label>Collection Description</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter Description" path="name"
														required="required" />

												</div>


											</div>




										</div>
										<!-- card left column -->

										<div class="col-md-6">
											<!-- card right column -->

											<form:hidden path="id" />

											<div class="card-body">
												<div class="form-group">
													<label>Collection Code</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter code" path="code" required="required" />
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
										<button type="submit" class="btn btn-info">Submit</button>
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

		<aside class="control-sidebar control-sidebar-dark"></aside>

	</div>

	<script src="resources/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<script
		src="resources/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/AdminLTE/dist/js/adminlte.js"></script>
	<script src="resources/AdminLTE/dist/js/demo.js"></script>
</body>
</html>
