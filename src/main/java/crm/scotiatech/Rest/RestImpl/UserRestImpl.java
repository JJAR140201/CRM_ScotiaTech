package crm.scotiatech.Rest.RestImpl;

import crm.scotiatech.Constents.SingUp;
import crm.scotiatech.Rest.UserRest;
import crm.scotiatech.Service.UserService;
import crm.scotiatech.Utils.SingUpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> singUp(Map<String, String> req) {
        try {
            return userService.singUp(req);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return SingUpUtils.getResponseEntity(SingUp.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
