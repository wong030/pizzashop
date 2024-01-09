document.querySelector("#registrationButton").onclick = signUp;



//Registrierungsprozess
async function signUp(){
	
	
    let firstname = document.querySelector("#user-first-name").value;
    let lastname = document.querySelector("#user-last-name").value;
    let street = document.querySelector("#user-street").value;
    let housenumber = document.querySelector("#user-house-number").value;
    let postcode = document.querySelector("#user-postal-code").value;
    let city = document.querySelector("#user-city").value;
    let email = document.querySelector("#user-email").value;
    let username = document.querySelector("#user-username").value;
    let password = document.querySelector("#user-password").value;
    //Zwecks der Einfärbung bei falschen Eingaben und der damit erforderlichen Auswertung der Funktionen
    //--> multiplikative Verknüpfung statt &
   
    let user = await signUpAPI(firstname, lastname, street, housenumber, postcode, city, email, username, password);
    
    }
   

//Übertragen der Daten an die API und damit zur Datenbank
async function signUpAPI(firstname, lastname, street, housenumber, postcode, city, email, username, password) {
   let gridContainerMain = document.getElementById('grid-container-main');
   let createAccountContainer = document.getElementById('create-account-container');
   let loginContainer = document.getElementById('login-container');
   let loginButton = document.getElementById('login-button');
   
    let user = {
        userName: username,
        password: password,
        email: email,
        firstName: firstname,
        lastName: lastname,
        street: street,
        streetNr: housenumber,
        zip: postcode,
        city: city
    }
    let createdUser = null;
    const response = await fetch("http://localhost:9082/api/user", {
        method: 'POST',
       
        headers: {
			'Content-type':'application/json',
			'Access-Control-Allow-Origin': '*'
        },
        body : JSON.stringify(user)
        })
        .then(response => response.json())
        .then(data => createdUser = data)
        .catch((error) => {
            console.error('Error:', error);
        });
        
    gridContainerMain.style.display = 'grid';
	loginContainer.style.display = 'none';
	createAccountContainer.style.display = 'none'; 
	loginButton.innerText = "LOGIN";
	
    return createdUser;
}


