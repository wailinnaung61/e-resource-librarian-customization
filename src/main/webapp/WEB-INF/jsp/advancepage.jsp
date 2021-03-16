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
<!--<link rel="stylesheet" href="custom.css"/> -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<style>
.header-custom {
    padding: 30px;
    text-align: center;
    color: white;
    font-size: 40px;
  }
  .header-custom2 {
    padding: 30px;
    background-image: url("resources/0629184a-7c7b-4e39-8cca-d0f2f7207fc4.jpg");
    background-size:100%;
    text-align: center;
    color: white;
    font-size: 25px;
  }
  .center {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 200px;
    border: 0px ;
  }
  a.nav-item {
    border-bottom: 0px solid transparent;
    color: white;
    padding: 14px 25px;
    text-align: center;
    text-decoration: none;
    display: block;
    
  }
  a.nav-item:hover {
    border: none;
    color: white;
  }
  a.hover{
    border:2px;
    color:white;
  }
  input[type=text], select, textarea {
    width: 100%;
    height: 40px !important;
    padding: 5px;
    border: 0px solid #ccc;
    border-radius: 4px;
    resize: vertical;
    background-color: #EFEFEF;
  }
.btn.btn-primary{
background-color: #475B9E;
}
.btn.btn-default{
  background-color: white;
  color: black;
  border: 2px solid #475B9E;
}
.btn.btn-outline-primary{
  border: 2px solid #475B9E;
  background-color: white;
  color: #475B9E;
  transition: all 1s;
 
}
.card-footer{
  background-color: white;
}
.btn.btn-outline-primary:hover {
  background-color:#475B9E;
  border: 2px solid #475B9E;
}
.m{
  border: 2px solid #475B9E;
  background-color: white;
  color: #475B9E;
  transition: all 1s;
  width : 100%; 
}
h5 ,h3 {
  color: #475B9E;
}
.mydiv2  {
   border-bottom-style: solid;
   border-color: #EFEFEF;
 }
 .mydiv2:last-child
{
    border-bottom:none;
}
.advance {
  border-right-style: solid;
  border-color: #475B9E;
}

.mydiv  {
  border-bottom-style: solid;
  border-color: #475B9E;
}
 .opac-list-card .title{
  color: #475B9E;
  font-size: 16.9px; 
  font-weight: bold;

}
.opac-list-card .author{

  font-size: 14px; 

}
.opac-list-card .publisher{
  color: #475B9E;
  font-size: 14px; 
}
.opac-list-card .itemtype{
  color: #475B9E;
  font-size: 14px; 
}
.opac-list-card .detail{
  color: #475B9E;
  font-size: 14px; 
}
.booklocation {
  color: #475B9E;
  font-size: 16px; 
}



.result {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 1px;
  width: 150px;
  height: 180px;
}
.bookimg {
  border: 0px solid #ddd;
  border-radius: 4px;
  padding: 5px;
  width: 100%;
  height: 300px;
}

.detail-title{
  color: #475B9E;
  font-size: 16px; 
}

.msui-card{
  -webkit-box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.16);
  -moz-box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.16);
  box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.16);
  border-width: 0px;
}
body{
  background: rgb(38,110,204);
  background: -moz-linear-gradient(180deg, rgba(38,110,204,1) 0%, rgba(45,80,131,1) 36%);
  background: -webkit-linear-gradient(180deg, rgba(38,110,204,1) 0%, rgba(45,80,131,1) 36%);
  background: linear-gradient(180deg, rgba(38,110,204,1) 0%, rgba(45,80,131,1) 36%);
  background-repeat: no-repeat;
  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#266ecc",endColorstr="#2d5083",GradientType=1);
}

.container-fluid {
  height: 100vh;
  border: 0px solid lightsteelblue;
  }

.headercard{
  background-image: url("../image/unnamed.jpg");
}

.vertical-center-row {
  vertical-align: middle;
}

