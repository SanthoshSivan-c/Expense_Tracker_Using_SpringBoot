package expense_tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

	@ExceptionHandler
	public ResponseEntity<?> exceptionHandling(Exception e){
		return new ResponseEntity<>("Msg:"+e.getMessage(),HttpStatus.NOT_FOUND);
	}
}
