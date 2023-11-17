package crm.scotiatech.Rest;

import crm.scotiatech.Dao.UserDao;
import crm.scotiatech.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * La interfaz UserRest define los endpoints REST relacionados con las operaciones de usuario,
 * como el registro (signup).
 * Proporciona un punto de entrada para realizar solicitudes de registro y obtener respuestas
 * a través de HTTP.
 */

@RequestMapping(path = "/user")
public interface UserRest {

    /**
     * Maneja una solicitud HTTP POST para el registro (signup) de usuarios.
     *
     * @param reqMap Un mapa que contiene datos de solicitud, como nombre, dirección
     *           de correo electrónico, contraseña, etc.
     * @return Un objeto ResponseEntity que contiene una respuesta HTTP que indica
     * el resultado del proceso de registro.
     */
    @PostMapping(path = "/singUp")
    public ResponseEntity<String> singUp(@RequestBody(required = true)Map<String, String> reqMap);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> reqMap);

    @GetMapping(path = "/get")
    public ResponseEntity<List<UserWrapper>> getAllUser();

    @PostMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true)Map<String, String> reqMap);
}
