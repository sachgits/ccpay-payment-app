package uk.gov.justice.payment.api.domain;

import uk.gov.justice.payment.api.json.api.CreatePaymentRequest;
import uk.gov.justice.payment.api.json.api.CreatePaymentResponse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    String paymentId;

    Integer amount;

    String paymentReference;

    String applicationReference;

    String serviceReference;

    String description;

    String returnUrl;

    public PaymentDetails(){};
    public PaymentDetails(CreatePaymentRequest request, CreatePaymentResponse response) {
        this.paymentId = response.getPaymentId();
        this.amount = request.getAmount();
        this.paymentReference=request.getPaymentReference();
        this.applicationReference=request.getApplicationReference();
        this.serviceReference=request.getServiceReference();
        this.description=request.getDescription();
        this.returnUrl=request.getReturnUrl();
    }
}
