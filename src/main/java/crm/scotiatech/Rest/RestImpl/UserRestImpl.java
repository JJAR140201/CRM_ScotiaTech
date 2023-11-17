package crm.scotiatech.Rest.RestImpl;

import crm.scotiatech.Constents.SingUp;
import crm.scotiatech.Rest.UserRest;
import crm.scotiatech.Service.UserService;
import crm.scotiatech.Utils.SingUpUtils;
import crm.scotiatech.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * La clase UserRestImpl implementa la interfaz UserRest y se encarga de proporcionar
 * endpoints REST relacionados con la funcionalidad de registro (signup) de usuarios.
 * Utiliza la inyección de dependencias para acceder a un objeto UserService que
 * maneja la lógica de registro de usuarios.
 */

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    /**
     * Maneja el proceso de registro (signup) de usuarios a través de una solicitud REST.
     *
     * @param req Un mapa que contiene datos de solicitud, como nombre,
     *            dirección de correo electrónico, contraseña, etc.
     * @return Un objeto ResponseEntity que contiene una respuesta HTTP que
     * indica el resultado del proceso de registro.
     */
    @Override
    public ResponseEntity<String> singUp(Map<String, String> req) {
        try {
            return userService.singUp(req);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        // En caso de una excepción, se devuelve una respuesta de error con un mensaje genérico.
        return SingUpUtils.getResponseEntity(SingUp.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> req) {
        try{
            return userService.login(req);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return SingUpUtils.getResponseEntity(SingUp.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try{
            return userService.getAllUser();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> reqMap) {
        try {
            return userService.update(reqMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return SingUpUtils.getResponseEntity(SingUp.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
