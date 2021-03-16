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
	cursor: pointer;
}
td {
	white-space: nowrap;
}
.dataTables_filter > label {
	display: flex;
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
			<div>
				<div class="card card" >
					<div class="card-header">
						<h3 class="card-title">Uploaded Records</h3>
					</div>
					<div class="card-body">
						<div>
							<table id="table1" class="table table-bordered table-hover table-responsive">
							  <thead>
							    <tr>
									<th class="not-export-col">#</th>
									<th>BiblioNumber</th>
									<th>ItemNumber</th>
									<th>Barcode</th>
									<th>Booksellerid</th>
									<th>Homebranch</th>
									<th>ItemcallNumber</th>
									<th>Keyword</th>
									<th>CollectionTitle</th>
									<th>PublisherCode</th>
									<th>EditionStatement</th>
									<th>Place</th>
									<th>Author</th>
									<th>Title</th>
									<th>Notes</th>
									<th>Timestamp</th>
									<th>Datecreated</th>
									<th>Itemtype</th>
									<th>Publishedyear</th>
									<th>ISBN</th>
							    </tr>
							  </thead>
							  <tbody>    
							  	<c:forEach var="data" items="${list}">                     
							    	<c:forEach var="i" items="${data.items}" varStatus="ic">
							    		<tr>
							    			<td>
						    					<button class="del-btn btn btn-sm btn-danger" data-id="${data.biblionumber}">
							    					<i class="fa fa-trash"></i>
							    				</button>
							    			</td>
							    			
											<td>${data.biblionumber}</td>
											<td>${i.itemnumber}</td>
											<td>${i.barcode}</td>
											<td>${i.booksellerid}</td>
											<td>${i.homebranch}</td>
											<td>${i.itemCallNumber}</td>
											<td>${i.keyword}</td>
											<td>${data.collectiontitle}</td>
											<td>${data.publishercode}</td>
											<td>${data.editionstatement}</td>
											<td>${data.place}</td>
											<td>${data.author}</td>
											<td>${data.title}</td>
											<td>${data.notes}</td>
											<td>${data.timestamp}</td>
											<td>${data.datecreated}</td>
											<td>${i.itemtype}</td>
											<td>${data.publishedyear}</td>
											<td>${data.isbn}</td>
									    </tr>
							    	</c:forEach>
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
		$(".del-btn").on('click', function(e){
			let that = $(this);
			let id = $(this).attr("data-id");
			bootbox.confirm("This will delete all items concerns with this biblio. Are you sure to this biblio? ", function(result){
				if(result){
					bootbox.dialog({
						closeButton: false,
						message: "<i class='fa fa-spinner fa-spin'></i> Deleting data..."
					});
					$.ajax({
						url: "${context}"+ '/biblio/delete/' + id,
						method: "DELETE",
						success: function(resp){
							bootbox.hideAll();
							bootbox.dialog({
								closeButton: false,
								message: "Biblio deletion success!"
							});
							that.closest("tr").remove();
						},
						error: function(err){
							bootbox.hideAll();
							bootbox.dialog({
								closeButton: false,
								message: "Failed to delete resource!"
							});
						},
						complete: function(){
							setTimeout(() => {
								bootbox.hideAll();
							}, 2000);
						}
					});
				}
			});
		});
		
		$(function() {
			var table = $('#table1').DataTable({
				columnDefs: [{
					"targets": [0],
					"orderable": false
				}],
				lengthChange: false,
		        buttons: [ { extend: 'csv', className: 'btn btn-sm btn-dark fa fa-save', text: '&nbsp; CSV ', title: 'Uploaded Records' }],
		        paging : true,
		        lengthChange: false,
		        ordering: true,
		        searching: true,
			});
			table.buttons().container().appendTo( '#table1_wrapper .col-md-6:eq(0)' );
		});
	</script>
</body>
</html>
