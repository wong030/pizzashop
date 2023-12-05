/**
 * 
 */

let PIZZALIST = ["Margherita", "Prosciutto", "Diavolo", "Fungi"];
let TOPPINGS = {
	'Margherita': 'Cheese, Tomato Sauce',
	'Prosciutto': 'Cheese, Tomato Sauce, Ham',
	'Diavolo': 'Cheese, Tomato Sauce, Pepperoni sausage, Onions, Peppers, Chili peppers, Garlic',
	'Fungi': 'Cheese, Tomato sauce, Mushrooms'
}

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
});

function pizzaClicked(event) {
	console.log('pizzaClicked');
	console.log(event);
	document.getElementById('pizzaDetails').removeAttribute('hidden');
	let name = event.target.innerHTML;
	document.getElementById('pizzaDetailsName').innerText = name;
	document.getElementById('pizzaDetailsToppings').innerText = TOPPINGS[name];
	document.getElementById('pizzaDetailsSize').innerText = 'Klein Mittel Groß';
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