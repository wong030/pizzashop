/**
 * 
 */
let CHOSENPIZZASIZE = "Small (&#8960 26cm)";
let CHOSENPIZZATOPPINGS = [];
let CHOSENPIZZA = "";
let CHOSENPIZZATOPPINGSPRICE = 0;
let PIZZALIST = {
	'Margherita': {
		'toppings': 'Tomato sauce, Cheese',
		'price': [7.5, 9, 11]
	},
	'Prosciutto': {
		'toppings': 'Tomato sauce, Cheese, Ham',
		'price': [8.5, 10, 12]
	},
	'Diavolo': {
		'toppings': 'Tomato sauce, Cheese, Pepperoni sausage, Onions, Peppers, Chili peppers, Garlic',
		'price': [9.5, 11, 13]
	},
	'Fungi': {
		'toppings': 'Tomato sauce, Cheese, Mushrooms',
		'price': [8, 9.5, 11.5]
	}
}
let EXTRA_TOPPINGS = {
	'Tomato sauce': {
		'id': 1,
		'price': '0.50'
	},
	'Cheese': {
		'id': 2,
		'price': '0.50'
	},
	'Mozzarella': {
		'id': 3,
		'price': '0.50'
	},
	'Salami': {
		'id': 4,
		'price': '1.00'
	},
	'Pepperoni sausage': {
		'id': 5,
		'price': '1.00'
	},
	'Ham': {
		'id': 6,
		'price': '1.00'
	},
	'Barbecue Sauce': {
		'id': 7,
		'price': '1.00'
	},
	'Peppers': {
		'id': 8,
		'price': '1.00'
	},
	'Garlic': {
		'id': 9,
		'price': '0.50'
	},
	'Onions': {
		'id': 10,
		'price': '0.50'
	},
	'Mushrooms': {
		'id': 11,
		'price': '0.50'
	},
	'Tuna': {
		'id': 12,
		'price': '1.00'
	},
	'Gorgonzola': {
		'id': 13,
		'price': '1.00'
	},
	'Rucola': {
		'id': 14,
		'price': '0.50'
	},
	'Chili peppers': {
		'id': 15,
		'price': '0.50'
	}
}
let SIZES =
	[
		"Small (&#8960 26cm)",
		"Medium (&#8960 30cm)",
		"Large (&#8960 36cm)"
	]

document.addEventListener("DOMContentLoaded", function(event) {
	let list = document.getElementById('Pizzaliste');
	Object.keys(PIZZALIST).forEach(function(pizza) {
		let pizzaContainer = document.createElement('div');
		pizzaContainer.className = 'card responsive';
		pizzaContainer.addEventListener("click", pizzaClicked)

		let pizzaGridContainer = document.createElement('div');
		pizzaGridContainer.className = 'grid-container';

		let pizzaBildContainerGridItem = document.createElement('div');
		pizzaBildContainerGridItem.className = 'grid-item';
		let pizzaTextContainerGridItem = document.createElement('div');
		pizzaTextContainerGridItem.className = 'grid-item';

		let pizzaBildContainer = document.createElement('div');
		pizzaBildContainer.className = 'card';
		let pizzaTextContainer = document.createElement('div');
		pizzaTextContainer.className = 'card';

		pizzaBildContainerGridItem.appendChild(pizzaBildContainer);
		pizzaTextContainerGridItem.appendChild(pizzaTextContainer);

		let li = document.createElement('li');
		li.innerText = pizza;
		li.className = 'pizzaEintrag'
		pizzaTextContainer.appendChild(li);

		pizzaGridContainer.appendChild(pizzaBildContainer);
		pizzaGridContainer.appendChild(pizzaTextContainer);

		pizzaContainer.appendChild(pizzaGridContainer);
		list.appendChild(pizzaContainer);
	})
	let toppingsContainer = document.getElementById('pizzaExtraToppingsContainer');
	Object.keys(EXTRA_TOPPINGS).forEach(function(topping) {
		let toppingLabel = document.createElement('label');
		toppingLabel.className = 'pizzaToppingsContainer';
		let price = EXTRA_TOPPINGS[topping]['price'];
		toppingLabel.innerText = topping + " (" + price + "€)";
		addCustomCheckbox(toppingLabel, topping);
		toppingsContainer.appendChild(toppingLabel);
	})
	let sizeContainer = document.getElementById('pizzaDetailsSize');
	SIZES.forEach(function(size) {
		let sizeLabel = document.createElement('label');
		sizeLabel.className = 'pizzaSizeContainer';
		sizeLabel.innerHTML = size;
		addCustomRadioButton(sizeLabel, size);
		sizeContainer.appendChild(sizeLabel);
	})

});

function addCustomCheckbox(label, topping) {
	let checkbox = document.createElement('input');
	checkbox.type = "checkbox";
	checkbox.name = topping;
	checkbox.addEventListener('click', checkboxClicked);
	label.appendChild(checkbox);
	let toppingsCheckBox = document.createElement('span');
	toppingsCheckBox.className = "toppingsCheckBox";
	label.appendChild(toppingsCheckBox);
}

function addCustomRadioButton(label, size) {
	let radioButton = document.createElement('input');
	radioButton.type = "radio";
	radioButton.id = size;
	radioButton.name = "sizeRadioButton";
	radioButton.value = size;
	radioButton.addEventListener('click', radioButtonClicked);
	if (size == "Small (&#8960 26cm)") {
		radioButton.setAttribute('checked', true);
	}
	label.appendChild(radioButton);
	let sizeRadioButton = document.createElement('span');
	sizeRadioButton.className = "sizeRadioButton";
	label.appendChild(sizeRadioButton);
}

function radioButtonClicked(event) {
	CHOSENPIZZASIZE = event.target.value;
	refreshPrice();
}

