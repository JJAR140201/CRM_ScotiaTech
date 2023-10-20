package crm.scotiatech.Dao;

import crm.scotiatech.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


/**
 * La interfaz UserDao define operaciones de acceso a datos para la entidad User.
 * Extiende la interfaz JpaRepository proporcionada por Spring Data JPA
 * para realizar operaciones CRUD básicas en la entidad User.
 */
public interface UserDao extends JpaRepository<User, Long> {


    /**
     * Recupera un objeto User basado en su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico del usuario que se busca.
     * @return Un objeto User que coincide con la dirección de correo electrónico
     * proporcionada, o null si no se encuentra ninguna coincidencia.
     */
    User findByEmailId(@Param("email") String email);

}
