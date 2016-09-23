
package uk.gov.justice.payment.api.json;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({

    "payment_id",
        "_links"
})
public class CreatePaymentResponseInternal {


    @JsonProperty("payment_id")
    private String paymentId;
    @JsonProperty("_links")
    private LinksInternal links;

    /**
     * 
     * @return
     *     The paymentId
     */
    @JsonProperty("payment_id")
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * 
     * @param paymentId
     *     The payment_id
     */
    @JsonProperty("payment_id")
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    /**
     *
     * @return
     *     The links
     */
    @JsonProperty("_links")
    public LinksInternal getLinks() {
        return links;
    }

    /**
     *
     * @param links
     *     The _links
     */
    @JsonProperty("_links")
    public void setLinks(LinksInternal links) {
        this.links = links;
    }



}
