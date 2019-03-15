var selectedFile;
$("#files-upload").on("change", function(event){
	selectedFile = event.target.files[0];
});

var name,size,Category,finish,price,App,image,number;

function callTile()
{
	//Take Data from User
	name = document.getElementById('name').value;
	size = document.getElementById('size').value;
	Category = document.getElementById('Category').value;
	finish = document.getElementById('finish').value;
	price = document.getElementById('price').value;
	App = document.getElementById('App').value;
	image = document.getElementById('files-upload').value;
	number = document.getElementById('number').value;
	doCheck();
}

function doCheck()
{
	//Retrive Data to check if tile already exist
	var rootRef = firebase.database().ref().child('Products/Tiles/'+Category);
	rootRef.once('value').then(function(data){
		if(data.child(name).exists())
			alert("Tile Exists");
		else
			doImageEntry();
		
	});
}
	
function doImageEntry()
{
	//Image Entry
	var filename = selectedFile.name;
	var storageRef = firebase.storage().ref('Products/Tiles/'+Category+'/'+name)
	storageRef.put(selectedFile).then(function(result){
		storageRef.getDownloadURL().then(function(url){
			image=url;
			doDataEntry();
		});
	});
}

function doDataEntry()
{
	//Database Entry
	var database = firebase.database();
	var tile = database.ref('Products/Tiles/'+Category+'/'+name);
	
	var data = {
		Dimension: size,
		Finish: finish,
		Price: price,
		Applications: App,
		Image:image,
		Pieces:number
	}
	tile.set(data);
	alert("Tile Added Successfully");	
}
