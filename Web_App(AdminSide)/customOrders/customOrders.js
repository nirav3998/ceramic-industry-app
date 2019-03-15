
function demo()
{
  var rootRef = firebase.database().ref("customOrders");
  rootRef.once("value").then(function(snapshot) {
      snapshot.forEach(function(childSnapshot) {
        var key = childSnapshot.key;
        document.getElementById('s').innerHTML += "username : "+key+"<br>";
        childSnapshot.forEach(function(grandChild){
          var item = grandChild.key;
          var value = grandChild.val();
          if(item=="URL")
          {
              var text = "<a href=" + value + ">"+"Image"+"</a>";
              document.getElementById('s').innerHTML += text + "<br><br><br>";
          }
          else
            document.getElementById('s').innerHTML += item+" : "+value+"<br>";
      });
    });
  });
}