window.addEventListener("load", initAccess);

let accessToken = null;
let user = null;
let id = null;

async function initAccess()
{
	
	document.querySelector("#login").onclick = login;
	document.querySelector("#logout-button").onclick =logout;
	let gridContainerMain = document.getElementById('grid-container-main');
	let createAccountContainer = document.getElementById('create-account-container');
	let loginContainer = document.getElementById('login-container');
	let loginButton = document.getElementById('login-button');
	let loginForm = document.getElementById('LoginInfo');
	let logoutForm = document.getElementById('LogoutInfo');
	let loginText = document.getElementById('loginText');
	

	accessToken = sessionStorage.getItem("token");
	
	let isLoggedIn = false;
	if(accessToken != null) {
		isLoggedIn = await checkLoginAPI();
	}

	if(isLoggedIn){
		loginContainer.style.display = "none";
		gridContainerMain.style.display = "grid";
		createAccountContainer.style.display = 'none';
		loginForm.style.display = "none";
		logoutForm.style.display = "block";
		loginText.innerHTML = "Willkommen, " + user;
	}
	else{
		
	}
}

async function checkLoginAPI() {
	await fetch("http://localhost:9082/api/access", {
		method: 'GET',
		headers: {
			'Content-type':'application/json',
			'token':accessToken}})
		.then(response => response.json())
		.then(data => {			
			accessToken = data.token;
			user = data.username;
			id = data.userId
			})
		.catch((error) => {
			console.error('Error:', error);
		});
	return user != null;
}

async function login(){
	
	let gridContainerMain = document.getElementById('grid-container-main');
	let createAccountContainer = document.getElementById('create-account-container');
	let loginContainer = document.getElementById('login-container');
	let loginButton = document.getElementById('login-button');
	let loginForm = document.getElementById('LoginInfo');
	let logoutForm = document.getElementById('LogoutInfo');
	let loginText = document.getElementById('loginText');
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
				id = data.userId;
				loginContainer.style.display = "none";
				gridContainerMain.style.display = "grid";
				createAccountContainer.style.display = 'none';
				loginForm.style.display = "none";
				logoutForm.style.display = "block";
				loginText.innerHTML = "Willkommen, " + user;
			}
		})
		.catch((error) => {
			console.error('Error:', error);
		});
}

function logout(){
	let gridContainerMain = document.getElementById('grid-container-main');
	let createAccountContainer = document.getElementById('create-account-container');
	let loginContainer = document.getElementById('login-container');
	let loginButton = document.getElementById('login-button');
	let loginForm = document.getElementById('LoginInfo');
	let logoutForm = document.getElementById('LogoutInfo');
	fetch("http://localhost:9082/api/access", {
		method: 'DELETE',
		headers: {'token':accessToken}})
		.then(response => {
			if (response.ok){
				loginForm.style.display = "block";
				logoutForm.style.display = "none";
				loginButton.innerText = "LOGIN";
				id = 0;
			}
		})
		.catch((error) => {
			console.error('Error:', error);
		});
}


