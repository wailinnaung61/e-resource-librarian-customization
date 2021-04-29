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
<title>Special Requests</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="resources/AdminLTE/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
<link rel="stylesheet"
	href="resources/AdminLTE/dist/css/adminlte.min.css">
<link rel="stylesheet" href="resources/themify-icons/themify-icons.css">
<link rel="stylesheet" href="resources/themify-icons/ie7/ie7.css">
<link rel="stylesheet"
	href="resources/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet"
	href="resources/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<link rel="stylesheet"
	href="resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<link rel="stylesheet" href="resources/plugins/jqvmap/jqvmap.min.css">
<link rel="stylesheet"
	href="resources/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<link rel="stylesheet"
	href="resources/plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet"
	href="resources/plugins/summernote/summernote-bs4.css">
<link rel="stylesheet"
	href="resources/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
<style type="text/css">
.status-badge {
	text-transform: capitalize;
}
</style>
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
							<h1 class="m-0">Special Requests List</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Special Requests List</li>
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
						<div class="col-md-12">

							<div class="card card" style="margin: 10px;">
								<div class="card-header">
									<h3 class="card-title">Special Request List</h3>
								</div>
								<div class="card-body">

									<div>
										<table id="example1"
											class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th>#</th>
													<th>Borrower Name</th>
													<th>Item Title</th>
													<th>Item Barcode</th>
													<th>Status</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="d" items="${data}" varStatus="c">
													<tr>
														<td>${c.index + 1}</td>
														<td>${d.username}</td>
														<td>${d.itemtitle}</td>
														<td>${d.itembarcode}</td>
														<td><span id="status-${d.id}"
															class="btn btn-dark btn-sm status-badge">
																${d.status eq 'approved' ? '<i class=\'fa fa-check\'></i>' : ''}
																${d.status eq 'declined' ? '<i class=\'fa fa-times\'></i>' : ''}
																${d.status} </span></td>
														<td>
															<button type="button"
																class="btn btn-success btn-sm approve-btn"
																data-id="${d.id}">Approve</button>
															<button type="button"
																class="btn btn-danger btn-sm decline-btn"
																data-id="${d.id}">Decline</button>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
	<script src="resources/plugins/jquery/jquery.min.js"></script>
	<script src="resources/plugins/jquery-ui/jquery-ui.min.js"></script>
	<script src="resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/plugins/jqvmap/jquery.vmap.min.js"></script>
	<script src="resources/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
	<script src="resources/plugins/jquery-knob/jquery.knob.min.js"></script>
	<script src="resources/plugins/moment/moment.min.js"></script>
	<script src="resources/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="resources/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<script src="resources/plugins/summernote/summernote-bs4.min.js"></script>
	<script
		src="resources/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<script src="resources/dist/js/adminlte.js"></script>
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
	<script>
		var pageContext = "${pageContext.request.contextPath}";
		$(".approve-btn")
				.on(
						"click",
						function(e) {
							var defaultText = $(this).html();
							var id = $(this).data("id");
							var that = $(this);
							$
									.ajax({
										url : pageContext
												+ '/update/specialrequest',
										method : 'POST',
										data : "id=" + id + "&status=0",
										beforeSend : function() {
											that
													.html("<i class='fa fa-spinner fa-spin'></i>");
											that.parent().find("button").attr(
													"disabled", true);
										},
										success : function(response) {
											that.html("Approve");
											$('#status-' + id)
													.html(
															"<i class='fa fa-check'></i> Approve");
										},
										error : function(err) {
											that.removeClass("btn-success")
													.addClass("btn-dark");
											that.html("Error Occurred !");
											setTimeout(
													function() {
														that
																.removeClass(
																		"btn-dark")
																.addClass(
																		"btn-success");
														that.html(defaultText);
														that.parent().find(
																"button").attr(
																"disabled",
																false);
													}, 5000);
										},
										complete : function() {
											that.parent().find("button").attr(
													"disabled", false);
										}
									});
						});

		$(".decline-btn")
				.on(
						"click",
						function(e) {
							var defaultText = $(this).html();
							var id = $(this).data("id");
							var that = $(this);
							$
									.ajax({
										url : pageContext
												+ '/update/specialrequest',
										method : 'POST',
										data : "id=" + id + "&status=1",
										beforeSend : function() {
											that
													.html("<i class='fa fa-spinner fa-spin'></i>");
											that.parent().find("button").attr(
													"disabled", true);
										},
										success : function(response) {
											that.html("Decline");
											$('#status-' + id)
													.html(
															"<i class='fa fa-times'></i> Decline");
										},
										error : function(err) {
											that.removeClass("btn-success")
													.addClass("btn-dark");
											that.html("Error Occurred !");
											setTimeout(
													function() {
														that
																.removeClass(
																		"btn-dark")
																.addClass(
																		"btn-danger");
														that.html(defaultText);
														that.attr("disabled",
																false);
													}, 3000);
										},
										complete : function() {
											that.parent().find("button").attr(
													"disabled", false);
										}
									});
						});

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