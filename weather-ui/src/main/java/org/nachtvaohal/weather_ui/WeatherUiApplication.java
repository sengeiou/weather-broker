package org.nachtvaohal.weather_ui;

import com.caucho.hessian.client.HessianProxyFactory;
import org.nachtvaohal.WeatherDataRemoteReceivingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@SpringBootApplication
public class WeatherUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherUiApplication.class, args);
    }

    // todo конечно же это надо вынести с отдельный класс
    @Bean
    public HessianProxyFactoryBean invoker() {
        HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:8080/weather_data_service/forecast");
        invoker.setServiceInterface(WeatherDataRemoteReceivingService.class);
        return invoker;
    }
}
