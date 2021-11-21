package com.fillswim.spring.rest.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {

    // Метод, который будет обрабатывать выбрасываемые исключения, если работник не найден
    // NoSuchEmployeeException - на данное исключение будет реагировать метод
    // ResponseEntity<EmployeeIncorrectData> - обёртка HTTP response
    // EmployeeIncorrectData - тип, ответственный за JSON
    // EmployeeIncorrectData - добавляется в HTTP response
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException exception) {

        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

    }

    // Метод будет реагировать на все исключения
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(Exception exception) {

        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);

    }
}
