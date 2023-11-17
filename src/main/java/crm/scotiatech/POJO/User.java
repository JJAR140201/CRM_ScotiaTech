package crm.scotiatech.POJO;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * La clase User representa la entidad de usuario en el sistema.
 * Contiene información sobre los usuarios, como su nombre,
 * número de contacto, dirección de correo electrónico, contraseña,
 * estado y rol. Esta clase está mapeada a una tabla "User"
 * en la base de datos y se utiliza para realizar operaciones de acceso a
 * datos y persistencia.
 */

@NamedQuery(name = "User.findByEmailId", query = "select u from User u where u.email =: email ")
@NamedQuery(name = "User.getAllUser", query = "select new crm.scotiatech.wrapper.UserWrapper(u.id, u.name, u.email, u.contactNumber, u.status) from User u where u.role='user'")


@Data
@Entity
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User  implements Serializable {

    private static final long   serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "name")
    private String name;
    @Column (name = "contactNumber")
    private String contactNumber;
    @Column (name = "email")
    private String email;
    @Column (name = "password")
    private String password;
    @Column (name = "status")
    private String status;
    @Column (name = "role")
    private String role;

    /**
     * Obtiene el ID del usuario.
     *
     * @return El ID del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Establece el ID del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param name El nuevo nombre del usuario.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el número de contacto del usuario.
     *
     * @return El número de contacto del usuario.
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Establece el número de contacto del usuario.
     *
     * @param contactNumber El nuevo número de contacto del usuario.
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Obtiene la dirección de correo electrónico del usuario.
     *
     * @return La dirección de correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece la dirección de correo electrónico del usuario.
     *
     * @param email La nueva dirección de correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el estado del usuario.
     *
     * @return El estado del usuario.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Establece el estado del usuario.
     *
     * @param status El nuevo estado del usuario.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return El rol del usuario.
     */
    public String getRole() {
        return role;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param role El nuevo rol del usuario.
     */
    public void setRole(String role) {
        this.role = role;
    }
}
