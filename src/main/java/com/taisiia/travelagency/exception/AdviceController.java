package com.taisiia.travelagency.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class AdviceController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        log.error("Not found entity", entityNotFoundException);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
        log.error("Dublicate email address", sqlIntegrityConstraintViolationException);
    }

    @ExceptionHandler(TourStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleTourStatusException(TourStatusException tourStatusException) {
        log.error("This status doesn't exists", tourStatusException);
        return ErrorMessage.builder()
                .message(tourStatusException.getMessage())
                .build();

    }

    @ExceptionHandler(TourException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleTourStatusException(TourException tourException) {
        log.error("Data is not correct", tourException);
    }

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleUserException(UserException userException) {
        log.error("user's data are not correct", userException);
        return ErrorMessage.builder()
                .message(userException.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error("Incorrect input data", methodArgumentNotValidException);
        List<ObjectError> allErrors = methodArgumentNotValidException.getBindingResult().getAllErrors();
        return allErrors.stream()
                .map(error -> {
                    FieldError fieldError = (FieldError) error;
                    return new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage());
                })
                .collect(Collectors.toList());
    }

    @ExceptionHandler(CityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleCityException(CityException cityException) {
        return ErrorMessage.builder()
                .message(cityException.getMessage())
                .build();
    }


    @ExceptionHandler(CountryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleCityException(CountryException countryException) {

        return ErrorMessage.builder()
                .message(countryException.getMessage())
                .build();
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleDateTimeParseException(DateTimeParseException dateTimeParseException) {
        return ErrorMessage.builder()
                .message(dateTimeParseException.getMessage())
                .build();
    }

    @ExceptionHandler(TokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleDateTimeParseException(TokenException tokenException) {
        return ErrorMessage.builder()
                .message(tokenException.getMessage())
                .build();
    }

    @ExceptionHandler(DiscountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleDateTimeParseException(DiscountException discountException) {
        return ErrorMessage.builder()
                .message(discountException.getMessage())
                .build();
    }

    @ExceptionHandler(HotelException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleDateTimeParseException(HotelException hotelException) {
        return ErrorMessage.builder()
                .message(hotelException.getMessage())
                .build();
    }

}
