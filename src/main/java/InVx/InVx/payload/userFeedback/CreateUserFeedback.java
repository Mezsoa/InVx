package InVx.InVx.payload.userFeedback;

public class CreateUserFeedback {
    private String username;
    private String message;
    private String category;

    public CreateUserFeedback(String username, String message, String category) {
        this.username = username;
        this.message = message;
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
