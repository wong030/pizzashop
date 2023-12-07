package app.dao;

import app.api.dto.CreateOrderDTO;
import app.model.Order;

public interface OrderDAO {

    public Order createOrder(CreateOrderDTO createData);

}