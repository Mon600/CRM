package app.web.exceptions;

import app.domain.exceptions.ClientNotFoundException;
import app.web.dto.response.ErrorDetail;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetail> handleConflictData(DataIntegrityViolationException ex) {
        String message = "Пользователь с такими данными уже существует. Проверьте корректность данных.";
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorDetail(
                                HttpStatus.CONFLICT.value(),
                                message,
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorDetail> handleOptimisticLock(ObjectOptimisticLockingFailureException ex) {
        String message = "Данные были изменены другим пользователем. Обновите страницу.";
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorDetail(
                                HttpStatus.CONFLICT.value(),
                                message,
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDetail> handleValidation(ValidationException ex) {
        String message = "Проверьте корректность введенных данных.";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorDetail(
                                HttpStatus.BAD_REQUEST.value(),
                                message,
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleClientNotFound(ClientNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorDetail(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage(),
                                LocalDateTime.now()
                )
        );
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetail> handleException(Exception ex) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(
//                        new ErrorDetail(
//                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                                ex.getMessage(),
//                                LocalDateTime.now()
//                        )
//                );
//    }
}
