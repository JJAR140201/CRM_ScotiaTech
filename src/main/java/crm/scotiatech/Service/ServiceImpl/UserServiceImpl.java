package crm.scotiatech.Service.ServiceImpl;

import crm.scotiatech.Constents.SingUp;
import crm.scotiatech.Dao.UserDao;
import crm.scotiatech.POJO.User;
import crm.scotiatech.Service.UserService;
import crm.scotiatech.Utils.SingUpUtils;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> singUp(Map<String, String> reqMap) {
        //log.info("Inside singup {}", reqMap);
        try {
            if (validateSingUpMap(reqMap)) {
                User user = userDao.findByEmailId(reqMap.get("email"));
                if (Objects.isNull(user)){
                    userDao.save(getUserFromMap(reqMap));
                    return SingUpUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
                } else {
                    return SingUpUtils.getResponseEntity("Email already exits", HttpStatus.BAD_REQUEST);
                }
            } else {
                return SingUpUtils.getResponseEntity(SingUp.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
        return SingUpUtils.getResponseEntity(SingUp.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSingUpMap(Map<String, String> reqMap) {
        if (reqMap.containsKey("name")
                && reqMap.containsKey("contactNumber")
                && reqMap.containsKey("email")
                && reqMap.containsKey("password")) {
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> reqMap){
        User user = new User();
        user.setName(reqMap.get("name"));
        user.setContactNumber(reqMap.get("contactNumber"));
        user.setEmail(reqMap.get("email"));
        user.setPassword(reqMap.get("password"));
        user.setStatus(reqMap.get("false"));
        user.setRole(reqMap.get("role"));

        return user;
    }
}
