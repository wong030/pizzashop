package app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import app.api.dto.CreateOrderDTO;
import app.model.Order;
import app.model.User;

public class OrderDAOImpl implements OrderDAO {

	@PersistenceContext(name = "jpa-unit")
	private EntityManager em;

	

	@Override
	@Transactional
	public Order createOrder(CreateOrderDTO createData) {

		User createdUser = em.find(User.class, createData.getUserId());
		Order order = new Order();
		order.setUser(createdUser);
        order.setStatus("In Queue");
        order.setPizza(createData.getPizzaId());
        order.setIngredient1Id(createData.getIngredient1Id());
        order.setIngredient2Id(createData.getIngredient2Id());
        order.setIngredient3Id(createData.getIngredient3Id());
        order.setIngredient4Id(createData.getIngredient4Id());
        order.setIngredient5Id(createData.getIngredient5Id());
        order.setIngredient6Id(createData.getIngredient6Id());
        order.setIngredient7Id(createData.getIngredient7Id());
        order.setIngredient8Id(createData.getIngredient8Id());
		

		em.persist(order);
		em.flush();
		em.refresh(order);

		return order;
	}

}