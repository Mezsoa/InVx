package InVx.InVx.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User {


    @Id
    private String id;


    @NotBlank(message = "username cannot be blank ")
    private String username;
    @NotBlank(message = "email cannot be blank ")
    private String email;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "date of birth cannot be blank ")
    private String dateOfBirth;
    @NotBlank(message = "firstname cannot be blank ")
    private String firstName;
    @NotBlank(message = "lastname cannot be blank ")
    private String lastName;
    @DBRef
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String email, String password, String dateOfBirth, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public boolean userHasRole(Role role){
        return roles.contains(role);
    }


    public @NotBlank(message = "username cannot be blank ") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "username cannot be blank ") String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public @NotBlank(message = "password cannot be blank") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "password cannot be blank") String password) {
        this.password = password;
    }

    public @NotBlank(message = "email cannot be blank ") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "email cannot be blank ") String email) {
        this.email = email;
    }

    public @NotBlank(message = "date of birth cannot be blank ") String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotBlank(message = "date of birth cannot be blank ") String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotBlank(message = "firstname cannot be blank ") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "firstname cannot be blank ") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "lastname cannot be blank ") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "lastname cannot be blank ") String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
