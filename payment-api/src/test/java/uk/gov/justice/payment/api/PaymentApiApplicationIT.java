package uk.gov.justice.payment.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.justice.payment.api.json.api.CreatePaymentRequest;

import static com.jayway.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
public class PaymentApiApplicationIT {

    private CreatePaymentRequest paymentRequest;

    @Before
    public void setUp() {
        RestAssured.port = 8181;
        paymentRequest = new CreatePaymentRequest();
        paymentRequest.setAmount(10);
        paymentRequest.setDescription("Test Desc");
        paymentRequest.setPaymentReference("TestRef");
        paymentRequest.setApplicationReference("Test case id");
        paymentRequest.setReturnUrl("https://localhost:8443/payment-result");
        paymentRequest.setServiceId("TEST_SERVICE");
    }


    @Test
    public void createPayment() {
        setUp();
        given().contentType("application/json").
                body(getJson(paymentRequest)).
                when().post("/payments").
                then().statusCode(201);
    }


    @Test
    public void viewPayment() {
        setUp();
        String paymentId = given().contentType("application/json").
                body(getJson(paymentRequest)).
                when().post("/payments").
                then().statusCode(201).extract().path("payment_id");

        given().contentType("application/json").
                when().get("/payments/" + paymentId).
                then().statusCode(200).extract().path("state.status").equals("created");

    }


    @Test
    public void cancelPayment() {
        setUp();

        String paymentId = given().contentType("application/json").
                body(getJson(paymentRequest)).
                when().post("/payments").
                then().statusCode(201).extract().path("payment_id");
        System.out.print("paymentId==" + paymentId);
        given().contentType("application/json").
                when().post("/payments/" + paymentId + "/cancel").
                then().statusCode(204);

    }


    private String getJson(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
