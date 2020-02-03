package org.nachtvaohal;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class CloseableHttpClientFactory {
    @Produces
    CloseableHttpClient createHttpClient() {
        return HttpClients.createDefault();
    }
}
