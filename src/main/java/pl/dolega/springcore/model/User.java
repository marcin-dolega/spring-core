package pl.dolega.springcore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /**
     * User Id. UNIQUE.
     * @return User Id.
     */

    private long id;
    private String name;
    private String email;


    /**
     * User email. UNIQUE.
     * @return User email.
     */
}
