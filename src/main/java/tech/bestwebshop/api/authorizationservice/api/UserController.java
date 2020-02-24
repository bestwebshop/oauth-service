package tech.bestwebshop.api.authorizationservice.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping("me")
    public Principal user(Principal principal){
        return principal;
    }
}
