package apilivros.apilivros.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import apilivros.apilivros.common.ConversorData;
import apilivros.apilivros.domain.exception.BadRequestException;
import apilivros.apilivros.domain.exception.ResourceNotFoundException;
import apilivros.apilivros.domain.model.ReplyError;


@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ReplyError> 
        handlerResourceNotFoundException(
                  ResourceNotFoundException ex){
            String dataHora = ConversorData
            .converterDateParaDataHora(new Date());
            ReplyError erro = new ReplyError(dataHora,
             HttpStatus.NOT_FOUND.value(), "NOT FOUND",
             ex.getMessage());
            return new ResponseEntity<>
            (erro, HttpStatus.NOT_FOUND); 
        }

    @ExceptionHandler(BadRequestException.class)
        public ResponseEntity<ReplyError> 
            handlerBadRequestException(
                      BadRequestException ex){
             String dataHora = ConversorData
            .converterDateParaDataHora(new Date());
            ReplyError erro = new ReplyError(dataHora,
            HttpStatus.BAD_REQUEST.value(), 
            "BAD REQUEST",
            ex.getMessage());
            return new ResponseEntity<>
            (erro, HttpStatus.BAD_REQUEST);    
            }

   
    @ExceptionHandler(Exception.class)
            public ResponseEntity<ReplyError> 
                handlerException(
                          Exception ex){
        String dataHora = ConversorData
        .converterDateParaDataHora(new Date());
        ReplyError erro = new ReplyError(dataHora,
        HttpStatus.INTERNAL_SERVER_ERROR.value(), 
        "INTERNAL SERVER ERROR",
        ex.getMessage());
        return new ResponseEntity<>
        (erro, HttpStatus.INTERNAL_SERVER_ERROR);  
                }         
}
