<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Items Detail</title>

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

<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

<link rel="stylesheet"
	href="resources/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">

<link rel="stylesheet"
	href="resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">

<link rel="stylesheet" href="resources/plugins/jqvmap/jqvmap.min.css">


<link rel="stylesheet"
	href="resources/plugins/summernote/summernote-bs4.css">

<style type="text/css">
.browse {
	color: blue;
}

a.browse:hover {
	background-color: none;
}

aside {
	background-color: #475B9E;
}

.uploadbtn {
	width: 127px;
	height: 127px;
	border: 0px;
	border-radius: 10px;
	background-color: #475B9E;
	color: white;
}

.uploadbtn:hover {
	background-color: #667cc4;
}

.uploadbtn:focus {
	border: 0px;
}

.inputWrapper {
	height: 32px;
	width: 64px;
	overflow: hidden;
	position: relative;
	cursor: pointer;
	background-color: #475B9E;
}

.inputWrapper:hover {
	background-color: #667cc4;
}

.fileInput {
	cursor: pointer;
	height: 100%;
	position: absolute;
	top: 0;
	text-align: center;
	right: 0;
	z-index: 99;
	font-size: 50px;
	opacity: 0;
	-moz-opacity: 0;
	filter: progid:DXImageTransform.Microsoft.Alpha(opacity=0)
}

.choosefile {
	color: white;
	margin-top: 3px;
	"
}

.uploadfile {
	color: white;
	border: 0px;
}

.addlink:focus {
	color: red;
}

label {
	font-weight: normal !important;
}

.navsearch {
	color: black;
}

.navsearch:hover {
	color: black;
}

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

