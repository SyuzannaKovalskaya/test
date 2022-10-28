package requests;

import dto.DtoCourier;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierRequests {
    public static Response createCourier(DtoCourier dtoCourier) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(dtoCourier)
                .when()
                .post("/api/v1/courier");
        return response;
    }

    public static Response loginCourier(DtoCourier dtoCourier) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(dtoCourier)
                .when()
                .post("/api/v1/courier/login");
        return response;
    }

    public static Response deleteCourier(DtoCourier dtoCourier) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .pathParam("id", dtoCourier.getId())
                .when()
                .delete("/api/v1/courier/{id}");
        return response;
    }

}
