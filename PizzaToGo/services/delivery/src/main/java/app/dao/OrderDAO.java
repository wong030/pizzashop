package app.dao;

import app.api.dto.CreateOrderDTO;
import app.model.Order;


public interface OrderDAO {

    Order createOrder(CreateOrderDTO createData);

    

}