function refreshPrice() {

	let sizeIndex = 0;
	SIZES.forEach(function(size, index) {
		if (size == CHOSENPIZZASIZE) {
			sizeIndex = index;
		}
	})

	let priceString = (PIZZALIST[CHOSENPIZZA]['price'][sizeIndex] + CHOSENPIZZATOPPINGSPRICE).toString();
	let price = priceString.includes('.') ? priceString + "0€" : priceString + ".00€";
	document.getElementById('pizzaDetailsPrice').innerText = price;

}

function checkboxClicked(event) {
	let topping = event.target.name;

	if (CHOSENPIZZATOPPINGS.includes(topping)) {
		CHOSENPIZZATOPPINGS.splice(CHOSENPIZZATOPPINGS.indexOf(topping), 1);
		CHOSENPIZZATOPPINGSPRICE = (CHOSENPIZZATOPPINGSPRICE - parseFloat(EXTRA_TOPPINGS[topping]['price']));
		refreshPrice();
	} else {
		if (CHOSENPIZZATOPPINGS.length < 8) {
			console.log('anzahl: ' + CHOSENPIZZATOPPINGS.length);
			console.log(CHOSENPIZZATOPPINGS);
			CHOSENPIZZATOPPINGS.push(topping);
			CHOSENPIZZATOPPINGSPRICE = (CHOSENPIZZATOPPINGSPRICE + parseFloat(EXTRA_TOPPINGS[topping]['price']));
			refreshPrice();
		} else if(CHOSENPIZZATOPPINGS.length >= 8){
			alert('You cannot add more than 8 toppings to your Pizza because of reasons!');
			document.getElementsByName(topping)[0].checked = false;
			
			console.log('now: ' + CHOSENPIZZATOPPINGS);
		}
	}
}

function pizzaClicked(event) {
	document.getElementById('pizzaDetails').removeAttribute('hidden');
	let name = event.target.innerHTML;
	CHOSENPIZZA = name;
	CHOSENPIZZATOPPINGS = PIZZALIST[name]['toppings'].split(', ');
	CHOSENPIZZATOPPINGS.forEach(function(topping){
		document.getElementsByName(topping)[0].checked = true;
	})
	document.getElementById('pizzaDetailsName').innerText = name;

	refreshPrice();
	document.getElementById('pizzaDetailsToppings').innerText = PIZZALIST[name]['toppings'];
	document.getElementById('pizzaName').value = name;
}

document.getElementById('loginForm').addEventListener("submit", function(event) {
	event.preventDefault();
})

document.getElementById('createAccountForm').addEventListener("submit", function(event) {
	event.preventDefault();
})



function addToCartButtonClicked(e) {

	let name = document.getElementById('pizzaName').value;
	let price = document.getElementById('pizzaDetailsPrice').innerText;
	price = parseFloat(price.slice(0, price.length - 2));
	
	let size = CHOSENPIZZASIZE.slice(0, 1).toLowerCase();
	let ingredients = [0,0,0,0,0,0,0,0];
	
	console.log(CHOSENPIZZATOPPINGS);
	CHOSENPIZZATOPPINGS.forEach(function(topping, index) {
		ingredients[index] = EXTRA_TOPPINGS[topping]['id'];
	})

	let order = {
		userId: id,
		pizzaId: name,
		price: price,
		size: size,
		ingredient1Id: ingredients[0],
		ingredient2Id: ingredients[1],
		ingredient3Id: ingredients[2],
		ingredient4Id: ingredients[3],
		ingredient5Id: ingredients[4],
		ingredient6Id: ingredients[5],
		ingredient7Id: ingredients[6],
		ingredient8Id: ingredients[7]
	}
	if (id == null) {
		alert('You have to be logged in to place an order!')
		return;
	}
	placeOrder(order);


}


async function placeOrder(order) {
	let createdOrder = null;

	const response = await fetch("http://localhost:9085/api/shop", {
		method: 'POST',
		headers: {
			'Content-type': 'application/json',
			'Access-Control-Allow-Origin': '*'
		},
		body: JSON.stringify(order)
	})
		.then(response => response.json())
		.then(data => createdOrder = data)
		.catch((error) => {
			console.error('Error:', error);
		});
		
	alert('Your Order' + order + 'was placed!');
}

function homeClicked(e) {
	location.reload();
}

function loginButtonClicked() {
	console.log('loginButtonClicked');
	let gridContainerMain = document.getElementById('grid-container-main');
	let createAccountContainer = document.getElementById('create-account-container');
	let loginContainer = document.getElementById('login-container');
	let loginButton = document.getElementById('login-button');
	if (gridContainerMain.getAttribute('style') == null || gridContainerMain.style.display == 'grid') {
		gridContainerMain.style.display = 'none';
		loginContainer.style.display = 'block';
		loginButton.innerText = 'BACK';
	} else if (gridContainerMain.style.display == 'none') {
		gridContainerMain.style.display = 'grid';
		loginContainer.style.display = 'none';
		createAccountContainer.style.display = 'none';
		loginButton.innerText = 'LOGIN';
	}
}

function login() {
	const form = document.getElementById("loginForm");
	const formData = new FormData(form);

	for (const key of formData.keys()) {
		console.log(key + ', ' + formData.get(key));
	}
}

function createAccountButtonClicked(e) {
	let loginContainer = document.getElementById('login-container');
	let createAccountContainer = document.getElementById('create-account-container');

	loginContainer.style.display = 'none';
	createAccountContainer.style.display = 'block';
}

function createAccount() {
	const form = document.getElementById("createAccountForm");
	const formData = new FormData(form);

	for (const key of formData.keys()) {
		console.log(key + ', ' + formData.get(key));
	}
}