package InVx.InVx.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "tasks")
public class Task {



    @Id
    private String id;
    @NotBlank(message = "title can not be empty")
    private String title;
    private String description;

    private String status;
    private String priority;
    @CreatedDate
    private Date createdAt = new Date();
//    @CreatedDate
//    private Date updatedAt = new Date();
//    @CreatedDate
//    private Date deletedAt = new Date();



    public Task() {
    }
    public String getId() {
        return id;
    }

    public @NotBlank(message = "title can not be empty") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "title can not be empty") String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public Date getDeletedAt() {
//        return deletedAt;
//    }
//
//    public void setDeletedAt(Date deletedAt) {
//        this.deletedAt = deletedAt;
//    }
}
