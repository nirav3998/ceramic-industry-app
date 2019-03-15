function logIn() 
{
	//alert("Working3");
      if (firebase.auth().currentUser) 
      {
        firebase.auth().signOut();
      } 
      else 
      {
        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        if (username.length < 1) 
        {
          alert('Please enter an username .');
          return;
        }
        if (password.length < 1) 
        {
          alert('Please enter a password.');
          return;
        }
        // Sign in with email and pass.
        firebase.auth().signInWithEmailAndPassword(username, password).catch(function(error) 
        {
          // Handle Errors here.
          var errorCode = error.code;
          var errorMessage = error.message;
          // [START_EXCLUDE]
          if (errorCode === 'auth/wrong-password') 
          {
            alert('Wrong password.');
          } 
          else 
          {
            alert('User Not Exist.');
          }
          console.log(error);
          document.getElementById('loginBtn').disabled = false;
        });
      }
      document.getElementById('loginBtn').disabled = true;
}




function initApp() 
{
	//alert("Working");
      firebase.auth().onAuthStateChanged(user => 
      {
      	//alert("Working2");
        if (user) 
        {
          location.href="file:///C:/Users/Raj/OneDrive/Desktop/edit/home.html";
        }
        else 
        {
          document.getElementById('loginBtn').textContent = 'Login';
        }
        document.getElementById('loginBtn').disabled = false;
      });
      document.getElementById('loginBtn').addEventListener('click', logIn, false);
    }
    window.onload = function() 
    {
      initApp();
    };

    window.onbeforeunload = function()
    {
      firebase.auth().signOut()
    };
