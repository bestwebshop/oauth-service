package tech.bestwebshop.api.authorizationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.bestwebshop.api.authorizationservice.db.UserRepository;
import tech.bestwebshop.api.authorizationservice.model.User;

import java.util.Optional;
import java.util.logging.Logger;

@Service("userService")
public class WebshopUserDetailsService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(WebshopUserDetailsService.class.getSimpleName());

    private final UserRepository userRepository;

    @Autowired
    public WebshopUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("User with username '" + username + "' logs in.");
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User with name '" + username + "' not found."));
    }
}
