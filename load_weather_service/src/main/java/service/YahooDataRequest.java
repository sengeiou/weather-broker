package service;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.IOException;
import java.util.logging.Logger;

@ApplicationScoped
public class YahooDataRequest implements DataRequest {

    private static final Logger LOG = Logger.getLogger(YahooDataRequest.class.getName());

    @Inject
    private SendMessage sendMessage;

    /**
     * Получение данных с Yahoo.
     * Пока что выводит полученные данные в лог.
     * @throws ClientProtocolException
     * @throws IOException
     */
    @Override
    public void getData() throws ClientProtocolException, IOException{
        try (
                CloseableHttpClient httpClient = HttpClients.createDefault()
                ) {
            // TODO Вынести параметры OAuth
            HttpGet httpGet = new HttpGet("https://weather-ydn-yql.media.yahoo.com/forecastrss" +
                    "?location=penza,ru" +
                    "&format=json" +
                    "&oauth_consumer_key=dj0yJmk9M2h5NVZTcnAyTXU3JmQ9WVdrOWR6ZFNUVEppTXpJbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWEz" +
                    "&oauth_signature_method=HMAC-SHA1" +
                    "&oauth_timestamp=1578050381" +
                    "&oauth_nonce=R29PFa4z7nr" +
                    "&oauth_version=1.0" +
                    "&oauth_signature=m4xf6NzbxGtLP7keYNcqOHrvALQ=");
            LOG.info("Executing request " + httpGet.getRequestLine());

            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status : " + status);
                }
            };
            // Получение JSON
            String responseBody = httpClient.execute(httpGet, responseHandler);
            LOG.info(responseBody);
            sendMessage.sendMessage(responseBody);
        }
    }
}