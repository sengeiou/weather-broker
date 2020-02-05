package org.nachtvaohal.remote;

import org.nachtvaohal.WeatherDataRemoteReceivingService;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

public class HttpInvokerServiceExporterFactory {

    @Bean(name = "/forecast")
    HttpInvokerServiceExporter accountService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        // Передается реализации сервиса
        exporter.setService(new WeatherDataRemoteReceivingServiceImpl());
        // Передается интерфейс сервиса
        exporter.setServiceInterface( WeatherDataRemoteReceivingService.class );
        return exporter;
    }

}
