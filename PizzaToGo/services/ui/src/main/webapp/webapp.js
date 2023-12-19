/**
 * 
 */

let PIZZALIST = ["Margherita", "Prosciutto", "Diavolo", "Fungi"];
let PIZZALIST_TOPPINGS = {
	'Margherita': 'Cheese, Tomato Sauce',
	'Prosciutto': 'Cheese, Tomato Sauce, Ham',
	'Diavolo': 'Cheese, Tomato Sauce, Pepperoni sausage, Onions, Peppers, Chili peppers, Garlic',
	'Fungi': 'Cheese, Tomato sauce, Mushrooms'
}
let EXTRA_TOPPINGS = ["Extra Cheese", "Mozzarella", "Salami", "Pepperoni sausage", "Ham", "Barbecue Sauce", "Peppers", "Garlic", "Onions", "Mushrooms", "Tuna", "Gorgonzola", "Rucola"];
let SIZES = ["Small (&#8960 26cm)", "Medium (&#8960 30cm)", "Large (&#8960 36cm)"]

document.addEventListener("DOMContentLoaded", function(event) {
	let list = document.getElementById('Pizzaliste');
	PIZZALIST.forEach(function(pizza) {
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
	EXTRA_TOPPINGS.forEach(function(topping) {
		let toppingLabel = document.createElement('label');
		toppingLabel.className = 'pizzaToppingsContainer';
		toppingLabel.innerText = topping;
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
	label.appendChild(radioButton);
	let sizeRadioButton = document.createElement('span');
	sizeRadioButton.className = "sizeRadioButton";
	label.appendChild(sizeRadioButton);
}

function pizzaClicked(event) {
	document.getElementById('pizzaDetails').removeAttribute('hidden');
	let name = event.target.innerHTML;
	document.getElementById('pizzaDetailsName').innerText = name;
	document.getElementById('pizzaDetailsToppings').innerText = PIZZALIST_TOPPINGS[name];
	document.getElementById('pizzaName').value = name;
}

function addToCartButtonClicked(e) {

	const form = document.getElementById("pizzaForm");
	const formData = new FormData(form);

	let name = document.getElementById('pizzaName').value;
	formData.append("pizzaName", name);

	for (const key of formData.keys()) {
		console.log(key + ', ' + formData.get(key));
	}
	
}

function homeClicked(e) {
	location.reload();	
}

function loginButtonClicked() {
	console.log('loginButtonClicked');
	let gridContainerMain = document.getElementById('grid-container-main');
	if (gridContainerMain.getAttribute('style') == null || gridContainerMain.style.display == 'grid') {
		gridContainerMain.style.display = 'none';
	} else if (gridContainerMain.style.display == 'none') {
		gridContainerMain.style.display = 'grid';
	}
}