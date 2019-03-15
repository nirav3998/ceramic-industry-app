
function updateTile()
{
	var Category = document.getElementById("Category").value;
	var name = document.getElementById("name").value;
	var storageRef = firebase.database().ref().child('Products/Tiles/'+Category);
	storageRef.once('value').then(function(data){
		if(data.child(name).exists())
		{
			var imageRef = firebase.storage().ref('Products/Tiles/'+Category+'/'+name)
			imageRef.delete().then(function(){
				storageRef = firebase.database().ref().child('Products/Tiles/'+Category+'/'+name);
				storageRef.remove();
				location.href="file:///C:/Users/Raj/OneDrive/Desktop/edit/Final_Tiles.html";
			});
		}
		else
			alert("Tile doesn't exists");
		
	});
}

function deleteTile()
{
	var Category = document.getElementById("Category").value;
	var name = document.getElementById("name").value;
	var storageRef = firebase.database().ref().child('Products/Tiles/'+Category);
	storageRef.once('value').then(function(data){
		if(data.child(name).exists())
		{
			var imageRef = firebase.storage().ref('Products/Tiles/'+Category+'/'+name)
			imageRef.delete().then(function(){
				storageRef = firebase.database().ref().child('Products/Tiles/'+Category+'/'+name);
				storageRef.remove();
			});
			alert("Tile Deleted Successfully");
		}
		else
			alert("Tile doesn't exists");
		
	});
}