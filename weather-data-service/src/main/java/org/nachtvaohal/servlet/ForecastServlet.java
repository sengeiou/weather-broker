//package org.nachtvaohal.servlet;
//
//import com.caucho.hessian.server.HessianServlet;
//import org.nachtvaohal.WeatherData;
//import org.nachtvaohal.WeatherDataRemoteReceivingService;
//import org.nachtvaohal.dao.StoreData;
//import org.nachtvaohal.model.WeatherDataModel;
//import org.nachtvaohal.remote.WeatherDataRemoteReceivingServiceImpl;
//import org.springframework.remoting.caucho.HessianServiceExporter;
//import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
//
//import javax.ejb.EJB;
//import javax.inject.Inject;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.logging.Logger;
//
//@WebServlet("/forecast")
//public class ForecastServlet extends HessianServlet implements WeatherDataRemoteReceivingService {
//
//    private final Logger LOG = Logger.getLogger(ForecastServlet.class.getName());
//
//    @Inject
//    private StoreData storeData;
//
//
//    @EJB
//    private WeatherDataRemoteReceivingServiceImpl weatherDataRemoteReceivingServiceImpl;
//
//    @Override
//    public WeatherData receiveWeather(String city, LocalDate date) {
//        WeatherDataModel weatherDataModel = null;
//        if (storeData != null) {
//            weatherDataModel = storeData.get(city, date);
//        }
//        WeatherData weatherData = new WeatherData(weatherDataModel.getCity(),
//                weatherDataModel.getDate(),
//                weatherDataModel.getLow(),
//                weatherDataModel.getHigh(),
//                weatherDataModel.getText());
//        LOG.info("weather information received");
//        return weatherData;
//    }
//}
