<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?"></script>
<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	var im = 'https://i.stack.imgur.com/VpVF8.png';
	if('geolocation' in navigator){
		var lat;
		var lon;
		navigator.geolocation.getCurrentPosition(function(position){
			console.log(position);
			this.lat = position.coords.latitude;
			this.lon = position.coords.longitude;
		})
        
		var latitude = [
			<c:forEach var="s" items="${hospital}">
				<c:out value="${s.latitude}"/>,
			</c:forEach>
			
		];
		
		var longitude = [
			<c:forEach var="s" items="${hospital}">
				<c:out value="${s.longitude}"/>,
			</c:forEach>
		];
		
		let name = [
			<c:forEach var="s" items="${hospital}">
				"<c:out value="${s.hname}"/>",
			</c:forEach>
		];
		
		let address = [
			<c:forEach var="s" items="${hospital}">
				"<c:out value="${s.city}, ${s.state}"/>",
			</c:forEach>
		];
		
		
        function initialize() {
            var map;
            var i;
            var marker1;
//             const directionsRenderer = new google.maps.DirectionsRenderer();
//             const directionsService = new google.maps.DirectionsService();
			var myLatLng = new google.maps.LatLng(this.lat, this.lon);
            var mapOptions = {
                zoom: 12,
                center: myLatLng,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            const marker = new google.maps.Marker({
                position: myLatLng,
                map: map,
                animation: google.maps.Animation.DROP,
                icon: im
            });
            var infowindow = new google.maps.InfoWindow(); 
            infowindow.setContent("Your Location");
            infowindow.open(map, marker);
            
//             directionsRenderer.setMap(map);
//             calculateAndDisplayRoute(directionsService, directionsRenderer);
//             document.getElementById("mode").addEventListener("change", () => {
//               calculateAndDisplayRoute(directionsService, directionsRenderer);
//             });
			
//             function calculateAndDisplayRoute(directionsService, directionsRenderer) {
//   			  const selectedMode = document.getElementById("mode").value;
  			  
  			for(i=0; i<latitude.length; i++) 
            {
         	   marker1 = new google.maps.Marker({
                    position: new google.maps.LatLng(latitude[i], longitude[i]),
                    map: map,
                    animation: google.maps.Animation.DROP
                });
         	   
         	   google.maps.event.addListener(marker1, 'click', (function(marker1, i) {
                    return function() {
                        infowindow.setContent('<h4><strong> ' + name[i] + '<strong></h4><strong>' + address[i] + '</strong>');
                        infowindow.open(map, marker1);
                    }
                })(marker1, i));
            
//          	  directionsService.route(
//         			    {
//         			      origin: new google.maps.LatLng(this.lat, this.lon),
//         			      destination: new google.maps.LatLng(latitude[i],longitude[i]),
        			      
//         			      travelMode: google.maps.TravelMode[selectedMode],
//         			    },
//         			    (response, status) => {
//         			      if (status == "OK") {
//         			        directionsRenderer.setDirections(response);
//         			      } else {
//         			        window.alert("Directions request failed due to " + status);
//         			      }
//         			    }
//         			  );
         	   
            }
  			  
  			  
//   			}
  		
          }
                   
            }
		
		
	
	else{
		console.log("Geolocation not available");
	}

        google.maps.event.addDomListener(window, 'load', initialize);

    $(document).ready(function() {
		$('button[type="submit"]').attr('disabled', true);
		$('input[type="text"]').on('keyup', function(){
			if($(this).val == ""){
				$('button[type="submit"]').attr('disabled', true);
			}
			else{
				$('button[type="submit"]').attr('disabled', false);
			}
		});
    });
    </script>

<style>
body {
	margin-top: 20px;
	background: #eee;
}

.btn {
	margin-bottom: 5px;
}

.grid {
	position: relative;
	width: 100%;
	background: #fff;
	color: #666666;
	border-radius: 2px;
	margin-bottom: 25px;
	box-shadow: 0px 1px 4px rgba(0, 0, 0, 0.1);
}

.grid .grid-body {
	padding: 15px 20px 15px 20px;
	font-size: 0.9em;
	line-height: 1.9em;
}

.search table tr td.rate {
	color: #f39c12;
	line-height: 50px;
}

.search table tr:hover {
	cursor: pointer;
}

.search table tr td.image {
	width: 50px;
}

.search table tr td img {
	width: 50px;
	height: 50px;
}

.search table tr td.rate {
	color: #f39c12;
	line-height: 50px;
}

.search table tr td.price {
	font-size: 1.5em;
	line-height: 50px;
}

.search #price1, .search #price2 {
	display: inline;
	font-weight: 600;
}

