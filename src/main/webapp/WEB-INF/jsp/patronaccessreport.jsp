<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="beans.BiblioSyncTypes" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Admin View</title>
<c:set var="context" value="${pageContext.request.contextPath }"/>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="${context}/resources/themify-icons/themify-icons.css">

<link rel="stylesheet" href="${context}/resources/themify-icons/ie7/ie7.css">

<link rel="stylesheet"
	href="${context}/resources/plugins/fontawesome-free/css/all.min.css">

<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

<link rel="stylesheet"
	href="${context}/resources/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">

<link rel="stylesheet"
	href="${context}/resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">

<link rel="stylesheet" href="${context}/resources/plugins/jqvmap/jqvmap.min.css">

<link rel="stylesheet" href="${context}/resources/dist/css/adminlte.min.css">

<link rel="stylesheet"
	href="${context}/resources/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">

<link rel="stylesheet"
	href="${context}/resources/plugins/daterangepicker/daterangepicker.css">

<link rel="stylesheet"
	href="${context}/resources/plugins/summernote/summernote-bs4.css">

<link rel="stylesheet"
	href="${context}/resources/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css">

<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
<link rel="stylesheet" href="${context}/resources/plugins/datatables-bs4/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="${context}/resources/plugins/buttons-datatable/buttons.bootstrap4.min.css">
<style type="text/css">
tr {
	text-align: center;
}