table.table.list-table>tbody>tr>td:first-of-type{
  max-width: 4rem;
  width: 4rem;
  padding-left: auto;
}
.details{
  
  padding-right: 20px;
  padding-left: 40px;
};
.header-custom logo-container{
	width:100px;
	height:100px;
}
.brand_logo {
			height: 100px;
			width: 100px;
			border-radius: 50%;
		}
		.header-custom {
    padding: 30px;
    text-align: center;
    background-image: url("../image/unnamed.jpg");
    color: white;
    font-size: 40px;
  }
    .center {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 200px;
    border: 0px ;
  }

  a.nav-item {
    border-bottom: 0px solid transparent;
    color: white;
    padding: 14px 25px;
    text-align: center;
    text-decoration: none;
    display: block;
    
  }

  a.nav-item:hover {
    border: none;
    color: white;
  }

  a.hover{
    border:2px;
    color:white;
  }
 

  input[type=text], select, textarea {
    width: 100%;
    height: 40px !important;
    padding: 5px;
    border: 0px solid #ccc;
    border-radius: 4px;
    resize: vertical;
    background-color: #EFEFEF;
  }


.btn.btn-primary{

background-color: #475B9E;
}

.btn.btn-default{
  background-color: white;
  color: black;
  border: 2px solid #475B9E;
}

.btn.btn-outline-primary{
 
  border: 2px solid #475B9E;
  background-color: white;
  color: #475B9E;
  transition: all 1s;
  
  
}
.card-footer{
  background-color: white;
}

.btn.btn-outline-primary:hover {
  background-color:#475B9E;
  border: 2px solid #475B9E;
}

.m{
 
  border: 2px solid #475B9E;
  background-color: white;
  color: #475B9E;
  transition: all 1s;
  width : 100%;
  
  
}



h5 ,h3 {
  color: #475B9E;
}



.mydiv2  {
   border-bottom-style: solid;
   border-color: #EFEFEF;
 }
 .mydiv2:last-child
{
    border-bottom:none;
}

.advance {
  border-right-style: solid;
  border-color: #475B9E;
}

.mydiv  {
  border-bottom-style: solid;
  border-color: #475B9E;
}


 .opac-list-card .title{
  color: #475B9E;
  font-size: 16.9px; 
  font-weight: bold;


}
.opac-list-card .author{

  font-size: 14px; 

}
.opac-list-card .publisher{
  color: #475B9E;
  font-size: 14px; 
}
.opac-list-card .itemtype{
  color: #475B9E;
  font-size: 14px; 
}
.opac-list-card .detail{
  color: #475B9E;
  font-size: 14px; 
}
.booklocation {
  color: #475B9E;
  font-size: 16px; 
}



.result {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 1px;
  width: 150px;
  height: 180px;
}
img {
  border: 0px solid #ddd;
  border-radius: 4px;
  padding: 5px;
  width: 100%;
  height: 300px;
}

.detail-title{
  color: #475B9E;
  font-size: 16px; 
}

.msui-card{
  -webkit-box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.16);
  -moz-box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.16);
  box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.16);
  border-width: 0px;
}
body{
  background: rgb(38,110,204);
  background: -moz-linear-gradient(180deg, rgba(38,110,204,1) 0%, rgba(45,80,131,1) 36%);
  background: -webkit-linear-gradient(180deg, rgba(38,110,204,1) 0%, rgba(45,80,131,1) 36%);
  background: linear-gradient(180deg, rgba(38,110,204,1) 0%, rgba(45,80,131,1) 36%);
  background-repeat: no-repeat;
  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#266ecc",endColorstr="#2d5083",GradientType=1);
}

.container-fluid {
  height: 100vh;
  border: 0px solid lightsteelblue;
  }

.headercard{
  background-image: url("../image/unnamed.jpg");
}

.vertical-center-row {
  vertical-align: middle;
}

table.table.list-table>tbody>tr>td:first-of-type{
  max-width: 4rem;
  width: 4rem;
  padding-left: auto;
}
.details{
  
  padding-right: 20px;
  padding-left: 40px;
};
</style>
</head>
<body style="background-color:#2D5083; min-height: 100px;">
<header class="header-custom2">
<div class="row">
	<div class="col-sm-12">
			<img src="resources/WhatsApp Image 2020-07-22 at 11.03.44 AM.jpeg" class="brand_logo" alt="Logo"/>

      <span class="brand-text font-weight-white logoheader title">E-Resource Myanmar</span>
	</div>
	</div>	
