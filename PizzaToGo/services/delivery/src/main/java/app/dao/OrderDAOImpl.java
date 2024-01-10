package app.dao;

import javax.enterprise.inject.Model;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import app.api.dto.CreateOrderDTO;
import app.model.Order;

@Named
@Model
public class OrderDAOImpl implements OrderDAO {

	@PersistenceContext(name = "jpa-unit")
	 EntityManager em;

	

	@Override
	@Transactional
	public Order createOrder(CreateOrderDTO createData) {

		
		Order order = new Order();
		order.setUserId(createData.getUserId());
        order.setStatus("In Queue");
        order.setPizza(createData.getPizzaId());
		order.setPrice(createData.getPrice());
		order.setSize(createData.getSize());
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