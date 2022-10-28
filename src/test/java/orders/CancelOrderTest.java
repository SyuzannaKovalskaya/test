package orders;

import dto.DtoOrder;
import io.restassured.response.Response;
import org.junit.Test;
import requests.OrderRequests;

import static org.hamcrest.Matchers.equalTo;

public class CancelOrderTest extends BaseOrdersTest {

    @Test
    public void cancelOrderTest() {
        DtoOrder order = new DtoOrder();
        Response response = OrderRequests.createOrder(order);
        order = response.as(DtoOrder.class);
        response = OrderRequests.cancelOrder(order);
                response.then().assertThat()
                .statusCode(200)
                        .and()
                .body("ok", equalTo("true"));
    }
}
