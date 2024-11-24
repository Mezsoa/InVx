package InVx.InVx.payload.userFeedback;

public class CreateUserFeedback {
    private String username;
    private String userId;
    private String message;
    private String category;

    public CreateUserFeedback(String username, String userId, String message, String category) {
        this.username = username;
        this.userId = userId;
        this.message = message;
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
