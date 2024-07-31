package InVx.InVx.payload.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;

public class UpdateUser {
    @Size(max = 20)
    private String firstname;
    @Size(max = 20)
    private String lastname;
    @Size(min = 5, max = 40)
    private String password;
    @Email
    @Indexed(unique = true)
    private String email;


    public @Size(max = 20) String getFirstname() {
        return firstname;
    }

    public void setFirstname(@Size(max = 20) String firstname) {
        this.firstname = firstname;
    }

    public @Size(max = 20) String getLastname() {
        return lastname;
    }

    public void setLastname(@Size(max = 20) String lastname) {
        this.lastname = lastname;
    }

    public @Size(min = 5, max = 40) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 5, max = 40) String password) {
        this.password = password;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }
}
