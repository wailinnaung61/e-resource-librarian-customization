<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextURL" value="${pageContext.request.contextPath }" />
<aside class="main-sidebar sidebar-dark-primary elevation-4" style="background-color:#282828;">
    <!-- Brand Logo -->
    <a href="#" class="brand-link">
      <img src="${pageContext.request.contextPath}/resources/msis.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">E-Resources Admin</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class with font-awesome or any other icon font library -->
          <li class="nav-item">
            <a href="${contextURL}/" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Dashboard
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-user"></i>
              <p>
                Accounts
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${contextURL}/userform" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Create Users</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${contextURL}/viewuser" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Users List</p>
                </a>
              </li>            
            </ul>
          </li>
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa fa-id-card"></i>
              <p>
                Members
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${contextURL}/addmembers" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Create Member</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${contextURL}/viewmember" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Members List</p>
                </a>
              </li>             
            </ul>
          </li>
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Itemtypes
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${contextURL}/additemtypes" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Create Itemtypes</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${contextURL}/viewitemtype" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Itemtypes List</p>
                </a>
              </li> 
              <li class="nav-item">
                <a href="${contextURL}/importitemtype" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Import Itemtypes</p>
                </a>
              </li>            
            </ul>
          </li>
          
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Collections
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${contextURL}/addcollection" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Create Collections</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${contextURL}/viewcollection" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Collections List</p>
                </a>
              </li> 
              <li class="nav-item">
                <a href="${contextURL}/importcollection" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Import Collections</p>
                </a>
              </li>            
            </ul>
          </li>
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-file"></i>
              <p>
                Data
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${contextURL}/addsinglebibliodata" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Create Single Record</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${contextURL}/additem" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Create Items</p>
                </a>
              </li> 
              <li class="nav-item">
                <a href="${contextURL}/biblioitemdetail" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Records Detail</p>
                </a>
              </li>   
              <li class="nav-item">
                <a href="${contextURL}/seriallist" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Serials List</p>
                </a>
              </li>            
            </ul>
          </li>
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-book"></i>
              <p>
                Report
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${contextURL}/report/readinghistory" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Reading History</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${contextURL}/reportbibliolist" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Records List</p>
                </a>
              </li> 
              <li class="nav-item">
                <a href="${contextURL}/report/itemspopularity" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Items Popularity</p>
                </a>
              </li>   
              <li class="nav-item">
                <a href="${contextURL}/report/patronaccess" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Access Report</p>
                </a>
              </li>            
            </ul>
          </li>
          <li class="nav-item">
            <a href="${contextURL}/specialrequests" class="nav-link">
              <i class="nav-icon fas fa-table"></i>
              <p>
                Special Requests
                <span class="badge badge-info right" id="specialrequestcount">0</span>
              </p>
            </a>
          </li>
          <li class="nav-header">Synchronize</li>
          <li class="nav-item">
            <a href="${contextURL}/bibliolist" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                Sync Records
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="${contextURL}/itemtypelist" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                Sync Itemtypes
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="${contextURL}/patronlist" class="nav-link">
              <i class="fas fa-circle nav-icon"></i>
              <p>
                Sync Members
              </p>
            </a>
          </li>
         <li class="nav-header">Settings</li>
          <li class="nav-item">
            <a href="${contextURL}/changepassword" class="nav-link">
              <i class="nav-icon fas fa-key"></i>
              <p>
                Change Password
              </p>
            </a>
          </li>
          <li class="nav-item"><a href="${contextURL}/logout"
				onclick="return confirm('Are you sure?')" class="nav-link ll"> <i
					class=" nav-icon fas fa-sign-out-alt "></i>
					<p>Log Out</p>
				</a>
		  </li>
          
  
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>
<script src="${contextURL}/resources/plugins/jquery/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$.ajax({
			url: '${contextURL}/specialrequest/count',
			success: function(response){
				if(response) {
					$("#specialrequestcount").html(response);
				}
			}
		});
	});
</script>  
  
  
  
  
  
  