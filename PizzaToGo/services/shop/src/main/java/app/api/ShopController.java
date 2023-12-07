package app.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import app.api.dto.CreateOrderDTO;
import app.dao.OrderDAO;
import app.model.Order;
import app.services.AuthenticationService;

@Path("/shop")
public class ShopController {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private OrderDAO orderDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response placeOrder(@HeaderParam("token") CreateOrderDTO createData) {

        // Authentication request to User-Management
        try {
            boolean response = authenticationService.authenticateUser(createData.getUserId());
            if (!response) {
                // redirect to login/register window
                return Response.status(404, "Not logged in").build();
            } else {
                final Order createdOrder = orderDAO.createOrder(createData);
                System.out.println();
                System.out.println("Created Rating: " + createdOrder);

                return Response.ok().build();

            }
        } catch (IOException e) {
            
            e.printStackTrace();
            return Response.serverError().build();

        } catch (InterruptedException e) {

            e.printStackTrace();
            return Response.serverError().build();
        }

    }

    /*
     * ????????Fetch
     * 
     * 
     * public String configurePizza(){
     * 
     * 
     * }
     */
}
