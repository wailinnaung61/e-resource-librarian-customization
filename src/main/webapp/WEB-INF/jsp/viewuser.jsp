<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Users List</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/fontawesome-free/css/all.min.css">
<!-- DataTables -->
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
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
							<h1 class="m-0">Users List</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Users List</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">

							<div class="card">
								<div class="card-header">
									<h3 class="card-title">View Users</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<table id="example1" class="table table-bordered table-striped">
										<thead class="bg-info">
											<tr>
												<th>#</th>
												<th>Name</th>
												<th>User Name</th>
												<th>Role</th>
												<th>Email</th>
												<th>Edit</th>
												<th>Del</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="user" items="${list}" varStatus="c">
												<tr>
													<td>${c.index+1}</td>
													<td>${user.name}</td>
													<td>${user.username}</td>
													<td>${user.rolename}</td>
													<td>${user.email}</td>
													<td><a
														href="${pageContext.request.contextPath}/edituser/${user.id}"
														class="btn btn-block bg-gradient-primary"><i
															class="fa fa-edit"></i></a></td>
													<td><a
														href="${pageContext.request.contextPath}/deleteuser/${user.id}"
														onclick="return confirm('Are you sure?')"
														class="btn btn-block bg-gradient-danger"><i
															class="fa fa-trash"></i></a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->

						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->

		</div>

		<%@ include file="footer.jsp"%>
	</div>


	<!-- jQuery -->
	<script src="resources/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="resources/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- DataTables  & Plugins -->
	<script
		src="resources/AdminLTE/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="resources/AdminLTE/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="resources/AdminLTE/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="resources/AdminLTE/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script
		src="resources/AdminLTE/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="resources/AdminLTE/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script src="resources/AdminLTE/plugins/jszip/jszip.min.js"></script>
	<script src="resources/AdminLTE/plugins/pdfmake/pdfmake.min.js"></script>
	<script src="resources/AdminLTE/plugins/pdfmake/vfs_fonts.js"></script>
	<script
		src="resources/AdminLTE/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
	<script
		src="resources/AdminLTE/plugins/datatables-buttons/js/buttons.print.min.js"></script>
	<script
		src="resources/AdminLTE/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
	<!-- AdminLTE App -->
	<script src="resources/AdminLTE/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="resources/AdminLTE/dist/js/demo.js"></script>
	<!-- Page specific script -->
	<script>
		$(function() {
			$("#example1").DataTable(
					{
						"paging" : true,
						"responsive" : true,
						"lengthChange" : false,
						"autoWidth" : false,
						"buttons" : [ "copy", "csv", "excel", "pdf", "print",
								"colvis" ]
					}).buttons().container().appendTo(
					'#example1_wrapper .col-md-6:eq(0)');

			$('#example2').DataTable({
				"paging" : true,
				"lengthChange" : false,
				"searching" : false,
				"ordering" : true,
				"info" : true,
				"autoWidth" : false,
				"responsive" : true,
			});
		});
	</script>
</body>
</html>
