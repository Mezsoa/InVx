package InVx.InVx.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "tasks")
public class Task {



    @Id
    private String id;



    private String userId;
    @NotBlank(message = "title can not be empty")
    private String title;
    private String description;
    private String status;
    private String priority;
    @CreatedDate
    private Date createdAt = new Date();
    // I choose to go with nested representation due to flexibility and clarity.
    @DBRef
    private User user;



    public Task() {
    }

    public String getId() {
        return id;
    }
    public String getUserId() {
        return userId;
    }
    public @NotBlank(message = "title can not be empty") String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getStatus() {
        return status;
    }
    public String getPriority() {
        return priority;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public User getUser() {
        return user;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setTitle(@NotBlank(message = "title can not be empty") String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
