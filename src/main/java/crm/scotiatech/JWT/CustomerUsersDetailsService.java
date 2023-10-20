package crm.scotiatech.JWT;

import crm.scotiatech.Dao.UserDao;
import crm.scotiatech.POJO.User;
import crm.scotiatech.Service.ServiceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

/**
 * La clase CustomerUsersDetailsService implementa la interfaz UserDetailsService de Spring Security para cargar detalles de usuario
 * y autenticar usuarios durante la autenticación. Esta clase se utiliza para la autenticación basada en el nombre de usuario
 * y contraseña almacenada en la base de datos.
 */


@Service
public class CustomerUsersDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(CustomerUsersDetailsService.class);

    private User userDetails;

    /**
     * Carga los detalles del usuario basados en el nombre de usuario proporcionado durante la autenticación.
     * Este método se utiliza para buscar un usuario en la base de datos y autenticarlo.
     *
     * @param username El nombre de usuario proporcionado durante la autenticación.
     * @return Un objeto UserDetails que representa al usuario autenticado.
     * @throws UsernameNotFoundException Si el usuario no se encuentra en la base de datos.
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Inside loadUserByUsername {}", username);
        userDetails = userDao.findByEmailId(username);
        if (!Objects.isNull(userDetails)){
            return new org.springframework.security.core.userdetails.User(userDetails.getEmail(),
                    userDetails.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    /**
     * Obtiene los detalles del usuario que se cargaron durante la autenticación.
     *
     * @return Un objeto User que representa los detalles del usuario autenticado.
     */

    public User getUserDetails(){
        return userDetails;
    }
}
