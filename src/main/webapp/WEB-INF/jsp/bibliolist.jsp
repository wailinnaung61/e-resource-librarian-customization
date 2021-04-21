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
<title>Sync Records</title>

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
	href="resources/plugins/summernote/summernote-bs4.css">
<link rel="stylesheet"
	href="resources/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css">

<style type="text/css">
.label-info {
	background-color: #5bc0de;
}

.label {
	display: inline;
	padding: .2em .6em .3em;
	font-size: 75%;
	font-weight: 700;
	line-height: 1;
	color: #fff;
	text-align: center;
	white-space: nowrap;
	vertical-align: baseline;
	border-radius: .25em;
}

.bootstrap-tagsinput {
	width: 100%;
	height: calc(2.25rem + 2px);
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
							<h1 class="m-0">Sync Records Items</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Sync Records Items</li>
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

							<div class="card">
								<div class="card-header bg-info">
									<h3 class="card-title">Synchronize Records Items</h3>
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-4">
											<select class="form-control" id="sync_type">
												<option value="${BiblioSyncTypes.SyncWithItems.toString()}">${BiblioSyncTypes.SyncWithItems.getDesc()}</option>
												<option
													value="${BiblioSyncTypes.SyncWithoutItems.toString()}">${BiblioSyncTypes.SyncWithoutItems.getDesc()}</option>
												<option
													value="${BiblioSyncTypes.SyncWithItemNumber.toString()}">${BiblioSyncTypes.SyncWithItemNumber.getDesc()}</option>
											</select>
										</div>
										<div class="col-6">
											<input type="number" data-role="tagsinput" id="sync_items">
										</div>
										<div class="col-2">
											<button class="ml-1 form-control bg-info" id="sync_single">Sync</button>
										</div>
									</div>
								</div>
							</div>

							<div>
							<div class="card card" style="margin:15px;">
								<div class="card-header bg-info">
									<h3 class="card-title">Recent Uploaded Records</h3>
								</div>
								<div class="card-body">
									<div id="sync_block" class="text-center">
										<c:choose>
											<c:when test="${bcount eq 0 && data eq null}">
												<div>There is no new item to synchronize.</div>
											</c:when>
											<c:when test="${bcount ne 0 && data eq null }">
												<div>
													<div>There are ${bcount} item/s to synchronize.</div>
													<div>
														<a
															href="${pageContext.request.contextPath}/sync/biblioitems"
															class="btn btn-sm btn-primary">Sync All</a>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="mb-3 pb-3 border-bottom">${data.size()}
													item/s Synchronized.</div>
											</c:otherwise>
										</c:choose>
									</div>

									<div class="${data ne null ? '':'d-none'}" id="sync_result">
										<table id="example1"
											class="table table-bordered table-striped">
											<thead>
												<tr>
													<th>Record No</th>
													<th>Item No</th>
													<th>Title</th>
													<th>Author</th>
													<th>Serial Issue</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:if test="${data != null }">
													<c:forEach var="d" items="${data}">
														<tr>
															<td>${d.biblionumber}</td>
															<td>${d.itemnumber}</td>
															<td>${d.title}</td>
															<td>${d.authorname}</td>
															<td>${d.enumchron eq null ? '-' : d.enumchron}</td>
															<td><a
																href="${pageContext.request.contextPath}/biblioitemdetail?searchType=item&id=${d.itemnumber}">Detail</a></td>
														</tr>
													</c:forEach>
												</c:if>
											</tbody>
										</table>
									</div>
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
	<script src="resources/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<script
		src="resources/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
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
	<script src="resources/AdminLTE/dist/js/adminlte.min.js"></script>
	<script
		src="resources/plugins/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
	<script src="resources/plugins/bootbox/bootbox.js"></script>
	<script>
		$("#sync_items").on('beforeItemAdd', function(e){
			if(!/[0-9]+$/.test(e.item)){
				e.cancel = true;
			}
		});
		var dialog = bootbox.dialog({
		    message: '<div class="text-center mb-0" id="message"><i class="fa fa-spin fa-cog"></i> Syncing... Please wait!</div>',
		    closeButton: false,
		    show: false
		});
		function showDialog(msg){
			$("#message").html('<div class="text-center mb-0" id="message"><i class="fa fa-spin fa-cog"></i> Syncing... Please wait!</div>');
			dialog.modal('show');
		}
		
		var pageContext = "${pageContext.request.contextPath}";
		$("#sync_single").click(function(e){
			dialog.modal('show');
			var ids = $("#sync_items").tagsinput("items").map(x => parseInt(x));
			var syncType = $("#sync_type").val();
			var form = new FormData();
			form.append("syncType", syncType);
			ids.forEach(x => {
				form.append("item", x);
			});
			
			$.ajax({
				url: pageContext + '/sync/biblioitems',
				method: 'POST',
				data: form,
				cache: false,
		        processData: false,
		        contentType: false,
		        beforeSend: function(){
		        	showDialog();
		        },
				success: function(response){
					$("#message").html(response.length + "item/s synced");
					var html = parseAndBuildResult(response);
					if(html != '')
					{
						$("#example1").find("tbody").append(html);
						$(".dataTables_empty").addClass('d-none');
						$("#sync_block").addClass('d-none');
						$("#sync_result").removeClass('d-none');
						$("#sync_items").tagsinput("removeAll");
					}
				},
				error: function(err){
					$("#message").html("Failed!");
				},
				complete: function(){
					setTimeout(function(){
						dialog.modal('hide');
					}, 2000);
				}
			});
			
		});
		
		function parseAndBuildResult(resp){
			return resp.map(x => {
							return '<tr><td>'+ x.biblionumber + '</td><td>' + x.itemnumber + '</td><td>' + x.title + '</td><td>' + x.authorname + '</td><td>' + (x.enumchron || '-') + '</td><td><a href="${pageContext.request.contextPath}/biblioitemdetail?searchType=item&id='+x.itemnumber+'">Detail</a></td></tr>';
						})
						.reduce((a,b) => a+b, '');
		}
		
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

		/* //Start Checkbox Download Jquery 
		$("input[type=checkbox]").on('change', function(event) {

			var self = $(this);
			if (self.is(":checked")) {
				var itemnumber = $(this).data("itemnumber");
				console.log("checked");
				console.log(itemnumber);

				//ajax
				$.ajax({
					url : "downloaddetails",
					type : 'POST',
					data : {
						"downloadable" : 1,
						"itemnumber" : itemnumber
					},
					success : function(data) {
					}

				});
				//ajax

			} else {
				var itemnumber = $(this).data("itemnumber");
				console.log("unchecked");
				console.log(itemnumber);

				//ajax
				$.ajax({
					url : "downloaddetails",
					type : 'POST',
					data : {
						"downloadable" : 0,
						"itemnumber" : itemnumber
					},
					success : function(data) {
					}

				});
				//ajax
			}

		});
		//End Checkbox Download Jquery

		//Start  Radio Access level Start Jquery	
		function accesslevel(itemnumber, hi) {

			if (hi == "partial") {
				console.log("itemnumber" + itemnumber);
				alert('Update Partial Access in Database');
				$("input[value=" + itemnumber + "]").prop('disabled', true);

				$.ajax({
					url : "radiodetails",
					type : 'POST',
					data : {
						"accesslevel" : "partial",
						"itemnumber" : itemnumber
					},
					success : function(data) {

					}

				});

			}

			else {
				console.log("itemnumber" + itemnumber);
				alert('Update Full Access in Database');
				$("input[value=" + itemnumber + "]").prop('disabled', false);
				$.ajax({
					url : "radiodetails",
					type : 'POST',
					data : {
						"accesslevel" : "ful	l",
						"itemnumber" : itemnumber
					},
					success : function(data) {
					}

				});
			}

		}
		//End Access level
		

		//for choose pdf to textbox
		$('#my_modal').find('input[type="file"]').change(function(e) {

			var filename = e.target.files[0].name;

			$('#my_modal').find('input[id="accesslink"]').val(filename);
		});

		$("#my_modal").on('show.bs.modal', function(event) {
			var b = $(event.relatedTarget);
			var itemnumber = b.data('itemnumber');
			console.log(itemnumber);
			var collection = b.data('collection');
			console.log(collection);
			var modal = $(this)

			modal.find('.modal-body #collection').val(collection);
			modal.find('.modal-body #itemnumber').val(itemnumber);

		});
		//end file upload

		//for image upload
		$('#imagemodal').find('input[type="file"]').change(function(e) {

			var filename = e.target.files[0].name;

			$('#imagemodal').find('input[id="accesslink"]').val(filename);
		});

		$("#imagemodal").on('show.bs.modal', function(event) {
			var b = $(event.relatedTarget);
			var itemnumber = b.data('itemnumber');
			console.log(itemnumber);

			var modal = $(this)

			modal.find('.modal-body #itemnumber').val(itemnumber);

		}); */
	</script>
</body>
</html>
