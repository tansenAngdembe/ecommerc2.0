package com.ecommerce.ecommerce_20.util.service;

import com.ecommerce.ecommerce_20.util.constant.Constants;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class EsewaService {


    private static final String SECRET = Constants.ESEWASERECT;

    public static String generateEsewaSignature( String data) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(SECRET.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes()));
            return hash;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while generating eSewa signature";
        }

    }


    public  String createSignature(Double total_amount,String transaction_uuid , String product_code) {
        String total = Double.toString(total_amount);
        String data = String.format("total_amount=%s,transaction_uuid=%s,product_code=%s", total, transaction_uuid, product_code);
        return generateEsewaSignature(data);
    }

    public String createResponseSignature(String transactionCode,String status,String totalAmount,String transactionUuid, String productCode,String signedFieldNames) {
        String data = String.format("transaction_code=%s,status=%s,total_amount=%s,transaction_uuid=%s,product_code=%s,signed_field_names=%s",
                transactionCode, status, totalAmount, transactionUuid, productCode,signedFieldNames);

        return generateEsewaSignature(data);
    }
}
