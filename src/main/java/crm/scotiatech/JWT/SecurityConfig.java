package crm.scotiatech.JWT;

import crm.scotiatech.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;


/**
 * La clase SecurityConfig configura la seguridad de la aplicación utilizando Spring Security. Define cómo se gestionan las
 * solicitudes de autenticación y autorización, las configuraciones de CORS, y cómo se autentican los usuarios.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtFilter jwtFilter;

    /**
     * Configura el servicio de autenticación para utilizar el servicio CustomerUsersDetailsService.
     *
     * @param auth El objeto AuthenticationManagerBuilder utilizado para configurar la autenticación.
     * @throws Exception Si hay un error al configurar la autenticación.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUsersDetailsService);
    }

    /**
     * Define el codificador de contraseñas utilizado en la aplicación.
     *
     * @return El codificador de contraseñas NoOpPasswordEncoder.
     */
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Crea un bean para el AuthenticationManager.
     *
     * @return El objeto AuthenticationManager configurado.
     * @throws Exception Si hay un error al configurar el AuthenticationManager.
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configura la seguridad de las solicitudes HTTP, incluyendo las reglas de autorización, CORS, CSRF y la autenticación.
     *
     * @param http El objeto HttpSecurity utilizado para configurar la seguridad.
     * @throws Exception Si hay un error al configurar la seguridad.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/login", "/user/singup", "/user/forgotPassword")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Agrega un filtro de autenticación personalizado antes del filtro UsernamePasswordAuthenticationFilter.
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
