package app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "pizza", nullable = false)
    private int pizza;

    @Column(name = "ingredient1_id")
    private int ingredient1Id;

    @Column(name = "ingredient2_id")
    private int ingredient2Id;

    @Column(name = "ingredient3_id")
    private int ingredient3Id;

    @Column(name = "ingredient4_id")
    private int ingredient4Id;

    @Column(name = "ingredient5_id")
    private int ingredient5Id;

    @Column(name = "ingredient6_id")
    private int ingredient6Id;

    @Column(name = "ingredient7_id")
    private int ingredient7Id;

    @Column(name = "ingredient8_id")
    private int ingredient8Id;

    public Order(){

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPizza() {
        return pizza;
    }

    public void setPizza(int pizza) {
        this.pizza = pizza;
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

