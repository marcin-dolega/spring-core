package pl.dolega.springcore.model.user;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
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
