package org.nachtvaohal.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Base64;
import java.util.Collections;
import java.util.logging.Logger;

@RequestScoped
public class YahooDataRequest implements DataRequest {

    @Inject
    private HttpClient httpClient;

    // TODO В будущем можно добавить ввод / изменение этих параметров через админскую консоль
    private final String appId = "w7RM2b32";
    private final String consumerKey = "dj0yJmk9M2h5NVZTcnAyTXU3JmQ9WVdrOWR6ZFNUVEppTXpJbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWEz";
    private final String consumerSecret = "6de3934cbb68703ab5eb86942d1093d63bae05bb";
    private final String url = "https://weather-ydn-yql.media.yahoo.com/forecastrss";
    private final long timestamp = generateTimeStamp();
    private final String oauthNonce = generateNonce();

    private static final Logger LOG = Logger.getLogger(YahooDataRequest.class.getName());

    public YahooDataRequest() {
    }

    @Override
    public String getWeatherData(String cityName) throws Exception {

        List<String> parameters = generateUrlParameters(cityName);
        StringBuilder parametersList = generateUrl(parameters);
        String signature = generateSignatureString(parametersList);
        String authorizationLine = generateAuthorizationLine(signature);

        // TODO если в названии города есть пробел - запрос не срабатывает
        HttpGet httpGet = new HttpGet(url + "?location=" + cityName + "&format=json");
        httpGet.addHeader("Authorization", authorizationLine);
        httpGet.addHeader("X-Yahoo-App-Id", appId);
        httpGet.addHeader("Content-Type", "application/json");
        LOG.info("Executing request " + cityName);

        // todo ResponseHandler необязательно создавать новый каждый раз
        ResponseHandler<String> responseHandler = response -> {
            LOG.info(response.getStatusLine().getReasonPhrase());
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status : " + status);
            }
        };
        // Получение JSON
        return httpClient.execute(httpGet, responseHandler);
    }

    private String generateNonce(){
        byte[] nonce = new byte[32];
        Random rand = new Random();
        rand.nextBytes(nonce);
        return new String(nonce).replaceAll("\\W", "");
    }

    private long generateTimeStamp() {
        return new Date().getTime() / 1000;
    }

    private List<String> generateUrlParameters(String cityName){
        List<String> parameters = new ArrayList<>();
        parameters.add("oauth_consumer_key=" + consumerKey);
        parameters.add("oauth_nonce=" + oauthNonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp=" + timestamp);
        parameters.add("oauth_version=1.0");
        // Make sure value is encoded
        try {
            parameters.add("location=" + URLEncoder.encode(cityName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        parameters.add("format=json");
        Collections.sort(parameters);
        return parameters;
    }

    private StringBuilder generateUrl(List<String> urlParameters) {
        StringBuilder parametersList = new StringBuilder();
        for (int i = 0; i < urlParameters.size(); i++) {
            parametersList.append((i > 0) ? "&" : "");
            parametersList.append(urlParameters.get(i));
        }
        return parametersList;
    }

    private String generateSignatureString(StringBuilder parametersList) throws UnsupportedEncodingException {
        String signatureString = "GET&"
                    + URLEncoder.encode(url, "UTF-8")
                    + "&"
                    + URLEncoder.encode(parametersList.toString(), "UTF-8");

        String signature = null;
        try {
            SecretKeySpec signingKey = new SecretKeySpec((consumerSecret + "&").getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
            Base64.Encoder encoder = Base64.getEncoder();
            signature = encoder.encodeToString(rawHMAC);
        } catch (Exception e) {
            System.err.println("Unable to append signature");
            // todo точно нужен system exit в случае исключения?
            System.exit(0);
        }
        return signature;
    }

    private String generateAuthorizationLine(String signature) {
        return "OAuth "
                + "oauth_consumer_key=\"" + consumerKey + "\", "
                + "oauth_nonce=\"" + oauthNonce + "\", "
                + "oauth_timestamp=\"" + timestamp + "\", "
                + "oauth_signature_method=\"HMAC-SHA1\", "
                + "oauth_signature=\"" + signature + "\", "
                + "oauth_version=\"1.0\"";
    }
}