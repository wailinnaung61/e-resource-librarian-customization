<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style type="text/css">
p {
	color: white;
}

.ll:hover {
	background-color: #1c91b7;
}

.navcolor>a {
	color: white;
}

.logoheader {
	font-size: 19px;
}

.title {
	color: white;
}

.welcome {
	color: white;
}

.uppersidebar {
	background-color: #1c91b7;
}

.lowersidebar {
	background-color: #1DA5D1;
}
.badge {
	background: #fd7e14;
	font-size: 85%;
}
</style>
<c:set var="contextURL" value="${pageContext.request.contextPath }" />
<div class="uppersidebar">
	<img src="${contextURL}/resources/pku.png"
		alt="University of Computer Studies(Pakokku)" class="brand-image img-circle elevation-5 w-50 m-auto p-2 d-block" style="border:1px solid white;">
	<div style="padding: 10px 5px;">
		<p align="center" style="font-size: 18px;">University of Computer Studies(Pakokku)</p>
	</div>
</div>
<div class="sidebar lowersidebar">
	<nav class="mt-2" id="navcolor">
		<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">

			<li class="nav-item has-treeview">
				<a href="#" class="nav-link ll"> <i class="nav-icon fas fa-user text-white"></i>
					<p style="color: white;">
						Users <i class="fas fa-angle-left right"></i>
					</p>
				</a>
				<ul class="nav nav-treeview">
					<li class="nav-item">
						<a href="${contextURL}/userform" class="nav-link ll">
							<i class="far fa-circle nav-icon text-white"></i>
							<p>Add New User</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="${contextURL}/viewuser" class="nav-link ll">
							<i class="far fa-circle nav-icon text-white"></i>
							<p>User Lists</p>
						</a>
					</li>
				</ul>
			</li>

			<li class="nav-item has-treeview">
				<a href="#" class="nav-link ll"> <i class="nav-icon fas fa-user text-white"></i>
					<p>
						Itemtypes <i class="fas fa-angle-left right"></i>
					</p>
				</a>
				<ul class="nav nav-treeview">
					<li class="nav-item">
						<a href="${contextURL}/additemtypes"
							class="nav-link ll">
							<i class="far fa-circle nav-icon text-white"></i>
							<p>Add Item Types</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="${contextURL}/viewitemtype" class="nav-link ll">
							<i class="far fa-circle nav-icon text-white"></i>
							<p>View Lists</p>
						</a>
					</li>
				</ul>
			</li>
			
			<li class="nav-item has-treeview">
				<a href="#" class="nav-link ll"> <i class="nav-icon fas fa-user text-white"></i>
					<p>
						Role <i class="fas fa-angle-left right"></i>
					</p>
				</a>
				<ul class="nav nav-treeview">
					<li class="nav-item">
						<a href="${contextURL}/AddRole"
							class="nav-link ll">
							<i class="far fa-circle nav-icon text-white"></i>
							<p>Add new Role</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="${contextURL}/ViewRole" class="nav-link ll">
							<i class="far fa-circle nav-icon text-white"></i>
							<p>Role Lists</p>
						</a>
					</li>
				</ul>
			</li>
			

			<li class="nav-item">
				<a href="${contextURL}/bibliolist" class="nav-link ll"> 
					<i class="nav-icon fas fa-th text-white"></i>
					<p>Biblio Sync</p>
				</a>
			</li>

			<li class="nav-item"><a href="${contextURL}/biblioitemdetail"
				class="nav-link ll"> <i class="nav-icon fas fa-th text-white"></i>
					<p>Biblio Detail</p>
				</a>
			</li>
			
			<li class="nav-item has-treeview">
				<a href="#" class="nav-link ll text-white">
					<i class="nav-icon fas fa-th"></i>
					<p>Report Lists <i class="fas fa-angle-left right"></i></p>
				</a>
				<ul class="nav nav-treeview">
					<li class="nav-item">
						<a href="${contextURL}/report/readinghistory" class="nav-link ll text-white">
							<i class="far fa-circle nav-icon"></i>
							<span>Reading History</span>
						</a>
					</li>
					<li class="nav-item">
						<a href="${contextURL}/reportbibliolist" class="nav-link ll text-white">
							<i class="nav-icon fas fa-cart-plus text-white"></i>
							<span>Biblio Report</span>
						</a>
					</li>
					<li class="nav-item">
						<a href="${contextURL}/report/itemspopularity" class="nav-link ll text-white">
							<i class="nav-icon fas fa-cart-plus text-white"></i>
							<span>Items Popularity</span>
						</a>
					</li>
					<li class="nav-item">
						<a href="${contextURL}/report/patronaccess" class="nav-link ll text-white">
							<i class="nav-icon fas fa-cart-plus text-white"></i>
							<span>Patron Access Report</span>
						</a>
					</li>
					
				</ul>
			</li>
			
			<li class="nav-item"><a href="${contextURL}/seriallist"
				class="nav-link ll"> <i class="nav-icon fas fa-th text-white"></i>
					<p>Serial Lists</p>
			</a></li>

			<li class="nav-item">
				<a href="${contextURL}/itemtypelist" class="nav-link ll"> 
					<i class="nav-icon fas fa-th text-white"></i>
					<p>Item Types</p>
				</a>
			</li>

			<li class="nav-item">
				<a href="${contextURL}/patronlist" class="nav-link ll">
					<i class="nav-icon fas fa-th text-white"></i>
					<p>Patron List</p>
				</a>
			</li>

			<li class="nav-item">
				<a href="${contextURL}/specialrequests" class="nav-link ll"> 
					<i class="nav-icon fas fa-th text-white"></i>
					<p>Special Request List &nbsp;<span class="badge" id="specialrequestcount">0</span></p>
				</a>
			</li>
			

			<li class="nav-item"><a href="${contextURL}/changepassword"
				class="nav-link ll"> <i class="nav-icon fas fa-key text-white"></i>
					<p>Change Password</p>
			</a></li>
			<li class="nav-item"><a href="${contextURL}/logout"
				onclick="return confirm('Are you sure?')" class="nav-link ll"> <i
					class=" nav-icon fas fa-sign-out-alt text-white"></i>
					<p>Log Out</p>
			</a></li>
			
		</ul>
	</nav>
</div>
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