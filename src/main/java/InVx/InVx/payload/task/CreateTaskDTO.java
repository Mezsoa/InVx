package InVx.InVx.payload.task;

import jakarta.validation.constraints.NotBlank;

public class CreateTaskDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private String title;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
