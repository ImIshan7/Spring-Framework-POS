package lk.ijse.spring.advisor;

import lk.ijse.spring.util.ResponseUtil;
import org.hibernate.tool.schema.ast.SqlScriptParserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
@CrossOrigin
public class AppWideExceptionHandler {

    @ExceptionHandler({RuntimeException.class})

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseUtil handleException(RuntimeException e) {
        return new ResponseUtil("Error",e.getMessage(),"");
    }

    @ExceptionHandler({ClassNotFoundException.class})

    public ResponseUtil handleAllClassNotFoundException(ClassNotFoundException e) {
        return new ResponseUtil("Error",e.getMessage(),"");
    }

    @ExceptionHandler({SQLException.class})

    public ResponseUtil handleAllClassException(SQLException e) {
        return new ResponseUtil("Error",e.getMessage(),"");
    }


}
