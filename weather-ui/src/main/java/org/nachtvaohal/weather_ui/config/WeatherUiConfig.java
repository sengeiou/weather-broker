package org.nachtvaohal.weather_ui.config;

import org.nachtvaohal.WeatherDataRemoteReceivingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class WeatherUiConfig {

    @Bean
    public HessianProxyFactoryBean invoker() {
        HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:8080/weather_data_service/forecast");
        invoker.setServiceInterface(WeatherDataRemoteReceivingService.class);
        return invoker;
    }
}
