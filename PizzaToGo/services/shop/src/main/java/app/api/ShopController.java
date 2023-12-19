package app.api;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import app.api.dto.CreateOrderDTO;
import app.dao.OrderDAO;
import app.model.Order;

@Path("/shop")
@Singleton
public class ShopController {

    @Inject
    private OrderDAO orderDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/order")
    public Response placeOrder(CreateOrderDTO createData) {

        try {
            final Order createdOrder = orderDAO.createOrder(createData);
            System.out.println();
            System.out.println("Created Order: " + createdOrder);
            return Response.ok().entity(createdOrder).build();

        } catch (Exception e) {

            e.printStackTrace();
            return Response.serverError().build();

        }

    }

}
