package com.AuthenticationDemo.Securtity.ECCOMERCE.CONTROLLLER;

import com.AuthenticationDemo.Securtity.ECCOMERCE.DTO.PaymentRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@RestController
@RequestMapping("/api/v4/mpesa")
public class mpesaIntegration {

    private static final String CONSUMER_KEY = "z6JdSnLAKDmVirHAHnne7eiWALZNhXplr20Q1uNd2ULMa36q";
    private static final String CONSUMER_SECRET = "vQgwBsTQGhnawy4AR1vPu7PKBfHOGmSAK5PnW0qJZdzL4hxBCjbyF5RsLz0IcBPc";
    private static final String MPESA_ACCESS_TOKEN_URL = "https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials";

    private static final String MPESA_STK_PUSH_URL = "https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest";

    @PostMapping("/stkpush")
    public ResponseEntity<String> makeStkPush(@RequestBody PaymentRequest paymentRequest) throws IOException {
        String phoneNumber = paymentRequest.getPhoneNumber();
        String amount = paymentRequest.getAmount();

  // Build a Request which Is Sent to the Mpesa access Token Url inorder To Get the Response
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String auth = Base64.getEncoder().encodeToString((CONSUMER_KEY + ":" + CONSUMER_SECRET).getBytes());
        Request tokenRequest = new Request.Builder()
                .url(MPESA_ACCESS_TOKEN_URL)
                .get()
                .addHeader("Authorization", "Basic " + auth)
                .build();
        //Executes the Get Request and Response Stored in the tokenResponse
        Response tokenResponse = client.newCall(tokenRequest).execute();
        if (!tokenResponse.isSuccessful()) {
            throw new IOException("Failed to get access token: " + tokenResponse);
        }

        // The Response Body is Parsed Inorder To extract the Access Token
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode tokenJson = objectMapper.readTree(tokenResponse.body().string());
        String accessToken = tokenJson.path("access_token").asText();

        // Step 2: Create STK Push request
        HttpHeaders stkPushHeaders = new HttpHeaders();
        stkPushHeaders.set("Authorization", "Bearer " + accessToken);
        stkPushHeaders.setContentType(MediaType.APPLICATION_JSON);

        int shortCode = 174379;
        String passkey = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
        String formattedPhone = "254" + phoneNumber.substring(1);
        String timestamp = generateTimestamp();
        String password = Base64.getEncoder().encodeToString((shortCode + passkey + timestamp).getBytes());
        String callBackUrl = "https://mydomain.com/path";

        String payload = "{\"BusinessShortCode\":\"" + shortCode + "\"," +
                "\"Password\":\"" + password + "\"," +
                "\"Timestamp\":\"" + timestamp + "\"," +
                "\"TransactionType\":\"CustomerPayBillOnline\"," +
                "\"Amount\":\"" + amount + "\"," +
                "\"PartyA\":\"" + formattedPhone + "\"," +
                "\"PartyB\":\"" + shortCode + "\"," +
                "\"PhoneNumber\":\"" + formattedPhone + "\"," +
                "\"CallBackURL\":\"" + callBackUrl + "\"," +
                "\"AccountReference\":\"Mpesa Test\"," +
                "\"TransactionDesc\":\"Testing stk push\"}";

        HttpEntity<String> stkPushRequestEntity = new HttpEntity<>(payload, stkPushHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> stkPushResponseEntity = restTemplate.exchange(MPESA_STK_PUSH_URL, HttpMethod.POST, stkPushRequestEntity, String.class);
        return new ResponseEntity<>(stkPushResponseEntity.getBody(), HttpStatus.OK);
    }

    private String generateTimestamp() {
        Date date = new Date();
        return String.format("%tY%<tm%<td%<tH%<tM%<tS", date);
    }
}
