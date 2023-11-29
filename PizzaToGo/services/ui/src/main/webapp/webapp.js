/**
 * 
 */

let Pizzaliste = ["Margherita", "Prosciutto", "Diavolo", "Tuna"];

document.addEventListener("DOMContentLoaded", function(event) {
	let list = document.getElementById('Pizzaliste');
	Pizzaliste.forEach(function(pizza) {
		let pizzaContainer = document.createElement('div');
		pizzaContainer.className = 'card';
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
	document.getElementById('pizzaDetails').removeAttribute('hidden');
}

function loginButtonClicked() {
	console.log('loginButtonClicked');
	let gridContainerMain = document.getElementById('grid-container-main');
	if (gridContainerMain.getAttribute('style') == null || gridContainerMain.style.display == 'grid'){
		gridContainerMain.style.display = 'none';
	} else if (gridContainerMain.style.display == 'none') {
		gridContainerMain.style.display = 'grid';
	}
}