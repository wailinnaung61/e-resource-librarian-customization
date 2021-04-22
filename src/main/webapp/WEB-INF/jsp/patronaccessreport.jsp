<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="beans.BiblioSyncTypes"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Admin View</title>
<c:set var="context" value="${pageContext.request.contextPath }" />
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/fontawesome-free/css/all.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/daterangepicker/daterangepicker.css">

<!-- Bootstrap Color Picker -->
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- Select2 -->
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/select2/css/select2.min.css">
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
<!-- BS Stepper -->
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/bs-stepper/css/bs-stepper.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/dist/css/adminlte.min.css">
<!-- DataTables -->
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="${context}/resources/AdminLTE/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
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
							<h1 class="m-0">Members Access List</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Members Access List</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<section class="content">
				<div class="container-fluid">
					<div class="card m-2 p-3">
						<form id="filterform" class="d-flex">
							<input type="text" id="filterDate" name="filterDate"
								class="form-control w-25 mx-2"> <input type="hidden"
								name="from" /> <input type="hidden" name="to" />
							<button class="btn btn-primary">Search</button>
						</form>
					</div>
					<div>
						<div class="card card" style="margin: 10px;">
							<div class="card-header bg-info">
								<h3 class="card-title">Member Access Report by category</h3>
							</div>
							<div class="card-body">
								<div>
									<table id="example1"
										class="table table-bordered table-striped table-hover">
										<thead>
											<tr>
												<th>#</th>
												<th>Category Name</th>
												<th>Access Count</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="d" items="${reportbycategory}" varStatus="c">
												<tr>
													<td>${c.index + 1}</td>
													<td>${d.category}</td>
													<td>${d.accesscount }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="mt-3">
						<div class="card card" style="margin: 10px;">
							<div class="card-header bg-info">
								<h3 class="card-title">Member Access Report by city</h3>
							</div>
							<div class="card-body">
								<div>
									<table id="example2"
										class="table table-bordered table-striped table-hover">
										<thead>
											<tr>
												<th>#</th>
												<th>City Name</th>
												<th>Access Count</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="d" items="${reportbycity}" varStatus="c">
												<tr>
													<td>${c.index + 1}</td>
													<td>${d.city}</td>
													<td>${d.accesscount }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
	<!-- jQuery -->
	<script
		src="${context}/resources/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="${context}/resources/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Select2 -->
	<script
		src="${context}/resources/AdminLTE/plugins/select2/js/select2.full.min.js"></script>
	<!-- Bootstrap4 Duallistbox -->
	<script
		src="${context}/resources/AdminLTE/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
	<!-- InputMask -->
	<script
		src="${context}/resources/AdminLTE/plugins/moment/moment.min.js"></script>
	<script
		src="${context}/resources/AdminLTE/plugins/inputmask/jquery.inputmask.min.js"></script>
	<!-- date-range-picker -->
	<script
		src="${context}/resources/AdminLTE/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap color picker -->
	<script
		src="${context}/resources/AdminLTE/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="${context}/resources/AdminLTE/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Bootstrap Switch -->
	<script
		src="${context}/resources/AdminLTE/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<!-- BS-Stepper -->
	<script
		src="${context}/resources/AdminLTE/plugins/bs-stepper/js/bs-stepper.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${context}/resources/AdminLTE/dist/js/adminlte.min.js"></script>
	<!-- DataTables  & Plugins -->
	<script
		src="${context}/resources/AdminLTE/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${context}/resources/AdminLTE/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="${context}/resources/AdminLTE/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="${context}/resources/AdminLTE/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script
		src="${context}/resources/AdminLTE/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="${context}/resources/AdminLTE/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script src="${context}/resources/AdminLTE/plugins/jszip/jszip.min.js"></script>
	<script
		src="${context}/resources/AdminLTE/plugins/pdfmake/pdfmake.min.js"></script>
	<script
		src="${context}/resources/AdminLTE/plugins/pdfmake/vfs_fonts.js"></script>
	<script
		src="${context}/resources/AdminLTE/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
	<script
		src="${context}/resources/AdminLTE/plugins/datatables-buttons/js/buttons.print.min.js"></script>
	<script
		src="${context}/resources/AdminLTE/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>

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

		});

		$(function() {
			$("#example2").DataTable(
					{
						"paging" : true,
						"responsive" : true,
						"lengthChange" : false,
						"autoWidth" : false,
						"buttons" : [ "copy", "csv", "excel", "pdf", "print",
								"colvis" ]
					}).buttons().container().appendTo(
					'#example2_wrapper .col-md-6:eq(0)');

		});
		const params = new URLSearchParams(window.location.search);
		$('#filterDate').daterangepicker({
			startDate : params.get('from') || moment(),
			endDate : params.get('to') || moment(),
			locale : {
				format : 'DD/MM/YYYY'
			}
		});
		$("#filterform").on('submit', function() {
			let date = $("#filterDate").val();
			if (date) {
				let dates = date.split(" - ");
				$("#filterform input[name='from']").val(dates[0].trim());
				$("#filterform input[name='to']").val(dates[1].trim());
			}
		});
		$(function() {
			var table = $('#table1').DataTable({
				lengthChange : false,
				buttons : [ {
					extend : 'csv',
					className : 'btn btn-sm btn-dark fa fa-save',
					text : '&nbsp; CSV ',
					title : 'Patron Access Report by category'
				} ],
				paging : true,
				lengthChange : false,
				ordering : false,
				searching : false
			});
			table.buttons().container().appendTo(
					'#table1_wrapper .col-md-6:eq(0)');

			var table2 = $('#table2').DataTable({
				lengthChange : false,
				buttons : [ {
					extend : 'csv',
					className : 'btn btn-sm btn-dark fa fa-save',
					text : '&nbsp; CSV ',
					title : 'Patron Access Report by city'
				} ],
				paging : true,
				lengthChange : false,
				ordering : false,
				searching : false
			});
			table2.buttons().container().appendTo(
					'#table2_wrapper .col-md-6:eq(0)');
		});
	</script>
</body>
</html>
