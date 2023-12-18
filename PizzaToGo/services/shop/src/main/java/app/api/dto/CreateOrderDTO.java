package app.api.dto;

public class CreateOrderDTO {

    private int userId, pizzaId,
            ingredient1Id,
            ingredient2Id,
            ingredient3Id,
            ingredient4Id,
            ingredient5Id,
            ingredient6Id,
            ingredient7Id,
            ingredient8Id;
    private String size;
    private double price;

    public CreateOrderDTO() {

    }

    public CreateOrderDTO(int userId, int pizzaId, String size, double price, int ingredient1Id, int ingredient2Id,
            int ingredient3Id,
            int ingredient4Id, int ingredient5Id, int ingredient6Id, int ingredient7Id, int ingredient8Id) {
        this.userId = userId;
        this.pizzaId = pizzaId;
        this.price = price;
        this.size = size;
        this.ingredient1Id = ingredient1Id;
        this.ingredient2Id = ingredient2Id;
        this.ingredient3Id = ingredient3Id;
        this.ingredient4Id = ingredient4Id;
        this.ingredient5Id = ingredient5Id;
        this.ingredient6Id = ingredient6Id;
        this.ingredient7Id = ingredient7Id;
        this.ingredient8Id = ingredient8Id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIngredient1Id() {
        return ingredient1Id;
    }

    public void setIngredient1Id(int ingredient1Id) {
        this.ingredient1Id = ingredient1Id;
    }

    public int getIngredient2Id() {
        return ingredient2Id;
    }

    public void setIngredient2Id(int ingredient2Id) {
        this.ingredient2Id = ingredient2Id;
    }

    public int getIngredient3Id() {
        return ingredient3Id;
    }

    public void setIngredient3Id(int ingredient3Id) {
        this.ingredient3Id = ingredient3Id;
    }

    public int getIngredient4Id() {
        return ingredient4Id;
    }

    public void setIngredient4Id(int ingredient4Id) {
        this.ingredient4Id = ingredient4Id;
    }

    public int getIngredient5Id() {
        return ingredient5Id;
    }

    public void setIngredient5Id(int ingredient5Id) {
        this.ingredient5Id = ingredient5Id;
    }

    public int getIngredient6Id() {
        return ingredient6Id;
    }

    public void setIngredient6Id(int ingredient6Id) {
        this.ingredient6Id = ingredient6Id;
    }

    public int getIngredient7Id() {
        return ingredient7Id;
    }

    public void setIngredient7Id(int ingredient7Id) {
        this.ingredient7Id = ingredient7Id;
    }

    public int getIngredient8Id() {
        return ingredient8Id;
    }

    public void setIngredient8Id(int ingredient8Id) {
        this.ingredient8Id = ingredient8Id;
    }

}
