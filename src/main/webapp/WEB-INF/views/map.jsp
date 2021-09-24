<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?"></script>
<title>Insert title here</title>

	<script type="text/javascript">
	if('geolocation' in navigator){
		var lat;
		var lon;
		navigator.geolocation.getCurrentPosition(function(position){
			console.log(position);
			this.lat = position.coords.latitude;
			this.lon = position.coords.longitude;
		})
        
		 var latitude = ${hos.latitude};
            var longitude = ${hos.longitude};
        function initialize() {
            var map;
            var initlatlng = new google.maps.LatLng(this.latitude,this.longitude);
            var mapOptions = {
                zoom: 12,
                center: new google.maps.LatLng(this.lat, this.lon),
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            var infowindow = new google.maps.InfoWindow(); 
            var marker, i;

           
           
                marker = new google.maps.Marker({
                    position: new google.maps.LatLng(this.latitude,this.longitude),
                    map: map
                });
			
           var hname = "${hos.hname}";
            var haddress = "${hos.address}";
                google.maps.event.addListener(marker, 'click', (function(marker, i) {
                    return function() {
                        infowindow.setContent(hname + haddress);
                        infowindow.open(map, marker);
                    }
                })(marker, i));
            }
        }
	
	else{
		console.log("Geolocation not available");
	}

        google.maps.event.addDomListener(window, 'load', initialize);
        
	
	
    </script>
</head>
<body>
<%@include file="navbar.jsp"%>
<!--Section: Contact v.1-->
<section class="section pb-5">

  <!--Section heading-->
  <h2 class="section-heading h1 pt-4">Know The Locality</h2>
  <!--Section description-->
  <p class="section-description pb-4"></p>

  <div class="row">

    <!--Grid column-->
    <div class="col-lg-5 mb-4">

      <!--Form with header-->
      <div class="card">

        <div class="card-body">
          <!--Header-->
          <div class="form-header blue accent-1">
            <h3><i class="fas fa-envelope"></i> ${hos.hname}</h3>
                     hello   		<h1> ${hos.hname}</h1>

          </div>

<br>          <br>

          <!--Body-->
          <div id="hospital">
          
<c:forEach var="hospital" items="${hospital}" >
          
          <div class="md-form">
            <i class="fas fa-user prefix grey-text"></i>
            <input type="text" id="form-name" value="${hospital.hname}" class="form-control" readonly>${hospital.hname}</input>
            <label for="form-name">Hospital name</label>
          </div>

          <div class="md-form">
            <i class="fas fa-envelope prefix grey-text"></i>
            <input type="text" id="form-email" class="form-control">
            <label for="form-email">City</label>
          </div>

          <div class="md-form">
            <i class="fas fa-tag prefix grey-text"></i>
            <input type="text" id="form-Subject" class="form-control">
            <label for="form-Subject">State</label>
          </div>

          <div class="md-form">
            <i class="fas fa-pencil-alt prefix grey-text"></i>
            <textarea id="form-text" class="form-control md-textarea" rows="3"></textarea>
            <label for="form-text">Address</label>
          </div>
            <div class="md-form">
            <i class="fas fa-pencil-alt prefix grey-text"></i>
            <textarea id="form-text" class="form-control md-textarea" rows="3"></textarea>
            <label for="form-text">Ratings</label>
          </div>

         </c:forEach>
</div>
        </div>

      </div>
      <!--Form with header-->

    </div>
    <!--Grid column-->

    <!--Grid column-->
    <div class="col-lg-7">

      <!--Google map-->
       <div id="map-canvas" class="z-depth-1-half map-container-6" style="width: 95%; height: 400px;"></div>

      </div>

      <br>
     
    </div>
    <!--Grid column-->


</section>
<!--Section: Contact v.1-->

</body>
</html>