a.nav-link.active {
	font-weight: bold;
}
</style>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Modal title</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Do you want to delete this resource?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">Yes,
						confirm.</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
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
							<h1 class="m-0">Records/Items Detail</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Records | Items Detail</li>
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
							<div class="card m-2">
								<div class="card-header bg-info">
									<h3 class="card-title">Search Record items</h3>
								</div>
								<div class="card-body row">
									<div class="mb-1">
										<form:form class="form-inline" method="GET"
											modelAttribute="searchBean">
											<form:select path="searchType" class="form-control mr-1">
												<form:option value="item">Item</form:option>
												<form:option value="biblio">Biblio</form:option>
											</form:select>
											<form:input type="text" path="id" class="form-control mr-1"
												required="required"
												placeholder="Enter item or biblio number" />
											<input type="submit" class="form-control bg-info" value="Go">
										</form:form>
									</div>
								</div>
							</div>
							<c:choose>
								<c:when test="${data != null }">
									<form:form method="post" modelAttribute="data"
										enctype="multipart/form-data" id="detail-form">
										<div class="card m-2">
											<div class="card-header header bg-info">
												<h3 class="card-title textheader">Records Detail</h3>
											</div>
											<div class="d-flex justify-content-around">
												<div class="flex-fill m-3">
													<label>Records Number</label> <input type="number"
														class="form-control" name="biblionumber"
														value="${data.biblionumber}" readonly />
												</div>
												<div class="flex-fill m-3">
													<label>Title</label> <input class="form-control"
														type="text" value="${data.title	}" readonly />
												</div>
												<div class="flex-fill m-3">
													<label>Author</label> <input class="form-control"
														type="text" value="${data.author}" readonly />
												</div>
											</div>
										</div>
										<div class="card m-2">
											<div class="card-header header bg-info">${("biblio" eq searchBean.searchType)? 'Item List':'Item Detail' }
											</div>
											<div class="card-body">
												<c:forEach items="${data.items}" var="item" varStatus="i_c">
													<div class="card m-2">
														<form:hidden path="items[${i_c.index}].itemnumber" />
														<form:hidden path="items[${i_c.index}].collection" />
														<div class="d-flex justify-content-around flex-wrap">
															<div class="flex-fill p-3 mt-2">
																<label>Bar code</label> <input class="form-control"
																	value="${data.items[i_c.index].barcode }" readonly />
															</div>
															<div class="flex-fill p-3 mt-2">
																<label>Item Call Number</label> <input
																	class="form-control"
																	value="${data.items[i_c.index].itemCallNumber }"
																	readonly />
															</div>
															<div class="flex-fill p-3 mt-2">
																<label>Keyword</label>
																<form:input class="form-control"
																	path="items[${i_c.index}].keyword" />
															</div>
															<div class="flex-fill p-3">
																<ul class="nav">
																	<li class="nav-item"><a
																		href="#bc-upload-tab-${i_c.index}" data-toggle="pill"
																		role="tab" class="nav-link active">Upload Book
																			Cover file</a></li>
																	<li class="nav-item"><a
																		href="#bc-url-tab-${i_c.index}" data-toggle="pill"
																		role="tab" class="nav-link ">Add Book Cover URL</a></li>
																</ul>
																<div class="tab-content file-upload-url"
																	data-url="${data.items[i_c.index].bookcover}">
																	<div id="bc-upload-tab-${i_c.index}"
																		class="tab-pane fade show active custom-file">
																		<input type="file"
																			name="items[${i_c.index }].bookcoverfile"
																			class="custom-file-input"
																			id="file-upload-${i_c.index}" /> <label
																			class="custom-file-label"
																			for="file-upload-${i_c.index}">Choose a file</label>
																	</div>
																	<div id="bc-url-tab-${i_c.index}" class="tab-pane fade">
																		<input type="text"
																			name="items[${i_c.index }].bookcover"
																			class="form-control" placeholder="Enter Url" />
																	</div>
																</div>
															</div>
														</div>
														<div
															class="card-subtitle w-100 p-1 mt-3 border-top bg-secondary">Resources</div>
														<div
															class="d-flex justify-content-around flex-wrap resources p-3 border m-1 rounded"
															data-item-count="${i_c.index}"
															data-sitem-count="${data.items[i_c.index].subItems.size() }">
															<div class="flex-fill w-100 resource-items">
																<c:forEach items="${item.subItems}" var="sItem"
																	varStatus="s_c">
																	<div
																		class="d-flex justify-content-around flex-wrap border-bottom resource pt-2"
																		id="resource-${i_c.index}-${s_c.index}">
																		<form:input type="hidden"
																			path="items[${i_c.index }].subItems[${s_c.index }].resourceId"
																			class="resource-id-${i_c.index}-${s_c.index}" />
																		<div
																			class="flex-fill w-100 d-flex justify-content-between">
																			<div># ${s_c.index + 1 }.</div>
																			<div>
																				<button type="button"
																					class="saved-item btn btn-sm btn-danger"
																					data-index="${i_c.index}-${s_c.index}">
																					<i class="fa fa-trash"></i>
																				</button>
																			</div>
																		</div>
																		<div class="flex-fill p-3 w-50">
																			<ul class="nav">
																				<li class="nav-item"><a
																					href="#uploadtab-${i_c.index}-${s_c.index}"
																					data-toggle="pill" role="tab"
																					class="nav-link active">Upload Resource file</a></li>
																				<li class="nav-item"><a
																					href="#urltab-${i_c.index}-${s_c.index}"
																					data-toggle="pill" role="tab" class="nav-link">Add
																						Resource URL</a></li>
																			</ul>
																			<div class="tab-content file-upload-url"
																				data-url="${data.items[i_c.index].subItems[s_c.index].resourceurl}">
																				<div id="uploadtab-${i_c.index}-${s_c.index}"
																					class="tab-pane fade show active custom-file">
																					<input type="file"
																						name="items[${i_c.index }].subItems[${s_c.index }].resourcefile"
																						class="custom-file-input"
																						id="file-upload-${i_c.index}-${s_c.index}" /> <label
																						class="custom-file-label"
																						for="file-upload-${i_c.index}-${s_c.index}">Choose
																						a file</label>
																				</div>
																				<div id="urltab-${i_c.index}-${s_c.index}"
																					class="tab-pane fade">
																					<input type="text"
																						name="items[${i_c.index }].subItems[${s_c.index }].resourceurl"
																						class="form-control" placeholder="Enter Url" />
																				</div>
																			</div>
																			<input type="hidden"
																				name="items[${i_c.index}].subItems[${s_c.index}].oldresourceurl"
																				value="${data.items[i_c.index].subItems[s_c.index].resourceurl}" />
																		</div>
																		<div class="flex-fill w-50 d-flex mt-2">
																			<div class="flex-fill p-3 w-25">
																				<label>Download</label>
																				<form:select class="form-control"
																					path="items[${i_c.index }].subItems[${s_c.index }].downloadable">
																					<form:option value="1">Yes</form:option>
																					<form:option value="0">No</form:option>
																				</form:select>
																			</div>
																			<div class="flex-fill p-3 w-25">
																				<label>Access Level</label>
																				<form:select
																					path="items[${i_c.index }].subItems[${s_c.index }].accesslevel"
																					class="form-control changed_trial"
																					data-id="${s_c.index}">
																					<form:option value="2">Partial</form:option>
																					<form:option value="1">Full</form:option>
																				</form:select>
																			</div>
																			<div class="flex-fill p-3 w-25">
																				<label>Access Page</label>
																				<form:input type="number"
																					path="items[${i_c.index }].subItems[${s_c.index }].accesspages"
																					class="form-control changed_trial"
																					data-id="${s_c.index}" />
																			</div>
																			<input
																				name="items[${i_c.index }].subItems[${s_c.index }].changed_access"
																				type="hidden" id="changed_trial_${s_c.index}"
																				value="0" />
																		</div>
																	</div>
																</c:forEach>
															</div>
															<div class="text-center p-3">
																<button type="button"
																	class="btn btn-sm btn-primary add-new-resource">Add
																	New</button>
															</div>
														</div>
													</div>
												</c:forEach>
												<div class="text-right m-3">
													<input type="submit" value="Update" class="btn btn-warning">
												</div>
											</div>
										</div>
									</form:form>
								</c:when>
								<c:otherwise>
									<div class="card m-2 text-center">
										<div class="card-header header">Data not found</div>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</section>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
	<script src="resources/plugins/jquery/jquery.min.js"></script>
	<script src="resources/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/dist/js/adminlte.js"></script>
	<script src="resources/plugins/handlebars/handlebars.min.js"></script>
	<script src="resources/plugins/bootbox/bootbox.js"></script>
	<script id="resource-template" type="text/x-handlebars-template">
		<div class="border-bottom pb-3">
		<div class="d-flex justify-content-around flex-wrap border-bottom resource pt-2" id="resource-{{item}}-{{subitem}}">
			<input type="hidden" name="items[{{item}}].subItems[{{subitem}}].resourceId" value="-1"/>
			<div class="flex-fill w-100 d-flex justify-content-between">
				<div># {{subitemindex}}.</div> 
				<div>
					<button type="button" class="btn btn-sm btn-danger" id="unsaved-item-{{item}}-{{subitem}}" data-index="{{item}}-{{subitem}}"><i class="fa fa-trash"></i></button>
				</div>
			</div>
			<div class="flex-fill p-3 w-50">
				<ul class="nav">
					<li class="nav-item">
						<a href="#uploadtab-{{item}}-{{subitem}}" data-toggle="pill" role="tab" class="nav-link active">Upload file</a>
					</li>
					<li class="nav-item">
						<a href="#urltab-{{item}}-{{subitem}}" data-toggle="pill" role="tab" class="nav-link">Add Url</a>
					</li>
				</ul>
				<div class="tab-content">
					<div id="uploadtab-{{item}}-{{subitem}}" class="tab-pane fade  show active custom-file">
						<input type="file" name="items[{{item}}].subItems[{{subitem}}].resourcefile" class="custom-file-input" id="file-upload-{{item}}-{{subitem}}"/>
						<label class="custom-file-label" for="file-upload-{{item}}-{{subitem}}">Choose a file</label>
					</div>
					<div id="urltab-{{item}}-{{subitem}}" class="tab-pane fade">
						<input type="text" name="items[{{item}}].subItems[{{subitem}}].resourceurl" class="form-control" placeholder="Enter Url"/>
					</div>
				</div>
			</div>
			<div class="flex-fill w-50 d-flex mt-2">
				<div class="flex-fill p-3 w-25">
				<label>Download</label>
					<select class="form-control" name="items[{{item}}].subItems[{{subitem}}].downloadable">
						<option value="1">Yes</option>
						<option value="0">No</option>
					</select>
				</div>
				<div class="flex-fill p-3 w-25">
					<label>Access Level</label>
					<select name="items[{{item}}].subItems[{{subitem}}].accesslevel" class="form-control">
						<option value="2">Partial</option>
						<option value="1">Full</option>
					</select>
				</div>
				<div class="flex-fill p-3 w-25">
					<label>Access Page</label>
					<input type="number" name="items[{{item}}].subItems[{{subitem}}].accesspages" class="form-control" value="0"/>
				</div>
			</div>
		</div>
		</div>
	</script>
	<script>
		var pageContext = "${pageContext.request.contextPath}";
		$(".add-new-resource").on(
				'click',
				function(e) {
					var parent = $(this).parents(".resources");
					var i_count = parent.data("item-count");
					var s_count = parent.data("sitem-count");
					var new_count = parseInt(s_count) + 1;
					parent.data("sitem-count", new_count);
					var source = $("#resource-template").html();
					var template = Handlebars.compile(source);
					var context = {
						item : parseInt(i_count),
						subitem : (new_count - 1),
						subitemindex : new_count
					};
					var html = template(context);
					parent.find(".resource-items").append(html);
					var index = i_count + "-" + s_count;
					$("#unsaved-item-" + index).on(
							'click',
							function(e) {
								bootbox.confirm("Are you sure to delete?",
										function(result) {
											$('#resource-' + index).remove();
										});
							});
					$("#file-upload-" + index).on(
							"change",
							function() {
								console.log()
								var fileName = $(this).val().split("\\").pop();
								fileName = fileName.split("/").pop();
								if (fileName.length > 30) {
									fileName = fileName.substring(0, 30)
											+ '... .' + fileName.split("\.")[1]
								}
								if (fileName == "") {
									fileName = "Choose a file";
								}
								$(this).siblings(".custom-file-label")
										.addClass("selected").html(fileName);
							});
				});
		$(".saved-item")
				.on(
						'click',
						function(e) {
							var index = $(this).attr("data-index");
							var id = $(".resource-id-" + index).val();
							id = parseInt(id);
							bootbox
									.confirm(
											"Are you sure to delete?",
											function(result) {
												if (result) {
													$
															.ajax({
																url : pageContext
																		+ '/biblioitemdetail/delete/resource/'
																		+ id,
																method : "DELETE",
																success : function(
																		resp) {
																	bootbox
																			.alert("Resource Deletion Success");
																	$(
																			'#resource-'
																					+ index)
																			.remove();
																},
																error : function(
																		err) {
																	bootbox
																			.alert("Failed to delete resource")
																}
															});
												}
											});
						});
		$("#detail-form")
				.on(
						'submit',
						function(e) {
							bootbox
									.dialog({
										closeButton : false,
										message : "<i class='fa fa-spinner fa-spin'></i> Uploading data..."
									});
							/* $(".custom-file:not(.active)").html(""); */
							$(".file-upload-url").each(
									function() {
										let data = $(this).attr("data-url");
										if (data != '--changed--') {
											$(this).find("input[type='text']")
													.val(data);
											$(this).find(".custom-file").html(
													"");
										}
									});
						});
		$(".custom-file-input").on(
				"change",
				function() {
					var fileName = $(this).val().split("\\").pop();
					fileName = fileName.split("/").pop();
					if (fileName.length > 30) {
						fileName = fileName.substring(0, 30) + '... .'
								+ fileName.split("\.")[1]
					}
					if (fileName == "") {
						fileName = "Choose a file";
					}
					$(this).siblings(".custom-file-label").addClass("selected")
							.html(fileName);
				});
		$(".file-upload-url").each(
				function() {
					let data = $(this).attr("data-url");
					if (data != "") {
						if (data.startsWith("http")) {
							$(this).find("input[type='text']").val(data);
						} else {
							var fileName = data.split("\\").pop();
							fileName = fileName.split("/").pop();
							if (fileName.length > 30) {
								fileName = fileName.substring(0, 30) + '... .'
										+ fileName.split("\.")[1]
							}
							$(this).find("label").html(fileName);
						}
					}
				});
		$(".file-upload-url input").on(
				"change",
				function() {
					$(this).closest(".file-upload-url").attr("data-url",
							"--changed--");
				});
		$(".changed_trial").on('change', function() {
			let id = $(this).attr("data-id");
			$("#changed_trial_" + id).val("1");
		});
	</script>
</body>
</html>
