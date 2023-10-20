package crm.scotiatech.Service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    ResponseEntity<String> singUp(Map<String, String> reqMap);
}
