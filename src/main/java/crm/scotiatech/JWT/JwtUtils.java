package crm.scotiatech.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * La clase JwtUtils proporciona funciones para generar, validar y extraer información de tokens JWT (JSON Web Tokens).
 * Estos tokens se utilizan para la autenticación y autorización de usuarios en una aplicación.
 */

@Service
public class JwtUtils {

    private String secret = "logincrm";

    /**
     * Extrae el nombre de usuario (subject) de un token JWT.
     *
     * @param token El token JWT del que se extraerá el nombre de usuario.
     * @return El nombre de usuario extraído del token.
     */
    public String extractUserName(String token){
        return extractClamis(token, Claims::getSubject);
    }

    /**
     * Extrae la fecha de expiración de un token JWT.
     *
     * @param token El token JWT del que se extraerá la fecha de expiración.
     * @return La fecha de expiración extraída del token.
     */
    public Date extractExpiration(String token){
        return extractClamis(token, Claims::getExpiration);
    }

    /**
     * Extrae información específica (claims) de un token JWT utilizando una función de resolución de claims.
     *
     * @param token          El token JWT del que se extraerán los claims.
     * @param claimsResolver La función que se utilizará para extraer los claims específicos.
     * @return La información específica (claims) extraída del token.
     */
    public <T> T extractClamis(String token, Function<Claims, T> claimsResolver){
        final  Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrae todos los claims de un token JWT.
     *
     * @param token El token JWT del que se extraerán todos los claims.
     * @return Todos los claims extraídos del token.
     */
    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Verifica si un token JWT ha expirado.
     *
     * @param token El token JWT que se verificará.
     * @return true si el token ha expirado, false si no ha expirado.
     */
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    /**
     * Genera un nuevo token JWT con los claims especificados.
     *
     * @param username El nombre de usuario que se incluirá en el token.
     * @param role     El rol del usuario que se incluirá en el token.
     * @return El token JWT generado.
     */
    public String generateToken(String username, String role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, username);
    }

    /**
     * Crea un token JWT con los claims especificados y firma el token con una clave secreta.
     *
     * @param claims  Los claims que se incluirán en el token.
     * @param subject El nombre de usuario (subject) que se incluirá en el token.
     * @return El token JWT firmado.
     */
    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis() + 1000 * 60 * 60 * 10)))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    /**
     * Valida un token JWT comparándolo con los detalles del usuario.
     *
     * @param token        El token JWT que se validará.
     * @param userDetails  Los detalles del usuario con los que se comparará el token.
     * @return true si el token es válido y coincide con los detalles del usuario, false si no es válido.
     */
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
