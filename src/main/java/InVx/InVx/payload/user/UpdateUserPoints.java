package InVx.InVx.payload.user;

import jakarta.validation.constraints.Size;

public class UpdateUserPoints {

    private String userId;

    @Size(max = 35)
    private int points;

    @Size(max = 35)
    public int getPoints() {
        return points;
    }

    public void setPoints(@Size(max = 35) int points) {
        this.points = points;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
