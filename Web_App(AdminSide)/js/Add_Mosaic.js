var selectedFile;
$("#files-upload").on("change", function(event){
	selectedFile = event.target.files[0];
});

var name,thickness,price,image;

function callMosaic()
{
	name = document.getElementById('name').value;
	thickness = document.getElementById('thickness').value;
	price = document.getElementById('price').value;
	image = document.getElementById('files-upload').value;
	doCheck();
 }
function doCheck()
{
	//Retrive Data to check if tile already exist
	var rootRef = firebase.database().ref().child('Products/Mosaic');
	rootRef.once('value').then(function(data){
		if(data.child(name).exists())
			alert("Mosaic Exists");
		else
			doImageEntry();
	});
}

function doImageEntry()
{
	//Image Entry
	var filename = selectedFile.name;
	var storageRef = firebase.storage().ref('Products/Mosaic/'+name);
	storageRef.put(selectedFile).then(function(result){
		storageRef.getDownloadURL().then(function(url){
			image=url;
			doDataEntry();
		});
	});
}

function doDataEntry()
{
	var database = firebase.database();
	var mosaic = database.ref('Products/Mosaic/'+name);
	var data = {
		Price: price,
		Image:image
	}
	mosaic.set(data);
	alert("Mosaic Added Successfully");
}
