<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Add Single Biblio Data</title>

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

			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Create Single Record</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Create Single Record</li>
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

						<div class="col-md-12" style="margin: auto;">
							<div class="card card-info">

								<div class="card-header header">
									<h3 class="card-title textheader">Search Framework</h3>
								</div>

								<div class="row">
									<!-- card row -->
									<div class="col-md-6">
										<!-- card left column -->
										<div class="card-body">
											<form:form class="form-inline" method="GET"
												modelAttribute="searchFramework">
												<form:select path="searchFramework"
													class="form-control mr-1 w-50">
													<form:option value="book">Book</form:option>
													<form:option value="cd">CD</form:option>
												</form:select>
												<input type="submit" class="form-control btn btn-info"
													value="Go">
											</form:form>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<!-- row -->

					<c:choose>

						<c:when test="${data != null }">

							<div class="row">

								<div class="col-md-10" style="margin: auto;">
									<form:form method="post"
										action="${pageContext.request.contextPath }/savebibliodata"
										modelAttribute="savedata">
										<div class="card card-info">
											<div class="card-header header">
												<h3 class="card-title textheader">Fill Data</h3>
											</div>
											<div class="row">
												<div class="col-md-12">
													<div class="card-body">
														<c:forEach var="d" items="${data}">
															<c:if test="${d.type == 'text'}">

																<div class="form-group">
																	<label>${d.label}</label>
																	<form:input type="text" class="form-control"
																		path="${d.path}"
																		required="${(d.required eq true) ? 'required' : ''}" />
																</div>
															</c:if>
															
															<c:if test="${d.type == 'textarea'}">

																<div class="form-group">
																	<label>${d.label}</label>
																	<textarea class="form-control"
																		name="${d.path}" rows=5
																		required="${(d.required eq true) ? 'required' : ''}"></textarea>
																</div>
															</c:if>
															
															
															<c:if test="${d.type == 'selectbox' && d.path == 'itemtype'}">
																<div class="form-group">
																	<label>${d.label}</label>
																	<form:select path="itemtype"
																		class="form-control select w-100">

																		<c:forEach var="e" items="${d.itemtypes}">
																			<option value="${e.code}">${e.name}</option>
																		</c:forEach>

																	</form:select>
																</div>
															</c:if>
														</c:forEach>
													</div>
												</div>
											</div>
											<div class="card-footer">
												<button type="submit" class="btn btn-info">Submit</button>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</c:when>

					</c:choose>
				</div>
				<!-- container -->
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
