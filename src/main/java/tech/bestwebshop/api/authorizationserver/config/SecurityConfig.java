package tech.bestwebshop.api.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        /*httpSecurity
                .csrf()
                .ignoringAntMatchers("/eureka/**");
        httpSecurity
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/login", "eureka/**")
                .permitAll();*/
        httpSecurity
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/login", "/oauth2/jwks.json", "/oauth2/introspect").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.defaultSuccessUrl("http://localhost/OAuthRedirectEndpoint", true)
                .permitAll()
                .and()
                .logout().permitAll();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("append","delete","entries","foreach","get",
                "has","keys","set","values","Authorization", "Content-Type", "Referer", "Sec-Fetch-Dest", "User-Agent"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

   /* @Bean
    @Override
    public UserDetailsService userDetailsService() {
        @SuppressWarnings("deprecation")
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("oauthuser").password("oauthpassword").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }*/

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
