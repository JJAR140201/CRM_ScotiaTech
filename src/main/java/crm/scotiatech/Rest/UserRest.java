package crm.scotiatech.Rest;

import crm.scotiatech.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @param req Un mapa que contiene datos de solicitud, como nombre, dirección
     *           de correo electrónico, contraseña, etc.
     * @return Un objeto ResponseEntity que contiene una respuesta HTTP que indica
     * el resultado del proceso de registro.
     */
    @PostMapping(path = "/singUp")
    public ResponseEntity<String> singUp(@RequestBody(required = true)Map<String, String> req);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> req);

    @GetMapping(path = "/get")
    public ResponseEntity<List<UserWrapper>> getAllUser();
}
