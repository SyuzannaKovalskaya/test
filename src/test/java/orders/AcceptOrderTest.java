package orders;

import dto.DtoCourier;
import dto.DtoOrder;
import dto.DtoOrders;
import io.restassured.response.Response;
import org.junit.Test;
import requests.CourierRequests;
import requests.OrderRequests;

import static org.hamcrest.Matchers.equalTo;

public class AcceptOrderTest extends BaseOrdersTest{
    @Test
    public void acceptOrderTest() {
        String login = String.format("Alex%d", (int) (Math.random() * (9999 - 1111) + 1111));
        String password = "12345";
        DtoCourier dtoCourier = new DtoCourier(login, password, "Alexey");
        CourierRequests.createCourier(dtoCourier);
        dtoCourier = new DtoCourier(login, password);
        Response responseCourier = CourierRequests.loginCourier(dtoCourier);
        dtoCourier = responseCourier.as(DtoCourier.class);

        DtoOrder order = new DtoOrder();
        Response response = OrderRequests.createOrder(order);
        order = response.as(DtoOrder.class);
        response = OrderRequests.getOrderByNumber(order);
        response.then().assertThat()
                .statusCode(200);
        DtoOrders dtoOrders = response.as(DtoOrders.class);

        response = OrderRequests.acceptOrder(dtoOrders.getOrder(), dtoCourier);
        response.then().assertThat()
                .statusCode(200)
                .and()
                .body("ok", equalTo(true));

        response = CourierRequests.deleteCourier(dtoCourier);
        response.then().assertThat()
                .statusCode(200)
                .and()
                .body("ok", equalTo(true));
    }
}
