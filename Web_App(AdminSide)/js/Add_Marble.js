var selectedFile;
$("#files-upload").on("change", function(event){
	selectedFile = event.target.files[0];
});

var name,Category,price,image,number;

function callMarble()
{
	//Take Data from User
	name = document.getElementById('name').value;
	Category = document.getElementById('Category').value;
	price = document.getElementById('price').value;
	image = document.getElementById('files-upload').value;
	number = document.getElementById('number').value;
	doCheck();
}

function doCheck()
{
	//Retrive Data to check if tile already exist
	var rootRef = firebase.database().ref().child('Products/Marble/'+Category);
	rootRef.once('value').then(function(data){
		if(data.child(name).exists())
			alert("Marble Exists");
		else
			doImageEntry();
		
	});
}
	
function doImageEntry()
{
	//Image Entry
	var filename = selectedFile.name;
	var storageRef = firebase.storage().ref('Products/Marble/'+Category+'/'+name)
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
	var marble = database.ref('Products/Marble/'+Category+'/'+name);

	var data = {
		Price: price,
		Image:image,
		Pieces:number
	}
	marble.set(data);
	alert("Marble Added Successfully");	
}