#map-canvas {
	height: 600px;
	weight: 600px;
	margin: 0px;
	padding: 0px
}

#floating-panel {
	position: absolute;
	top: 10px;
	left: 25%;
	z-index: 5;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
	text-align: center;
	font-family: "Roboto", "sans-serif";
	line-height: 30px;
	padding-left: 10px;
}

.product {
	width: 20%;
}
</style>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<br>
	<div class="container">
		
		<!-- Search Bar -->
	
		<form method="get" action="search" class="example">
			<div class="input-group">
				<input type="text" name="keyword" class="form-control"
					placeholder="Search by hospital name, address, city, state, country">
				<span class="input-group-btn"><button class="btn btn-primary"
						type="submit">
						<i class="fa fa-search"></i>
					</button></span>
			</div>
		</form>
		<br>
		
		<!-- BEGIN FILTERS -->
		
		<div align="center">
			<form method="get" action="uploadFilterForm">
				<select id="city" name="city"
					style="padding: 10px; margin-top: -6px; border: 0; border-radius: 2px; border: 1px solid grey; background: #f1f1f1;">
					<option value="defaultCity" selected>Select a city:</option>
					<option value="Mumbai">Mumbai</option>
					<option value="Delhi">Delhi</option>
				</select> <select id="ratings" name="ratings"
					style="padding: 10px; margin-top: -6px; border: 0; border-radius: 2px; border: 1px solid grey; background: #f1f1f1;">
					<option value="0" selected>Select ratings:</option>
					<option value="5">5 star</option>
					<option value="4">4 star</option>
					<option value="3">3 star</option>
					<option value="2">2 star</option>
					<option value="1">1 star</option>
				</select> <select id="state" name="state"
					style="padding: 10px; margin-top: -6px; border: 0; border-radius: 2px; border: 1px solid grey; background: #f1f1f1;">
					<option value="defaultState" selected>Select state:</option>
					<option value="Maharashtra">Maharashtra</option>
					<option value="Punjab">Punjab</option>
					<option value="Haryana">Haryana</option>
					<option value="Karnataka">Karnataka</option>
					<option value="TamilNadu">Tamil Nadu</option>
				</select> <select id="ratingString" name="ratingString"
					style="padding: 10px; margin-top: -6px; border: 0; border-radius: 2px; border: 1px solid grey; background: #f1f1f1;">
					<option value="defaultRatingString" selected>Sort Rating
						By:</option>
					<option value="HighToLow">High To Low</option>
					<option value="LowToHigh">Low To High</option>
				</select> <input type="submit" value="Apply"
					style="width: 100px; padding: 10px; background: #40a829; color: white; font-size: 17px; border: 1px solid grey; border-radius: 2px; border-left: none; cursor: pointer">
			</form>
		</div>
	</div>
	<div class="row">
	
		<!-- BEGIN SEARCH RESULT -->
	
		<div class="col-md-12">
			<div class="grid search">
				<div class="grid-body">
					<div class="row">
						<div class="col-md-5">
							<h2>
								<i class="fa fa-file-o"></i> Result
							</h2>
							<hr>
							<div class="table-responsive">
								<c:if test="${empty hospital}">
									<h5 style="color: red">No hospitals found!</h5>
								</c:if>
								<c:if test="${not empty hospital}">
									<c:forEach var="hospital" items="${hospital}" varStatus="i">
										<table class="table table-hover">
											<tbody>
												<tr>
													<td class="number text-center">${hospital.hosp_id}</td>
													<td class="product"><strong>${hospital.hname}</strong><br>${hospital.description}</td>
													<td class="state text-center">${hospital.city},
														${hospital.state}</td>
													<td class="rate"><script type="text/javascript">
															    $(function () {
																	$("#rating${i.index}").rateYo({
																		rating: ${hospital.ratings},
																		readOnly: true
																	});
																});
															</script>
														<div id="rating${i.index}">
														</div>
													</td>
												</tr>
											</tbody>
										</table>
									</c:forEach>
								</c:if>
							</div>
							<!-- BEGIN RESULT -->
						</div>
						<div class="col-md-7">
							<h2>
								<i class="fa fa-map-marker"></i> Map
							</h2>
							<hr>
							<!-- <div id="floating-panel">
								<b>Mode of Travel: </b> <select id="mode">
									<option value="DRIVING">Driving</option>
									<option value="WALKING">Walking</option>
									<option value="BICYCLING">Bicycling</option>
									<option value="TRANSIT">Transit</option>
								</select>
							</div> -->
							<div id="map-canvas"></div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>