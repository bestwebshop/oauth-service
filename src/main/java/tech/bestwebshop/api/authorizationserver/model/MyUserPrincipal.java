package tech.bestwebshop.api.authorizationserver.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyUserPrincipal implements UserDetails {

    private User user;

    public MyUserPrincipal(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = user.getRole();

        final List<GrantedAuthority> grantedAuthorities;

        switch(role.getTyp()){
            case "admin": grantedAuthorities = Collections.unmodifiableList(Arrays.asList(new SimpleGrantedAuthority("USER"),
                    new SimpleGrantedAuthority("ADMIN")));
            break;
            case "user": grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
            break;
            default: grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("NONE"));
        }

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser(){
        return user;
    }
}
