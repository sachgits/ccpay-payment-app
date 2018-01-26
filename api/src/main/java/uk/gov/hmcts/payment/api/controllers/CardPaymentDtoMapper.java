package uk.gov.hmcts.payment.api.controllers;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import lombok.SneakyThrows;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.payment.api.v1.contract.PaymentDto;
import uk.gov.hmcts.payment.api.v1.controllers.PaymentController;
import uk.gov.hmcts.payment.api.contract.FeeDto;
import uk.gov.hmcts.payment.api.contract.CardPaymentDto;
import uk.gov.hmcts.payment.api.model.Fee;
import uk.gov.hmcts.payment.api.model.Payment;
import uk.gov.hmcts.payment.api.model.PaymentFeeLink;

import javax.smartcardio.Card;

@Component
public class CardPaymentDtoMapper {

    public CardPaymentDto toCardPaymentDto(PaymentFeeLink paymentFeeLink) {
        Payment payment = paymentFeeLink.getPayments().get(0);
        return CardPaymentDto.payment2DtoWith()
            .state(toStateDto(payment.getStatus(), payment.getFinished()))
            .reference(payment.getReference())
            .dateCreated(payment.getDateCreated())
            .links(new CardPaymentDto.LinksDto(
                payment.getNextUrl() == null ? null : new CardPaymentDto.LinkDto(payment.getNextUrl(), "GET"),
                payment.getCancelUrl() == null ? null : cancellationLink(payment.getUserId(), payment.getId())
            ))
            .build();
    }

    public CardPaymentDto toReconciliationResponseDto(PaymentFeeLink paymentFeeLink) {
        Payment payment = paymentFeeLink.getPayments().get(0);
        return CardPaymentDto.payment2DtoWith()
            .paymentReference(payment.getReference())
            .serviceType(payment.getServiceType())
            .siteId(payment.getSiteId())
            .amount(payment.getAmount())
            .currency(payment.getCurrency())
            .paymentStatus(payment.getStatus())
            .dateCreated(payment.getDateCreated())
            .paymentMethod(payment.getPaymentMethod().getName())
            .paymentProvider(payment.getPaymentProvider().getName())
            .feeDtos(toFeeDtos(paymentFeeLink.getFees()))
            .build();

    }

    public List<FeeDto> toFeeDtos(List<Fee> fees) {
        return fees.stream().map(this::toFeeDto).collect(Collectors.toList());
    }

    public List<Fee> toFees(List<FeeDto> feeDtos) {
        return feeDtos.stream().map(this::toFee).collect(Collectors.toList());
    }

    private Fee toFee(FeeDto feeDto) {
        return Fee.feeWith().amount(feeDto.getAmount()).code(feeDto.getCode()).version(feeDto.getVersion()).build();
    }

    private FeeDto toFeeDto(Fee fee) {
        return FeeDto.feeDtoWith().amount(fee.getAmount()).code(fee.getCode()).version(fee.getVersion()).build();
    }

    private CardPaymentDto.StateDto toStateDto(String status, Boolean finished) {
        return new CardPaymentDto.StateDto(status, finished);
    }

    @SneakyThrows(NoSuchMethodException.class)
    private CardPaymentDto.LinkDto cancellationLink(String userId, Integer paymentId) {
        Method method = PaymentController.class.getMethod("cancel", String.class, Integer.class);
        return new CardPaymentDto.LinkDto(ControllerLinkBuilder.linkTo(method, userId, paymentId).toString(), "POST");
    }

}
