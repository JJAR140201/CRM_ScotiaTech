package crm.scotiatech.Service;

import crm.scotiatech.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * La interfaz UserService define métodos relacionados con la funcionalidad
 * de registro (signup) de usuarios.
 * Proporciona un punto de entrada para interactuar con el servicio de registro
 * de usuarios y obtener respuestas en forma de objetos ResponseEntity.
 */

public interface UserService {

    /**
     * Realiza el proceso de registro (signup) de usuarios utilizando los datos
     * proporcionados en el mapa de solicitud.
     *
     * @param reqMap Un mapa que contiene datos de solicitud, como nombre, dirección
     *              de correo electrónico, contraseña, etc.
     * @return Un objeto ResponseEntity que contiene una respuesta HTTP que indica
     * el resultado del proceso de registro.
     */
    ResponseEntity<String> singUp(Map<String, String> reqMap);

    ResponseEntity<String> login(Map<String, String> reqMap);

    ResponseEntity<List<UserWrapper>> getAllUser();
    ResponseEntity<String> update(Map<String, String> reqMap);
}
