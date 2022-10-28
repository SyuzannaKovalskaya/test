package courier;

import dto.DtoCourier;
import io.restassured.response.Response;
import org.junit.Test;
import requests.CourierRequests;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest extends BaseCourierTest{


    @Test
    public void createNewCourierTest() {
        String login = String.format("Alex%d", (int) (Math.random() * (9999 - 1111) + 1111));
        String password = "12345";
        DtoCourier courier = new DtoCourier(login, password, "Alexey");
        Response response = CourierRequests.createCourier(courier);
        response.then().assertThat()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));
        courier = new DtoCourier(login, password);
        loginAndDeleteCourier(courier);
    }
   @Test
    public void createNewCourierWithoutRequiredFieldsTest() {
        String login = String.format("Alex%d", (int) (Math.random() * (9999 - 1111) + 1111));
        String password = "12345";
        DtoCourier courier = new DtoCourier("", password, "Alexey");
        Response response = CourierRequests.createCourier(courier);
        response.then().assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

        courier = new DtoCourier(login, "", "Alexey");
        response = CourierRequests.createCourier(courier);
        response.then().assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

        courier = new DtoCourier(login, password, "");
        response = CourierRequests.createCourier(courier);
        response.then().assertThat()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));
    }


    @Test
    public void cantCreateTwoOwnersCouriersTest() {
        String login = String.format("Alex%d", (int) (Math.random() * (9999 - 1111) + 1111));
        String password = "12345";
        DtoCourier courier = new DtoCourier(login, password, "Alexey");
        Response response = CourierRequests.createCourier(courier);
        response.then().assertThat()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));

        response = CourierRequests.createCourier(courier);
        response.then().assertThat()
                .statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));


        courier = new DtoCourier(login, password);
        loginAndDeleteCourier(courier);

    }

}
