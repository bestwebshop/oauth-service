package tech.bestwebshop.api.authorizationserver.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Role implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @NonNull
    private int id;

    @NonNull
    private String typ;

    @NonNull
    private int level;
}

