<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>View Itemtype</title>

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
							<h1 class="m-0">Create Items</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Create Items</li>
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
								<div class="card-header header bg-info">
									<h3 class="card-title textheader">Create Item</h3>
								</div>

								<div class="row">
									<!-- card row -->
									<div class="col-md-6" style="margin: auto;">
										<!-- card left column -->
										<div class="card-body">
											<form action="additemsearch" method="get">
												<div class="input-group">
													<input type="number" class="form-control"
														value="${bibliodata}"
														placeholder="Search with Record Number"
														name="biblionumber" id="biblionumber" required /> <span
														class="input-group-append">
														<button type="submit" class="btn btn-info btn-flat">Go</button>
													</span>
												</div>
											</form>
										</div>
									</div>
								</div>
								<form:form method="post" action="saveitems"
									modelAttribute="data">
									<div class="row">
										<!-- card row -->
										<div class="col-md-6">
											<!-- card left column -->
											<div class="card-body">
												<form:input type="hidden" class="form-control"
													value="${bibliodata}" placeholder="Enter Record Number"
													path="biblionumber" required="required" readonly="true" />
												<div class="form-group">
													<label>BookSeller Id</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter BooksellerId" path="booksellerid" />
												</div>
												<div class="form-group">
													<label>HomeBranch</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter HomeBranch" path="homebranch" />
												</div>
												<div class="form-group">
													<label>Item Call Number</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter Itemcallnumber" path="itemcallnumber"
														required="required" />
												</div>
												<div class="form-group">
													<label>Barcode</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter Barcode" path="barcode"
														required="required" />
												</div>
											</div>
										</div>
										<!-- card left column -->

										<div class="col-md-6">
											<!-- card right column -->
											<div class="card-body">
												<div class="form-group">
													<label>Item Type</label>
													<form:select class="form-control select w-100"
														path="itemtype">
														<c:forEach var="e" items="${itemtypes}">
															<option value="${e.code}">${e.name}</option>
														</c:forEach>
													</form:select>
												</div>

												<div class="form-group">
													<label>Published Date</label>
													<form:input type="text" class="form-control" id=""
														placeholder="Enter PublishedDate" path="publicationyear"
														required="required" />
												</div>

												<div class="form-group">
													<label>Collection</label>
													<form:select class="form-control select w-100"
														path="collection">
														<c:forEach var="e" items="${collections}">
															<option value="${e.name}">${e.name}</option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
									</div>
									<c:if test="${alert ne null }">
										<div class="alert alert-danger text-center" role="alert">${alert}</div>
									</c:if>
									<c:if test="${message ne null }">
										<div class="p-3 text-success text-center">
											<i class="fa fa-check-circle"></i>${message}
										</div>
									</c:if>
									<div class="card-footer">
										<button type="submit" class="btn btn-info">Submit</button>
										<c:if test="${bibliodata ne null }">
											<a
												href="${pageContext.request.contextPath}/importitemsearch?biblionumber=${bibliodata}"
												class="btn btn-secondary float-right" role="button"
												aria-pressed="true">Import >>></a>
										</c:if>
									</div>
								</form:form>
							</div>
							<!-- card -->

							<div class="card">
								<!-- /.card-header -->
								<div class="card-body">
									<table id="example1" class="table table-bordered table-striped">
										<thead>
											<tr class="bg-info">
												<th style="width: 55px;">#</th>
												<th>Item Number</th>
												<th>Bookseller id</th>
												<th>Homebranch</th>
												<th>Call Number</th>
												<th>Barcode</th>
												<th>Itemtype</th>
												<th>Published Date</th>
												<th>Collection</th>
												<th>Del</th>
											</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when test="${itemdata ne null }">
													<c:forEach var="tempItems" items="${itemdata}"
														varStatus="c">

														<c:url var="deletelink" value="/deleteitem">
															<c:param name="itemId" value="${tempItems.itemnumber}" />
															<c:param name="biblionumber" value="${bibliodata}" />
														</c:url>
														<tr>
															<td>${c.index+1}</td>
															<td>${tempItems.itemnumber}</td>
															<td>${tempItems.booksellerid}</td>
															<td>${tempItems.homebranch}</td>
															<td>${tempItems.itemcallnumber}</td>
															<td>${tempItems.barcode}</td>
															<td>${tempItems.itemtype}</td>
															<td>${tempItems.publicationyear}</td>
															<td>${tempItems.collection}</td>
															<td><a href="${deletelink}"
																onclick="return confirm('Are you sure?')"
																class="btn btn-block bg-gradient-danger"><i
																	class="fa fa-trash"></i></a></td>
														</tr>
													</c:forEach>
												</c:when>
											</c:choose>

										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
							</div>

						</div>
						<!-- column -->

					</div>
					<!-- row -->
				</div>
				<!-- container -->
			</section>

		</div>
		<!-- wrapper -->

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
						"pageLength" : 2,
						"paging" : true,
						"responsive" : true,
						"lengthChange" : false,
						"ordering" : false,
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