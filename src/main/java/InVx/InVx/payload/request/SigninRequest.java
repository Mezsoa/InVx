package InVx.InVx.payload.request;

import jakarta.validation.constraints.NotBlank;

public class SigninRequest {

    @NotBlank
    public String username;

    @NotBlank
    String password;

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }
}
