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
<link rel="stylesheet" href="resources/dist/css/detail.css"/>
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
<body>
<header class="header-custom2">
<div class="row">
	<div class="col-sm-12">
			<img src="resources/WhatsApp Image 2020-07-22 at 11.03.44 AM.jpeg" class="brand_logo" alt="Logo"/>

      <span class="brand-text font-weight-white logoheader title">E-Resource Myanmar</span>
	</div>
	</div>	
</header>

<div class="container mt-3 mb-3" >

   <div class="col-12" style="margin:20px;">
   <div class="card p-3 msui-card">

            <div class="row px-4">

                <div class="col-md-9 ">
                    <div class="row">
                        <h3><b>ချစ်သောမဏိစန္ဒာ</b></h3>
                    </div>
                    <div class="row mb-3">
                        <div class="detail-title py-1">Book from <a href="#">Natinal Library(Naypyitaw)</a></div>
                    </div>

                    <div class="row py-1">                        
                            <div class="col-3">
                                <p class="detail-title">Author</p>
                            </div>

                            <div class="col-9">
                                	ချစ်ဉီးညို
                            </div>                       
                    </div>

                    <div class="row py-1">                        
                        <div class="col-3">
                            <p class="detail-title">Publisher</p>
                        </div>

                        <div class="col-9">
                            	သက်ချိုဦးစာပေ ၊ ၂၀၀၉ ရန်ကုန်
                        </div>                       
                    </div>

                    <div class="row py-1">                        
                        <div class="col-3">
                            <p class="detail-title">Subject(s)</p>
                        </div>

                        <div class="col-9">
                            subject
                        </div>                       
                    </div>

                    <div class="row py-1">                        
                        <div class="col-3">
                            <p class="detail-title">Edition</p>
                        </div>

                        <div class="col-9">
                            edition
                        </div>                       
                    </div>

                    <div class="row py-1">                        
                        <div class="col-3">
                            <p class="detail-title">Contents</p>
                        </div>

                        <div class="col-9">
                          Testing
                        </div>                       
                    </div>

                    <div class="row py-3">                        
                        <div class="col-3">
                            <p class="detail-title">Summary</p>
                        </div>

                        <div class="col-9">
                           Testing
                        </div>                       
                    </div>
		</div>
  
  
                <div class="col-md-3 mt-3">
                <div class="opac-list-card">
                    <img class="bookimg" src="https://www.w3schools.com/w3css/img_lights.jpg"   alt="Paris">
                     <div class="detail py-1">
                        <div class="title py-1">  <a href="{{url('opacSearch/1/1')}}" class="btn btn-outline-primary m">Read Book </a></div>
                        	
       					<div class="title py-1">  <a href="{{url('opacSearch/1/1')}}" class="btn btn-outline-primary m">Download PDF</a></div>
   								
                </div>
                    </div>
                  
                </div>
                
                
            </div>
        </div>

</div>
</div>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
   <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
</body> 
</html>