package tech.bestwebshop.api.authorizationserver.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tech.bestwebshop.api.authorizationserver.model.User;

import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(MyUserDetailsService.class.getSimpleName());

    private final RestTemplate restTemplate;

    private static final String USER_SERVICE_URL = "http://localhost:9201/user-api/";

    private final PasswordEncoder encoder;

    public MyUserDetailsService(RestTemplate restTemplate, PasswordEncoder encoder){
        this.restTemplate = restTemplate;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        ResponseEntity<User> userResponseEntity;
        try {
            userResponseEntity = restTemplate.exchange(USER_SERVICE_URL + "/users?username=" + username,
                    HttpMethod.GET, buildHttpEntity(), User.class);
        } catch (HttpClientErrorException.NotFound e){
            LOGGER.warning("Not Found exception: " + e.getMessage() + "\n" + e.getStackTrace());
            return null;
        } catch (Exception ex){
            LOGGER.warning("Exception: " + ex.getMessage() + "\n" + ex.getStackTrace());
            return null;
        }

        User user = requireNonNull(userResponseEntity.getBody());

        org.springframework.security.core.userdetails.User.UserBuilder userBuilder =
                org.springframework.security.core.userdetails.User.builder().passwordEncoder(encoder::encode);

        UserDetails userDetails = userBuilder
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(this.getRolesFromUser(user))
                .build();

        System.out.println("Found User " + user);

        return userDetails;
    }

    private String[] getRolesFromUser(User user){
        String [] roles;
        switch(user.getRole().getTyp()){
            case "admin":
                roles = new String[]{"USER", "ADMIN"};
                break;
            case "user":
                roles = new String[]{"USER"};
                break;
            default:
                roles = new String[]{"NONE"};
                break;
        }
        return roles;
    }

    private <T> HttpEntity<T> buildHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        return new HttpEntity<T>(null, headers);
    }
}
