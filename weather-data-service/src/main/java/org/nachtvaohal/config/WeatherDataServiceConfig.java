//package org.nachtvaohal.config;
//
//import org.nachtvaohal.WeatherDataRemoteReceivingService;
//import org.nachtvaohal.remote.WeatherDataRemoteReceivingServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
//
//@Configuration
//public class WeatherDataServiceConfig {
//    /*
//    Определение приложения с компонентом типа HttpInvokerServiceExporter в контексте.
//    Он позаботится о раскрытии в веб-приложении точки входа HTTP, которая впоследствии будет вызываться клиентом.
//    Стоит отметить, что HTTPInvoker Spring использует имя bean-компонента HttpInvokerServiceExporter
//    в качестве относительного пути для URL-адреса конечной точки HTTP.     */
//    @Bean(name = "/forecast")
//    HttpInvokerServiceExporter forecast() {
//        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
//        // Передается реализации сервиса
//        exporter.setService(new WeatherDataRemoteReceivingServiceImpl());
//        // Передается интерфейс сервиса
//        exporter.setServiceInterface( WeatherDataRemoteReceivingService.class );
//        return exporter;
//    }
//
//}
