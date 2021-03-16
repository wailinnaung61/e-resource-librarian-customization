<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>View Collection</title>
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
     
  <link rel="stylesheet" href="resources/themify-icons/themify-icons.css">

  <link rel="stylesheet" href="resources/themify-icons/ie7/ie7.css">
 
  <link rel="stylesheet" href="resources/plugins/fontawesome-free/css/all.min.css">
  
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  
  <link rel="stylesheet" href="resources/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
  
  <link rel="stylesheet" href="resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  
  <link rel="stylesheet" href="resources/plugins/jqvmap/jqvmap.min.css">
  
  <link rel="stylesheet" href="resources/dist/css/adminlte.min.css">
  
  <link rel="stylesheet" href="resources/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  
  <link rel="stylesheet" href="resources/plugins/daterangepicker/daterangepicker.css">
 
  <link rel="stylesheet" href="resources/plugins/summernote/summernote-bs4.css">
 
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  
  <style type="text/css">
  tr
  {
  text-align:center;
  }
  th
  {
    text-align:center;
  }
aside
{
background-color:#475B9E;
}
.trbg
{
background-color: #475B9E;
color:white;
}
  </style>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

 
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">   
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="btton"><i class="fas fa-bars" style="color:black;"></i></a>       
      </li>    
    </ul>
    <ul class="navbar-nav ml-auto">
      <li>
			<font size="4px" style="font-family:Times New Roman, Times, serif">${loginname}</font>&nbsp;&nbsp;<svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
	 	    width="40px" height="40px" viewBox="0 0 64 64" enable-background="new 0 0 64 64" xml:space="preserve">
		    <g id="USER_3_" enable-background="new" >
			<g id="USER">
		    <g>
			<path d="M32,0C14.327,0,0,14.327,0,32s14.327,32,32,32s32-14.327,32-32S49.673,0,32,0z M51.253,49.43
			c-3.767-1.826-2.382-0.398-7.31-2.427c-5.041-2.073-6.235-2.749-6.235-2.749L37.664,39.5c0,0,1.888-1.422,2.477-5.917
			c1.178,0.338,1.578-1.372,1.642-2.464c0.069-1.055,0.696-4.346-0.745-4.052c0.295-2.197,0.527-4.183,0.421-5.235
			c-0.36-3.691-2.931-7.544-9.42-7.826c-5.517,0.282-9.098,4.138-9.46,7.829c-0.104,1.052,0.108,3.036,0.403,5.236
			c-1.441-0.297-0.821,2.999-0.758,4.054c0.07,1.092,0.46,2.809,1.641,2.469c0.587,4.495,2.475,5.93,2.475,5.93L26.293,44.3
			c0,0-1.195,0.724-6.236,2.796c-4.927,2.027-3.544,0.512-7.31,2.334C8.568,44.816,6,38.715,6,32C6,17.641,17.641,6,32,6
			c14.359,0,26,11.641,26,26C58,38.716,55.432,44.816,51.253,49.43z"/>
			</g>
			</g>
			</g>
			</svg>
      </li>
      <li>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </li>
    </ul>
  </nav>
 


  
  <aside class="main-sidebar elevation-4">
  
    <%@ include file = "sidebar.jsp" %> 

  </aside>

 
 <div class="content-wrapper">
       
          <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h4>Collection Lists</h4>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              
              <!-- /.card-header --> 
              <div class="card-body">
                <table id="example2" class="table table-bordered table-hover">
                  <thead>
                  <tr class="trbg">
                    <th style="width:55px;">#</th>
                    <th>Collection</th>
                    <th style="width:100px;">Edit</th>
                    <th style="width:100px;">Delete</th>
                  </tr>
                  </thead>
                  <tbody>
             <c:forEach var="user" items="${list}">   
				    <tr>
				    <td>${user.collectionid}</td>
				    <td>${user.collection}</td>
				     
				   <td><a href="editcollection/${user.collectionid}" class="btn btn-outline-primary btn-icon-only btn-circle"><i class="ti-pencil"></i></a></td>
				    <td>
				    <a href="deletecollection/${user.collectionid}" onclick="return confirm('Are you sure?')" class="btn btn-outline-danger btn-icon-only btn-circle"><i class="ti-trash" style="color:red"></i></a>
				    </td>
				    </tr>
		     </c:forEach> 
                  </tbody>
                  <tfoot>
                  <tr class="trbg">
                    <th>#</th>
                    <th>Collection</th>
                    <th>Edit</th>
                    <th>Delete</th>
                  </tr>
                  </tfoot>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->

        </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->   
      
  </div>
  
  <footer class="main-footer">
  </footer>

</div>



<!-- jQuery -->
<script src="resources/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- DataTables -->
<script src="resources/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="resources/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="resources/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="resources/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<!-- AdminLTE App -->
<script src="resources/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="resources/dist/js/demo.js"></script>
<!-- page script -->
<script>
  $(function () {
    $("#example1").DataTable({
      "responsive": true,
      "autoWidth": false,
    });
    $('#example2').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false,
      "responsive": true,
    });
  });
</script>
</body>
</html>