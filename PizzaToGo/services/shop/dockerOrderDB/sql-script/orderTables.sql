CREATE TABLE IF NOT EXISTS pizzas (
    pizza_id int auto_increment primary key,
    pizza_name varchar(255) not null
)CHARACTER SET utf8mb4;

CREATE TABLE IF NOT EXISTS ingredients (
    ingredient_id int auto_increment primary key,
    ingredient_name varchar(255) not null
)CHARACTER SET utf8mb4;

CREATE TABLE IF NOT EXISTS orders (
    order_id int auto_increment primary key,
    user_id int not null,
    status varchar(50) not null,
    pizza int not null,
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
