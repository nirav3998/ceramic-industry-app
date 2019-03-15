
function orderList()
{
	var rootRef = firebase.database().ref("orders");
	rootRef.once("value").then(function(snapshot) {
    	snapshot.forEach(function(childSnapshot){ 
    			var key = childSnapshot.key;
    			//alert(key);
    			childSnapshot.forEach(function(grandChild){
    				var status=0;
    				var childData = grandChild.key;
    			//alert(childData);
    			//document.getElementById('s').innerHTML += "username : "+key+"<br>"+"Order ID : "+childData+"<br>";
    			grandChild.forEach(function(ggChild){
    				var item = ggChild.key;
    				var value = ggChild.val();
    				//alert(ggg);
    				//alert(gg);
    				if(value=="pending" && status==0)
    				{
    					status = 1;
    				}	
   					else if(status==1)
    					document.getElementById('s').innerHTML += item+" : "+value+"<br>";
    			});
    			if(status==1)
    			{
    				document.getElementById('s').innerHTML += "username : "+key+"<br>"+"Order ID : "+childData+"<br>";
    				document.getElementById('s').innerHTML += "<hr class='style18'>";
    			}	
    		});
    	});
  	});
}

window.onload = function() 
{
    orderList();
};