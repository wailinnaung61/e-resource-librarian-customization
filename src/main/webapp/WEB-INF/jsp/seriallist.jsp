<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Admin View</title>
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/themify-icons/themify-icons.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/themify-icons/ie7/ie7.css">
 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/fontawesome-free/css/all.min.css">
  
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/jqvmap/jqvmap.min.css">
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/adminlte.min.css">
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/daterangepicker/daterangepicker.css">
 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/summernote/summernote-bs4.css">
 
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
.browse
{
color:blue;
}
a.browse:hover
{
background-color:none;
}
aside
{
background-color:#475B9E;
}
.uploadbtn
{
width:127px;
height:127px;
border:0px;
border-radius:10px;
background-color:#475B9E;
color:white;
}
.uploadbtn:hover
{
background-color:#667cc4;
}
.uploadbtn:focus
{
border:0px;
}
.inputWrapper {
    height: 32px;
    width: 64px;
    overflow: hidden;
    position: relative;
    cursor: pointer;
    background-color: #475B9E;
}
.inputWrapper:hover
{
background-color:#667cc4;
}
.fileInput {
    cursor: pointer;
    height: 100%;
    position:absolute;
    top: 0;
    text-align:center;
    right: 0;
    z-index: 99;  
    font-size:50px;
    opacity: 0;
    -moz-opacity: 0;
    filter:progid:DXImageTransform.Microsoft.Alpha(opacity=0)
}
.choosefile
{
color:white;
margin-top:3px;"
}
.uploadfile
{
color:white;
border:0px;
}
.addlink:focus
{
color:red;
}
label {
    font-weight: normal !important;
}
.navsearch
{
color:black;
}
.navsearch:hover
{
color:black;
}
.btn
{
color:white;
background-color:#475B9E;
border:0px;
}
.btn:hover
{
background-color:#667cc4;
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
    
    
    <div class="card card" style="margin:10px;">
              <div class="card-header">
                <h3 class="card-title">Uploaded Records</h3>
              </div>
              <div class="card-body">
           
           
               <form:form method="post" action="showviewdetail" role="form">    
              <table id="example2" class="table table-bordered table-striped table-hover">
                  <thead>
                  <tr>
                    <th style="width:10px;">Biblio Number</th>
                    <th style="width:250px;">Title</th>
                    <th style="width:130px;">Number of Issues</th>                  
                    <th style="width:130px;">Action</th>
                   
                  </tr>
                  </thead>
                  <tbody>
           
                  <c:forEach var="data" items="${list}">                                        
                    <tr>
	                <td id="number" style="text-align:left;">${data.biblionumber}</td>
	                
				    <td>${data.title}</td>
				    
				    <td>${data.itemcount}</td>
				   
				    <td><a href="serialviewdetail/${data.biblionumber}" class="btn btn-outline-primary btn-icon-only btn-circle">View Detail</a></td>	
				    
				    </tr>		    				    
		            		            		            	        
	       		</c:forEach>  
	                    
                  </tbody>
                </table>
                </form:form>
              
            </div><!-- /.card-body -->
            <!-- /.card -->
      
    </div>
                                         
  </div>
    
  <footer class="main-footer">
  </footer>

  
</div>



<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/plugins/jquery-ui/jquery-ui.min.js"></script>
<script>
  $.widget.bridge('uibutton', $.ui.button)
</script>
<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/chart.js/Chart.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/sparklines/sparkline.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery-knob/jquery.knob.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/moment/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/daterangepicker/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/summernote/summernote-bs4.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.js"></script>
<script src="${pageContext.request.contextPath}/resources/dist/js/pages/dashboard.js"></script>
<script src="${pageContext.request.contextPath}/resources/dist/js/demo.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
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
      "ordering": false,
      "info": true,
      "autoWidth": false,
      "responsive": true,
    });
  });
  
//Start Checkbox Download Jquery 
$("input[type=checkbox]").on('change', function (event) {
	
	var self = $(this);
    if (self.is(":checked"))
    	{
    	var itemnumber=$(this).data("itemnumber");
    	console.log("checked");
    	console.log(itemnumber);
    	
    	//ajax
 		  $.ajax({
 		         url: "downloaddetails",
 		         type: 'POST',
 		         data: {"downloadable":1,"itemnumber":itemnumber},	         
 		         success: function(data){
 		      }

 		   });
 		  //ajax
    	
    	
    	
    	}
    else
    	{
    	var itemnumber=$(this).data("itemnumber");
    	console.log("unchecked");
    	console.log(itemnumber);
    	
    	//ajax
		  $.ajax({
		         url: "downloaddetails",
		         type: 'POST',
		         data: {"downloadable":0,"itemnumber":itemnumber},	         
		         success: function(data){
		      }

		   });
		  //ajax
    	}
	
});
//End Checkbox Download Jquery
		
	//Start  Radio Access level Start Jquery	
	function accesslevel(itemnumber,hi)
{
	
	if(hi=="partial")
		{
		console.log("itemnumber"+itemnumber);
		alert('Update Partial Access in Database');
		$("input[value="+itemnumber+"]").prop('disabled', true);
						
				$.ajax({
			         url: "radiodetails",
			         type: 'POST',
			         data: {"accesslevel":"partial","itemnumber":itemnumber},	         
			         success: function(data){
			        	 
			        	 
			      }
		
			   });
		
		}
	
	else
		{
		console.log("itemnumber"+itemnumber);
		alert('Update Full Access in Database');
		$("input[value="+itemnumber+"]").prop('disabled', false);
		  $.ajax({
		         url: "radiodetails",
		         type: 'POST',
		         data: {"accesslevel":"full","itemnumber":itemnumber},	         
		         success: function(data){
		      }

		   });
		}

}
//End Access level
	
	//for choose pdf to textbox
	$('#my_modal').find('input[type="file"]').change(function(e) {
		 
		var filename=e.target.files[0].name;
		
		$('#my_modal').find('input[id="accesslink"]').val(filename);	
	  });
	
	  
	  $("#my_modal").on('show.bs.modal', function (event) {
		    var b=$(event.relatedTarget);
		    var itemnumber=b.data('itemnumber');
		    console.log(itemnumber);
		    var collection=b.data('collection');
		    console.log(collection);
		    var modal=$(this)
		    
		    modal.find('.modal-body #collection').val(collection);
		    modal.find('.modal-body #itemnumber').val(itemnumber);
			
			
			
		  });
	  //end file upload
	  
	  //for image upload
	  $('#imagemodal').find('input[type="file"]').change(function(e) {
		 
		var filename=e.target.files[0].name;
		
		$('#imagemodal').find('input[id="accesslink"]').val(filename);	
	  });
	  
	  
	  $("#imagemodal").on('show.bs.modal', function (event) {
		    var b=$(event.relatedTarget);
		    var itemnumber=b.data('itemnumber');
		    console.log(itemnumber);
		   
		    var modal=$(this)
		    
		    modal.find('.modal-body #itemnumber').val(itemnumber);
			
			
			
		  });
  </script>
</body>
</html>
