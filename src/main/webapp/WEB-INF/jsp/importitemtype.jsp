<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Import Item Types</title>

<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/plugins/fontawesome-free/css/all.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/plugins/daterangepicker/daterangepicker.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- Select2 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/plugins/select2/css/select2.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
<!-- BS Stepper -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/plugins/bs-stepper/css/bs-stepper.min.css">
<!-- dropzonejs -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/plugins/dropzone/min/dropzone.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/AdminLTE/dist/css/adminlte.min.css">

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
							<h1 class="m-0">Import ItemTypes</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Import ItemTypes</li>
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
								<div class="card-header">
									<h3 class="card-title">Import ItemTypes</h3>
								</div>
								<div class="card-body">
									<form action="${pageContext.request.contextPath}/itemtypes/saveExcelItemtype" method="post"
										enctype="multipart/form-data">
										<div class="row">
											<div class="col-md-12">
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

											</div>
											<!-- column -->
										</div>
										<!-- row -->
									</form>
								</div>
								<!-- card body -->
							</div>
						</div>

					</div>
					<!-- /.row -->
				</div>
				<!-- container -->
			</section>
			<%-- <form action="saveExcelItemtype" method="post"
				enctype="multipart/form-data">
				Select File: <input type="file" name="file" required
					accept=".xlsx,.csv" /> <input type="submit" value="Upload File" />
			</form>
			<button onclick="download_csv_file()">Download CSV</button> --%>
		</div>

		<%@ include file="footer.jsp"%>

	</div>

	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/resources/AdminLTE/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="${pageContext.request.contextPath}/resources/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${pageContext.request.contextPath}/resources/AdminLTE/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="${pageContext.request.contextPath}/resources/AdminLTE/dist/js/demo.js"></script>
	<!-- Page specific script -->
	<script>
		//create CSV file data in an array
		var csvFileData = [ [ 'Alan Walker', 'Singer' ],
				[ 'Cristiano Ronaldo', 'Footballer' ],
				[ 'Saina Nehwal', 'Badminton Player' ],
				[ 'Arijit Singh', 'Singer' ], [ 'Terence Lewis', 'Dancer' ] ];

		//create a user-defined function to download CSV file 
		function download_csv_file() {

			//define the heading for each row of the data
			var csv = 'ItemType,ItemCode\n';

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
			hiddenElement.download = 'Famous Personalities.csv';
			hiddenElement.click();
		}
	</script>
</body>
</html>
