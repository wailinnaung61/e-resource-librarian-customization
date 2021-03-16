<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<title>EResource</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/dist/css/userviewmain.css"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<style>
.header-custom2 {
    padding: 30px;
    background-image: url("resources/0629184a-7c7b-4e39-8cca-d0f2f7207fc4.jpg");
    background-size:100%;
    text-align: center;
    color: white;
    font-size: 25px;
  }
</style>
</head>
<body style="background-color:#2D5083;">
<header class="header-custom2">
<div class="row">
	<div class="col-sm-12">
			<img src="resources/WhatsApp Image 2020-07-22 at 11.03.44 AM.jpeg" class="brand_logo" alt="Logo"/>
            <span class="brand-text font-weight-white logoheader title">E-Resource Myanmar</span>
	</div>
	</div>	
</header>
    
<form action="#" method="POST">
   
<div class="container mt-3">
    <div class="row">
       
        <div class="col-12">
            <nav>
                <div class="nav nav-tabs" style="border:none" id="nav-tab" role="tablist">
                  <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#" role="tab" aria-controls="simple" aria-selected="true">Search</a>
                  <a class="nav-item nav-link" id="nav-profile-tab"  href="advancepage" role="tab" aria-controls="advanced" aria-selected="false">Advanced Search</a>
                </div>
              </nav>
              <div class="tab-content card  p-3" style="border-top:none;transition: none" id="nav-tabContent">
                <div class="tab-pane fade show active" id="simple" role="tabpanel" aria-labelledby="simple">
                    <form action="" method="post">
                        <div class="row">
                            <div class="col-md-2 mt-3">
                                <select name="idx" class="custom-select">
                                    <option value="all"> All </option>
                                    <option value="title"> Title </option>
                                    <option value="subject"> Subject </option>
                                    <option value="author"> Author </option>
                                    <option value="isbn"> ISBN </option>
                                    <option value="publishedyear"> Published Year </option>
                                </select>
                            </div>
                            <div class="col-md-8 mt-3">
                                <input type="text" name="keyword" id="" class="form-control" placeholder="Please enter search condition" >
                            </div>
                            <div class="col-md-2 mt-3">
                                <button type="submit" class="btn btn-primary btn-block">
                                    Search
                                </button>
                            </div>
                            
                        </div>
                    </form>
                </div>
                
              </div>
        </div>
        
        
</div> 
</div>
</form>

<div class="fixed-bottom" style="background-color:black;height:50px;text-align:center;">
<p style="margin-top:9px;color:#A3A3A3;">Copyright © 2020 National Library Myanmar (Nay Pyi Taw)</p>
</div>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
   <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
</body> 
</html>