<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Add ItemTypes</title>

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
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>500 Error Page</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">500 Error Page</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="error-page">
					<h2 class="headline text-danger">500</h2>

					<div class="error-content">
						<h3>
							<i class="fas fa-exclamation-triangle text-danger"></i> Oops!
							Something went wrong.
						</h3>

						<p>
							We will work on fixing that right away. Meanwhile, you may <a
								href="${pageContext.request.contextPath}/">return to dashboard</a> or try using the
							search form.
						</p>

						<form class="search-form">
							<div class="input-group">
								<input type="text" name="search" class="form-control"
									placeholder="Search">

								<div class="input-group-append">
									<button type="submit" name="submit" class="btn btn-danger">
										<i class="fas fa-search"></i>
									</button>
								</div>
							</div>
							<!-- /.input-group -->
						</form>
					</div>
				</div>
				<!-- /.error-page -->

			</section>

		</div>
		<!-- wrapper -->

		<%@include file="footer.jsp"%>

	</div>

	<script src="resources/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<script
		src="resources/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/AdminLTE/dist/js/adminlte.js"></script>
	<script src="resources/AdminLTE/dist/js/demo.js"></script>
</body>
</html>
