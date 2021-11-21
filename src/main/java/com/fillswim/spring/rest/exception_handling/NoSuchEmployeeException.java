package com.fillswim.spring.rest.exception_handling;

// Создание исключения для обработки ошибки поиска работника
public class NoSuchEmployeeException extends RuntimeException{

    public NoSuchEmployeeException(String message) {
        super(message);
    }
}
