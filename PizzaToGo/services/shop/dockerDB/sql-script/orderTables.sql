CREATE TABLE IF NOT EXISTS ingredients (
    ingredient_id int primary key,
    ingredient_name varchar(255) not null
)CHARACTER SET utf8mb4;


INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (0, 'keine weitere Zutat');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (1, 'Tomato Sauce');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (2, 'Extra Cheese');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (3, 'Mozzarella');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (4, 'Salami');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (5, 'Pepperoni sausage');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (6, 'Ham');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (7, 'Barbecue Sauce');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (8, 'Peppers');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (9, 'Garlic');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (10, 'Onions');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (11, 'Mushrooms');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (12, 'Tuna');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (13, 'Gorgonzola');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (14, 'Rucola');
INSERT INTO ingredients (ingredient_id, ingredient_name) VALUES (15, 'Chili peppers');




CREATE TABLE IF NOT EXISTS pizzas (
    pizza_id int auto_increment primary key,
    pizza_name varchar(255) not null,
    price decimal(5,2) not null,
    ingredient1_id int not null,
    ingredient2_id int not null,
    ingredient3_id int not null,
    ingredient4_id int not null,
    ingredient5_id int not null,
    ingredient6_id int not null,
    ingredient7_id int not null,
    ingredient8_id int not null,
    FOREIGN KEY (ingredient1_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient2_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient3_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient4_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient5_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient6_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient7_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient8_id) references ingredients(ingredient_id)
)CHARACTER SET utf8mb4;


INSERT INTO pizzas (pizza_name, price, ingredient1_id, ingredient2_id, ingredient3_id, ingredient4_id, ingredient5_id, ingredient6_id, ingredient7_id, ingredient8_id) VALUES ('Margherita', 9.95, 1, 3, 0, 0, 0, 0, 0, 0);
INSERT INTO pizzas (pizza_name, price, ingredient1_id, ingredient2_id, ingredient3_id, ingredient4_id, ingredient5_id, ingredient6_id, ingredient7_id, ingredient8_id) VALUES ('Prosciutto', 11.95, 1, 2, 6, 0, 0, 0, 0, 0);
INSERT INTO pizzas (pizza_name, price, ingredient1_id, ingredient2_id, ingredient3_id, ingredient4_id, ingredient5_id, ingredient6_id, ingredient7_id, ingredient8_id) VALUES ('Diavolo', 13.95, 1, 2, 5, 10, 8, 15, 9, 0);
INSERT INTO pizzas (pizza_name, price, ingredient1_id, ingredient2_id, ingredient3_id, ingredient4_id, ingredient5_id, ingredient6_id, ingredient7_id, ingredient8_id) VALUES ('Fungi', 11.95, 1, 2, 11, 0, 0, 0, 0, 0);




CREATE TABLE IF NOT EXISTS orders (
    order_id int auto_increment primary key,
    user_id int not null,
    status varchar(50) not null,
    pizza int not null,
    size varchar(50) not null,
    price decimal(6,2) not null,
    ingredient1_id int not null,
    ingredient2_id int not null,
    ingredient3_id int not null,
    ingredient4_id int not null,
    ingredient5_id int not null,
    ingredient6_id int not null,
    ingredient7_id int not null,
    ingredient8_id int not null,
    FOREIGN KEY (pizza) references pizzas(pizza_id),
    FOREIGN KEY (ingredient1_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient2_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient3_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient4_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient5_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient6_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient7_id) references ingredients(ingredient_id),
    FOREIGN KEY (ingredient8_id) references ingredients(ingredient_id)
)CHARACTER SET utf8mb4;
