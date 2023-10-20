package crm.scotiatech.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SingUpUtils {

    private SingUpUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMenssage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMenssage+"\"}", httpStatus);
    }
}
