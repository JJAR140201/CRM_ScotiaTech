package crm.scotiatech.Service.ServiceImpl;

import crm.scotiatech.Constents.SingUp;
import crm.scotiatech.Dao.UserDao;
import crm.scotiatech.JWT.CustomerUsersDetailsService;
import crm.scotiatech.JWT.JwtUtils;
import crm.scotiatech.POJO.User;
import crm.scotiatech.Service.UserService;
import crm.scotiatech.Utils.SingUpUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.*;

import java.util.Map;
import java.util.Objects;

/**
 * La clase UserServiceImpl es una implementación de la interfaz UserService y
 * proporciona servicios relacionados con el registro de usuarios.
 * Se encarga de validar los datos de registro, verificar la existencia del
 * correo electrónico en la base de datos y, si es válido, registrar al usuario.
 * Utiliza la inyección de dependencias para acceder a un objeto UserDao que se
 * encarga de las operaciones de acceso a datos.
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * Maneja el proceso de registro (signup) de usuarios y proporciona
     * una respuesta HTTP basada en el resultado del proceso.
     *
     * @param reqMap Un mapa que contiene datos de solicitud, como nombre,
     *              dirección de correo electrónico, contraseña, etc.
     * @return Un objeto ResponseEntity que contiene una respuesta HTTP que
     * indica el resultado del proceso de registro.
     */
    @Override
    public ResponseEntity<String> singUp(Map<String, String> reqMap) {
        logger.info("Inside singup {}", reqMap);
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

    @Override
    public ResponseEntity<String> login(Map<String, String> reqMap) {
        logger.info("Inside login {}", reqMap);
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(reqMap.get("email"),
                            reqMap.get("password"))
            );
            if (auth.isAuthenticated()){
                if(customerUsersDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")){
                    return new ResponseEntity<String>("{\"token\":\"" +
                            jwtUtils.generateToken(customerUsersDetailsService.getUserDetails().getEmail(),
                                    customerUsersDetailsService.getUserDetails().getRole()) + "\"}",
                    HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<String>("{\"message\":\"" + "Wait for admin approval." + "\"}",
                            HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception ex){
            logger.error("{}", ex);
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials." + "\"}",
                HttpStatus.BAD_REQUEST);
    }
}
