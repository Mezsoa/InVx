package InVx.InVx.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SignupRequest {

    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    @NotBlank
    @Size(min = 4, max = 20)
    private String password;

    @NotBlank
    @Email
    private String email;

    private Set<String> roles;


    @NotBlank(message = "date of birth cannot be blank ")
    private String dateOfBirth;
    @NotBlank(message = "firstname cannot be blank ")
    private String firstName;
    @NotBlank(message = "lastname cannot be blank ")
    private String lastName;

    public @NotBlank @Size(min = 4, max = 20) String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Size(min = 4, max = 20) String username) {
        this.username = username;
    }

    public @NotBlank(message = "lastname cannot be blank ") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "lastname cannot be blank ") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "firstname cannot be blank ") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "firstname cannot be blank ") String firstName) {
        this.firstName = firstName;
    }

    @NotBlank(message = "date of birth cannot be blank ")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotBlank(message = "date of birth cannot be blank ") String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public @NotBlank @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email String email) {
        this.email = email;
    }

    public @NotBlank @Size(min = 4, max = 20) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(min = 4, max = 20) String password) {
        this.password = password;
    }
}
