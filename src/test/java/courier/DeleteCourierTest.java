package courier;

import dto.DtoCourier;
import io.restassured.response.Response;
import org.junit.Test;
import requests.CourierRequests;

import static org.hamcrest.Matchers.equalTo;

public class DeleteCourierTest extends BaseCourierTest{


    @Test
    public void cantDeleteCouriersWithoutNonExistentIdTest() {
        DtoCourier dtoCourier = new DtoCourier(0000000000);
        Response response = CourierRequests.deleteCourier(dtoCourier);
        response.then().assertThat()
                .statusCode(404)
                .and()
                .body("message", equalTo("Курьера с таким id нет."));
    }
}
