package org.nachtvaohal.weather_ui.exceptions;

import com.caucho.hessian.io.HessianServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WeatherExceptionHandler {

    @ExceptionHandler(HessianServiceException.class)
    protected String handleHessianServiceException(HessianServiceException e) {
        return "errorpage";
    }

}
