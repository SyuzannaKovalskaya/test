package orders;

import dto.DtoOrder;
import io.restassured.response.Response;
import org.junit.Test;
import requests.OrderRequests;

import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderTest extends BaseOrdersTest{

    @Test
    public void createOrderTest() {
        DtoOrder order = new DtoOrder();
        Response response = OrderRequests.createOrder(order);
        response.then().assertThat()
                .statusCode(201)
                .and()
                .body("track", notNullValue());
    }
}
