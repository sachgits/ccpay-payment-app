package uk.gov.justice.payment.api.acceptancetests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.http.HttpStatus.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
public class PaymentsKeysMissingValidationTest extends TestBase {


    @Test
    public void test1_POST_Empty_Json() throws IOException {
        String formatted_json = formatedJson("", "", "", "", "", "", "", "", "", "", "", "");
        scenario.given().body(formatted_json)
                .when().post()
                .then().statusCode(BAD_REQUEST.value());
    }

    @Test
    public void test2_POST_Without_Amount_Attribute() throws IOException {
        String formatted_json = formatedJson("", "0", CONFIG.getProperty("k_application_reference"),
                CONFIG.getProperty("application_ref"), CONFIG.getProperty("k_description"),
                CONFIG.getProperty("description"), CONFIG.getProperty("k_email"), CONFIG.getProperty("email"),
                CONFIG.getProperty("k_payment_reference"), CONFIG.getProperty("payment_ref"),
                CONFIG.getProperty("k_return_url"), CONFIG.getProperty("ret_url")
        );

        scenario.given().body(formatted_json)
                .when().post()
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body(equalTo("amount: may not be null"));
    }

    @Test
    public void test3_POST_Without_App_Ref_Attribute() throws IOException {
        String formatted_json = formatedJson(CONFIG.getProperty("k_amount"), CONFIG.getProperty("amount"), "", "",
                CONFIG.getProperty("k_description"), CONFIG.getProperty("description"), CONFIG.getProperty("k_email"),
                CONFIG.getProperty("email"), CONFIG.getProperty("k_payment_reference"),
                CONFIG.getProperty("payment_ref"), CONFIG.getProperty("k_return_url"), CONFIG.getProperty("ret_url")
        );

        scenario.given().body(formatted_json)
                .when().post()
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body(equalTo(CONFIG.getProperty("application_man_msg")));
    }

    @Test
    public void test4_POST_Without_Desc_Attribute() throws IOException {
        String formatted_json = formatedJson(CONFIG.getProperty("k_amount"), CONFIG.getProperty("amount"),
                CONFIG.getProperty("k_application_reference"), CONFIG.getProperty("application_ref"), "", "",
                CONFIG.getProperty("k_email"), CONFIG.getProperty("email"), CONFIG.getProperty("k_payment_reference"),
                CONFIG.getProperty("payment_ref"), CONFIG.getProperty("k_return_url"), CONFIG.getProperty("ret_url")
        );

        scenario.given().body(formatted_json)
                .when().post()
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body(equalTo(CONFIG.getProperty("description_man_msg")));
    }

    @Test
    public void test5_POST_Without_Pay_Ref_Attribute() throws IOException {
        String formatted_json = formatedJson(CONFIG.getProperty("k_amount"), CONFIG.getProperty("amount"),
                CONFIG.getProperty("k_application_reference"), CONFIG.getProperty("application_ref"),
                CONFIG.getProperty("k_description"), CONFIG.getProperty("description"), CONFIG.getProperty("k_email"),
                CONFIG.getProperty("email"), "", "", CONFIG.getProperty("k_return_url"), CONFIG.getProperty("ret_url")
        );

        scenario.given().body(formatted_json)
                .when().post()
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body(equalTo(CONFIG.getProperty("payment_ref_man_msg")));
    }

    @Test
    public void test6_POST_Without_Ret_Url_Attribute() throws IOException {
        String formatted_json = formatedJson(CONFIG.getProperty("k_amount"), CONFIG.getProperty("amount"),
                CONFIG.getProperty("k_application_reference"), CONFIG.getProperty("application_ref"),
                CONFIG.getProperty("k_description"), CONFIG.getProperty("description"), CONFIG.getProperty("k_email"),
                CONFIG.getProperty("email"), CONFIG.getProperty("k_payment_reference"),
                CONFIG.getProperty("payment_ref"), "", ""
        );

        scenario.given().body(formatted_json)
                .when().post()
                .then()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body(equalTo(CONFIG.getProperty("ret_url_man_msg")));
    }


    private String formatedJson(String k_amount, String amount, String k_application_ref, String application_ref,
                                String k_description, String description, String k_email, String email, String k_payment_ref,
                                String payment_ref, String k_ret_url, String ret_url)
            throws IOException {

        Map<String, String> m = new HashMap<String, String>();
        m.put("k_amount", k_amount);
        m.put("amount", amount);
        m.put("k_application_ref", k_application_ref);
        m.put("application_ref", application_ref);
        m.put("k_description", k_description);
        m.put("description", description);
        m.put("k_email", k_email);
        m.put("email", email);
        m.put("k_payment_ref", k_payment_ref);
        m.put("payment_ref", payment_ref);
        m.put("k_ret_url", k_ret_url);
        m.put("ret_url", ret_url);

        return getProcessedTemplateValue("Message_Appeal_Post_Key_Validation.json", m);
    }


}