<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Import Item</title>
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
							<h1 class="m-0">Import Items</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Import Item</li>
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
							<div class="row mb-2">
								<div class="col-md-10 mr-auto ml-auto">
									<form:form action="importitemsearch" modelAttribute="data"
										method="post">
										<div class="input-group w-50 float-right">
											<form:input type="number" class="form-control"
												value="${bibliodata}"
												placeholder="Search with Record Number" path="biblionumber"
												required="required" />
											<span class="input-group-append">
												<button type="submit" class="btn btn-info btn-flat">Go</button>
											</span>
										</div>
									</form:form>
								</div>
							</div>

							<div class="card col-md-10 mr-auto ml-auto">
								<div class="card-body">
									<form action="saveExcelItem" method="post"
										enctype="multipart/form-data">
										<div class="row">
											<div class="col-md-12">
												<input type="hidden" class="form-control w-25"
													value="${bibliodata}" name="recordnumber" required readonly />
												<div class="form-group">
													<label for="exampleInputFile">File input</label>
													<div class="input-group">
														<div class="custom-file">
															<input type="file" class="custom-file-input"
																id="exampleInputFile" name="file" required
																accept=".xlsx,.csv"> <label
																class="custom-file-label" for="exampleInputFile">Choose
																file</label>
														</div>
													</div>
												</div>
												<div class="form-group">

													<input type="submit" value="Upload" class="btn btn-info" />
													<input type="submit" value="Download Sample CSV File"
														onclick="download_csv_file()"
														class="btn btn-warning text-white" style="float: right;" />

												</div>
												<c:if test="${alert ne null }">
													<div class="alert alert-danger text-center" role="alert">${alert}</div>
												</c:if>

											</div>
											<!-- column -->
										</div>
										<!-- row -->
									</form>
								</div>
								<!-- card body -->
							</div>

							<div class="card">
								<!-- /.card-header -->
								<div class="card-body">
									<table id="example1" class="table table-bordered table-striped">
										<thead>
											<tr class="bg-info">
												<th style="width: 55px;">#</th>
												<th>Bookseller id</th>
												<th>Homebranch</th>
												<th>Item Call Number</th>
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

														<c:url var="deletelink" value="/deleteimportitem">
															<c:param name="itemId" value="${tempItems.itemnumber}" />
														</c:url>
														<tr>
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
					</div>
				</div>
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

		//create CSV file data in an array
		var csvFileData = [
				[ 'Alan Walker', 'Singer', 'Alan Walker', 'Singer',
						'Alan Walker', 'Singer', 'Alan Walker' ],
				[ 'Alan Walker', 'Singer', 'Alan Walker', 'Singer',
						'Alan Walker', 'Singer', 'Alan Walker' ],
				[ 'Alan Walker', 'Singer', 'Alan Walker', 'Singer',
						'Alan Walker', 'Singer', 'Alan Walker' ],
				[ 'Alan Walker', 'Singer', 'Alan Walker', 'Singer',
						'Alan Walker', 'Singer', 'Alan Walker' ],
				[ 'Alan Walker', 'Singer', 'Alan Walker', 'Singer',
						'Alan Walker', 'Singer', 'Alan Walker' ] ];

		//create a user-defined function to download CSV file 
		function download_csv_file() {

			//define the heading for each row of the data
			var csv = 'BookSellerId,HomeBranch,ItemCallNumber,Barcode,ItemTypeCode,PublishedDate,CollectionCode\n';

			//merge the data with CSV
			csvFileData.forEach(function(row) {
				csv += row.join(',');
				csv += "\n";
			});

			var hiddenElement = document.createElement('a');
			hiddenElement.href = 'data:text/csv;charset=utf-8,'
					+ encodeURI(csv);
			hiddenElement.target = '_blank';

			//provide the name for the CSV file to be downloaded
			hiddenElement.download = 'SimpleItem.csv';
			hiddenElement.click();
		}
	</script>


</body>
</html>