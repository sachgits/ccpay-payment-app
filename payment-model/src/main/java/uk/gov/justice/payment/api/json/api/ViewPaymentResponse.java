
package uk.gov.justice.payment.api.json.api;

import com.fasterxml.jackson.annotation.*;
import uk.gov.justice.payment.api.json.AbstractDomainObject;
import uk.gov.justice.payment.api.json.external.GDSViewPaymentResponse;
import uk.gov.justice.payment.api.json.external.Links;
import uk.gov.justice.payment.api.json.external.RefundSummary;
import uk.gov.justice.payment.api.json.external.State;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "amount",
        "state",
        "description",
        "reference",
        "payment_id",
        "created_date"

})
public class ViewPaymentResponse  extends AbstractDomainObject  {

    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("state")
    private State state;
    @JsonProperty("description")
    private String description;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("payment_id")
    private String paymentId;
    @JsonProperty("created_date")
    private String createdDate;

    public ViewPaymentResponse(){}

    public ViewPaymentResponse(GDSViewPaymentResponse gdsViewPaymentResponse) {
        this.amount=gdsViewPaymentResponse.getAmount();
        this.state=gdsViewPaymentResponse.getState();
        this.description=gdsViewPaymentResponse.getDescription();
        this.reference=gdsViewPaymentResponse.getReference();
        this.paymentId=gdsViewPaymentResponse.getPaymentId();
        this.createdDate=gdsViewPaymentResponse.getCreatedDate();
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The amount
     */
    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     *     The amount
     */
    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     *     The state
     */
    @JsonProperty("state")
    public State getState() {
        return state;
    }

    /**
     *
     * @param state
     *     The state
     */
    @JsonProperty("state")
    public void setState(State state) {
        this.state = state;
    }

    /**
     *
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The reference
     */
    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    /**
     *
     * @param reference
     *     The reference
     */
    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

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
     *     The createdDate
     */
    @JsonProperty("created_date")
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     *
     * @param createdDate
     *     The created_date
     */
    @JsonProperty("created_date")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }



    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return super.toString(this);
    }
}
