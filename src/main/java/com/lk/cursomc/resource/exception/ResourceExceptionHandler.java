package com.lk.cursomc.resource.exception;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.lk.cursomc.services.exceptions.AuthorizationException;
import com.lk.cursomc.services.exceptions.DataIntegrityException;
import com.lk.cursomc.services.exceptions.FileException;
import com.lk.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    // Tratativa para objeto não encontado.
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exc, HttpServletRequest request){
        StandardError err = new StandardError(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);   // status: 404
    }

    // tratativa quando tentar excluir uma categoria que possui produtos
    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException exc, HttpServletRequest request){
        StandardError err = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); // status: 400
    }

    // Tratativa para mensagem de validação de parametros no endpoint
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException exc, HttpServletRequest request){
        ValidationError err = new ValidationError(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação!",
                System.currentTimeMillis());

        // recupera os erros da exception e add a nossa tratativa de validação.
        for (FieldError fe : exc.getBindingResult().getFieldErrors()){
            err.addError(fe.getField(), fe.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); // status: 400
    }

    // Tratativa para Restrição de conteúdo: cliente só recupera ele mesmo.
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(AuthorizationException exc, HttpServletRequest request){
        StandardError err = new StandardError(
                HttpStatus.FORBIDDEN.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);   // status: 404
    }

    // Tratativa para Upload de Arquivos na AWS.
    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> file(FileException exc, HttpServletRequest request){
        StandardError err = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);   // status: 400
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonService(AmazonServiceException exc, HttpServletRequest request){

        HttpStatus statusCode = HttpStatus.valueOf(exc.getErrorCode());

        StandardError err = new StandardError(
                statusCode.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return ResponseEntity.status(statusCode).body(err);   // status que vem da excetion da AWS
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClient(AmazonClientException exc, HttpServletRequest request){
        StandardError err = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);   // status: 400
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3(AmazonS3Exception exc, HttpServletRequest request){
        StandardError err = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);   // status: 400
    }

}
