package com.gottarollwithit.traconference.helper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleGeneralException(Exception e) {
        ModelAndView modelAndView = new ModelAndView(StaticURLs.ERROR_URL);
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoSuchElementException(NoSuchElementException e) {
        ModelAndView modelAndView = new ModelAndView(StaticURLs.ERROR_URL);
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }

}
