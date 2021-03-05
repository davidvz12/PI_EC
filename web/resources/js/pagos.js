/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


src="https://www.paypal.com/sdk/js?client-id=AYkI84ClZeHfKRdIGWY8gv1xMi0936vz9bHWKac1yB2h76sVqTsVjk_whwuidBNNSgLnVU2dGTbBkjm2">
paypal.Buttons({
    createOrder: function(data, actions) {
      return actions.order.create({
        purchase_units: [{
          amount: {
            value: document.getElementById("monto").value
          }
        }]
      });
    },
    onApprove: function(data, actions) {
      return actions.order.capture().then(function(details) {
         document.getElementById('guardarbd').click();
        alert('Transacción exitosa');
          //location.replace("http://localhost:8080/Pi8/faces/inicioSt.xhtml")
      });
    }
  }).render('#paypal-button-container');
  
  
  
   function initMap() {
                var myLatlng = {lat: -1.028453, lng: -79.467503};

                var map = new google.maps.Map(
                        document.getElementById('map'), {zoom: 12, center: myLatlng});

                // Create the initial InfoWindow.
                var infoWindow = new google.maps.InfoWindow(
                        {content: 'Click the map to get Lat/Lng!', position: myLatlng});
                infoWindow.open(map);

                // Configure the click listener.
                map.addListener('click', function (mapsMouseEvent) {
                    // Close the current InfoWindow.
                    infoWindow.close();

                    // Create a new InfoWindow.
                    infoWindow = new google.maps.InfoWindow({position: mapsMouseEvent.latLng});
                    infoWindow.setContent(mapsMouseEvent.latLng.toString());
                    infoWindow.open(map);
                });
            }

