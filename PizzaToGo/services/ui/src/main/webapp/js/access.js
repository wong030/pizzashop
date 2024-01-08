window.addEventListener("load", initAccess);

async function initAccess()
{
	
	document.querySelector("#login").onclick = login;
	
	

	accessToken = sessionStorage.getItem("token");
	let isLoggedIn = false;
	if(accessToken != null) {
		
	}

	
}

async function login(){
	let loginData = {
		username: document.querySelector("#user-loginName").value,
		password: document.querySelector("#user-loginPassword").value};
	await fetch("http://localhost:9082/api/access", {
		method: 'POST',
		headers: {'Content-type':'application/json'},
		body : JSON.stringify(loginData)
		})
		.then(response => {
			switch(response.status){
				case 200:
					
					return response.json();
				case 401:
					
					alert("Benutzer oder Passwort sind falsch!\nBitte erneut versuchen.");
					return null;
				case 409:
					alert("Der Benutzer ist bereits angemeldet");
					return null;
				default:
					alert("Ein Fehler ist aufgetreten");
					return null;
			}
		})
		.then(data => {
			if(data != null){
				console.log(data);
				accessToken = data.token;
				sessionStorage.setItem("token", accessToken);
				user = data.username;
				
			}
		})
		.catch((error) => {
			console.error('Error:', error);
		});
}

function logout(){
	fetch("data/access", {
		method: 'DELETE',
		headers: {'token':accessToken}})
		.then(response => {
			if (response.ok){
				showLogin();
			}
		})
		.catch((error) => {
			console.error('Error:', error);
		});
}
