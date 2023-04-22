package vttp2022.miniproject02.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.exception.StripeException;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import vttp2022.miniproject02.server.model.Subscription;

@RestController
@RequestMapping("/sub")
public class StripeController {

	@Value("${SECRET_KEY_STRIPE}") // this is also the api key
    private String secretKey;

    @PostMapping(value="/subscribe", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> subscribe(@RequestBody Subscription payment) throws StripeException {

		System.out.println("paymentWithCheckoutPage called");
		System.out.println(payment.getName());
		System.out.println(payment.getAmount());

		// We initilize stripe object with the api key
		Stripe.apiKey = secretKey;
		// We create a stripe session
		SessionCreateParams params = SessionCreateParams.builder()
				// We will use the credit card payment method
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
				.setCancelUrl(
						payment.getCancelUrl())
				.addLineItem(
						SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
								.setPriceData(
										SessionCreateParams.LineItem.PriceData.builder()
												.setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
												.setProductData(SessionCreateParams.LineItem.PriceData.ProductData
														.builder().setName(payment.getName()).build())
												.build())
								.build())
				.build();

		Session session = Session.create(params);

		Map<String, String> responseData = new HashMap<>();
		// responseData.put("id", session.getId());
		responseData.put("id", session.getId());

		ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(responseData);
        } catch (JsonProcessingException e) {
            // Handle the exception
            jsonResponse = "{\"error\":\"Failed to create JSON response\"}";
        }

		System.out.print(jsonResponse);

        return ResponseEntity.ok(jsonResponse);
	}

}
