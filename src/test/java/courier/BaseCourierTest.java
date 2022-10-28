package courier;

import dto.DtoCourier;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import requests.CourierRequests;

import static org.hamcrest.Matchers.equalTo;

public class BaseCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    protected void loginAndDeleteCourier(DtoCourier courier) {
        Response response = CourierRequests.loginCourier(courier);
        response.then().assertThat()
                .statusCode(200);
        courier = response
                .as(DtoCourier.class);

        response = CourierRequests.deleteCourier(courier);
        response.then().assertThat()
                .statusCode(200)
                .and()
                .body("ok", equalTo(true));
    }
}
