package crm.scotiatech.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * La clase SingUpUtils proporciona métodos útiles para generar respuestas HTTP
 * estandarizadas relacionadas con el proceso de registro (signup) de usuarios.
 * Estos métodos se utilizan para crear objetos ResponseEntity con mensajes de
 * respuesta y códigos de estado HTTP personalizables.
 */
public class SingUpUtils {

    // Constructor privado para evitar instanciación de la clase.
    private SingUpUtils(){

    }

    /**
     * Crea un objeto ResponseEntity que contiene un mensaje de respuesta y un código
     * de estado HTTP personalizables.
     *
     * @param responseMessage El mensaje de respuesta que se incluirá en el cuerpo
     *                       de la respuesta HTTP.
     * @param httpStatus El código de estado HTTP que se incluirá
     *                  en la respuesta.
     * @return Un objeto ResponseEntity con el mensaje y el código de estado especificados.
     */
    public static ResponseEntity<String> getResponseEntity(String responseMenssage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMenssage+"\"}", httpStatus);
    }
}
