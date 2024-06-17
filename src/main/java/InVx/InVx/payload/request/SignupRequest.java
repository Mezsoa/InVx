package InVx.InVx.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class SignupRequest {


    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Email
    @Size(min = 11, max = 20)
    private String email;
    @NotBlank
    @Size(min = 4, max = 20)
    private String password;
    @NotBlank(message = "date of birth cannot be blank ")
    private String dateOfBirth;
    @NotBlank(message = "firstname cannot be blank ")
    private String firstName;
    @NotBlank(message = "lastname cannot be blank ")
    private String lastName;
    private Set<String> roles;

    public @NotBlank @Email @Size(min = 11, max = 20) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email @Size(min = 11, max = 20) String email) {
        this.email = email;
    }

    public @NotBlank @Size(min = 3, max = 20) String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Size(min = 3, max = 20) String username) {
        this.username = username;
    }

    public @NotBlank @Size(min = 4, max = 20) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(min = 4, max = 20) String password) {
        this.password = password;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}







