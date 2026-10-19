package com.example.PaymentServiceJune2025.paymentgateways;

import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements IPaymentGateway {

    @Value("${stripe.key}")
    private String stripeKey;

    @Override
    public String generatePaymentLink(Long amount, String orderId, String phoneNumber, String name, String email) {
        try {
            StripeClient client = new StripeClient(stripeKey);

            PriceCreateParams priceParams = PriceCreateParams.builder()
                    .setCurrency("inr")
                    .setUnitAmount(amount)
                    .setProductData(
                            PriceCreateParams.ProductData.builder()
                                    .setName("Order " + orderId)
                                    .build())
                    .build();

            Price price = client.prices().create(priceParams);

            PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
                    .addLineItem(
                            PaymentLinkCreateParams.LineItem.builder()
                                    .setPrice(price.getId())
                                    .setQuantity(1L)
                                    .build()).setAfterCompletion(
                                            PaymentLinkCreateParams.AfterCompletion.builder().setType(
                                                    PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                                    .setRedirect(
                                                            PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                                    .setUrl("https://scaler.com").build()
                                                    ).build()
                    )
                    .putMetadata("orderId", orderId)
                    .build();

            PaymentLink paymentLink = client.paymentLinks().create(params);
            return paymentLink.getUrl();
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}