th {
	text-align: center;
}
</style>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="btton"><i class="fas fa-bars"
						style="color: black;"></i></a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li><font size="4px"
					style="font-family: Times New Roman, Times, serif">${loginname}</font>&nbsp;&nbsp;<svg
						version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg"
						xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
						width="40px" height="40px" viewBox="0 0 64 64"
						enable-background="new 0 0 64 64" xml:space="preserve">
		    <g id="USER_3_" enable-background="new">
			<g id="USER">
		    <g>
			<path
							d="M32,0C14.327,0,0,14.327,0,32s14.327,32,32,32s32-14.327,32-32S49.673,0,32,0z M51.253,49.43
			c-3.767-1.826-2.382-0.398-7.31-2.427c-5.041-2.073-6.235-2.749-6.235-2.749L37.664,39.5c0,0,1.888-1.422,2.477-5.917
			c1.178,0.338,1.578-1.372,1.642-2.464c0.069-1.055,0.696-4.346-0.745-4.052c0.295-2.197,0.527-4.183,0.421-5.235
			c-0.36-3.691-2.931-7.544-9.42-7.826c-5.517,0.282-9.098,4.138-9.46,7.829c-0.104,1.052,0.108,3.036,0.403,5.236
			c-1.441-0.297-0.821,2.999-0.758,4.054c0.07,1.092,0.46,2.809,1.641,2.469c0.587,4.495,2.475,5.93,2.475,5.93L26.293,44.3
			c0,0-1.195,0.724-6.236,2.796c-4.927,2.027-3.544,0.512-7.31,2.334C8.568,44.816,6,38.715,6,32C6,17.641,17.641,6,32,6
			c14.359,0,26,11.641,26,26C58,38.716,55.432,44.816,51.253,49.43z" />
			</g>
			</g>
			</g>
			</svg></li>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
		</nav>

		<aside class="main-sidebar elevation-4">
			<%@ include file="sidebar.jsp"%>
		</aside>

		<div class="content-wrapper">
			<div class="card m-2 p-4">
				<form id="filterform" class="d-flex">
					<input type="text" id="filterDate" name="filterDate" class="form-control w-25 mx-2">
					<input type="hidden" name="from"/>
					<input type="hidden" name="to"/>
					<button class="btn btn-primary">Search</button>
				</form>
			</div>
			<div>
				<div class="card card" style="margin: 10px;">
					<div class="card-header">
						<h3 class="card-title">Patron Access Report by category</h3>
					</div>
					<div class="card-body">
						<div>
							<table id="table1" class="table table-bordered table-striped table-hover">
								<thead>
									<tr>
										<th style="width: 130px;">#</th>
										<th style="width: 150px;">Category Name</th>
										<th style="width: 150px;">Access Count</th>
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
			<div>
				<div class="card card" style="margin: 10px;">
					<div class="card-header">
						<h3 class="card-title">Patron Access Report by city</h3>
					</div>
					<div class="card-body">
						<div>
							<table id="table2" class="table table-bordered table-striped table-hover">
								<thead>
									<tr>
										<th style="width: 130px;">#</th>
										<th style="width: 150px;">City Name</th>
										<th style="width: 150px;">Access Count</th>
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
		
		<footer class="main-footer"> </footer>
	</div>
	<script src="${context}/resources/plugins/jquery/jquery.min.js"></script>
	<script src="${context}/resources/plugins/jquery-ui/jquery-ui.min.js"></script>
	<script>
		$.widget.bridge('uibutton', $.ui.button)
	</script>
	<script src="${context}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${context}/resources/plugins/chart.js/Chart.min.js"></script>
	<script src="${context}/resources/plugins/sparklines/sparkline.js"></script>
	<script src="${context}/resources/plugins/jqvmap/jquery.vmap.min.js"></script>
	<script src="${context}/resources/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
	<script src="${context}/resources/plugins/jquery-knob/jquery.knob.min.js"></script>
	<script src="${context}/resources/plugins/moment/moment.min.js"></script>
	<script src="${context}/resources/plugins/daterangepicker/daterangepicker.js"></script>
	<script src="${context}/resources/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<script src="${context}/resources/plugins/summernote/summernote-bs4.min.js"></script>
	<script src="${context}/resources/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<script src="${context}/resources/dist/js/adminlte.js"></script>
	<script src="${context}/resources/dist/js/pages/dashboard.js"></script>
	<script src="${context}/resources/dist/js/demo.js"></script>
	<script src="${context}/resources/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="${context}/resources/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script src="${context}/resources/plugins/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
	<script src="${context}/resources/plugins/bootbox/bootbox.js"></script>
	<script src="${context}/resources/plugins/datatables/dataTables.buttons.min.js"></script>
	<script src="${context}/resources/plugins/buttons-datatable/buttons.html5.min.js"></script>
	
	<script src="${context}/resources/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script src="${context}/resources/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	
	<script>
		const params = new URLSearchParams(window.location.search);
		$('#filterDate').daterangepicker({
			startDate: params.get('from') || moment(),
		    endDate: params.get('to') || moment(),
		    locale: {
		    	format: 'DD/MM/YYYY'
		    }
		});
		$("#filterform").on('submit', function(){
			let date = $("#filterDate").val();
			if(date) {
				let dates = date.split(" - ");
				$("#filterform input[name='from']").val(dates[0].trim());
				$("#filterform input[name='to']").val(dates[1].trim());
			}
		});
		$(function() {
			var table = $('#table1').DataTable({
				lengthChange: false,
		        buttons: [ { extend: 'csv', className: 'btn btn-sm btn-dark fa fa-save', text: '&nbsp; CSV ', title: 'Patron Access Report by category' } ],
		        paging : true,
		        lengthChange: false,
		        ordering: false,
		        searching: false
			});
			table.buttons().container().appendTo( '#table1_wrapper .col-md-6:eq(0)' );
			
			var table2 = $('#table2').DataTable({
				lengthChange: false,
		        buttons: [ { extend: 'csv', className: 'btn btn-sm btn-dark fa fa-save', text: '&nbsp; CSV ', title: 'Patron Access Report by city' } ],
		        paging : true,
		        lengthChange: false,
		        ordering: false,
		        searching: false
			});
			table2.buttons().container().appendTo( '#table2_wrapper .col-md-6:eq(0)' );
		});
	</script>
</body>
</html>