</header>

<div class="container mt-3">
  
    <div class="row">
  
        <div class="col-12">
            <nav>
                <div class="nav nav-tabs" style="border:none" id="nav-tab" role="tablist">
                  <a class="nav-item nav-link" id="nav-home-tab" href="userviewmain"  role="tab" aria-controls="simple" aria-selected="true">Search</a>
                  <a class="nav-item nav-link active" id="nav-profile-tab" data-toggle="tab" href="advancepage" role="tab" aria-controls="advanced" aria-selected="false">Advanced Search</a>
                </div>
              </nav>
              <div class="tab-content card p-3" style="border-top:none;" id="nav-tabContent">
                <div class="tab-pane fade show active" id="simple" role="tabpanel" aria-labelledby="simple">
                      <div class="row">
                        <div class="col-md-7 mt-3">
                          <h5><b>Search Keyword</b></h5> 
                        </div>
                        <div class="col-md-5 mt-3">
                          <h5><b>Other</b></h5> 
                        </div>
                      </div>
                      <form action="hello" method="POST">  
                        <div class="row">
                            <div class="col-md-7 advance py-1">
                              
                    		                   
                              <div class="row">
                                <div class="col-md-2 mt-3"></div>
                                <div class="col-md-3 mt-3 select-style">
                                  <select name="idx1" class="custom-select">
                                    <option value="default"> Default</option>
                                     <option value="title"> Title </option>
                                    <option value="subject"> Subject </option>
                                    <option value="author"> Author </option>
                                    <option value="barcode"> Barcode </option>
                                    <option value="publishedyear"> Published Year </option>
                                </select>
                                </div>
                                <div class="col-md-7 mt-3 select-style">
                                  <input type="text" name="keyword1" id="" >
                                </div>
                                </div>


                                
                              <div class="row">
                                <div class="col-md-2 mt-3">AND</div>
                                <div class="col-md-3 mt-3 select-style">
                                  <select name="idx2" class="custom-select">
                                    <option value="default"> Default</option>
                                     <option value="title"> Title </option>
                                    <option value="subject"> Subject </option>
                                    <option value="author"> Author </option>
                                    <option value="barcode"> Barcode </option>
                                    <option value="publishedyear"> Published Year </option>
                                </select>
                                </div>
                                <div class="col-md-7 mt-3 select-style">
                                  <input type="text" name="keyword2" id="" >
                                </div>
                                </div>

                              
                                <div class="row">
                                  <div class="col-md-2 mt-3">AND</div>
                                  <div class="col-md-3 mt-3 select-style">
                                    <select name="idx3" class="custom-select">
                                      <option value="default"> Default</option>
                                      <option value="title"> Title </option>
                                    <option value="subject"> Subject </option>
                                    <option value="author"> Author </option>
                                    <option value="barcode"> Barcode </option>
                                    <option value="publishedyear"> Published Year </option>
                                  </select>
                                  </div>
                                  <div class="col-md-7 mt-3 select-style">
                                    <input type="text" name="keyword3" id=""  >
                                  </div>
                                  </div>
                            </div>
                            <div class="col-md-5 py-1">
                                    <div class="row">
                                      <div class="col-md-5 mt-3">Item Perpage</div>
                                      <div class="col-md-7 mt-3 select-style">
                                        <select name="page" class="custom-select">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>                                  
                                        </select>
                                         
                                      
                                      </div>
                                      </div>

                                      <div class="row">
                                        <div class="col-md-5 mt-3"></div>
                                        <div class="col-md-7 mt-3">
                                          <button type="submit" class="btn btn-primary btn-block">
                                            Search
                                        </button>
                                          
                                        </div>
                                        </div>                              
                            </div>
                        </div> 
                        </form>                  
                </div>
                
              </div>
        </div>
    </div> 
</div>

  <div class="fixed-bottom" style="background-color:black;height:50px;text-align:center;">
  <p style="margin-top:9px;color:#A3A3A3;">Copyright © 2019 National Library Myanmar (Nay Pyi Taw)</p>
  </div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
</body> 
</html>