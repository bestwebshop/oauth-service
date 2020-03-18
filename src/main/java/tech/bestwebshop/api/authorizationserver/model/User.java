package tech.bestwebshop.api.authorizationserver.model;

import lombok.*;

@Data
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @NonNull
    private int id;

    @NonNull
    private String username;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
    private String password;

    @NonNull
    private Role role;
}

