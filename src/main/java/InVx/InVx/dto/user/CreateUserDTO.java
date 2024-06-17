package InVx.InVx.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class CreateUserDTO {



    @NotBlank(message = "Username cannot be blank")
    private String username;
    @Email
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotBlank(message = "Date of birth cannot be blank")
    private String dateOfBirth;
    @NotBlank(message = "Firstname cannot be blank")
    private String firstName;
    @NotBlank(message = "Lastname cannot be blank")
    private String lastName;

    private Set<String> usersRoles = new HashSet<>();




    public @NotBlank(message = "Username cannot be blank") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username cannot be blank") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password cannot be blank") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password cannot be blank") String password) {
        this.password = password;
    }

    public @Email @NotBlank(message = "Email cannot be blank") String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank(message = "Email cannot be blank") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Date of birth cannot be blank") String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotBlank(message = "Date of birth cannot be blank") String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotBlank(message = "Firstname cannot be blank") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "Firstname cannot be blank") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Lastname cannot be blank") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Lastname cannot be blank") String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(Set<String> usersRoles) {
        this.usersRoles = usersRoles;
    }
}
